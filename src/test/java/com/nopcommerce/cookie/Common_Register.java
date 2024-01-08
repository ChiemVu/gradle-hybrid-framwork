package com.nopcommerce.cookie;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

public class Common_Register extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String password;
	public static String emailAddress, firstName, lastName;
	public static Set<Cookie> cookies;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		emailAddress = getEmailRandom();
		firstName = "John";
		lastName = "Kennedy";
		password = "123456";

		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");
		
		homePage = registerPage.clickToNopCommerceLogo();

		loginPage = homePage.clickToLoginLink();

		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton();
		homePage.sleepInSecond(5);

		cookies = homePage.getBrowserCookies(driver);

		closeBrowser();
	}

}
