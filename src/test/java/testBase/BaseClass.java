package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties properties;
	public String concatPath = ".";

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = { "sanity", "regression", "master" })
	@Parameters({ "OS", "Browser" })
	public void setup(String OS, String Browser) {

		try {
			FileInputStream fileReader = new FileInputStream(".//src/test/resources/config.properties");
			properties = new Properties();
			properties.load(fileReader);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		logger = LogManager.getLogger(this.getClass()); // Loading Log4j XML File

		// Setting Selenium Grid
		if (properties.getProperty("EXECUTION_ENVIRONMENT").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// Setting up OS
			switch (OS.toLowerCase()) {
			case "windows":
				capabilities.setPlatform(Platform.WINDOWS);
				break;
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			default:
				logger.info("No Matching OS");
				return;
			}

			// Setting up Browser
			switch (Browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				logger.info("[+] Started the Chrome Browser");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				logger.info("[+] Started the Edge Browser");
				break;
			default:
				System.out.println("No Matching Browser!");
				return;
			}
			try {
				driver = new RemoteWebDriver(new URL(properties.getProperty("GRID_URL")),capabilities);
			} catch (MalformedURLException e) {
				logger.error(e.getMessage());
			}
		} else if(properties.getProperty("EXECUTION_ENVIRONMENT").equalsIgnoreCase("local")) {
			switch (Browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				logger.info("[+] Started the Chrome Browser");
				break;
			case "edge":
				driver = new EdgeDriver();
				logger.info("[+] Started the Edge Browser");
				break;
			case "brave":
				ChromeOptions options = new ChromeOptions();
				options.setBinary(properties.getProperty("BRAVE_BROWSER_BINARY_PATH"));
				driver = new ChromeDriver(options);
				logger.info("[+] Started the Brave Browser");
				break;
			default:
				System.out.println("No Matching Browser!");
				return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("URL"));
		logger.info("[+] Opened the PARA BANK Portal");
		driver.manage().window().maximize();
		logger.info("[+] Maximized the Chrome Browser");
	}

	@AfterClass(groups = { "sanity", "regression", "master" })
	public void tearDown() {
		logger.info("[+] Closing the Browser");
		driver.close();
	}

	public static String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\resources\\reports\\screenshots\\" + tname + "_"
				+ timeStamp + ".png";
//		String targetFilePath = ".\\resources\\reports\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
//		sourceFile.renameTo(targetFile);
		FileUtils.copyFile(sourceFile, targetFile);
		return targetFilePath;
	}

}
