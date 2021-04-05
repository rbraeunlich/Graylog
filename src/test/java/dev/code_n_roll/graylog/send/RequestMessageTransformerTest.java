package dev.code_n_roll.graylog.send;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.code_n_roll.graylog.parse.RequestMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class RequestMessageTransformerTest {

  @Test
  void shouldTransformMessageProperly() {
	RequestMessage requestMessage = message();
	ObjectNode gelfMessage = RequestMessageTransformer.transform(requestMessage);

	assertThat(gelfMessage)
		.usingRecursiveComparison()
		.ignoringFields("timestamp")
		.isEqualTo(expectedMessage(requestMessage));
  }

  private RequestMessage message() {
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

  private ObjectNode expectedMessage(RequestMessage requestMessage) {
	Map<String, Object> message = new HashMap<>();
	message.put("version", "1.1");
	message.put("host", "example.com");
	message.put("short_message", "Graylog client message");
	message.put("_ClientDeviceType", requestMessage.getClientDeviceType());
	message.put("_ClientIP", requestMessage.getClientIP());
	message.put("_ClientIPClass", requestMessage.getClientIPClass());
	message.put("_ClientStatus", requestMessage.getClientStatus());
	message.put("_ClientRequestBytes", requestMessage.getClientRequestBytes());
	message.put("_ClientRequestReferer", requestMessage.getClientRequestReferer());
	message.put("_ClientRequestURI", requestMessage.getClientRequestURI());
	message.put("_ClientRequestUserAgent", requestMessage.getClientRequestUserAgent());
	message.put("_ClientSrcPort", requestMessage.getClientSrcPort());
	message.put("_EdgeServerIP", requestMessage.getEdgeServerIP());
	message.put("_EdgeStartTimestamp", requestMessage.getEdgeStartTimestamp());
	message.put("_DestinationIP", requestMessage.getDestinationIP());
	message.put("_OriginResponseBytes", requestMessage.getOriginResponseBytes());
	message.put("_OriginResponseTime", requestMessage.getOriginResponseTime());
	return new JsonMapper().valueToTree(message);
  }
}
