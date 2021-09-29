package dev.seniorjava.githubstatisticsbot.listeners;

import static java.lang.String.format;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import dev.seniorjava.githubstatisticsbot.events.StartPressedEvent;
import dev.seniorjava.githubstatisticsbot.keyboards.StatisticsSourceKeyboard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartedPressedEventListener {

  private final TelegramBot bot;

  @EventListener(StartPressedEvent.class)
  public void handle(final StartPressedEvent event) {
    final String messageToUser = format(
        "Hello, %s.\nChoose which statistics you are interested in:", event.userName());
    bot.execute(new SendMessage(event.chatId(), messageToUser)
        .replyMarkup(StatisticsSourceKeyboard.create()));
  }
}
