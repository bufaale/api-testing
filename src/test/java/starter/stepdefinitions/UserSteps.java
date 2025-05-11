package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import starter.model.TestRequestDefinition;

import starter.tasks.PerformRequest;

public class UserSteps {

    @Given("the user is created with")
    public void createUser(TestRequestDefinition testRequest) {
        OnStage.theActorInTheSpotlight().attemptsTo(PerformRequest.from(testRequest));
    }

    @Then("the user is retrieved with")
    public void getUser(TestRequestDefinition testRequest) {
        OnStage.theActorInTheSpotlight().attemptsTo(PerformRequest.from(testRequest));
    }
}
