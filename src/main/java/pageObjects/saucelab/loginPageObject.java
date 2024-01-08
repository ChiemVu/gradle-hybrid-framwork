package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.saucelab.LoginPageUI;

public class loginPageObject extends BasePage{
	WebDriver driver;
	public loginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterToUsernameTextbox(String saucelabUsername) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, saucelabUsername);
	}

	public void enterToPasswordTextbox(String saucelabPassword) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, saucelabPassword);
	}

	public productPageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getproductPage(driver);
	}
}
