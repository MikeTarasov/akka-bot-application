import akka.actor.typed.ActorSystem;
import com.example.akka.bot.GuardianActor;

public class UserCliApplication {
    public static void main(String[] args) {
        //#actor-system
        final ActorSystem<GuardianActor.AskQuestion> greeterMain =
                ActorSystem.create(GuardianActor.create(), "guardian-actor");
        //#actor-system

        //#main-send-messages
        greeterMain.tell(new GuardianActor.AskQuestion("Charles"));
        //#main-send-messages

//        try {
//            System.out.println(">>> Press ENTER to exit <<<");
//            System.in.read();
//        } catch (IOException ignored) {
//        } finally {
//            greeterMain.terminate();
//        }
    }
}
