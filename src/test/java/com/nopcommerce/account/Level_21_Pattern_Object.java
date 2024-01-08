package com.nopcommerce.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.RegisterPageObject;

public class Level_21_Pattern_Object extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void User_01_Register_Empty_Data() {
		registerPage = homePage.clickToRegisterLink();

		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("FirstName"), "First name is required.");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("LastName"), "Last name is required.");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Email"), "Email is required.");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Password"), "Password is required.");
		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("ConfirmPassword"), "Password is required.");
	}

	@Test
	public void User_02_Register_Invalid_Email() {
		homePage = registerPage.clickToNopCommerceLogo();

		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.enterToTextboxByID("FirstName", "John");
		registerPage.enterToTextboxByID("LastName", "Kennedy");
		registerPage.enterToTextboxByID("Email", "john@kennedy@us");
		registerPage.enterToTextboxByID("Password", "123456");
		registerPage.enterToTextboxByID("ConfirmPassword", "123456");

		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Email"), "Wrong email");
	}

	@Test
	public void User_03_Register_Invalid_Password() {
		homePage = registerPage.clickToNopCommerceLogo();

		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.enterToTextboxByID("FirstName", "John");
		registerPage.enterToTextboxByID("LastName", "Kennedy");
		registerPage.enterToTextboxByID("Email", "john@kennedy.us");
		registerPage.enterToTextboxByID("Password", "123");
		registerPage.enterToTextboxByID("ConfirmPassword", "123");

		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("Password"), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void User_04_Register_Incorrect_Confirm_Password() {
		homePage = registerPage.clickToNopCommerceLogo();

		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.enterToTextboxByID("FirstName", "John");
		registerPage.enterToTextboxByID("LastName", "Kennedy");
		registerPage.enterToTextboxByID("Email", "john@kennedy.us");
		registerPage.enterToTextboxByID("Password", "123456");
		registerPage.enterToTextboxByID("ConfirmPassword", "654123");

		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getTextboxErrorMessageByID("ConfirmPassword"), "The password and confirmation password do not match.");
	}

	@Test
	public void User_05_Register_Success() {
		homePage = registerPage.clickToNopCommerceLogo();

		homePage.clickToHeaderLinkByName("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.enterToTextboxByID("FirstName", "John");
		registerPage.enterToTextboxByID("LastName", "Kennedy");
		registerPage.enterToTextboxByID("Email", emailAddress);
		registerPage.enterToTextboxByID("Password", "123456");
		registerPage.enterToTextboxByID("ConfirmPassword", "123456");

		registerPage.clickToButtonByText("Register");

		Assert.assertEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");
	}

	@Test
	public void User_06_Login_Success() {
		homePage = registerPage.clickToNopCommerceLogo();

		homePage.clickToHeaderLinkByName("Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		loginPage.enterToTextboxByID("Email", emailAddress);
		loginPage.enterToTextboxByID("Password", "123456");

		loginPage.clickToButtonByText("Log in");
		homePage = PageGeneratorManager.getHomePage(driver);

		homePage.clickToHeaderLinkByName("My account");
		customerPage = PageGeneratorManager.getCustomerPage(driver);

		Assert.assertEquals(customerPage.getTextboxAttributeByID("FirstName"), "John");
		Assert.assertEquals(customerPage.getTextboxAttributeByID("LastName"), "Kennedy");
		Assert.assertEquals(customerPage.getTextboxAttributeByID("Email"), emailAddress);
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
