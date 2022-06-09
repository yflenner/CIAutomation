package demo;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class Example {

    private WebDriver driver;


//    @BeforeClass
//    @Parameters({"browserName"})
//    public void openBrowser(String browser) {
//        if (browser.equalsIgnoreCase("chrome")) {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//        }
//        else if (browser.equalsIgnoreCase("firefox")) {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//        }
//        else {
//            throw new RuntimeException("Wrong Browser Name");
//        }
//        driver.manage().window().maximize();
//        driver.get("https://www.whatsmybrowser.org/");
//    }


    // Working with Docker / Zalenium
    @BeforeClass
    @Parameters({"browserName"})
    public void openBrowser(String browser) throws MalformedURLException {
        if (browser.equalsIgnoreCase("chrome")) {
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        }
        else {
            throw new RuntimeException("Wrong Browser Name");
        }
        driver.manage().window().maximize();
        driver.get("https://www.whatsmybrowser.org/");
    }

    @Test
    public void test01_verifyBrowser() {
        String browserName = driver.findElement(By.cssSelector(".splash h2.header")).getText();
        System.out.println("Browser Name: " + browserName);
        assertEquals(driver.getTitle(), "What browser am I using?");
    }

    @AfterClass
    public void closeBrowser() {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        driver.quit();
    }
}
