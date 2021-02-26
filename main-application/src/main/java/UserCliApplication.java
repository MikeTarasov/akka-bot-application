import akka.actor.typed.ActorSystem;
import com.example.akka.bot.GuardianActor;

import java.util.Scanner;

public class UserCliApplication {

    private static ActorSystem<GuardianActor.AskQuestion> guardianActor;

    public static void main(String[] args) {

        guardianActor = ActorSystem.create(GuardianActor.create(), "guardian-actor");

        userCli();

        guardianActor.terminate();
    }

    private static void userCli() {
        System.out.println("\tHello! I am chat bot! Ask me about anything:");
        boolean finish = false;
        Scanner scanner = new Scanner(System.in);

        while (!finish) {
            String input = scanner.nextLine();

            if ("n".equalsIgnoreCase(input)) {
                finish = true;
            } else {
                guardianActor.tell(new GuardianActor.AskQuestion(input));
                System.out.println("\n\tNext question: [Y/N]");
            }
        }
    }
}
