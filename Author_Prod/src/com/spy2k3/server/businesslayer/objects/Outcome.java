package com.spy2k3.server.businesslayer.objects;

import org.json.JSONObject;

import com.spy2k3.server.businesslayer.objects.Constants.Nodetype;

public class Outcome extends GraphNode{
	
	//Fields
	private String desc;
	private String pid;
	
	private boolean isSetDesc = false;
	private boolean isSetPid = false;
	
	public Outcome(){
		this.nodetype = Nodetype.OUTCOME;
	}
	
	
	//Setters and Getters
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.isSetDesc = true;
		this.desc = desc;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.isSetPid = true;
		this.pid = pid;
	}

	public boolean isSetDesc() {
		return isSetDesc;
	}

	public void setSetDesc(boolean isSetDesc) {
		this.isSetDesc = isSetDesc;
	}


	public boolean isSetPid() {
		return isSetPid;
	}


	public void setSetPid(boolean isSetPid) {
		this.isSetPid = isSetPid;
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
			jo.put("pid", pid);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return jo;
	}
	
	
}
