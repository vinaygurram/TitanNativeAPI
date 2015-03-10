package com.spy2k3.server.frontend.outcome;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.json.JSONObject;

import com.spy2k3.server.frontend.valueobjects.JsonRPCVO;
import com.spy2k3.server.frontend.valueobjects.OutcomeVO;
import com.spy2k3.server.frontend.valueobjects.SubOutcomeVO;

@CrossOriginResourceSharing(allowAllOrigins = true)
@WebService
@Path ("/outcome")
@InInterceptors(interceptors = "org.apache.cxf.interceptor.LoggingInInterceptor")
@OutInterceptors(interceptors = "org.apache.cxf.interceptor.LoggingOutInterceptor")
public class OutcomeRSBridge extends GenericRSBridge{
	
	public OutcomeRSBridge(){
		super.init();;
		init();
		
	}
	
	//private CBAPIInf cbapi;
	public void init(){
		/*if(cbapi!=null)
			setOutcomeApi();*/
	}
	/*
	private setCBAPI(){
		OutcomeAPIInf oapiInf=OutcomeAPIFactory.getOutcomeAPI();
		this.cbapi=oapiInf;
	}
	
	private CBAPI getCBAPI()
	{
		return cbapi;
	}*/
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public Response addOutcome(OutcomeVO input){		
		
		try {			
			//check for authorization
			System.out.println("sdfdsafdsafsadfsa");
			System.out.println("sdfsdafsadfw");
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).build();		 
			}
			
			//check for valid data object
			boolean isValidOutcome = checkIsValidJSONForPost(input);
			if(!isValidOutcome) {
				System.out.println("Validation failed");
				return Response.status(400).build();				 
			}
			
			//call the outcome api
			//String id = ((OutcomeAPIInf)getCBAPI()).addOutcome(input);
			String id =cbapi .addOutcome(input);

			if(id==null || id.isEmpty()){
				System.out.println("internal error");
				return Response.status(500).build();
			}else{
				return Response.status(200).entity(id).build();
			}						
		} catch(NullPointerException e1){
			//return Response.status(404).entity("Null pointer exception").header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST").header("Access-Control-Max-Age", "151200").header("Access-Control-Allow-Headers", "Content-Type,x-requested-with").build();
		}catch (Exception e) {
			e.printStackTrace();		
		}										
		return Response.status(404).build();
	}
	
	@GET
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")	
	public Response getOutcome(@PathParam("id") String id){
		
		try {
			
			JSONObject jo = new JSONObject(); 
			//check for authorization
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).entity(jo).build();		 
			}
			
			//pass the id to outcome apit
			
			jo = cbapi.getOutcome(id);
			if(null==jo){
				return Response.status(500).entity("{\"error\" : \"Outcome not found\"}").build();
			}else{
				return Response.status(200).entity(jo.toString()).build();
			}
			
		}catch(NullPointerException e1){
			return Response.status(404).entity("{\"error\" : \"Outcome not found\"}").build();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(404).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOutcome(String input){
		
		try {
			System.out.println("in the update method");
			//check for authorization
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).build();		 
			}
			//check for valid object
			int isValidJson = checkIsValidJSONForPut(input);
			if(isValidJson==-1) {
				System.out.println("Validation failed");
				return Response.status(400).build();				 
			}
			
			//pass the id to outcome api				
			boolean status =cbapi.updateOutcome(input);
			
			if(status){
				return Response.status(200).build();
			}else{
				return Response.status(500).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(404).build();
	}
	
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/{id}")	
	public Response deleteOutcome(@PathParam("id") String id){
		try {
			
			//check for authorization
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).build();		 
			}
			//pass the outcome to api and get status
			boolean status  = cbapi.deleteOutcome(id);
			if(status){
				return Response.status(200).build();
			}else{
				return Response.status(500).build();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return Response.status(404).build();
	}
	
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.TEXT_PLAIN)
	@Path("/jsonRPC")
	public Response executeJsonRPC(JsonRPCVO jo){
		try {
			System.out.println("hello jsonrpc");
			//check for authorization
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).build();		 
			}
			
			//pass the outcome to api and get status
			JSONObject status  = cbapi.jsonRPC(jo);
			System.out.println(status.toString());
			return Response.status(200).entity(status.toString()).build();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return Response.status(404).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST,OPTIONS").header("Access-Control-Max-Age", "151200").header("Access-Control-Allow-Headers", "Content-Type,x-requested-with").build();
	}
	
	@POST
	@Consumes (MediaType.APPLICATION_JSON) 
	@Produces (MediaType.TEXT_PLAIN)
	@Path("/suboutcome")
	public Response addSubOutcome(SubOutcomeVO jsonSubOutcome){
		try {
			//check for authorization
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).build();		 
			}
			
			//check for validation object
			boolean isValidJson = checkIsValidSuboutcome(jsonSubOutcome);
			if(!isValidJson){
				System.out.println("Not valid json");
				return Response.status(400).build();
			}
			
			//call the api and get the result
			String id = cbapi.addSubOutcome(jsonSubOutcome);
			
			if(id.isEmpty()) return Response.status(500).entity(id).build();
			return Response.status(200).entity(id).build();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return Response.status(404).build();
	}
	
	@DELETE
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.TEXT_PLAIN)
	@Path("/suboutcome")
	public Response deleteSubOutcome(SubOutcomeVO jsonSubOutcome){
		try {
			
			//check for authorization
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).build();		 
			}
			//check for validation object
			boolean isValidJson = checkIsValidSuboutcome(jsonSubOutcome);
			if(!isValidJson){
				System.out.println("Not valid json");
				return Response.status(400).build();
			}
			
			//post to the api and get status
			boolean status = cbapi.deleteSubOutcome(jsonSubOutcome);
			if(!status) return Response.status(500).build();
			return Response.status(200).build();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Response.status(404).build();
	}
	

}
