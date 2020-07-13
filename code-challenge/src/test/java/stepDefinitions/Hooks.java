package stepDefinitions;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.net.MalformedURLException;


public class Hooks{
    public static WebDriver driver;
    @Before
    public void openBrowser() throws MalformedURLException {
        String browser = System.getProperty("BROWSER");
        if(browser==null)
        {
            browser = System.getenv("BROWSER");
            if(browser==null)
            {
                browser= "chrome";
            }
        }
        switch (browser)
        {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }
        System.out.println("Opening Browser...."+browser);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
    @After
    public void embedScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        driver.quit();

    }
}