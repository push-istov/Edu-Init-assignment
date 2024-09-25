import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Singleton Pattern with Observer for Conflict Detection

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private Observer conflictObserver;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        conflictObserver = new ConflictObserver();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        for (Task existingTask : tasks) {
            if (isConflict(task, existingTask)) {
                conflictObserver.notifyConflict(task, existingTask);
                return;
            }
        }
        tasks.add(task);
        System.out.println("Task added successfully. No conflicts.");
    }

    public void removeTask(String description) {
        Task taskToRemove = findTask(description);
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        tasks.sort(Comparator.comparing(Task::getStartTime));
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void viewTasksByPriority(String priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with priority: " + priority);
        }
    }

    private Task findTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                return task;
            }
        }
        return null;
    }

    private boolean isConflict(Task newTask, Task existingTask) {
        return newTask.getStartTime().isBefore(existingTask.getEndTime()) && newTask.getEndTime().isAfter(existingTask.getStartTime());
    }
}
