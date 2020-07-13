package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class SearchResultPage extends BasePage {
    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.CSS, using = ".product-container")
    private WebElement productContainer;

    @FindBy(how = How.CSS, using = ".list-item")
    private List<WebElement> listItems;

    @FindBy(how = How.CSS, using = ".next-pagination-list .next-btn")
    private List<WebElement> paginationList;

    @FindBy(how = How.CSS, using = ".list-pagination")
    private WebElement listPagination;

    @FindBy(how = How.CSS, using = ".next-dialog-close")
    private WebElement closeDialogBodyButton;

    String paginationListSelector = ".next-pagination-list .next-btn";
    String dialogSelector = ".next-dialog-body";

    public SearchResultPage(WebDriver driver){
        super(driver);
        super.waitForPageToLoad();
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        this.checkDialog();
    }

    public List<WebElement> getItems() {
        return this.listItems;
    }

    public WebElement getItem(int position) {
        List<WebElement> items = this.getItems();
        int itemsSize = items.size();
        Assert.assertTrue(itemsSize >= position, "Items for this searching contains less than expected");
        return items.get(position-1);
    }

    public List<WebElement> getPaginationItems() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",listPagination);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(paginationListSelector)));
        return this.paginationList;
    }

    public SearchResultPage goToPage(int page) {
        List<WebElement> paginationItems = this.getPaginationItems();
        int itemsSize = paginationItems.size();
        Assert.assertTrue(itemsSize >= page, "Pages for this searching contains less than expected");

        WebElement item = paginationItems.get(page-1);
        Actions actions = new Actions(this.driver);
        actions.moveToElement(item).click().build().perform();
        super.waitForPageToLoad();
        return new SearchResultPage(this.driver);
    }

    private void checkDialog() {
        if(driver.findElements( By.cssSelector(dialogSelector) ).size() != 0)
        {
            this.closeDialogBodyButton.click();
        }
    }

}
