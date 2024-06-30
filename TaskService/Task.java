package project;

public class Task {
    private final String taskId;
    private String taskName;
    private String description;

    public Task(String taskId, String taskName, String description) {
        if (taskId == null || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID must be non-null and no longer than 10 characters.");
        }
        if (taskName == null || taskName.length() > 20) {
            throw new IllegalArgumentException("Task Name must be non-null and no longer than 20 characters.");
        }
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Task Description must be non-null and no longer than 50 characters.");
        }
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
    }

    // Getters and setters
    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        if (taskName == null || taskName.length() > 20) {
            throw new IllegalArgumentException("Task Name must be non-null and no longer than 20 characters.");
        }
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Task Description must be non-null and no longer than 50 characters.");
        }
        this.description = description;
    }
}
