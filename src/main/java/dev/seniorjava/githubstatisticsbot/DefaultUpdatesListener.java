package dev.seniorjava.githubstatisticsbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import dev.seniorjava.githubstatisticsbot.factories.FactoriesResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultUpdatesListener implements UpdatesListener {

  private final TelegramBot bot;
  private final FactoriesResolver factoriesResolver;

  @Override
  public int process(final List<Update> updates) {
    updates.forEach(this::process);
    return UpdatesListener.CONFIRMED_UPDATES_ALL;
  }

  private void process(final Update update) {
    try {
      factoriesResolver.resolve(update);
    } catch (RuntimeException ex) {
      // todo hope it exists. A place for improvements (create a handler for exceptions)
      final Long chatId = update.message().chat().id();
      log.error("Cannot process request.", ex);
      bot.execute(new SendMessage(chatId, "Sorry, I can't process your message. Try again."));
    }
  }
}
