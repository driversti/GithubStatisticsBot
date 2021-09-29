package dev.seniorjava.githubstatisticsbot.keyboards;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class InlineKeyboardBuilder {

  private final List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
  private List<InlineKeyboardButton> row = null;

  InlineKeyboardBuilder row() {
    if (isRowNotEnded()) {
      endRow();
      log.debug("The current row was not ended properly!");
    }
    this.row = new ArrayList<>();
    return this;
  }

  InlineKeyboardBuilder button(String text, String callbackData) {
    row.add(new InlineKeyboardButton(text).callbackData(callbackData));
    return this;
  }

  InlineKeyboardBuilder endRow() {
    this.keyboard.add(this.row);
    this.row = null;
    return this;
  }

  private boolean isRowNotEnded() {
    return this.row != null;
  }

  InlineKeyboardMarkup build() {
    if (isRowNotEnded()) {
      endRow();
    }
    InlineKeyboardButton[][] inlineKeyboard = keyboard.stream()
        .map(list -> list.toArray(InlineKeyboardButton[]::new))
        .toArray(InlineKeyboardButton[][]::new);
    return new InlineKeyboardMarkup(inlineKeyboard);
  }

  static InlineKeyboardBuilder builder() {
    return new InlineKeyboardBuilder();
  }
}
