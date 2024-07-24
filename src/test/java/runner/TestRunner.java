package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

import org.testng.annotations.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"C:\\Users\\srika\\IdeaProjects\\CucumberBDDAutomation\\src\\test\\resources\\features\\editcart.feature"},
        glue = {"org.amazon.shoppingcart.stepdefs"},
        dryRun = false,
        plugin = {"pretty:STDOUT","html:test-reports\\html-reports\\editshoppingcart-feature-reports.html",
        "json:target\\cucumber.json",
        "junit:target\\cucumber-results.xml",
        "rerun:target\\rerun.txt"}
//        publish = true
)

public class TestRunner {

}

