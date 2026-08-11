package persistence;

import model.Goal;
import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGoal(String name, Goal goal) {
        assertEquals(name, goal.getName());
    }

    protected void checkTask(String name, Task task) {
        assertEquals(name, task.getName());
    }
}
