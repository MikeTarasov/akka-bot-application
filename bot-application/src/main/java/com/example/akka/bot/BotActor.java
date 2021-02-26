package com.example.akka.bot;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class BotActor extends AbstractBehavior<ConfirmActor.Response> {

    private final int max;
    private int greetingCounter;

    private BotActor(ActorContext<ConfirmActor.Response> context, int max) {
        super(context);
        this.max = max;
    }

    public static Behavior<ConfirmActor.Response> create(int max) {
        return Behaviors.setup(context -> new BotActor(context, max));
    }

    @Override
    public Receive<ConfirmActor.Response> createReceive() {
        return newReceiveBuilder().onMessage(ConfirmActor.Response.class, this::onResponse).build();
    }

    private Behavior<ConfirmActor.Response> onResponse(ConfirmActor.Response message) {
        greetingCounter++;
        getContext().getLog().info("Greeting {} for {}", greetingCounter, message.whom);
        if (greetingCounter == max) {
            return Behaviors.stopped();
        } else {
            message.from.tell(new ConfirmActor.Request(message.whom, getContext().getSelf()));
            return this;
        }
    }
}
