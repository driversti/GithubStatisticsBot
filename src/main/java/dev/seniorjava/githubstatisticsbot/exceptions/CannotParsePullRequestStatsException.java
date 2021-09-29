package dev.seniorjava.githubstatisticsbot.exceptions;

public class CannotParsePullRequestStatsException extends RuntimeException {

  public CannotParsePullRequestStatsException() {
    super();
  }

  public CannotParsePullRequestStatsException(final String message) {
    super(message);
  }

  public CannotParsePullRequestStatsException(final Throwable cause) {
    super(cause);
  }

  public CannotParsePullRequestStatsException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
