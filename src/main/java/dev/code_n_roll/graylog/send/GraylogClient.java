package dev.code_n_roll.graylog.send;

import java.util.concurrent.TimeUnit;

import dev.code_n_roll.graylog.parse.RequestMessage;
import org.graylog2.gelfclient.GelfConfiguration;
import org.graylog2.gelfclient.GelfMessage;
import org.graylog2.gelfclient.GelfMessageBuilder;
import org.graylog2.gelfclient.GelfMessageLevel;
import org.graylog2.gelfclient.GelfTransports;
import org.graylog2.gelfclient.transport.GelfTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraylogClient {

  private static final Logger LOG = LoggerFactory.getLogger(GraylogClient.class);

  private final GelfTransport transport;

  public GraylogClient(GelfTransport gelfTransport) {
	this.transport = gelfTransport;
  }

  public void sendMessage(RequestMessage requestMessage) {
	GelfMessage message = RequestMessageTransformer.transform(requestMessage);
	try {
	  LOG.debug("Sending message to Graylog server.");
	  this.transport.send(message);
	} catch (InterruptedException ex) {
	  LOG.error("Couldn't send message to Graylog server.", ex);
	}
  }

  public void flush() {
	LOG.info("Flushing messages to Graylog server.");
	this.transport.flushAndStopSynchronously(10, TimeUnit.SECONDS, 1);
  }
}
