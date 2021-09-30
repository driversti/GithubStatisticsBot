package dev.seniorjava.githubstatisticsbot.stats.formatters;

import dev.seniorjava.githubstatisticsbot.stats.collectors.ReactionStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StatisticsFormatter {

  public String format(final Map<String, List<ReactionStatistics>> statisticsPerUser) {
    if (statisticsPerUser.isEmpty()) {
      return "Sorry, no statistics found";
    }

    // would be better format to a list. Let's to the end client to choose how to show the info
    return statisticsPerUser.entrySet().stream()
        .map(this::formatOne)
        .collect(Collectors.joining("\n\n"));
  }

  private String formatOne(final Entry<String, List<ReactionStatistics>> entry) {
    final String username = entry.getKey();
    final List<ReactionStatistics> stats = entry.getValue();

    return String.format("%s\n%d\uD83D\uDC4D %d\uD83D\uDC4E %d\uD83D\uDE04 %d\uD83C\uDF89 "
            + "%d\uD83D\uDE15 %d❤️ %d\uD83D\uDE80 %d\uD83D\uDC40", username,
        sumOf(stats, ReactionStatistics::pluses), sumOf(stats, ReactionStatistics::minuses),
        sumOf(stats, ReactionStatistics::laughs), sumOf(stats, ReactionStatistics::hoorays),
        sumOf(stats, ReactionStatistics::confused), sumOf(stats, ReactionStatistics::hearts),
        sumOf(stats, ReactionStatistics::rockets), sumOf(stats, ReactionStatistics::eyes));
  }

  private int sumOf(final List<ReactionStatistics> statistics,
      final ToIntFunction<ReactionStatistics> mapper) {
    return statistics.stream().mapToInt(mapper).sum();
  }
}
