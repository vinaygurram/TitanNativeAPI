package com.spy2k3.server.businesslayer;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.spy2k3.server.businesslayer.objects.Constants.Nodetype;
import com.spy2k3.server.businesslayer.objects.GraphEdge;
import com.spy2k3.server.businesslayer.objects.Outcome;
import com.spy2k3.server.businesslayer.objects.ProgramOutcome;
import com.spy2k3.server.businesslayer.objects.Sub;
import com.spy2k3.server.frontend.valueobjects.JsonRPCVO;
import com.spy2k3.server.frontend.valueobjects.OutcomeVO;
import com.spy2k3.server.frontend.valueobjects.ProgramOutcomeVO;
import com.spy2k3.server.frontend.valueobjects.RequestObject;
import com.spy2k3.server.frontend.valueobjects.SubOutcomeVO;

/**
 * Created by Vinay on 11/7/14.
 */
public class CbapiImpl extends Cbapi{
	
	public CbapiImpl(){
		super.init();
		init();
		
	}
	
	public void init(){};

    public String addOutcome(OutcomeVO json) throws NullPointerException{
        try{
        	//create business outcome and set properties
        	Outcome node = new Outcome();
        	node.setId(json.getProperty("id"));
        	node.setDesc(json.getProperty("desc"));
        	node.setName(json.getProperty("name"));
        	node.setPid(json.getProperty("pid"));        	        	
        	
        	//pass the outcome to frames dao 
            Outcome ot = (Outcome)daoManager.getOutComeDAO().addNode(node);
            return ot.getId();
        }catch (NullPointerException e1){
        	e1.printStackTrace();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return null;
    }
    public String addProgramOutcome(ProgramOutcomeVO json) throws NullPointerException{
        try{
        	//create business outcome
        	ProgramOutcome pvo = new ProgramOutcome(); 
        	pvo.setName( json.getProperty("name"));
        	pvo.setDesc(json.getProperty("desc"));
        	pvo.setJson(json.getProperty("json"));
        	pvo.setOwner(json.getProperty("owner"));
        	System.out.println("owner is "+ json.getProperty("owner"));
        	
            ProgramOutcome pot = (ProgramOutcome)daoManager.getOutComeDAO().addNode(pvo);
            return pot.getId();
        }catch (NullPointerException e1){
        	e1.printStackTrace();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return null;
    }
    

    public JSONObject getOutcome(String id){
        JSONObject jo = null;
        try{        	              
            Outcome ot = (Outcome) daoManager.getOutComeDAO().getNode(id,Nodetype.OUTCOME);            
            jo = new JSONObject();
            jo.put("outcome",ot.getJSONObject());
        }catch (NullPointerException n1){
        	throw n1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo;
    }
    
    public JSONObject getProgramOutcome(String id){
        JSONObject jo = null;
        try{        	              
            ProgramOutcome pot = (ProgramOutcome) daoManager.getOutComeDAO().getNode(id,Nodetype.PROGRAME_OUTCOME);            
            jo = new JSONObject();
            jo.put("programoutcome",pot.getJSONObject());
        }catch (NullPointerException n1){
        	throw n1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return jo;
    }
    
    public JSONObject getMaps(String id){
    	return daoManager.getOutComeDAO().getMaps(id);
    }

    /*public boolean updateOutcome(OutcomeVO jo){
    	boolean status = false;
    	try {
    		//create a graph Node 
    		GraphNode outcome = new GenericOutcome();
        	Iterable<String> keys = jo.getKeys();
        	Iterator<String> it = keys.iterator();
        	while(it.hasNext()){
        		String property = it.next();
        		outcome.setProperty(property, jo.getProperty(property));
        	}
			GenericOutcome ro = (GenericOutcome) daoManager.getOutComeDAO().updateNode(outcome, Nodetype.OUTCOME);
			if(ro!=null) status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return status;    	
    }*/
    
    public boolean updateOutcome(String ss){
    	boolean status = false;
    	try {
    		//create a graph Node     		
    		
    		JSONObject jo = new JSONObject(ss);
    		Outcome ot = new Outcome();
    		if(jo.has("id")) ot.setId(jo.getString("id"));
    		if(jo.has("pid")) ot.setPid(jo.getString("pid"));
    		if(jo.has("name") && !jo.getString("name").isEmpty()) {
    			ot.setName(jo.getString("name"));
    		}else{
    			return false;
    		}
    		if(jo.has("desc") && !jo.getString("desc").isEmpty()) {
    			ot.setDesc(jo.getString("desc"));    		    		
    		}else{
    			return false;
    		}
			Outcome ro = (Outcome) daoManager.getOutComeDAO().updateNode(ot);
			JSONObject jod = ro.getJSONObject();
			System.out.println("this is "+ jod.toString());
			if(ro!=null) status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return status;    	
    }
    
    public boolean updateProgramOutcome(String ss){
    	boolean status = false;
    	try {
    		//create a graph Node     		
    		
    		JSONObject jo = new JSONObject(ss);
    		System.out.println("jo is "+jo.toString());
    		
    		ProgramOutcome ot = new ProgramOutcome();
    		if(jo.has("id")) ot.setId(jo.getString("id"));
    		if(jo.has("json")) ot.setJson(jo.getString("json"));
    		if(jo.has("name") && !jo.getString("name").isEmpty()) {
    			ot.setName(jo.getString("name"));
    		}else{
    			return false;
    		}
    		if(jo.has("desc") && !jo.getString("desc").isEmpty()) {
    			ot.setDesc(jo.getString("desc"));
    		}else{
    			return false;
    		}
    		if(jo.has("owner")) ot.setOwner(jo.getString("owner"));  
			ProgramOutcome ro = (ProgramOutcome) daoManager.getOutComeDAO().updateNode(ot);
			if(ro!=null) status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return status;    	
    }
    
/*    public boolean updateProgramOutcome(ProgramOutcomeVO jo){
    	boolean status = false;
    	try {
    		//create a graph Node 
    		GraphNode outcome = new GenericOutcome();
        	Iterable<String> keys = jo.getKeys();
        	Iterator<String> it = keys.iterator();
        	while(it.hasNext()){
        		String property = it.next();
        		outcome.setProperty(property, jo.getProperty(property));
        	}
    		outcome.setProperty("id", jo.getProperty("id"));
    		outcome.setProperty("name", jo.getProperty("name"));
        	outcome.setProperty("desc", jo.getProperty("desc"));
        	outcome.setProperty("json", jo.getProperty("json"));
			GenericOutcome ro = (GenericOutcome) daoManager.getOutComeDAO().updateNode(outcome, Nodetype.OUTCOME);
			if(ro!=null) status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return status;    	
    }*/
    
    
    public JSONObject jsonRPC(JsonRPCVO jo){
    	
    	try {
    		HashMap<String, String> idMap= new HashMap<String, String>();
    		
			RequestObject[] rc = jo.requests;
			
			JSONObject retunjson = new JSONObject();			
			JSONArray jsonarray = new JSONArray();
			retunjson.put("requests", jsonarray);
			
			for(RequestObject ro : rc){
				if(ro.method.equalsIgnoreCase("POST")){
					if(ro.path.equalsIgnoreCase("outcome")){
						//create outcome
						OutcomeVO ovo = new OutcomeVO();
						ovo.setProperty("id", ro.payload.id);
						if(ro.payload.name.isEmpty()){
							JSONObject jot = new JSONObject();
							jot.put( ro.payload.id,"");
							jot.put( "pid",ovo.getProperty("pid"));
							jot.put( "operation","create");
							jsonarray.put(jot);	
							jsonarray.getClass();
						}else{
							ovo.setProperty("name", ro.payload.name);
							ovo.setProperty("desc", "this is a test");
							ovo.setProperty("pid", ro.payload.pid);
							if(idMap.containsKey(ro.payload.pid)){
								ovo.setProperty("pid", idMap.get(ro.payload.pid));
							}
							String id = addOutcome(ovo);						
													
							//create edge
							//check for original id
												
							SubOutcomeVO svo = new SubOutcomeVO();
							svo.setProperty("id", id);
							if(idMap.containsKey(ro.payload.pid)){
								System.out.println("pid is" + idMap.get(ro.payload.pid));
								svo.setProperty("pid", idMap.get(ro.payload.pid));
							}else{
								svo.setProperty("pid", ro.payload.pid);
							}						
							addSubOutcome(svo);
							
							JSONObject jot = new JSONObject();
							jot.put( ro.payload.id,id);
							jot.put( "pid",ovo.getProperty("pid"));
							jot.put( "operation","create");
							jsonarray.put(jot);	
							jsonarray.getClass();
							idMap.put(ro.payload.id, id);
						}
						
						
					}
				}else if(ro.method.equalsIgnoreCase("PUT")){									
					if(ro.path.equalsIgnoreCase("outcome")){
						if(ro.payload.operation.equalsIgnoreCase("move")){
							
							SubOutcomeVO ovo = new SubOutcomeVO();
							ovo.setProperty("id", ro.payload.id);
							ovo.setProperty("pid", ro.payload.pid);
							
							
							//also updating the name *****change
							/*Outcome ot = new Outcome();
							ot.setId(ro.payload.id);
							ot.setName(ro.payload.name);*/
							
							
							//also updating the name *****change
							
							if(idMap.containsKey(ovo.getProperty("id"))){
								ovo.setProperty("id", idMap.get(ovo.getProperty("id")));
							}
							if(idMap.containsKey(ovo.getProperty("pid"))){
								ovo.setProperty("pid", idMap.get(ovo.getProperty("pid")));
							}
							deleteSubOutcome(ovo);
							String id = addSubOutcome(ovo);
							
							JSONObject jot = new JSONObject();
							jot.put( ro.payload.id,id);
							jot.put( "pid",ovo.getProperty("pid"));
							jot.put( "operation","move");
							jsonarray.put(jot);	
							
						}else if(ro.payload.operation.equalsIgnoreCase("edit")){
							/*OutcomeVO ovo = new OutcomeVO();
							ovo.setProperty("id", ro.payload.id);
							ovo.setProperty("name", ro.payload.name);*/
							
							Outcome ot = new Outcome();
							
							ot.setId(ro.payload.id);
							if(ro.payload.name.isEmpty()) {
								JSONObject jot = new JSONObject();
								jot.put( ro.payload.id,false);
								jot.put( "pid",ot.getPid());
								jot.put( "operation","edit");
								jsonarray.put(jot);
							}else{
								ot.setName(ro.payload.name);
								
								if(idMap.containsKey(ot.getId())){
									//ovo.setProperty("id", idMap.get(ovo.getProperty("id")));
									ot.setId( idMap.get(ot.getId()));
								}
								boolean status = updateOutcome(ot.getJSONObject().toString());
								JSONObject jot = new JSONObject();
								jot.put( ro.payload.id,status);
								jot.put( "pid",ot.getPid());
								jot.put( "operation","edit");
								jsonarray.put(jot);
							}
							
						}
																		
						/*if(idMap.containsKey(ovo.getProperty("id"))){
							ovo.setProperty("id", idMap.get(ovo.getProperty("id")));
						}
						boolean id = updateOutcome(ovo);
						JSONObject jot = new JSONObject();
						jot.put(id +" ", ovo.getProperty("id"));
						jsonarray.put(jot);		*/										
					}
				}else if(ro.method.equalsIgnoreCase("DELETE")){
					if(ro.path.equalsIgnoreCase("outcome")){
						String id = ro.payload.id;
						boolean status = deleteOutcome(id);
						JSONObject jot = new JSONObject();
						
						jot.put(id , status);
						jot.put("operation" , "delete");
						jsonarray.put(jot);												
					}
				}
			}
			return retunjson;
		} catch (Exception e) {
			e.printStackTrace();			
		}    	
    	return null;
    }

    public boolean deleteOutcome(String id){    	    	
    	try {    		    		
    		return daoManager.getOutComeDAO().deleteNode(id);
		} catch (Exception e) {
			e.printStackTrace();
		}    	    
        return false;
    }

    /**
     * this method throws ChildNotFoundException, ParentNotFoundException, AlreadyConnectedException ,Exception
     * @param child child outcome to be added
     * @param parent parent of the child outcome
     */
    public String addSubOutcome(SubOutcomeVO jsonSubOutcome){
        String edgeId="";
    	try {
    		GraphEdge edge = new Sub();
    		edge.setProperty("inNodeid", jsonSubOutcome.getProperty("id"));
    		edge.setProperty("outNodeid", jsonSubOutcome.getProperty("pid"));
    		edge.setProperty("label", "sub");
    		//edge
			edge =  daoManager.getOutComeDAO().addEdge(edge);
			return edge.getProperty("id");
		} catch (Exception e) {
			e.printStackTrace();			
		}
    	return edgeId;
    }
    
    
    
	@Override
	public boolean deleteSubOutcome(SubOutcomeVO jsonSubOutcome) {
		try {
			GraphEdge edge = new Sub();
    		edge.setProperty("inNodeid", jsonSubOutcome.getProperty("id"));
    		edge.setProperty("outNodeid", jsonSubOutcome.getProperty("pid"));
    		edge.setProperty("label", "sub");
    		return daoManager.getOutComeDAO().deleteEdge(edge);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
