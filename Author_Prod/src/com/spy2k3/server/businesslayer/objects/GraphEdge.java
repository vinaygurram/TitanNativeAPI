package com.spy2k3.server.businesslayer.objects;

import java.util.ArrayList;

import com.spy2k3.server.businesslayer.objects.Constants.Edgetype;

public abstract class GraphEdge {
	public Edgetype edgetype;
	protected String id;
	protected String label;
	protected String inNodeid;
	protected String outNodeid;
	
	public Iterable<String> getKeys(){
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("id");
		keys.add("label");
		keys.add("inNodeid");
		keys.add("outNodeid");
		return keys;
	}
	
	public String getProperty(String property) throws NullPointerException{
		if(property==null || property.isEmpty()) throw new NullPointerException();
		if(property.contentEquals("id")) return id;
		if(property.contentEquals("label")) return label;
		if(property.contentEquals("inNodeid")) return inNodeid;
		if(property.contentEquals("outNodeid")) return outNodeid;
		throw new NullPointerException("S Property Not found");
	}
	public void setProperty(String property, String value) throws NullPointerException{
		if(property==null || property.isEmpty()) throw new NullPointerException();
		if(property.contentEquals("id")) {
			this.id =  value;
			return;
		}
		if(property.contentEquals("label")) {
			this.label =  value;
			return;
		}
		if(property.contentEquals("inNodeid")) {
			this.inNodeid =  value;
			return;
		}
		if(property.contentEquals("outNodeid")) {
			this.outNodeid =  value;
			return;
		}
		throw new NullPointerException("S Property Not found");
	}
}