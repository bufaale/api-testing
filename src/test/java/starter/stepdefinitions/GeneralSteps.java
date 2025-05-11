package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actors.OnStage;

public class GeneralSteps {

    @Given("the actor named {string}")
    public void setUPActorName(String actorName) {
        OnStage.theActorCalled(actorName);
    }
}
