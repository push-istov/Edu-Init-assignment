//Observer class to observe conflicts

public class ConflictObserver implements Observer {
    @Override
    public void notifyConflict(Task newTask, Task existingTask) {
        System.out.println("Error: Task '" + newTask.getDescription() + "' conflicts with existing task '" + existingTask.getDescription() + "'.");
    }
}
