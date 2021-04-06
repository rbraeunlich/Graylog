package dev.code_n_roll.graylog.send;

import com.github.tomakehurst.wiremock.WireMockServer;
import dev.code_n_roll.graylog.parse.RequestMessage;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class GraylogClientTest {

  private WireMockServer wireMockServer;

  @BeforeEach
  void setUp() {
	this.wireMockServer = new WireMockServer();
	wireMockServer.start();
  }

  @AfterEach
  void tearDown() {
	wireMockServer.stop();
  }

  @Test
  void shouldSendMessageToGraylogServer() {
	mockSuccessResponseFromServer();
	GraylogClient client = new GraylogClient(new OkHttpClient.Builder().build(), wireMockServer.baseUrl() + "/gelf");

	client.sendMessage(message());

	verify(postRequestedFor(urlEqualTo("/gelf")));
  }

  private void mockSuccessResponseFromServer() {
	stubFor(
		post("/gelf").withRequestBody(equalToJson("{\n" +
			"    \"version\" : \"1.1\",\n" +
			"    \"host\" : \"example.com\",\n" +
			"    \"short_message\" : \"Graylog client message\",\n" +
			"    \"_ClientDeviceType\" : \"desktop\",\n" +
			"    \"_ClientIP\" :\n" +
			"    \"11.73.87.52\",\n" +
			"    \"_ClientIPClass\" : \"noRecord\",\n" +
			"    \"_ClientStatus\" : \"403\",\n" +
			"    \"_ClientRequestBytes\" : 889,\n" +
			"    \"_ClientRequestReferer\" : \"graylog.org\",\n" +
			"    \"_ClientRequestURI\" : \"/search\",\n" +
			"    \"_ClientRequestUserAgent\" : \"Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko\",\n" +
			"    \"_ClientSrcPort\" : 122,\n" +
			"    \"_EdgeServerIP\" : \"156.20.151.71\",\n" +
			"    \"_EdgeStartTimestamp\" : 1576929197,\n" +
			"    \"_DestinationIP\" : \"115.242.153.30\",\n" +
			"    \"_OriginResponseBytes\" : 821,\n" +
			"    \"_OriginResponseTime\" : 337000000\n" +
			"}"))
			.willReturn(ok())
	);
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

}
