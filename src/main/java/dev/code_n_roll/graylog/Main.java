package dev.code_n_roll.graylog;

import dev.code_n_roll.graylog.parse.MessageParser;

public class Main {

  public static void main(String[] args) {
    GraylogApplication application = new GraylogApplication();
    application.start();
  }
}
