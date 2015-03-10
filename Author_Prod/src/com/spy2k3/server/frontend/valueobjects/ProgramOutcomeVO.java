package com.spy2k3.server.frontend.valueobjects;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties()
public class ProgramOutcomeVO {
	@JsonProperty
	private String name;
	@JsonProperty
	private String desc;
	@JsonProperty
	private String id;
	@JsonProperty
	private String json;
	@JsonProperty
	private String owner;
	
	public String getProperty(String key) throws NullPointerException{
		if (null == key ||key.isEmpty()) throw new NullPointerException("Argument is null");
		if(key.equalsIgnoreCase("id")) return id;
		if(key.equalsIgnoreCase("name")) return name;
		if(key.equalsIgnoreCase("desc")) return desc;
		if(key.equalsIgnoreCase("json")) return json;
		if(key.equalsIgnoreCase("owner")) return owner;
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
		if(property.equalsIgnoreCase("json")) {
			this.json =  value;
			return;
		}	
		if(property.equalsIgnoreCase("owner")) {
			this.json =  value;
			return;
		}
		throw new NullPointerException(" Property Not found");
	}
	
	public Iterable<String> getKeys(){
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("name");
		keys.add("desc");
		keys.add("id");
		keys.add("json");
		keys.add("owner");
		return keys;
	}
	
	
}