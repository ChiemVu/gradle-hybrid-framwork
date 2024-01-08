package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

public class DependentsPageObject extends BaseActions {
	WebDriver driver;

	public DependentsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
