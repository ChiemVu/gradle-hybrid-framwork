package com.nopcommerce.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;

public class Level_17_ReportNG extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String emailAddress = getEmailRandom();
	private String firstName, lastName, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		firstName = "John";
		lastName = "Kennedy";
		password = "123456";
	}

	@Test
	public void User_01_Register_Validate() {
		log.info("Register - Step 01: Verify Register link displayed");
		verifyFalse(homePage.isRegisterLinkDisplayed());
		
		log.info("Register - Step 01: Verify Register link displayed");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 03: Verify error message at FirstName textbox");
		verifyEquals(registerPage.getFirstNameErrorMessageText(), "First name is required.");
		
		log.info("Register - Step 04: Verify error message at LastName textbox");
		verifyEquals(registerPage.getLastNameErrorMessageText(), "Last name is required.");
	}

	@Test
	public void User_02_Register_Success() {
		log.info("Register - Step 01: Enter to First Name textbox with value is " + firstName);
		registerPage.refreshCurrentPage(driver);
		registerPage.enterToFirstNameTextbox(firstName);
		
		log.info("Register - Step 02: Enter to Last Name textbox with value is " + lastName);
		registerPage.enterToLastNameTextbox(lastName);
		
		log.info("Register - Step 03: Enter to Email Address textbox with value is " + emailAddress);
		registerPage.enterToEmailTextbox(emailAddress);
		
		log.info("Register - Step 04: Enter to Password textbox with value is " + password);
		registerPage.enterToPasswordTextbox(password);
		
		log.info("Register - Step 05: Enter to Confirm Password textbox with value is " + password);
		registerPage.enterToConfirmPasswordTextbox(password);

		log.info("Register - Step 06: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register - Step 07: Verify success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
