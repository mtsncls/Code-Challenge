package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

public abstract class BasePage {

    private WebDriver driver;
    private static Logger log = Logger.getLogger(BasePage.class.getName());

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.waitForPageToLoad();
    }

    public void navigate(String url){driver.get(url); }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}

