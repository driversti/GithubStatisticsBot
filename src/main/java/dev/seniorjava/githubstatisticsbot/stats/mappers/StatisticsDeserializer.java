package dev.seniorjava.githubstatisticsbot.stats.mappers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import dev.seniorjava.githubstatisticsbot.stats.collectors.ReactionStatistics;
import dev.seniorjava.githubstatisticsbot.stats.collectors.ReactionStatistics.Reactions;
import dev.seniorjava.githubstatisticsbot.stats.collectors.ReactionStatistics.User;
import java.io.IOException;

public class StatisticsDeserializer extends JsonDeserializer<ReactionStatistics> {

  @Override
  public ReactionStatistics deserialize(final JsonParser parser, final DeserializationContext context)
      throws IOException {

    final JsonNode node = parser.getCodec().readTree(parser);
    Reactions reactions = parseReactions(node.get("reactions"));
    User user = parseUser(node.get("user"));

    return new ReactionStatistics(user, reactions);
  }

  private Reactions parseReactions(final JsonNode reactions) {
    if (reactions == null) {
      return null;
    }
    return new Reactions(
        reactions.get("+1").asInt(),
        reactions.get("-1").asInt(),
        reactions.get("laugh").asInt(),
        reactions.get("hooray").asInt(),
        reactions.get("confused").asInt(),
        reactions.get("heart").asInt(),
        reactions.get("rocket").asInt(),
        reactions.get("eyes").asInt()
    );
  }

  private User parseUser(final JsonNode user) {
    if (user == null) {
      return null;
    }
    return new User(
        user.get("login").asText()
    );
  }
}
