package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Task;
import project.TaskService;

class TaskServiceTest {

    @Test
    public void testAddTaskWithDuplicateId() {
        Task task1 = new Task("id34567890", "name", "description, this is exactly fifty characters long");
        Task task2 = new Task("id34567890", "name", "description, this is exactly fifty characters long");
        TaskService taskService = new TaskService();
        taskService.addTask(task1);
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(task2);
        });
    }
    
    @Test
    public void testDeleteNonexistentTask() {
    	Task task1 = new Task("id34567890", "name", "description, this is exactly fifty characters long");
    	TaskService taskService = new TaskService();
    	taskService.addTask(task1);
    	assertThrows(IllegalArgumentException.class, () -> {
    		taskService.deleteTask("idnone");
    	});
    }
    
    @Test
    public void testDeleteRemovesTask() {
    	Task task1 = new Task("id34567890", "name", "description, this is exactly fifty characters long");
    	TaskService taskService = new TaskService();
    	taskService.addTask(task1);
    	assertTrue(taskService.getTasks().isEmpty());
    }
    
    @Test
    public void testUpdateTaskName() {
    	Task task1 = new Task("id34567890", "name", "description, this is exactly fifty characters long");
    	TaskService taskService = new TaskService();
    	taskService.addTask(task1);
    	taskService.updateTaskName("id34567890", "newname");
    	assertEquals("newname", taskService.getTasks().get("id34567890").getTaskName());
    }
    
    @Test
    public void testUpdateTaskDescription() {
    	Task task1 = new Task("id34567890", "name", "description, this is exactly fifty characters long");
    	TaskService taskService = new TaskService();
    	taskService.addTask(task1);
    	taskService.updateTaskDescription("id34567890", "new description");
    	assertEquals("new description", taskService.getTasks().get("id34567890").getDescription());
    }
}
