import java.util.Stack;

//Following is a system for an online course editor, instructors can perform actions like adding lessons, quizzes, and other content to their courses. 
//These actions are undoable and redoable, allowing instructors to backtrack their changes or reapply them if needed.

// Command Interface
interface Command {
    void execute();
    void undo();
}

// Receiver
class Course {
    private String title;

    public Course(String title) {
        this.title = title;
    }

    public void addContent(String content) {
        System.out.println("Added content: " + content + " to course: " + title);
    }

    public void removeContent(String content) {
        System.out.println("Removed content: " + content + " from course: " + title);
    }
}

// Concrete Command: Add Lesson
class AddLessonCommand implements Command {
    private Course course;
    private String lesson;

    public AddLessonCommand(Course course, String lesson) {
        this.course = course;
        this.lesson = lesson;
    }

    @Override
    public void execute() {
        course.addContent(lesson);
    }

    @Override
    public void undo() {
        course.removeContent(lesson);
    }
}

// Concrete Command: Remove Lesson
class RemoveLessonCommand implements Command {
    private Course course;
    private String lesson;

    public RemoveLessonCommand(Course course, String lesson) {
        this.course = course;
        this.lesson = lesson;
    }

    @Override
    public void execute() {
        course.removeContent(lesson);
    }

    @Override
    public void undo() {
        course.addContent(lesson);
    }
}

// Invoker: Handles command history and undo/redo operations
class CommandInvoker {
    private Stack<Command> commandHistory = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
        redoStack.clear(); // Clear redo stack on a new command execution
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
            redoStack.push(command);
        } else {
            System.out.println("No commands to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            commandHistory.push(command);
        } else {
            System.out.println("No commands to redo.");
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Course course = new Course("Design Patterns in Java");

        // Create commands
        Command addLesson1 = new AddLessonCommand(course, "Lesson 1: Introduction");
        Command addLesson2 = new AddLessonCommand(course, "Lesson 2: Command Pattern");

        // Invoker to handle the commands
        CommandInvoker invoker = new CommandInvoker();

        // Execute commands
        invoker.executeCommand(addLesson1);
        invoker.executeCommand(addLesson2);

        // Undo last command
        invoker.undo(); // Undo Lesson 2 addition
        invoker.undo(); // Undo Lesson 1 addition

        // Redo last undo
        invoker.redo(); // Redo Lesson 1 addition
        invoker.redo(); // Redo Lesson 2 addition
    }
}
