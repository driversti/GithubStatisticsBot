package dev.seniorjava.githubstatisticsbot.stats.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.seniorjava.githubstatisticsbot.exceptions.CannotParsePullRequestStatsException;
import dev.seniorjava.githubstatisticsbot.stats.collectors.ReactionStatistics;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticsMapper {

  private final ObjectMapper objectMapper;

  public List<ReactionStatistics> map(final String json) {
    try {
      return objectMapper.readValue(json, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      log.error("Cannot read PR stats from json", e);
      throw new CannotParsePullRequestStatsException(e);
    }
  }
}
