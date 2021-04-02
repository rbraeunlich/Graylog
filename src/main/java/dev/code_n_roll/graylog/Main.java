package dev.code_n_roll.graylog;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.code_n_roll.graylog.parse.ParseModule;
import dev.code_n_roll.graylog.send.SendModule;

public class Main {

  public static void main(String[] args) {
	Injector injector = Guice.createInjector(
		new ParseModule(),
		new SendModule()
	);

	injector.getInstance(GraylogApplication.class).start();
  }
}
