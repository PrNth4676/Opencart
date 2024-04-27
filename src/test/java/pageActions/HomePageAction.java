package pageActions;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePage;

public class HomePageAction extends HomePage {
	

	public HomePageAction(WebDriver driver) {
		super(driver);
	}

	/** ACTION METHODS **/
	public void clickRegister() {
		link_register.click();
	}

	public void enterFieldValues(String elementType, String value) {
		switch (elementType.toLowerCase()) {
		case "username":
			inputText_username.sendKeys(value);
			break;
		case "password":
			inputText_password.sendKeys(value);
			break;
		}
	}
	
	public void clickLogIn() {
		button_LogIn.click();
	}
}
