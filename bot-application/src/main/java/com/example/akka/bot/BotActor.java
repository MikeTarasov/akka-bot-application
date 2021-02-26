package com.example.akka.bot;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class BotActor extends AbstractBehavior<ConfirmActor.Response> {

    private BotActor(ActorContext<ConfirmActor.Response> context) {
        super(context);
    }

    public static Behavior<ConfirmActor.Response> create() {
        return Behaviors.setup(BotActor::new);
    }

    @Override
    public Receive<ConfirmActor.Response> createReceive() {
        return newReceiveBuilder().onMessage(ConfirmActor.Response.class, this::onResponse).build();
    }

    private Behavior<ConfirmActor.Response> onResponse(ConfirmActor.Response message) {
        getContext().getLog().info("Try to find answer to {}", message.whom);


        message.from.tell(new ConfirmActor.Request(message.whom, getContext().getSelf()));

        return this;
    }

    private MessageResponse requestToApi(String request) {
        return null;
    }
}
