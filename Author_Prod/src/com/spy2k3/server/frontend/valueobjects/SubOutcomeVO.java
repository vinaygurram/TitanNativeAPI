package com.spy2k3.server.frontend.valueobjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties
public class SubOutcomeVO {
	@JsonProperty
	private String pid;
	@JsonProperty
	private String id;
	
	public String getProperty(String key){
		if (null == key ||key.isEmpty()) throw new NullPointerException("Key is empty");
		if (key.contentEquals("id")) return id;
		if (key.contentEquals("pid")) return pid;
		throw new NullPointerException("S Property Not found");
	}
	
	public void setProperty(String property, String value) throws NullPointerException{
		if(property==null || property.isEmpty()) throw new NullPointerException();
		if(property.contentEquals("id")) {
			this.id =  value;
			return;
		}
		if(property.contentEquals("pid")) {
			this.pid =  value;
			return;
		}
		throw new NullPointerException("Property Not found");
	}
}
