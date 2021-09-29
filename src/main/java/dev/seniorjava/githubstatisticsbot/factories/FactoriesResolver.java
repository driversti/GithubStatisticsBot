package dev.seniorjava.githubstatisticsbot.factories;

import com.pengrad.telegrambot.model.Update;
import dev.seniorjava.githubstatisticsbot.events.Event;
import dev.seniorjava.githubstatisticsbot.exceptions.EventFactoryNotFoundException;
import dev.seniorjava.githubstatisticsbot.exceptions.TooManyEventFactoriesException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FactoriesResolver {

  private static final int FIRST = 0;

  private final Set<EventFactory> eventFactories;
  private final ApplicationEventPublisher eventPublisher;

  public void resolve(final Update update) {
    final List<EventFactory> foundImplementations = eventFactories.stream()
        .filter(eventFactory -> eventFactory.canCreateEvent(update))
        .collect(Collectors.toList());

    if (foundImplementations.isEmpty()) {
      throw new EventFactoryNotFoundException();
    }
    if (foundImplementations.size() > 1) {
      throw new TooManyEventFactoriesException();
    }

    final Event event = foundImplementations.get(FIRST).create(update);
    eventPublisher.publishEvent(event);
  }
}
