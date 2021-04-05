package dev.code_n_roll.graylog.send;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.code_n_roll.graylog.parse.RequestMessage;

/**
 * Transforms a {@link dev.code_n_roll.graylog.parse.RequestMessage} into a message following the
 * GELF format (https://docs.graylog.org/en/4.0/pages/gelf.html).
 */
public class RequestMessageTransformer {

  private static final String VERSION_FIELD = "version";
  private static final String GELF_VERSION = "1.1";
  private static final String HOST_FIELD = "host";
  private static final String SHORT_MESSAGE = "short_message";
  public static final ObjectMapper OBJECT_MAPPER = new JsonMapper();

  public static ObjectNode transform(RequestMessage requestMessage) {
	ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
	addMandatoryFields(objectNode);
	addAdditionalFields(objectNode, requestMessage);
	return objectNode;
  }

  private static void addMandatoryFields(ObjectNode objectNode) {
	objectNode.put(VERSION_FIELD, GELF_VERSION);
	objectNode.put(HOST_FIELD, "example.com");
	objectNode.put(SHORT_MESSAGE, "Graylog client message");
  }

  private static void addAdditionalFields(ObjectNode objectNode, RequestMessage requestMessage) {
	objectNode.put("_ClientDeviceType", requestMessage.getClientDeviceType());
	objectNode.put("_ClientIP", requestMessage.getClientIP());
	objectNode.put("_ClientIPClass", requestMessage.getClientIPClass());
	objectNode.put("_ClientStatus", requestMessage.getClientStatus());
	objectNode.put("_ClientRequestBytes", requestMessage.getClientRequestBytes());
	objectNode.put("_ClientRequestReferer", requestMessage.getClientRequestReferer());
	objectNode.put("_ClientRequestURI", requestMessage.getClientRequestURI());
	objectNode.put("_ClientRequestUserAgent", requestMessage.getClientRequestUserAgent());
	objectNode.put("_ClientSrcPort", requestMessage.getClientSrcPort());
	objectNode.put("_EdgeServerIP", requestMessage.getEdgeServerIP());
	objectNode.put("_EdgeStartTimestamp", requestMessage.getEdgeStartTimestamp());
	objectNode.put("_DestinationIP", requestMessage.getDestinationIP());
	objectNode.put("_OriginResponseBytes", requestMessage.getOriginResponseBytes());
	objectNode.put("_OriginResponseTime", requestMessage.getOriginResponseTime());
  }

}
