package commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	private WebDriver driver;
	protected final Logger log;

	public BaseTest() {
		log = LogManager.getLogger(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		Path path = null;
		File extensionFilePath = null;

		switch (browser) {
		case FIREFOX:
//			FirefoxProfile profile = new FirefoxProfile();
//			File ffFile = new File(GlobalConstants.BROWSER_EXTENSION + "wappalyzer.xpi");
//			profile.addExtension(ffFile);
//
//			FirefoxOptions ffOption = new FirefoxOptions();
//			ffOption.setProfile(profile);
//
//			driver = new FirefoxDriver(ffOption);

			driver = new FirefoxDriver();
			Path xpiPath = Paths.get(GlobalConstants.BROWSER_EXTENSION + "wappalyzer.xpi");
			FirefoxDriver ffDriver = (FirefoxDriver) driver;
			ffDriver.installExtension(xpiPath);
			driver = ffDriver;

			break;
		case CHROME:
//			File chromeFile = new File(GlobalConstants.BROWSER_EXTENSION + "wappalyzer.crx");
//			ChromeOptions choption = new ChromeOptions();
//			choption.addExtensions(chromeFile);

			ChromeOptions options = new ChromeOptions();
			path = Paths.get(GlobalConstants.BROWSER_EXTENSION + "wappalyzer.crx");
			extensionFilePath = new File(path.toUri());
			options.addExtensions(extensionFilePath);
			driver = new ChromeDriver(options);
			break;
		case EDGE:
			EdgeOptions edgeOption = new EdgeOptions();
			path = Paths.get(GlobalConstants.BROWSER_EXTENSION + "wappalyzer.crx");
			extensionFilePath = new File(path.toUri());
			edgeOption.addExtensions(extensionFilePath);
			driver = new EdgeDriver(edgeOption);
			break;
		case CHROME_HEADLESS:
			ChromeOptions chOption = new ChromeOptions();
			chOption.addArguments("--headless");
			chOption.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chOption);
			break;
		case FIREFOX_HEADLESS:
			FirefoxOptions ffHeadlessOption = new FirefoxOptions();
			ffHeadlessOption.addArguments("--headless");
			ffHeadlessOption.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(ffHeadlessOption);
			break;
		case EDGE_HEADLESS:
			EdgeOptions egOption = new EdgeOptions();
			egOption.addArguments("--headless");
			egOption.addArguments("window-size=1920x1080");
			driver = new EdgeDriver(egOption);
			break;
		case IE:
			driver = new InternetExplorerDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid.");
		}

		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1920, 1080));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.get("https://demo.nopcommerce.com/");
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String url) {
		// BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case CHROME:
			// Log To File
			ChromeDriverService chromeService = new ChromeDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG + "ChromeDriverLog.log")).build();

			// Disable automation bar infor
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			driver = new ChromeDriver(chromeService, options);
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		case OPERA:
			// driver = new OperaDriver();
			break;
		case SAFARI:
			driver = new SafariDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid.");
		}

		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1920, 1080));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.get(url);
		return driver;
	}

	protected String getEmailRandom() {
		Random rand = new Random();
		return "john" + rand.nextInt(99999) + "@kennedy.us";
	}
	
	//Random 3 number
	public static int getRandomNumber() {
		int uLimit = 999;
		int lLimit = 100;
		Random rand = new Random();
		return lLimit + rand.nextInt(uLimit - lLimit);
	}
	
	// range (minimum - maximum)
	public static int getRandomNumber(int minimum, int maximum) {
		Random rand = new Random();
		return minimum + rand.nextInt(maximum - minimum);
	}

	// Get random number by date time minute second (no duplicate)
	public static long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}

	protected void closeBrowser() {
		// Tạo ra 1 biến là cmd bằng null
		String cmd = null;
		try {
			String osName = GlobalConstants.OS_NAME.toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI 'IMAGENAME eq " + browserDriverName + "*'";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			log.info("Command Line = " + cmd);

			// 1 - Close browser
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			// 2 - Quit driver (executable)
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info("----------------- PASSED -----------------");
		} catch (Throwable e) {
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("----------------- FAILED -----------------");
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info("----------------- PASSED -----------------");
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("----------------- FAILED -----------------");
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("----------------- PASSED -----------------");
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("----------------- FAILED -----------------");
		}
		return pass;
	}

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void deleteFileInReport() {
		// Remove all file in ReportNG screenshot (image)
		deleteAllFileInFolder("reportNGImage");

		// Remove all file in Allure attachment (json file)
		deleteAllFileInFolder("allure-json");
	}

	public void deleteAllFileInFolder(String folderName) {
		try {
			String pathFolderDownload = GlobalConstants.RELATIVE_PROJECT_PATH + File.separator + folderName;
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			if (listOfFiles.length != 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
						new File(listOfFiles[i].toString()).delete();
					}
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

}
