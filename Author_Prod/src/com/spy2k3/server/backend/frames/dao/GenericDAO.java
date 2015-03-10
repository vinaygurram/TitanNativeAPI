package com.spy2k3.server.backend.frames.dao;

import org.json.JSONObject;

import com.spy2k3.server.businesslayer.objects.GraphEdge;
import com.spy2k3.server.businesslayer.objects.GraphNode;
import com.spy2k3.server.businesslayer.objects.Constants.Nodetype;
import com.thinkaurelius.titan.core.TitanException;

public interface GenericDAO {
			
	public GraphNode addNode(GraphNode newNode) throws TitanException;
	
	public GraphNode getNode(String id, Nodetype type)  throws NullPointerException, TitanException;
	
	public boolean deleteNode(String id) throws TitanException;
	
	public GraphNode updateNode(GraphNode node) throws TitanException;
	
	public GraphEdge addEdge(GraphEdge newEdge)  throws TitanException;	
	
	public GraphEdge updateEdge(GraphEdge edge) throws TitanException;
	
	public GraphEdge getEdge(GraphEdge edge) throws TitanException;
	
	public boolean deleteEdge(GraphEdge edge) throws TitanException;
	/*public JSONObject getNodeGraph(String id,Nodetype type)  throws NullPointerException, TitanException;*/
	
	public JSONObject getMaps(String id);
}
