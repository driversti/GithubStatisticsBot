package dev.seniorjava.githubstatisticsbot.factories;

import com.pengrad.telegrambot.model.Update;
import dev.seniorjava.githubstatisticsbot.events.Event;

public interface EventFactory {

  Event create(final Update update);

  boolean canCreateEvent(final Update update);
}
