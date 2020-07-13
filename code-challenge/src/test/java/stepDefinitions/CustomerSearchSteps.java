package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pageObjects.HomePage;
import pageObjects.SearchResultPage;
import static org.testng.AssertJUnit.assertTrue;

public class CustomerSearchSteps {

    HomePage homePage;
    SearchResultPage searchResultPage;
    WebElement item;

    @Given("I navigate to aliexpress page")
    public void i_navigate_to_aliexpress_page() {
        homePage = new HomePage(Hooks.driver);
        homePage.navigate();
    }

    @When("I search for {string} keyword")
    public void i_search_for_keyword(String keyword) {
        searchResultPage = homePage.searchByKeyword(keyword);
    }

    @When("I navigate to the {int} results page")
    public void i_navigate_to_the_results_page(int pageNumber) {
        searchResultPage = searchResultPage.goToPage(pageNumber);
    }

    @When("select the {int} ad")
    public void select_the_ad(int adPosition) {
        item = searchResultPage.getItem(adPosition);
    }

    @Then("I should see one item to bought")
    public void i_should_see_item_to_bought() {
        String itemText = item.getText();
        assertTrue(itemText.contains("iPhone"));
    }
}