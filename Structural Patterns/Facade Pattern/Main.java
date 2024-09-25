//Following system provides a simple interface to allow multiple actions
//Facade pattern allows us to do this without having to access each action separately
//It is handled by the CourseSubscriptionFacade class

// Subsystems
class PaymentProcessor {
    public void processPayment(String student, double amount) {
        System.out.println("Processing payment of $" + amount + " for " + student + ".");
    }
}

class EnrollmentManager {
    public void enroll(String student, String course) {
        System.out.println("Enrolling " + student + " in " + course + ".");
    }
}

class EmailService {
    public void sendWelcomeEmail(String student) {
        System.out.println("Sending welcome email to " + student + ".");
    }
}

// Facade Class
class CourseSubscriptionFacade {
    private PaymentProcessor paymentProcessor = new PaymentProcessor();
    private EnrollmentManager enrollmentManager = new EnrollmentManager();
    private EmailService emailService = new EmailService();

    public void subscribe(String student, String course, double amount) {
        paymentProcessor.processPayment(student, amount);
        enrollmentManager.enroll(student, course);
        emailService.sendWelcomeEmail(student);
    }
}

public class Main {
    public static void main(String[] args) {
        CourseSubscriptionFacade facade = new CourseSubscriptionFacade();
        facade.subscribe("Raj", "Design Patterns in Java", 100.0);
    }
}
