package commons;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {

	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelToAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getTextInAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendkeyToAlert(WebDriver driver, String keysToSend) {
		waitForAlertPresence(driver).sendKeys(keysToSend);
	}

	protected void switchToWindowByID(WebDriver driver, String pageID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(pageID)) {
				driver.switchTo().window(id);
				sleepInSecond(1);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String pageTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			sleepInSecond(1);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(pageTitle)) {
				break;
			}
		}
	}

	protected void closeAllWindowWithoutParentID(WebDriver driver, String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected Set<Cookie> getBrowserCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	protected void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	protected void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	protected void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	protected void sendkeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	protected String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}

	protected String getElementAttribute(WebDriver driver, WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}

	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
	}
}
