package dev.seniorjava.githubstatisticsbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotRunner {

  private final TelegramBot bot;
  private final UpdatesListener updatesListener;

  @EventListener(ApplicationReadyEvent.class)
  public void run() {
    bot.setUpdatesListener(updatesListener);
  }
}
