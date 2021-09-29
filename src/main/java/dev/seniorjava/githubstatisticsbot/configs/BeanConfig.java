package dev.seniorjava.githubstatisticsbot.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Bean
  TelegramBot bot(@Value("${app.bot.token}") String botToken) {
    return new TelegramBot(botToken);
  }

  @Bean
  OkHttpClient httpClient() {
    return new OkHttpClient();
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
