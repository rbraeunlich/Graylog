package dev.code_n_roll.graylog.send;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.code_n_roll.graylog.parse.RequestMessage;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraylogClient {

  private static final Logger LOG = LoggerFactory.getLogger(GraylogClient.class);

  private static final ObjectMapper OBJECT_MAPPER = new JsonMapper();

  private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

  private final OkHttpClient httpClient;
  private final String graylogServerGelfHttpInputUrl;

  public GraylogClient(OkHttpClient httpClient, String graylogServerGelfHttpInputUrl) {
	this.httpClient = httpClient;
	this.graylogServerGelfHttpInputUrl = graylogServerGelfHttpInputUrl;
  }

  public void sendMessage(RequestMessage requestMessage) {
	ObjectNode message = RequestMessageTransformer.transform(requestMessage);
	try {
	  LOG.debug("Sending message to Graylog server.");
	  Request request = createRequest(message);
	  performRequest(request);
	} catch (Exception ex) {
	  LOG.error("Couldn't send message to Graylog server.", ex);
	}
  }

  private Request createRequest(ObjectNode message) throws JsonProcessingException {
	return new Request.Builder()
		.url(this.graylogServerGelfHttpInputUrl)
		.post(RequestBody.create(OBJECT_MAPPER.writeValueAsString(message), JSON))
		.build();
  }

  private void performRequest(Request request) throws IOException {
	try (Response response = this.httpClient.newCall(request).execute()) {
	  if (response.isSuccessful()) {
		LOG.debug("Received success response code {} from server.", response.code());
	  } else {
		LOG.error("Received error response code {} from server.", response.code());
	  }
	}
  }
}
