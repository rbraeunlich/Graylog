package dev.code_n_roll.graylog;

import java.util.List;

import com.google.inject.Inject;
import dev.code_n_roll.graylog.parse.MessageParser;
import dev.code_n_roll.graylog.parse.RequestMessage;
import dev.code_n_roll.graylog.send.GraylogClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraylogApplication {

  private static final Logger LOG = LoggerFactory.getLogger(GraylogApplication.class);

  private final MessageParser messageParser;

  private final GraylogClient graylogClient;

  @Inject
  public GraylogApplication(MessageParser messageParser,
							GraylogClient graylogClient) {
	this.messageParser = messageParser;
	this.graylogClient = graylogClient;
  }

  public void start() {
	LOG.info("Starting application.");
	List<RequestMessage> messages = this.messageParser.parseMessageFile();
	LOG.info("Parsed {} messages.", messages.size());
	messages.forEach(this.graylogClient::sendMessage);
	this.graylogClient.flush();
	LOG.info("Finished sending messages to Graylog server.");
  }
}
