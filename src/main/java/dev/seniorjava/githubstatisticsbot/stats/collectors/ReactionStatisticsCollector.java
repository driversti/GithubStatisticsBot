package dev.seniorjava.githubstatisticsbot.stats.collectors;

import dev.seniorjava.githubstatisticsbot.exceptions.CannotFetchGithubDataException;
import dev.seniorjava.githubstatisticsbot.stats.mappers.StatisticsMapper;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactionStatisticsCollector {

  private final OkHttpClient httpClient;
  private final StatisticsMapper mapper;

  public Collection<ReactionStatistics> fromPullRequest(final String sourceURL) {
    final String targetURL = getPullRequestReactionsURL(sourceURL);
    return collectStats(targetURL);
  }

  public Collection<ReactionStatistics> fromCommit(final String sourceURL) {
    final String targetURL = getCommitReactionsURL(sourceURL);
    return collectStats(targetURL);
  }

  private Collection<ReactionStatistics> collectStats(final String targetURL) {
    try {
      final String jsonContent = getJsonContent(targetURL);
      return mapper.map(jsonContent);
    } catch (Exception exception) {
      log.error("Cannot fetch pull request.", exception);
    }
    return Collections.emptyList();
  }

  private String getJsonContent(final String targetURL) throws IOException {
    try (final var response = httpClient.newCall(createRequest(targetURL)).execute()) {
      final ResponseBody body = response.body();
      if (response.isSuccessful() && body != null) {
        final String json = body.string();
        body.close();
        return json;
      }
    }
    throw new CannotFetchGithubDataException(targetURL);
  }

  private Request createRequest(final String targetURL) {
    log.debug(targetURL);
    return new Request.Builder()
        .header("Accept", "application/vnd.github.squirrel-girl-preview+json")
        .url(targetURL)
        .get()
        .build();
  }

  private String getPullRequestReactionsURL(final String url) {
    return url.replace("https://github.com/", "https://api.github.com/repos/")
        .replace("/pull/", "/pulls/")
        .concat("/comments");
  }

  private String getCommitReactionsURL(final String url) {
    return url.replace("https://github.com/", "https://api.github.com/repos/")
        .replace("/commit/", "/commits/")
        .concat("/comments");
  }
}
