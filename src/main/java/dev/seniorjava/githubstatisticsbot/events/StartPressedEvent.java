package dev.seniorjava.githubstatisticsbot.events;

public record StartPressedEvent(Long chatId, String userName) implements Event {

}
