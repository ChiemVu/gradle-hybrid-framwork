package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.AddressPageObject;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.OrderPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointPageObject;

public class PageGeneratorManager {

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static CustomerPageObject getCustomerPage(WebDriver driver) {
		return new CustomerPageObject(driver);
	}

	public static AddressPageObject getAddressPage(WebDriver driver) {
		return new AddressPageObject(driver);
	}

	public static OrderPageObject getOrderPage(WebDriver driver) {
		return new OrderPageObject(driver);
	}

	public static RewardPointPageObject getRewardPointPage(WebDriver driver) {
		return new RewardPointPageObject(driver);
	}

	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

}
