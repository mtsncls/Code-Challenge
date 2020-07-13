package util;

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        strict = true,
        plugin = {"json:target/cucumber-report-feature-composite.json"}
)
public class CustomAbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    public CustomAbstractTestNGCucumberTests() {
    }

    @BeforeClass(
            alwaysRun = true
    )
    public void setUpClass() throws Exception {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        this.testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider(
            parallel = true
    )
    public Object[][] scenarios() {
        return this.testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(
            alwaysRun = true
    )
    public void tearDownClass() throws Exception {
        this.testNGCucumberRunner.finish();
    }
}
