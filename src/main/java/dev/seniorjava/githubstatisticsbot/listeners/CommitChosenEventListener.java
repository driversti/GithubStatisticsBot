package dev.seniorjava.githubstatisticsbot.listeners;

import static java.lang.String.format;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import dev.seniorjava.githubstatisticsbot.events.CommitChosenEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommitChosenEventListener {

  private static final String COMMIT_LINK_FORMAT = "https://github.com/{owner}/{repo_name}/commit/{hash}";

  private final TelegramBot bot;

  @EventListener(CommitChosenEvent.class)
  public void handle(final CommitChosenEvent event) {
    final String messageToUser = format(
        "Please, provide a link to a commit strictly in the following format: %s",
        COMMIT_LINK_FORMAT);
    bot.execute(new SendMessage(event.chatId(), messageToUser)
        .replyMarkup(new ForceReply()));
  }
}
