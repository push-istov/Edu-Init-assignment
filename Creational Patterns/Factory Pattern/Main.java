//Following is a system for generating different types of quizzes (e.g., multiple-choice, true/false, and short-answer quizzes) based on the course.
//Quiz objects can be created without knowing the exact class of the object

// Product Interface
interface Quiz {
    void create();
}

// Concrete Products
class MultipleChoiceQuiz implements Quiz {
    @Override
    public void create() {
        System.out.println("Creating a multiple-choice quiz.");
    }
}

class TrueFalseQuiz implements Quiz {
    @Override
    public void create() {
        System.out.println("Creating a true/false quiz.");
    }
}

class ShortAnswerQuiz implements Quiz {
    @Override
    public void create() {
        System.out.println("Creating a short answer quiz.");
    }
}

// Factory Class
class QuizFactory {
    public static Quiz createQuiz(String quizType) {
        switch (quizType) {
            case "multiple_choice":
                return new MultipleChoiceQuiz();
            case "true_false":
                return new TrueFalseQuiz();
            case "short_answer":
                return new ShortAnswerQuiz();
            default:
                throw new IllegalArgumentException("Unknown quiz type");
        }
    }
}

// Objects quiz1 and quiz2 created without specifying the exact class of the object
public class Main {
    public static void main(String[] args) {
        Quiz quiz1 = QuizFactory.createQuiz("multiple_choice");
        quiz1.create();

        Quiz quiz2 = QuizFactory.createQuiz("short_answer");
        quiz2.create();
    }
}
