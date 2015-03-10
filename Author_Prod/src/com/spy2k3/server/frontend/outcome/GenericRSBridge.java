package com.spy2k3.server.frontend.outcome;

import org.json.JSONObject;

import com.spy2k3.server.frontend.valueobjects.OutcomeVO;
import com.spy2k3.server.frontend.valueobjects.SubOutcomeVO;

public abstract class GenericRSBridge extends OutcomeAPI{
	
	public GenericRSBridge(){
	}
	
	public void init(){
		super.init();
	}	
	
	//utility methods
	
	public boolean checkIsOutcome(String type, String service){
		if (type.contentEquals("outcome") && service.contentEquals("author")) return true;
		return false;
	}
	
	public boolean checkIsValidJSONForPost(OutcomeVO jo)throws NullPointerException{		
		if(null == jo.getProperty("name")|| jo.getProperty("name").isEmpty()) throw new NullPointerException("name is empty");
		if(null == jo.getProperty("desc")|| jo.getProperty("desc").isEmpty()) throw new NullPointerException("desc is null");
		return true;
	}
	
	public int checkIsValidJSONForPut(String jo){		
		try {
			JSONObject jm = new JSONObject(jo);
			if(null==jo) return 0;
			if(jm.get("id")==null) return -1;
			if(null == jm.get("name") || null==jm.get("desc")) return 1;
			return 2;					
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return 0;		
	}
	
	public boolean checkIsValidSuboutcome(SubOutcomeVO jsonSubOutcome){
		
		try {
			if(jsonSubOutcome==null || jsonSubOutcome.getProperty("id").isEmpty() 
					|| jsonSubOutcome.getProperty("pid").isEmpty()) return false;
			return true;
		} catch (Exception e) {			
			e.printStackTrace();			
		}
		return false;
	}
}
