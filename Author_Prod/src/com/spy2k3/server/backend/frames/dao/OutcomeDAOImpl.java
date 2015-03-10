package com.spy2k3.server.backend.frames.dao;

import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.spy2k3.server.businesslayer.objects.Constants.Edgetype;
import com.spy2k3.server.businesslayer.objects.Constants.Nodetype;
import com.spy2k3.server.businesslayer.objects.GraphEdge;
import com.spy2k3.server.businesslayer.objects.GraphNode;
import com.spy2k3.server.businesslayer.objects.Outcome;
import com.spy2k3.server.businesslayer.objects.ProgramOutcome;
import com.thinkaurelius.titan.core.TitanException;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

public class OutcomeDAOImpl extends OutcomeDAO{
	
	public OutcomeDAOImpl(DBConnection connection){
		super(connection);
	}

	@Override
	public GraphNode addNode(GraphNode newNode)  throws TitanException{
        try{
        	//create a empty vertex
        	Vertex vertex = dbconnection.getTitanGraph().addVertex(null);
        	
        	//set properties
        	if(newNode.getNodetype().equals(Nodetype.OUTCOME)){        		
        		Outcome node = (Outcome) newNode;        		
        		vertex.setProperty("name", node.getName());
        		vertex.setProperty("desc", node.getDesc());
        		vertex.setProperty("pid", node.getPid());
        		vertex.setProperty("created_at", System.currentTimeMillis());
        		vertex.setProperty("updated_at", System.currentTimeMillis());
        		
        	}else if (newNode.getNodetype().equals(Nodetype.PROGRAME_OUTCOME)){
        		
        		
        		System.out.println("com eher with out errors");
        		ProgramOutcome node = (ProgramOutcome) newNode;
        		vertex.setProperty("name", node.getName());
        		vertex.setProperty("desc", node.getDesc());
        		vertex.setProperty("json", node.getJson());
        		vertex.setProperty("owner", node.getOwner());
        		vertex.setProperty("created_at", System.currentTimeMillis());
        		vertex.setProperty("updated_at", System.currentTimeMillis());
        		
        	}
        	
        	//commit        	
            dbconnection.getTitanGraph().commit();
            
            
            //make return object
            
            if (newNode.getNodetype() ==Nodetype.OUTCOME){
            	Outcome returnNode  = new Outcome();
            	returnNode.setId(String.valueOf(vertex.getId()));
            	 
            	returnNode.setName( String.valueOf(vertex.getProperty("name")));
            	returnNode.setDesc( String.valueOf(vertex.getProperty("desc")));
            	returnNode.setPid( String.valueOf(vertex.getProperty("pid")));  
            	returnNode.setCreated_at( String.valueOf(vertex.getProperty("created_at")));
            	returnNode.setUpdated_at( String.valueOf(vertex.getProperty("updated_at")));
            	return returnNode;
            }
            else if(newNode.getNodetype() == Nodetype.PROGRAME_OUTCOME){
            	ProgramOutcome returnNode  = new ProgramOutcome();
            	returnNode.setId(String.valueOf(vertex.getId()));
            	returnNode.setName( String.valueOf(vertex.getProperty("name")));
            	returnNode.setDesc( String.valueOf(vertex.getProperty("desc")));
            	returnNode.setJson( String.valueOf(vertex.getProperty("json")));
            	returnNode.setOwner( String.valueOf(vertex.getProperty("owner"))); 
            	returnNode.setCreated_at( String.valueOf(vertex.getProperty("created_at")));
            	returnNode.setUpdated_at( String.valueOf(vertex.getProperty("updated_at")));
            	return returnNode;
            }            
            return null;

        }catch(TitanException e){        	
        	e.printStackTrace();
        }catch(Exception e1){
        	e1.printStackTrace();
        }
        return null;
	}

	@Override
	public GraphNode getNode (String id, Nodetype type)  throws NullPointerException, TitanException{
		try {  
        	if(type.equals(Nodetype.OUTCOME)){
        		OutcomeFrame vt =  (OutcomeFrame)dbconnection.getFramedGraph().getVertex(id, OutcomeFrame.class);
        		if(vt==null) throw new NullPointerException("Node not found");
        		Outcome node = new Outcome();
        		node.setId(id);
        		if(vt.getName()!=null)node.setName(vt.getName());
        		if(vt.getDesc()!=null)node.setDesc(vt.getDesc());
        		if(vt.getPid()!=null)node.setPid( vt.getPid());
        		
        		node.setCreated_at(String.valueOf(vt.getCreated()));
        		node.setUpdated_at(String.valueOf(vt.getUpdated()));
        		return node;
        		
        	}else if(type.equals(Nodetype.PROGRAME_OUTCOME)){
        		OutcomeFrame vt =  (OutcomeFrame)dbconnection.getFramedGraph().getVertex(id, OutcomeFrame.class);
        		if(vt==null) throw new NullPointerException("Node not found");
        		ProgramOutcome node = new ProgramOutcome();
        		node.setId(id);
        		if(vt.getName()!=null)node.setName(vt.getName());
        		if(vt.getDesc()!=null)node.setDesc(vt.getDesc());
        		if(vt.getJson()!=null)node.setJson( vt.getJson());
        		if(vt.getOwner()!=null)node.setOwner( vt.getOwner());
        		node.setCreated_at(String.valueOf(vt.getCreated()));
        		node.setUpdated_at(String.valueOf(vt.getUpdated()));
        		return node;
        	}
        	return null;
        }catch(TitanException e1){
        	e1.printStackTrace();        	
        }catch (Exception e){
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public boolean deleteNode(String id)  throws TitanException{
		boolean status = false;
    	try {
    		Vertex v = dbconnection.getFramedGraph().getVertex(id);
    		
    		System.out.println("Vertex is "+v.getProperty("name"));
        	dbconnection.getTitanGraph().removeVertex(v);
        	dbconnection.getTitanGraph().commit();        	
        	status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return status;
	}

	@Override
	public GraphNode updateNode(GraphNode node)  throws TitanException{
		try {
        	//Get the id and then get the object
			String id = node.getId();        	
        	OutcomeFrame fot =  (OutcomeFrame)dbconnection.getFramedGraph().getVertex(id, OutcomeFrame.class);
            if(fot==null){
            	System.out.println("No outcome found");
            	return null;
            }
			
			if(node.getNodetype().equals(Nodetype.OUTCOME)){
				Outcome outcome = (Outcome) node;
				
	        	if(outcome.isSetDesc())  fot.setDesc(outcome.getDesc());        	
	        	if(outcome.isSetName()) fot.setName(outcome.getName());
	        	if(outcome.isSetPid()) fot.setJson(outcome.getPid());
	        	long l = System.currentTimeMillis();
	        	fot.setUpdated(l);
	        	
	        	dbconnection.getTitanGraph().commit();
	        	
	        	outcome.setDesc(fot.getDesc());
	        	outcome.setName(fot.getName());  
	        	outcome.setCreated_at(String.valueOf(fot.getCreated()));
	        	outcome.setPid(fot.getPid());
	        	outcome.setUpdated_at(String.valueOf(fot.getUpdated()));
	        	return outcome;
	        	
			}else if(node.getNodetype().equals(Nodetype.PROGRAME_OUTCOME)){
				ProgramOutcome outcome = (ProgramOutcome) node;
				
	        	if(outcome.isSetDesc())  fot.setDesc(outcome.getDesc());        	
	        	if(outcome.isSetName()) fot.setName(outcome.getName());
	        	if(outcome.isSetJson()) fot.setJson(outcome.getJson());
	        	if(outcome.isSetOwner()) fot.setOwner(outcome.getOwner());
	        	long p = System.currentTimeMillis();
	        	fot.setUpdated(p);
	        	
	        	dbconnection.getTitanGraph().commit();
	        	
	        	outcome.setDesc(fot.getDesc());
	        	outcome.setName(fot.getName());  	        	
	        	outcome.setJson(fot.getJson());
	        	outcome.setOwner(fot.getOwner());
	        	outcome.setCreated_at(String.valueOf(fot.getCreated()));
	        	outcome.setUpdated_at(String.valueOf(fot.getUpdated()));
	        	return outcome;
			}
        	
		} catch (Exception e) {
			e.printStackTrace();
		}    	    	    	
    	return null;
	}
	

	@Override
	public GraphEdge addEdge(GraphEdge edge) throws TitanException {
		try {			
			if(edge.edgetype.equals(Edgetype.SUB)){
				//get the parameters
				String fid = edge.getProperty("outNodeid");
				String tid  = edge.getProperty("inNodeid");
				String label = edge.getProperty("label");
				
				edge.setProperty("id","");;
				
				//get the vertices from db and add the edge
				Vertex fVertx = dbconnection.getFramedGraph().getVertex(fid);
				Vertex tVertx = dbconnection.getFramedGraph().getVertex(tid);
				
				if(fVertx==null || tVertx ==null) {
					System.out.println("one of the vertex is null");
					return null;
				}
				com.tinkerpop.blueprints.Edge titanEdge = dbconnection.getTitanGraph().addEdge(null, fVertx, tVertx, label);
				edge.setProperty("id",String.valueOf(titanEdge.getId()));
				dbconnection.getTitanGraph().commit();
				return edge;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@Override
	public boolean deleteEdge(GraphEdge edge) throws TitanException {
		try {			
			if(edge.edgetype.equals(Edgetype.SUB)){
				//get the parameters
				String fid = edge.getProperty("outNodeid");
				String tid  = edge.getProperty("inNodeid");
				String label = edge.getProperty("label");					
				
				//get the vertices from db and add the edge
				Vertex fVertx = dbconnection.getFramedGraph().getVertex(fid);
				Vertex tVertx = dbconnection.getFramedGraph().getVertex(tid);
				String tVertxId = String.valueOf(tVertx.getId());
				if(fVertx==null || tVertx ==null) {
					System.out.println("one of txhe vert");
					return false;
				}
				Iterable<com.tinkerpop.blueprints.Edge> edges = fVertx.getEdges(Direction.OUT, label);
				Iterator<com.tinkerpop.blueprints.Edge> it = edges.iterator();
				
				while(it.hasNext()){
					com.tinkerpop.blueprints.Edge tempEdge = it.next();
					String tempId = String.valueOf(tempEdge.getVertex(Direction.IN).getId());
					if(tempId.contentEquals(tVertxId)){
						dbconnection.getTitanGraph().removeEdge(tempEdge);
						dbconnection.getTitanGraph().commit();
						return true;
					}					
				}
			}else{
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}*/
	@Override
	public boolean deleteEdge(GraphEdge edge) throws TitanException {
		try {			
			if(edge.edgetype.equals(Edgetype.SUB)){
				//get the parameters
				
				String tid  = edge.getProperty("inNodeid");
				String label = edge.getProperty("label");					
				
				//get the vertices from db and add the edge
				Vertex tVertx = dbconnection.getFramedGraph().getVertex(tid);
				if( tVertx.equals(null)) {
					System.out.println("vertex does not exist");
					return false;
				}
				Iterable<com.tinkerpop.blueprints.Edge> edges = tVertx.getEdges(Direction.IN, label);
				Iterator<com.tinkerpop.blueprints.Edge> it = edges.iterator();
				
				while(it.hasNext()){
					com.tinkerpop.blueprints.Edge tempEdge = it.next();					
					dbconnection.getTitanGraph().removeEdge(tempEdge);
					dbconnection.getTitanGraph().commit();
					return true;					
				}
			}else{
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
/*
	@Override
	public boolean deleteSubOutcome(Sub edge) {
		try {
			//get the parameters
			String fid = edge.getFid();
			String tid  = edge.getTid();
			String label = edge.getLabel();					
			
			//get the vertices from db and add the edge
			Vertex fVertx = dbconnection.getFramedGraph().getVertex(fid);
			Vertex tVertx = dbconnection.getFramedGraph().getVertex(tid);
			if(fVertx==null || tVertx ==null) {
				System.out.println("one of txhe vert");
				return false;
			}
								
			Iterable<com.tinkerpop.blueprints.Edge> edges = fVertx.getEdges(Direction.OUT, label);
			Iterator<com.tinkerpop.blueprints.Edge> it = edges.iterator();
			
			while(it.hasNext()){
				com.tinkerpop.blueprints.Edge tempEdge = it.next();
				if(tempEdge.getVertex(Direction.OUT).equals(tVertx)){
					dbconnection.getTitanGraph().removeEdge(tempEdge);
					dbconnection.getTitanGraph().commit();
					return true;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Sub addPermissions(Sub edge) {
		// TODO Auto-generated method stub
		return null;
	}*/



	@Override
	public GraphEdge updateEdge(GraphEdge edge) throws TitanException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphEdge getEdge(GraphEdge edge) throws TitanException {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public JSONObject getNodeGraph (String id, Nodetype type){
		try {  
        	if(type.equals(Nodetype.OUTCOME)){
        		
        		OutcomeFrame vt =  (OutcomeFrame)dbconnection.getFramedGraph().getVertex(id, OutcomeFrame.class);
        		Vertex titanV = dbconnection.getTitanGraph().getVertex(vt);
        		if(titanV==null) throw new NullPointerException("Node not found");
        		return getJsonData(titanV);
        		
        	}else if(type.equals(Nodetype.PERSON)){
        		
        	}   
            return null;
        }catch(TitanException e1){
        	e1.printStackTrace();
        	throw e1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
	}*/
	
/*	public JSONObject getJsonData(Vertex v) throws JSONException{
		if(v.equals(null)) return new JSONObject();
		
		JSONObject job = new JSONObject();
		job.put("name", v.getProperty("name"));
		job.put("desc", v.getProperty("name"));
		
		//get children
		Iterable<com.tinkerpop.blueprints.Edge> edges = v.getEdges(Direction.OUT, "sub");
		Iterator<com.tinkerpop.blueprints.Edge> it = edges.iterator();
		JSONArray ja = new JSONArray();
		while(it.hasNext()){
			com.tinkerpop.blueprints.Edge e = it.next();
			Vertex child = e.getVertex(Direction.IN);
			ja.put(getJsonData(child));
		}
		if(ja!=null) job.put("children", ja);
		return job;
	}*/
	
	
	public JSONObject getMaps(String id){
		if(id.equals(null)) return new JSONObject();
		try {
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();
			jo.put("vertices", ja);
			Iterable<Vertex> it = dbconnection.getTitanGraph().getVertices("owner", id);
			
			
			System.out.println("id is"+ "  "+id+" "+it.toString());
			Iterator<Vertex> its = it.iterator();
			
			while(its.hasNext()){
				Vertex v = its.next();
				JSONObject jb = new JSONObject();
				try {
					Set<String> dd = v.getPropertyKeys();
					Iterator<String> ip = dd.iterator();
					while(ip.hasNext()){
						String key = ip.next();
						jb.put(key, v.getProperty(key));
					}
					jb.put("id", v.getId());
					System.out.println(v.toString());
					ja.put(jb);
					
				} catch (Exception e) {
					// TODO: handle exception
				}							
			}
			return jo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
