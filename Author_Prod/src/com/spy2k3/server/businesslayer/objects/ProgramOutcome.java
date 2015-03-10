package com.spy2k3.server.businesslayer.objects;

import org.json.JSONObject;

public class ProgramOutcome extends GraphNode{
	
	//Fields
	public String desc;
	public String json;
	public String owner;
	
	private boolean isSetDesc;
	private boolean isSetJson;
	private boolean isSetOwner;
	
	public ProgramOutcome(){
		this.nodetype = nodetype.PROGRAME_OUTCOME;
	}
	
	//Getters and Setters
	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.isSetDesc = true;
		this.desc = desc;
	}


	public String getJson() {
		return json;
	}


	public void setJson(String json) {
		this.isSetJson = true;
		this.json = json;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.isSetOwner = true;
		this.owner = owner;
	}


	public boolean isSetDesc() {
		return isSetDesc;
	}


	public void setSetDesc(boolean isSetDesc) {
		this.isSetDesc = isSetDesc;
	}


	public boolean isSetJson() {
		return isSetJson;
	}


	public void setSetJson(boolean isSetJson) {
		this.isSetJson = isSetJson;
	}


	public boolean isSetOwner() {
		return isSetOwner;
	}


	public void setSetOwner(boolean isSetOwner) {
		this.isSetOwner = isSetOwner;
	}
	
	public JSONObject getJSONObject(){
		JSONObject jo =null;
		
		try {			
			jo= new JSONObject();
			jo.put("id", id);
			jo.put("name", name);
			jo.put("created_at", created_at);
			jo.put("updated_at", updated_at);
			jo.put("desc", desc);
			jo.put("json", json);
			jo.put("owner", owner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jo;
	}
}
