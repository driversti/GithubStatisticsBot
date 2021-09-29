package dev.seniorjava.githubstatisticsbot.events;

public record CommitLinkProvidedEvent(Long chatId, String link) implements Event {

}
