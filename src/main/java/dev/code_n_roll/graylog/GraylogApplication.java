package dev.code_n_roll.graylog;

import java.util.List;

import dev.code_n_roll.graylog.parse.MessageParser;
import dev.code_n_roll.graylog.parse.RequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraylogApplication {

  private static final Logger LOG = LoggerFactory.getLogger(GraylogApplication.class);

  public void start() {
	LOG.info("Starting application.");
	MessageParser parser = new MessageParser("/sample-messages.txt");
	List<RequestMessage> messages = parser.parseMessageFile();
	LOG.info("Parsed {} messages.", messages.size());
  }
}
