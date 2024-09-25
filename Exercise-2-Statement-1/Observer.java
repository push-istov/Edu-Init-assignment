//Observer Interface

public interface Observer {
    void notifyConflict(Task newTask, Task existingTask);
}
