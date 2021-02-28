import akka.actor.typed.ActorSystem;
import com.example.akka.bot.actors.GuardianActor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UserCliApplication {

    private static ActorSystem<GuardianActor.AskQuestion> guardianActor;

    public static void main(String[] args) {

        guardianActor = ActorSystem.create(GuardianActor.create(), "guardian-actor");

        userCli();

        guardianActor.terminate();
        System.exit(0);
    }

    private static void userCli() {
        System.out.println("\tПривет! Я простой чат-бот! Задавай вопрос: 'help' - список команд, для выхода 'N' или 'n'");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = URLEncoder.encode(scanner.nextLine().toLowerCase(), StandardCharsets.UTF_8);

            if ("n".equals(input)) {
                scanner.close();
                System.out.println("Пока-пока!");
                break;
            } else {
                guardianActor.tell(new GuardianActor.AskQuestion(input));
            }
        }
    }
}
