package com.nopcommerce.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

public class Level_11_Global_Contants extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private String emailAddress = getEmailRandom();
	private String adminUrl = GlobalConstants.DEV_ADMIN_URL;
	private String userUrl = GlobalConstants.DEV_USER_URL;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void User_01_User_To_Admin() {
		registerPage = homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextbox("John");
		registerPage.enterToLastNameTextbox("Kennedy");
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123456");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");

		homePage = registerPage.clickToNopCommerceLogo();

		userLoginPage = homePage.clickToLoginLink();

		homePage = userLoginPage.loginToUser(emailAddress, "123456");
		// ....

		// Logout ra
		homePage.clickToLogoutLink();

		// Home Page (User) -> Login Page (Admin)
		homePage.openPageUrl(driver, adminUrl);

		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		adminDashboardPage = adminLoginPage.loginToAdmin(GlobalConstants.DEV_ADMIN_USERNAME, GlobalConstants.DEV_ADMIN_PASSWORD);
		Assert.assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));

		// ...
	}

	@Test
	public void User_02_Admin_To_User() {
		adminLoginPage = adminDashboardPage.clickToLogoutLink();

		// Login Page (Admin) -> Home Page (User)
		adminLoginPage.openPageUrl(driver, userUrl);
		homePage = PageGeneratorManager.getHomePage(driver);

		userLoginPage = homePage.clickToLoginLink();

		homePage = userLoginPage.loginToUser(emailAddress, "123456");
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
