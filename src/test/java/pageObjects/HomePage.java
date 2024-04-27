package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public @FindBy(xpath = "//a[text()='Register']") WebElement link_register;
	public @FindBy(name="username") WebElement inputText_username;
	public @FindBy(name="password") WebElement inputText_password;
	public @FindBy(xpath="//input[@value='Log In']") WebElement button_LogIn;
}
