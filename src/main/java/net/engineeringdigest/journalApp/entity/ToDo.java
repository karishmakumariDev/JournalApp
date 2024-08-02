package net.engineeringdigest.journalApp.entity;

import java.util.Objects;
public class ToDo {
    private long id;
    private String taskName;
    private boolean complete;

    // Default constructor
    public ToDo() {
    }

    public ToDo(long id, String taskName, boolean complete) {
        this.id = id;
        this.taskName = taskName;
        this.complete = complete;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    // Override toString for better debugging
    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", complete='" + complete + '\'' +
                '}';
    }

    // Override equals and hashCode for proper comparison and usage in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo that = (ToDo) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
