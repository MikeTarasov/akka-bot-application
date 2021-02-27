package com.example.akka.bot.api;

//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MessageResponse {

    private String message;

    //    @JsonCreator
    public MessageResponse(String message) {
        this.message = message;
    }
}