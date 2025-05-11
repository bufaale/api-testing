package starter.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class Hooks {

    @Before
    public void setTheStage() {
        System.out.println("✅ Ejecutando setTheStage()");
        OnStage.setTheStage(new OnlineCast());
    }
}
