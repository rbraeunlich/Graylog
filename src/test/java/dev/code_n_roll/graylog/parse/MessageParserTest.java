package dev.code_n_roll.graylog.parse;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageParserTest {

  @Test
  void shouldParseMessage() {
	MessageParser parser = new MessageParser("/messages/test-message.txt");

	List<RequestMessage> messages = parser.parseMessageFile();

	assertThat(messages).containsOnly(expectedMessage());
  }

  @Test
  void shouldReturnEmptyListInCaseOfMissingFile() {
	MessageParser parser = new MessageParser("/messages/no-message.txt");

	List<RequestMessage> messages = parser.parseMessageFile();

	assertThat(messages).isEmpty();
  }

  @Test
  void shouldReturnEmptyListInCaseOfInvalidJson() {
	MessageParser parser = new MessageParser("/messages/invalid-message.txt");

	List<RequestMessage> messages = parser.parseMessageFile();

	assertThat(messages).isEmpty();
  }

  private RequestMessage expectedMessage() {
	return new RequestMessage(
		"desktop",
		"11.73.87.52",
		"noRecord",
		"403",
		889L,
		"graylog.org",
		"/search",
		"Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko",
		122,
		"156.20.151.71",
		1576929197L,
		"115.242.153.30",
		821L,
		337000000L
	);
  }

}
