package com.spy2k3.server.businesslayer.objects;

import com.spy2k3.server.businesslayer.objects.Constants.Nodetype;

public abstract class GraphNode {
	protected Nodetype nodetype;
	protected String id;
	protected String name;
	protected boolean isSetName = false;
	
	protected String created_at;
	protected String updated_at;
	
	public Nodetype getNodetype(){
		return this.nodetype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.isSetName = true;
		this.name = name;
	}

	public boolean isSetName() {
		return isSetName;
	}

	public void setSetName(boolean isSetName) {
		this.isSetName = isSetName;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
}
