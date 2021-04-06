package dev.code_n_roll.graylog.send;

import java.time.Duration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import okhttp3.OkHttpClient;

public class SendModule extends AbstractModule {

  public static final int CONNECT_TIMEOUT_MS = 5000;
  public static final int READ_TIMEOUT_MS = 1000;

  private static final String GRAYLOG_SERVER_GELF_HTTP_INPUT_URL = "http://127.0.0.1:12201/gelf";

  @Provides
  static OkHttpClient provideGelfTransport() {
	return new OkHttpClient.Builder().connectTimeout(Duration.ofMillis(CONNECT_TIMEOUT_MS))
		.readTimeout(Duration.ofMillis(READ_TIMEOUT_MS))
		.build();
  }

  @Provides
  static GraylogClient provideGraylogClient(OkHttpClient okHttpClient) {
	return new GraylogClient(okHttpClient, GRAYLOG_SERVER_GELF_HTTP_INPUT_URL);
  }
}
