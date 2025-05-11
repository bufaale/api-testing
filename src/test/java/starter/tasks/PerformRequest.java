package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import starter.engine.JsonTestExecutor;
import starter.model.TestRequestDefinition;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PerformRequest implements Task {

    private final TestRequestDefinition requestDefinition;

    public PerformRequest(TestRequestDefinition requestDefinition) {
        this.requestDefinition = requestDefinition;
    }

    public static PerformRequest from(TestRequestDefinition requestDefinition) {
        return instrumented(PerformRequest.class, requestDefinition);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JsonTestExecutor.runTestFromJson(requestDefinition);
        // si querés, podés pasar el `section`, `saveRequestAs`, etc. como parte de la lógica del executor
    }
}
