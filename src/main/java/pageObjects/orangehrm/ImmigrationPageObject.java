package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;

public class ImmigrationPageObject extends BaseActions {
	WebDriver driver;

	public ImmigrationPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
