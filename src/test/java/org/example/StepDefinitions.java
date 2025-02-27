package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

class IsItFriday {
    static String isItFriday(String today) {
        return today.equals("Friday") ? "TGIF" : "Nope";
    }
}

public class StepDefinitions {
    private String today;
    private String actualAnswer;

    @Given("today is {string}")
    public void today_is(String today) {
        // Write code here that turns the phrase above into concrete actions
        this.today = today;
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_friday_yet() {
        // Write code here that turns the phrase above into concrete actions
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(actualAnswer).isEqualTo(expectedAnswer);
    }

    @Given("today is Friday")
    public void today_is_friday() {
        // Write code here that turns the phrase above into concrete actions
        today = "Friday";
    }

}
