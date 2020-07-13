package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private WebDriver driver;

    private static String PAGE_URL="https://www.aliexpress.com";

    @FindBy(how = How.CSS, using = ".search-key")
    private WebElement searchTextField;

    @FindBy(how = How.CSS, using = ".search-button")
    private WebElement searchButton;

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigate() {
        super.navigate(PAGE_URL);
    }

    public SearchResultPage searchByKeyword(CharSequence keyWord) {
        this.sendKeyWord(keyWord);
        this.clickSearchButton();
        return new SearchResultPage(this.driver);
    }

    public void sendKeyWord(CharSequence keyWord){
        searchTextField.sendKeys(keyWord);
    }

    public void clickSearchButton(){
        searchButton.click();
    }

}
