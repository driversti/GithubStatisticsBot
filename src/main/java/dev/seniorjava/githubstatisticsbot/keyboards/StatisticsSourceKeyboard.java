package dev.seniorjava.githubstatisticsbot.keyboards;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StatisticsSourceKeyboard {

  public InlineKeyboardMarkup create() {
    return InlineKeyboardBuilder.builder()
        .row()
        .button("Pull Request", "/pull_request")
        .button("Commit", "/commit")
        .endRow()
        .build();
  }
}
