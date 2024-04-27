package testCases;

import org.testng.annotations.Test;

import pageActions.HomePageAction;
import testBase.BaseClass;


public class TC_002_LoginTest extends BaseClass {

	@Test(groups = {"sanity","master"})
	public void verify_Login() {
		try {
			logger.info("**********Started the Test of TC_001***********");
			logger.debug("**APPLICATION LOGS**");
			HomePageAction pageAction = new HomePageAction(driver);
			logger.info("Entered username");
			pageAction.enterFieldValues("username", properties.getProperty("Login_Username"));
			logger.info("Entered password");
			pageAction.enterFieldValues("password", properties.getProperty("Login_Password"));
			logger.info("Clicked on Log In");
			pageAction.clickLogIn();

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			logger.info("**********Finished the Test of TC_001***********");
		}
	}
}
