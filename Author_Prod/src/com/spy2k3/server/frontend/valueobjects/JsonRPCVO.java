package com.spy2k3.server.frontend.valueobjects;

import org.codehaus.jackson.annotate.JsonProperty;

@org.codehaus.jackson.annotate.JsonIgnoreProperties
public class JsonRPCVO {
	@JsonProperty
	public RequestObject[] requests;
}
