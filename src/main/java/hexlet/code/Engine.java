package hexlet.code;

import java.util.Scanner;

public class Engine {

    public static final int ATTEMPTS_NUMBER = 3;
    public static final int RANDOM_RANGE = 10;
    public static final String QUESTION = "Question: %s ";
    private static final String YOUR_ANSWER = "Your answer: ";
    private static final String CORRECT = "Correct!";
    private static final String CONGRATS = "Congratulations, %s!";
    private static final String WRONG_ANSWER = "'%s' is wrong answer ;(. Correct answer was '%s'";
    private static final String TRY_AGAIN = "Let's try again, %s!";
    private static final String WELCOME = "Welcome to the Brain Games!";
    private static final String YOUR_NAME = "May I have your name? ";
    private static String user;

    public static void start(String gameTask, String[] question, String[] correctAnswer) {
        meetUser();
        int attempt = 0;
        while (attempt < ATTEMPTS_NUMBER) {
            if (attempt == 0) {
                showUserMessage(gameTask);
            }
            showUserMessage(question[attempt]);
            String userAnswer = getUserAnswer();
            showUserMessage(YOUR_ANSWER + userAnswer);
            if (userAnswer.equals(correctAnswer[attempt])) {
                String positiveResult = getPositiveResult(attempt + 1, user);
                showUserMessage(positiveResult);
                attempt++;
            } else {
                String negativeResult = getNegativeResult(userAnswer, correctAnswer[attempt], user);
                showUserMessage(negativeResult);
                attempt = ATTEMPTS_NUMBER;
            }
        }
    }

    public static void meetUser() {
        showUserMessage(WELCOME);
        showUserMessage(YOUR_NAME);
        user = getUserName();
        Cli.greetUser(user);
    }

    private static String getUserName() {
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();
        return userName;
    }

    private static String getUserAnswer() {
        Scanner scan = new Scanner(System.in);
        return  scan.nextLine();
    }

    private static String getPositiveResult(int attempt, String userName) {
        String positiveResult = String.format(CONGRATS, userName);
        return (attempt == ATTEMPTS_NUMBER) ? String.format("%s\n%s", CORRECT, positiveResult) : CORRECT;
    }

    private static String getNegativeResult(String userAnswer, String correctAnswer, String userName) {
        String wrongAnswer = String.format(WRONG_ANSWER, userAnswer, correctAnswer);
        String goodbye = String.format(TRY_AGAIN, userName);
        String negativeResult = String.format("%s\n%s", wrongAnswer, goodbye);
        return negativeResult;
    }

    private static void showUserMessage(String userMessage) {
        System.out.println(userMessage);
    }
}
