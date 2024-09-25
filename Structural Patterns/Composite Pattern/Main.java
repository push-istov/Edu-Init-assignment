import java.util.ArrayList;
import java.util.List;

//Following system allows for a structure to hierarchically handle components of an online platform
//Composite pattern allows us to construct and represent said hierarchy

// Component Interface
interface CourseComponent {
    void showDetails();
}

// Leaf Classes
class Lesson implements CourseComponent {
    private String title;

    public Lesson(String title) {
        this.title = title;
    }

    @Override
    public void showDetails() {
        System.out.println("Lesson: " + title);
    }
}

class Quiz implements CourseComponent {
    private String title;

    public Quiz(String title) {
        this.title = title;
    }

    @Override
    public void showDetails() {
        System.out.println("Quiz: " + title);
    }
}

// Composite Class
class Course implements CourseComponent {
    private String title;
    private List<CourseComponent> components = new ArrayList<>();

    public Course(String title) {
        this.title = title;
    }

    public void addComponent(CourseComponent component) {
        components.add(component);
    }

    public void removeComponent(CourseComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Course: " + title);
        for (CourseComponent component : components) {
            component.showDetails();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Course course = new Course("Introduction to Java");
        course.addComponent(new Lesson("Lesson 1: Basics"));
        course.addComponent(new Lesson("Lesson 2: Control Structures"));
        course.addComponent(new Quiz("Quiz 1"));

        course.showDetails();
    }
}
