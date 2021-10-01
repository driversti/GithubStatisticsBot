package dev.seniorjava.githubstatisticsbot.listeners;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.SendMessage;
import dev.seniorjava.githubstatisticsbot.events.PullRequestLinkProvidedEvent;
import dev.seniorjava.githubstatisticsbot.stats.collectors.ReactionStatisticsCollector;
import dev.seniorjava.githubstatisticsbot.stats.collectors.ReactionStatistics;
import dev.seniorjava.githubstatisticsbot.stats.formatters.StatisticsFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PullRequestLinkProvidedListener {

  private final TelegramBot bot;
  private final ReactionStatisticsCollector collector;
  private final StatisticsFormatter statisticsFormatter;

  @EventListener(PullRequestLinkProvidedEvent.class)
  public void handle(final PullRequestLinkProvidedEvent event) {
    Collection<ReactionStatistics> stats = collector.fromPullRequest(event.link());

    final String formattedStats = statisticsFormatter.format(groupByUsername(stats));

    bot.execute(new SendMessage(event.chatId(), formattedStats)
        .replyMarkup(new ReplyKeyboardRemove()));
  }

  private static Map<String, List<ReactionStatistics>> groupByUsername(final Collection<ReactionStatistics> stats) {
    return stats.stream()
        .collect(Collectors.groupingBy(ReactionStatistics::username, Collectors.toList()));
  }
}
