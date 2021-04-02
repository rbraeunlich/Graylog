package dev.code_n_roll.graylog.parse;

import java.util.Objects;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Simple POJO to contain the data from the sample-messages.txt.
 * Since they look like requests to a webserver I called this class RequestMessage.
 */
public class RequestMessage {
  private final String clientDeviceType;
  private final String clientIP; // TODO use IP class
  private final String clientIPClass;
  private final String clientStatus; // TODO use HTTPStatus class
  private final Long clientRequestBytes;
  private final String clientRequestReferer;
  private final String clientRequestURI;
  private final String clientRequestUserAgent;
  private final Integer clientSrcPort;
  private final String edgeServerIP; // TODO use IP class
  private final Long edgeStartTimestamp;
  private final String destinationIP; // TODO use IP class
  private final Long originResponseBytes;
  private final Long originResponseTime;

  @JsonCreator
  public RequestMessage(
	  @JsonProperty("ClientDeviceType") String clientDeviceType,
	  @JsonProperty("ClientIP") String clientIP,
	  @JsonProperty("ClientIPClass") String clientIPClass,
	  @JsonProperty("ClientStatus") String clientStatus,
	  @JsonProperty("ClientRequestBytes") Long clientRequestBytes,
	  @JsonProperty("ClientRequestReferer") String clientRequestReferer,
	  @JsonProperty("ClientRequestURI") String clientRequestURI,
	  @JsonProperty("ClientRequestUserAgent") String clientRequestUserAgent,
	  @JsonProperty("ClientSrcPort") Integer clientSrcPort,
	  @JsonProperty("EdgeServerIP") String edgeServerIP,
	  @JsonProperty("EdgeStartTimestamp") Long edgeStartTimestamp,
	  @JsonProperty("DestinationIP") String destinationIP,
	  @JsonProperty("OriginResponseBytes") Long originResponseBytes,
	  @JsonProperty("OriginResponseTime") Long originResponseTime
  ) {
	this.clientDeviceType = clientDeviceType;
	this.clientIP = clientIP;
	this.clientIPClass = clientIPClass;
	this.clientStatus = clientStatus;
	this.clientRequestBytes = clientRequestBytes;
	this.clientRequestReferer = clientRequestReferer;
	this.clientRequestURI = clientRequestURI;
	this.clientRequestUserAgent = clientRequestUserAgent;
	this.clientSrcPort = clientSrcPort;
	this.edgeServerIP = edgeServerIP;
	this.edgeStartTimestamp = edgeStartTimestamp;
	this.destinationIP = destinationIP;
	this.originResponseBytes = originResponseBytes;
	this.originResponseTime = originResponseTime;
  }

  public String getClientDeviceType() {
	return clientDeviceType;
  }

  public String getClientIP() {
	return clientIP;
  }

  public String getClientIPClass() {
	return clientIPClass;
  }

  public String getClientStatus() {
	return clientStatus;
  }

  public Long getClientRequestBytes() {
	return clientRequestBytes;
  }

  public String getClientRequestReferer() {
	return clientRequestReferer;
  }

  public String getClientRequestURI() {
	return clientRequestURI;
  }

  public String getClientRequestUserAgent() {
	return clientRequestUserAgent;
  }

  public Integer getClientSrcPort() {
	return clientSrcPort;
  }

  public String getEdgeServerIP() {
	return edgeServerIP;
  }

  public Long getEdgeStartTimestamp() {
	return edgeStartTimestamp;
  }

  public String getDestinationIP() {
	return destinationIP;
  }

  public Long getOriginResponseBytes() {
	return originResponseBytes;
  }

  public Long getOriginResponseTime() {
	return originResponseTime;
  }

  @Override
  public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	RequestMessage that = (RequestMessage) o;
	return Objects.equals(getClientDeviceType(), that.getClientDeviceType()) && Objects.equals(getClientIP(), that.getClientIP()) && Objects.equals(getClientIPClass(), that.getClientIPClass()) && Objects.equals(getClientStatus(), that.getClientStatus()) && Objects.equals(getClientRequestBytes(), that.getClientRequestBytes()) && Objects.equals(getClientRequestReferer(), that.getClientRequestReferer()) && Objects.equals(getClientRequestURI(), that.getClientRequestURI()) && Objects.equals(getClientRequestUserAgent(), that.getClientRequestUserAgent()) && Objects.equals(getClientSrcPort(), that.getClientSrcPort()) && Objects.equals(getEdgeServerIP(), that.getEdgeServerIP()) && Objects.equals(getEdgeStartTimestamp(), that.getEdgeStartTimestamp()) && Objects.equals(getDestinationIP(), that.getDestinationIP()) && Objects.equals(getOriginResponseBytes(), that.getOriginResponseBytes()) && Objects.equals(getOriginResponseTime(), that.getOriginResponseTime());
  }

  @Override
  public int hashCode() {
	return Objects.hash(getClientDeviceType(), getClientIP(), getClientIPClass(), getClientStatus(), getClientRequestBytes(), getClientRequestReferer(), getClientRequestURI(), getClientRequestUserAgent(), getClientSrcPort(), getEdgeServerIP(), getEdgeStartTimestamp(), getDestinationIP(), getOriginResponseBytes(), getOriginResponseTime());
  }

  @Override
  public String toString() {
	return new StringJoiner(", ", RequestMessage.class.getSimpleName() + "[", "]")
		.add("clientDeviceType='" + clientDeviceType + "'")
		.add("clientIP='" + clientIP + "'")
		.add("clientIPClass='" + clientIPClass + "'")
		.add("clientStatus='" + clientStatus + "'")
		.add("clientRequestBytes=" + clientRequestBytes)
		.add("clientRequestReferer='" + clientRequestReferer + "'")
		.add("clientRequestURI='" + clientRequestURI + "'")
		.add("clientRequestUserAgent='" + clientRequestUserAgent + "'")
		.add("clientSrcPort=" + clientSrcPort)
		.add("edgeServerIP='" + edgeServerIP + "'")
		.add("edgeStartTimestamp=" + edgeStartTimestamp)
		.add("destinationIP='" + destinationIP + "'")
		.add("originResponseBytes=" + originResponseBytes)
		.add("originResponseTime=" + originResponseTime)
		.toString();
  }
}
