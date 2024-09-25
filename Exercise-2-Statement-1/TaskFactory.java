import java.time.LocalTime;

//Factory pattern to create tasks

public class TaskFactory {
    public static Task createTask(String description, String startTime, String endTime, String priority) throws IllegalArgumentException {
        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);
            if (end.isBefore(start)) {
                throw new IllegalArgumentException("Error: End time must be after start time.");
            }
            return new Task(description, start, end, priority);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: Invalid time format.");
        }
    }
}
