package dev.code_n_roll.graylog.parse;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ParseModule extends AbstractModule {

  @Provides
  static MessageParser provideMessageParser() {
	return new MessageParser("/sample-messages.txt");
  }
}
