package runnerFiles;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;
import util.CustomAbstractTestNGCucumberTests;


@CucumberOptions(
        dryRun = false,
        strict = true,
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports/smokeTestResults.json",
                "html:target/cucumber-reports"
        },
        monochrome = false
)
@Test
public class UITest extends CustomAbstractTestNGCucumberTests {

}