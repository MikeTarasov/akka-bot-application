package com.example.akka.bot.actors;

import akka.actor.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class BotActor extends AbstractBehavior<ConfirmActor.Response> {

    private final String apiHost = "http://localhost:8080";

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
        System.out.println("\tИщу ответ в апи: " + message.whom);
        ActorSystem system = ActorSystem.create();

        Http.get(system)
                .singleRequest(HttpRequest.GET(apiHost.concat("/ask?question=").concat(message.whom)))

                .exceptionally(throwable -> HttpResponse.create().withStatus(400))

                .whenComplete((httpResponse, throwable) -> {

                    if (httpResponse.status().isSuccess()) {

                        httpResponse.entity()
                                .toStrict(FiniteDuration.create(20, TimeUnit.SECONDS).toMillis(), system)
                                .thenApply(strict -> {
                                    String string = strict.getData().utf8String();
                                    System.out.println(string);
                                    return string;
                                });
                    } else {
                        System.out.println("Api service is not available!");
                    }
                });
        return this;
    }
}