package com.example.akka.bot;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.Objects;

public class ConfirmActor extends AbstractBehavior<ConfirmActor.Request> {

    private ConfirmActor(ActorContext<Request> context) {
        super(context);
    }

    public static Behavior<Request> create() {
        return Behaviors.setup(ConfirmActor::new);
    }

    @Override
    public Receive<Request> createReceive() {
        return newReceiveBuilder().onMessage(Request.class, this::onRequest).build();
    }

    private Behavior<Request> onRequest(Request command) {
        getContext().getLog().info("What an interesting question {}!", command.whom);

        command.replyTo.tell(new Response(command.whom, getContext().getSelf()));

        return this;
    }


    public static final class Request {

        public final String whom;
        public final ActorRef<Response> replyTo;

        public Request(String whom, ActorRef<Response> replyTo) {
            this.whom = whom;
            this.replyTo = replyTo;
        }
    }


    public static final class Response {

        public final String whom;
        public final ActorRef<Request> from;

        public Response(String whom, ActorRef<Request> from) {
            this.whom = whom;
            this.from = from;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Response response = (Response) o;
            return Objects.equals(whom, response.whom) &&
                    Objects.equals(from, response.from);
        }

        @Override
        public int hashCode() {
            return Objects.hash(whom, from);
        }

        @Override
        public String toString() {
            return "Response{" +
                    "whom='" + whom + '\'' +
                    ", from=" + from +
                    '}';
        }
    }
}