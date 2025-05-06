package org.example.Steps;

import org.example.Main;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@CucumberContextConfiguration
@SpringBootTest(classes = Main.class)
public class StepDefinition {
    @Autowired
    private MockMvc mvc;

    private ResultActions action;
}
