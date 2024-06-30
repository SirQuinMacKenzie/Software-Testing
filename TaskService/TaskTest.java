package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import project.Task;

class TaskTest {

    @Test
    void testTask() {
        Task task = new Task("id34567890", "name", "description, this is exactly fifty characters long");
        assertTrue(task.getTaskId().equals("id34567890"));
        assertTrue(task.getTaskName().equals("name"));
        assertTrue(task.getDescription().equals("description, this is exactly fifty characters long"));
    }

    @Test
    void testTaskIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("id345678901", "name", "description, this is exactly fifty characters long");
        });
    }

    @Test
    void testTaskNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("id34567890", "name56789012345678901", "description, this is exactly fifty characters long");
        });
    }

    @Test
    void testTaskDescriptionTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("id34567890", "name", "description, this is exactly fifty characters long, except now");
        });
    }

    @Test
    void testTaskWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "name", "description, this is exactly fifty characters long");
        });
    }

    @Test
    void testTaskWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("id34567890", null, "description, this is exactly fifty characters long");
        });
    }

    @Test
    void testTaskWithNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("id34567890", "name", null);
        });
    }
}

