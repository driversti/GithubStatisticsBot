package dev.seniorjava.githubstatisticsbot.stats.formatters;

import dev.seniorjava.githubstatisticsbot.stats.collectors.ReactionStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
            + "%d\uD83D\uDE15 %d❤️ %d\uD83D\uDE80 %d\uD83D\uDC40",
        username, thumbsUp(stats), thumbsDown(stats), laughs(stats), hoorays(stats),
        confused(stats), hearts(stats), rockets(stats), eyes(stats));
  }

  private int thumbsUp(final List<ReactionStatistics> stats) {
    return stats.stream().mapToInt(ReactionStatistics::pluses).sum();
  }

  private int thumbsDown(final List<ReactionStatistics> stats) {
    return stats.stream().mapToInt(ReactionStatistics::minuses).sum();
  }

  private int laughs(final List<ReactionStatistics> stats) {
    return stats.stream().mapToInt(ReactionStatistics::laughs).sum();
  }

  private int hoorays(final List<ReactionStatistics> stats) {
    return stats.stream().mapToInt(ReactionStatistics::hoorays).sum();
  }

  private int confused(final List<ReactionStatistics> stats) {
    return stats.stream().mapToInt(ReactionStatistics::confused).sum();
  }

  private int hearts(final List<ReactionStatistics> stats) {
    return stats.stream().mapToInt(ReactionStatistics::hearts).sum();
  }

  private int rockets(final List<ReactionStatistics> stats) {
    return stats.stream().mapToInt(ReactionStatistics::rockets).sum();
  }

  private int eyes(final List<ReactionStatistics> stats) {
    return stats.stream().mapToInt(ReactionStatistics::eyes).sum();
  }
}
