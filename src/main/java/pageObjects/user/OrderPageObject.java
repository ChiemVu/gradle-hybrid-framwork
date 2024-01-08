package pageObjects.user;

import org.openqa.selenium.WebDriver;

public class OrderPageObject extends MyAccountSideBarPageObject {
	WebDriver driver;

	public OrderPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
