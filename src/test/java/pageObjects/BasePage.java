package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	/**
	 * @author Pratik Nath
	 * @Description : It includes only constructor and will be invoked by every Page
	 *              Object Class Constructor
	 **/

	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		//The driver set below can be used to initiate web elements using the @FindBy annotation and driver.findBy will not be needed.
		PageFactory.initElements(driver, this);
	}
}
