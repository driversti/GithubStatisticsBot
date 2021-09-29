package dev.seniorjava.githubstatisticsbot.exceptions;

public class CannotFetchGithubDataException extends RuntimeException {

  public CannotFetchGithubDataException() {
    super();
  }

  public CannotFetchGithubDataException(final String message) {
    super(message);
  }
}
