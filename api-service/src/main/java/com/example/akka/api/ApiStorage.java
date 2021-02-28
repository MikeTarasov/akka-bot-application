package com.example.akka.api;

import java.util.HashMap;
import java.util.Map;

public class ApiStorage {

    private static final Map<String, String> answers = new HashMap<>();

    static {
        answers.put("привет", "Доброго времени суток!");
        answers.put("как дела", "Замечательно, а у тебя?");
        answers.put("чем занимаешься", "С тобой общаюсь!");
        answers.put("что умеешь", "Отвечать на простые вопросы");
        answers.put("как погодка", "Мороз и солнце, день чудесный");
        answers.put("как спалось", "Я бот, мне спать не нужно");
        answers.put("пока", "Ты заходи, если что...");
        answers.put("help", "Умею отвечать на вопросы: привет, как дела, чем занимаешься, что умеешь, как погодка, " +
                "как спалось, пока, help");
    }

    public static String findAnswer(String question) {
        if (answers.containsKey(question.toLowerCase())) {
            return answers.get(question);
        }
        return null;
    }
}