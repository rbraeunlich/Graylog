package dev.code_n_roll.graylog.send;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.graylog2.gelfclient.GelfConfiguration;
import org.graylog2.gelfclient.GelfTransports;

public class SendModule extends AbstractModule {

  public static final int QUEUE_SIZE = 512;
  public static final int CONNECT_TIMEOUT_MS = 5000;
  public static final int RECONNECT_DELAY_MS = 1000;
  public static final int SEND_BUFFER_SIZE_BYTE = 32768;

  @Provides
  static GelfConfiguration provideGelfConfiguration() {
	return new GelfConfiguration(9000)
		.transport(GelfTransports.UDP)
		.queueSize(QUEUE_SIZE)
		.connectTimeout(CONNECT_TIMEOUT_MS)
		.reconnectDelay(RECONNECT_DELAY_MS)
		.tcpNoDelay(true)
		.sendBufferSize(SEND_BUFFER_SIZE_BYTE);
  }

  @Provides
  static GraylogClient provideGraylogClient(GelfConfiguration gelfConfiguration) {
	return new GraylogClient(gelfConfiguration);
  }
}
