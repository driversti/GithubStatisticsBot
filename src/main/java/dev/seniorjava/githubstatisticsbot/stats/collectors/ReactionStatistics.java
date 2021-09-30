package dev.seniorjava.githubstatisticsbot.stats.collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.seniorjava.githubstatisticsbot.stats.mappers.StatisticsDeserializer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonDeserialize(using = StatisticsDeserializer.class)
public class ReactionStatistics {

  private User user;
  private Reactions reactions;

  public String username() {
    return user == null ? null : user.login;
  }

  public int pluses() {
    return reactions == null ? 0 : reactions.plus;
  }

  public int minuses() {
    return reactions == null ? 0 : reactions.minus;
  }

  public int laughs() {
    return reactions == null ? 0 : reactions.laugh;
  }

  public int hoorays() {
    return reactions == null ? 0 : reactions.hooray;
  }

  public int confused() {
    return reactions == null ? 0 : reactions.confused;
  }

  public int hearts() {
    return reactions == null ? 0 : reactions.heart;
  }

  public int rockets() {
    return reactions == null ? 0 : reactions.rocket;
  }

  public int eyes() {
    return reactions == null ? 0 : reactions.eyes;
  }

  @AllArgsConstructor
  public static class User {

    private String login;
  }

  @AllArgsConstructor
  public static class Reactions {

    private int plus;
    private int minus;
    private int laugh;
    private int hooray;
    private int confused;
    private int heart;
    private int rocket;
    private int eyes;
  }
}
