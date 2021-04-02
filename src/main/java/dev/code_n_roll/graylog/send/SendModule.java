package dev.code_n_roll.graylog.send;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import dev.code_n_roll.graylog.parse.MessageParser;

public class SendModule extends AbstractModule {

  @Provides
  static GraylogClient provideGraylogClient() {
	return new GraylogClient();
  }

}
