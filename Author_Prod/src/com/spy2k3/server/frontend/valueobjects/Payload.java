package com.spy2k3.server.frontend.valueobjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties
public class Payload{
	@JsonProperty
	public String id;
	@JsonProperty
	public String name;
	@JsonProperty
	public String pid;
	@JsonProperty
	public String operation;	
}
