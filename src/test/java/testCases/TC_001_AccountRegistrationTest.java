package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageActions.AccountRegistrationPageAction;
import pageActions.HomePageAction;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = { "regression", "master" })
	public void verify_account_registration() {
		try {
			logger.info("**********Started the Test of TC_001***********");
			logger.debug("**APPLICATION LOGS**");
			HomePageAction pageAction = new HomePageAction(driver);
			AccountRegistrationPageAction registrationPageAction = new AccountRegistrationPageAction(driver);
			logger.info("[+] Clicked in Register Link");
			pageAction.clickRegister();
			registrationPageAction.enterValues("First Name", "string", 5);
			registrationPageAction.enterValues("Last Name", "string", 5);
			registrationPageAction.enterValues("Address", "string", 15);
			registrationPageAction.enterValues("City", "string", 8);
			registrationPageAction.enterValues("State", "string", 5);
			registrationPageAction.enterValues("Zip Code", "numeric", 6);
			registrationPageAction.enterValues("Phone #", "numeric", 10);
			registrationPageAction.enterValues("SSN", "numeric", 12);
			registrationPageAction.enterValues("Username", "alphanumeric", 8);
			registrationPageAction.enterValues("Password", "alphanumeric", 5);
			registrationPageAction.enterValues("Confirm", "null", 0);
			registrationPageAction.clickOnRegister();
			String confirmMessage = registrationPageAction.getConfirmationMessage();
			try {
				Assert.assertEquals(confirmMessage, "Your account was created successfully. You are now logged in.");
			} catch (AssertionError e) {
				logger.error("[!] Assertion Failed as there is Incorrect Confirmation Message");
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].setAttribute('style','background:violet;border:4x solid green;')",
						registrationPageAction.textMessage_registrationSuccess);
				Assert.fail("Incorrect Confirmation Message");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			logger.info("**********Finished the Test of TC_001***********");
		}
	}
}
