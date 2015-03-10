package com.spy2k3.server.frontend.valueobjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestObject{
	@JsonProperty
	public String method;
	@JsonProperty
	public String path;
	@JsonProperty
	public Payload payload;
}

