package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static loginPageObject getLoginPage(WebDriver driver) {
		return new loginPageObject(driver);
	}

	public static productPageObject getproductPage(WebDriver driver) {
		return new productPageObject(driver);
	}
}
