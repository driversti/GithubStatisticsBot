package dev.seniorjava.githubstatisticsbot.factories;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import dev.seniorjava.githubstatisticsbot.events.Event;
import dev.seniorjava.githubstatisticsbot.events.StartPressedEvent;
import dev.seniorjava.githubstatisticsbot.exceptions.EventCannotBeCreatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartPressedEventFactory implements EventFactory {

  private static final String COMMAND = "/start";

  @Override
  public Event create(final Update update) {
    // fixme repeated checks :(
    if (!canCreateEvent(update)) {
      throw new EventCannotBeCreatedException();
    }
    final User user = update.message().from();
    final String userName = user.firstName() != null ? user.firstName() : user.username();
    return new StartPressedEvent(update.message().chat().id(), userName);
  }

  @Override
  public boolean canCreateEvent(final Update update) {
    if (update == null || update.message() == null) {
      return false;
    }
    return COMMAND.equals(update.message().text());
  }
}
