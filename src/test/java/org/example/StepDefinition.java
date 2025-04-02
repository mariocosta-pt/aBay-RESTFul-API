package org.example;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@CucumberContextConfiguration
@SpringBootTest(classes = Main.class)
public class StepDefinition {

}
