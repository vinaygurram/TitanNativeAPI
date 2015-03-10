package com.spy2k3.server.frontend.valueobjects;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties()
public class OutcomeVO {
	@JsonProperty
	private String name;
	@JsonProperty
	private String desc;
	@JsonProperty
	private String id;
	@JsonProperty
	private String pid;
	
	public String getProperty(String key) throws NullPointerException{
		if(key==null || key.isEmpty()) throw new NullPointerException();
		if(key.equalsIgnoreCase("id")) return id;
		if(key.equalsIgnoreCase("name")) return name;
		if(key.equalsIgnoreCase("desc")) return desc;
		if(key.equalsIgnoreCase("pid")) return pid;
		throw new NullPointerException("S Property Not found");
	}
	
	public void setProperty(String property, String value) throws NullPointerException{
		if(property==null || property.isEmpty()) throw new NullPointerException();
		if(property.equalsIgnoreCase("id")) {
			this.id =  value;
			return;
		}
		if(property.equalsIgnoreCase("name")) {
			this.name =  value;
			return;
		}
		if(property.equalsIgnoreCase("desc")) {
			this.desc =  value;
			return;
		}
		if(property.equalsIgnoreCase("pid")) {
			this.pid =  value;
			return;
		}
		
		throw new NullPointerException("S Property Not found");
	}
	
	public Iterable<String> getKeys(){
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("name");
		keys.add("desc");
		keys.add("id");
		keys.add("pid");
		return keys;
	}
}
