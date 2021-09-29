package dev.seniorjava.githubstatisticsbot.factories;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import dev.seniorjava.githubstatisticsbot.events.CommitLinkProvidedEvent;
import dev.seniorjava.githubstatisticsbot.events.Event;
import dev.seniorjava.githubstatisticsbot.events.PullRequestLinkProvidedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommitLinkProvidedEventFactory implements EventFactory {

  @Override
  public Event create(final Update update) {
    final Message message = update.message();
    return new CommitLinkProvidedEvent(message.chat().id(), message.text());
  }

  @Override
  public boolean canCreateEvent(final Update update) {
    if (update == null || update.message() == null || update.message().replyToMessage() == null) {
      return false;
    }
    final String expectedMessageStart = "Please, provide a link to a commit strictly in the following format";
    return update.message().replyToMessage().text() != null &&
        update.message().replyToMessage().text().startsWith(expectedMessageStart);
  }
}
