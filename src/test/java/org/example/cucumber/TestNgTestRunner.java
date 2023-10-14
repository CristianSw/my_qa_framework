package org.example.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/org/example/cucumber", glue = "org.example.step_definitions",
        monochrome = true, plugin = {"html:target/cucumber.html"}, tags = "@Negative")
public class TestNgTestRunner extends AbstractTestNGCucumberTests {

}
