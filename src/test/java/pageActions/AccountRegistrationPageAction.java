package pageActions;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.AccountRegistrationPage;

public class AccountRegistrationPageAction extends AccountRegistrationPage {

	AccountRegistrationPage registrationPage;
	public static String password;

	public AccountRegistrationPageAction(WebDriver driver) {
		super(driver);
		registrationPage = new AccountRegistrationPage(driver);
	}

	/** ACTION METHODS **/
	public static String generateRandomString(String valueType, int values) {
		String generatedString = null;
		switch (valueType.toLowerCase()) {
		case "string":
			generatedString = RandomStringUtils.randomAlphabetic(values);
			break;
		case "numeric":
			generatedString = RandomStringUtils.randomNumeric(values);
			break;
		case "alphanumeric":
			generatedString = RandomStringUtils.randomAlphabetic(values) + RandomStringUtils.randomNumeric(values);
			break;
		}
		return generatedString;
	}

	public void enterValues(String elementType, String valueType, int lengthOfValue) {
		if(elementType=="Password") {
			password = generateRandomString(valueType, lengthOfValue);
			registrationPage.fieldTextValue(elementType).sendKeys(password);
		} else if(elementType=="Confirm") {
			registrationPage.fieldTextValue(elementType).sendKeys(password);
		} else {
			registrationPage.fieldTextValue(elementType).sendKeys(generateRandomString(valueType, lengthOfValue));
		}
	}

	public void setPrivacyPolicy() {
		registrationPage.checkbox_AgreePrivacyPolicy.click();
	}

	public void clickOnRegister() {
		registrationPage.button_Register.click();
	}

	public void verifyHuman() {
		if (registrationPage.textMessage_VerifyHuman.isDisplayed()) {
			registrationPage.checkbox_VerifyHuman.click();
		}
	}

	public String getConfirmationMessage() {
		try {
			return registrationPage.textMessage_registrationSuccess.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
