package org.example;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest
@CucumberOptions(
        features = "src/test/resources/",
        glue = "org.example.Steps",
        plugin = { "json:target/cucumber.json", "pretty", "html:target/cucumber-reports" }
)
public class RunCucumberTest {
}