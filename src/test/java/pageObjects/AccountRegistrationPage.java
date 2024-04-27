package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	public @FindBy(xpath = "//label[text()='I have read and agree to the ']/../input") WebElement checkbox_AgreePrivacyPolicy;
	public @FindBy(xpath = "//input[@value='Register']") WebElement button_Register;
	public @FindBy(xpath = "//p[text()='Your account was created successfully. You are now logged in.']") WebElement textMessage_registrationSuccess;
	public @FindBy(xpath = "//a[text()='Continue']") WebElement button_ContinuePostRegistration;
	public @FindBy(xpath = "//span[text()='Verify you are human']/../span[1]") WebElement checkbox_VerifyHuman;
	public @FindBy(xpath = "//span[text()='Verify you are human']") WebElement textMessage_VerifyHuman;

	public WebElement fieldTextValue(String fieldType) {
		return driver.findElement(By.xpath("//b[text()='"+fieldType+":']/../following-sibling::td/input"));
	}

}
