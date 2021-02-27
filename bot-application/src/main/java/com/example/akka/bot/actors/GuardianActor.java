package com.example.akka.bot.actors;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class GuardianActor extends AbstractBehavior<GuardianActor.AskQuestion> {

    private final ActorRef<ConfirmActor.Request> confirmActor;

    private GuardianActor(ActorContext<AskQuestion> context) {
        super(context);
        //#create-actors
        confirmActor = context.spawn(ConfirmActor.create(), "confirm-actor");
        //#create-actors
    }

    public static Behavior<AskQuestion> create() {
        return Behaviors.setup(GuardianActor::new);
    }

    @Override
    public Receive<AskQuestion> createReceive() {
        return newReceiveBuilder().onMessage(AskQuestion.class, this::onAskQuestion).build();
    }

    private Behavior<AskQuestion> onAskQuestion(AskQuestion command) {
        //#create-actors
        ActorRef<ConfirmActor.Response> replyTo =
                getContext().spawn(BotActor.create(), command.name);
        confirmActor.tell(new ConfirmActor.Request(command.name, replyTo));
        //#create-actors
        return this;
    }

    public static class AskQuestion {
        public final String name;

        public AskQuestion(String name) {
            this.name = name;
        }
    }
}