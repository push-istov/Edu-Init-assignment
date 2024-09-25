import java.util.ArrayList;
import java.util.List;

// Following is a system that allows students to enroll in courses and progress through different modules. 
//The system notifies multiple parts (e.g., progress bar, certificate eligibility, and leaderboard) when a student completes a module.

// Observer Interface
interface Observer {
    void update(String student, List<String> progress, String title);
}

//Subject Interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String student);
}
// Subject Class
class Course implements Subject{
    private String title;
    private List<Observer> observers = new ArrayList<>();
    private List<String> progress = new ArrayList<>();

    public Course(String title) {
        this.title = title;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void completeModule(String student, String module) {
        progress.add(module);
        notifyObservers(student);
    }

    public void notifyObservers(String student) {
        for (Observer observer : observers) {
            observer.update(student, new ArrayList<>(progress), this.title);
        }
    }
}

// Concrete Observers
class ProgressBar implements Observer {
    @Override
    public void update(String student, List<String> progress, String title) {
        System.out.println("ProgressBar: " + student + " has completed " + progress.size() + " modules of the course " + title);
    }
}

class CertificateSystem implements Observer {
    @Override
    public void update(String student, List<String> progress, String title) {
        if (progress.size() == 5) { // Assuming 5 modules to complete course
            System.out.println("CertificateSystem: " + student + " is eligible for a certificate for the course " + title);
        }
    }
}

class Leaderboard implements Observer {
    @Override
    public void update(String student, List<String> progress, String title) {
        System.out.println("Leaderboard: Updating position for " + student + " based on progress for the course " + title);
    }
}

public class Main {
    public static void main(String[] args) {
        Course course = new Course("Design Patterns in Java");
        ProgressBar progressBar = new ProgressBar();
        CertificateSystem certificateSystem = new CertificateSystem();
        Leaderboard leaderboard = new Leaderboard();

        course.registerObserver(progressBar);
        course.registerObserver(certificateSystem);
        course.registerObserver(leaderboard);

        course.completeModule("Raj", "Module 1");
        course.completeModule("Raj", "Module 2");
        course.completeModule("Raj", "Module 3");
        course.completeModule("Raj", "Module 4");
        course.completeModule("Raj", "Module 5");
    }
}
