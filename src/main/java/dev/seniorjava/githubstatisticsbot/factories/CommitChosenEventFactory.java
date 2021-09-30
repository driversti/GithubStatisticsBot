package dev.seniorjava.githubstatisticsbot.factories;

import com.pengrad.telegrambot.model.Update;
import dev.seniorjava.githubstatisticsbot.events.CommitChosenEvent;
import dev.seniorjava.githubstatisticsbot.events.Event;
import dev.seniorjava.githubstatisticsbot.exceptions.EventCannotBeCreatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommitChosenEventFactory implements EventFactory {

  private static final String COMMAND = "/commit";

  @Override
  public Event create(final Update update) {
    // fixme repeated checks :(
    if (!canCreateEvent(update)) {
      throw new EventCannotBeCreatedException();
    }
    return new CommitChosenEvent(update.callbackQuery().message().chat().id());
  }

  @Override
  public boolean canCreateEvent(final Update update) {
    if (update == null || update.callbackQuery() == null) {
      return false;
    }
    return COMMAND.equals(update.callbackQuery().data());
  }
}
