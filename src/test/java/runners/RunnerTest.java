package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = "stepdefinitions",
        dryRun = false,
//        monochrome = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty","html:target/uiReport.html","rerun:target/uiFailedTests.txt"},
        tags = "@NHS_28"
)

public class RunnerTest {
}
