package dev.seniorjava.githubstatisticsbot.events;

public record PullRequestLinkProvidedEvent(Long chatId, String link) implements Event {

}
