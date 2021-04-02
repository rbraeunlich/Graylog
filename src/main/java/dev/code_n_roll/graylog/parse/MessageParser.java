package dev.code_n_roll.graylog.parse;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reads the sample messages file. Since the file as a whole isn't valid JSON, it is read line by line and then passed to the ObjectMapper.
 */
public class MessageParser {

  private static final Logger LOG = LoggerFactory.getLogger(MessageParser.class);

  private static final ObjectMapper OBJECT_MAPPER = new JsonMapper();

  private final String messageFile;

  /**
   * @param messageFile the file that contains the messages. Has to be on the classpath.
   */
  public MessageParser(String messageFile) {
	this.messageFile = messageFile;
  }

  public List<RequestMessage> parseMessageFile() {
	InputStream resource = this.getClass().getResourceAsStream(messageFile);
	if (resource == null) {
	  LOG.warn("Couldn't find message file.");
	  return Collections.emptyList();
	}
	Scanner scanner = new Scanner(resource, StandardCharsets.UTF_8.name());
	List<RequestMessage> messages = new ArrayList<>();
	return parseMessageFile(scanner, messages);
  }

  private List<RequestMessage> parseMessageFile(Scanner scanner, List<RequestMessage> messages) {
	while (scanner.hasNext()) {
	  String line = scanner.nextLine();
	  try {
		RequestMessage requestMessage = OBJECT_MAPPER.readValue(line, RequestMessage.class);
		messages.add(requestMessage);
	  } catch (JsonProcessingException ex) {
		LOG.error("Error while parsing message file.", ex);
		break;
	  }
	}
	return messages;
  }
}
