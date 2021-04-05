package dev.code_n_roll.graylog.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.code_n_roll.graylog.parse.RequestMessage;
import org.graylog2.gelfclient.GelfMessage;
import org.graylog2.gelfclient.GelfMessageBuilder;
import org.graylog2.gelfclient.GelfMessageLevel;

/**
 * Transforms a {@link dev.code_n_roll.graylog.parse.RequestMessage} into a message following the
 * GELF format (https://docs.graylog.org/en/4.0/pages/gelf.html).
 */
public class RequestMessageTransformer {

  public static GelfMessage transform(RequestMessage requestMessage) {
	return new GelfMessageBuilder("", "example.com")
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
