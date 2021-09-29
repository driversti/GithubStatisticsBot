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
    return user.login;
  }

  public int pluses() {
    return reactions.plus;
  }

  public int minuses() {
    return reactions.minus;
  }

  public int laughs() {
    return reactions.laugh;
  }

  public int hoorays() {
    return reactions.hooray;
  }

  public int confused() {
    return reactions.confused;
  }

  public int hearts() {
    return reactions.heart;
  }

  public int rockets() {
    return reactions.rocket;
  }

  public int eyes() {
    return reactions.eyes;
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
