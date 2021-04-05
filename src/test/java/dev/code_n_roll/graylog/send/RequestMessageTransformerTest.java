package dev.code_n_roll.graylog.send;

import dev.code_n_roll.graylog.parse.RequestMessage;
import org.graylog2.gelfclient.GelfMessage;
import org.graylog2.gelfclient.GelfMessageBuilder;
import org.graylog2.gelfclient.GelfMessageLevel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class RequestMessageTransformerTest {

  @Test
  void shouldTransformMessageProperly(){
	RequestMessage requestMessage = message();
	GelfMessage gelfMessage = RequestMessageTransformer.transform(requestMessage);

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

  private GelfMessage expectedMessage(RequestMessage requestMessage) {
    return 	new GelfMessageBuilder("", "example.com")
		.level(GelfMessageLevel.INFO)
		.additionalField("_ClientDeviceType", requestMessage.getClientDeviceType())
		.additionalField("_ClientIP", requestMessage.getClientIP())
		.additionalField("_ClientIPClass", requestMessage.getClientIPClass())
		.additionalField("_ClientStatus", requestMessage.getClientStatus())
		.additionalField("_ClientRequestBytes", requestMessage.getClientRequestBytes())
		.additionalField("_ClientRequestReferer", requestMessage.getClientRequestReferer())
		.additionalField("_ClientRequestURI", requestMessage.getClientRequestURI())
		.additionalField("_ClientRequestUserAgent", requestMessage.getClientRequestUserAgent())
		.additionalField("_ClientSrcPort", requestMessage.getClientSrcPort())
		.additionalField("_EdgeServerIP", requestMessage.getEdgeServerIP())
		.additionalField("_EdgeStartTimestamp", requestMessage.getEdgeStartTimestamp())
		.additionalField("_DestinationIP", requestMessage.getDestinationIP())
		.additionalField("_OriginResponseBytes", requestMessage.getOriginResponseBytes())
		.additionalField("_OriginResponseTime", requestMessage.getOriginResponseTime())
		.build();
  }
}
