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

import com.spy2k3.server.frontend.valueobjects.ProgramOutcomeVO;

@Path ("/programoutcome")
@CrossOriginResourceSharing(allowAllOrigins = true)
@WebService
@InInterceptors(interceptors = "org.apache.cxf.interceptor.LoggingInInterceptor")
@OutInterceptors(interceptors = "org.apache.cxf.interceptor.LoggingOutInterceptor")
public class ProgramOutcomeRSBridge extends GenericRSBridge{
	
	public ProgramOutcomeRSBridge(){
		super.init();;
		init();
		
	}
	public void init(){
	}
	
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public Response addOutcome(ProgramOutcomeVO pvo){		
		
		try {			
			//check for authorization
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).build();		 
			}
			
			//call the outcome api
			String id =cbapi.addProgramOutcome(pvo);

			if(id==null || id.isEmpty()){
				System.out.println("Internal Error");
				return Response.status(404).build();
			}else{
				return Response.status(200).entity(id).build();
			}						
		} catch(NullPointerException e1){
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
			
			
			jo = cbapi.getProgramOutcome(id);
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
	
	@GET
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/maplist/{id}")	
	public Response getMaplist(@PathParam("id") String id){
		
		try {
			
			JSONObject jo = new JSONObject(); 
			//check for authorization
			boolean outcomecheck = checkIsOutcome(type, service);
			if(outcomecheck==false) {
				System.out.println("Authorization failed");
				return Response.status(401).entity(jo).build();		 
			}
			
			//pass the id to outcome apit
			
			
			jo = cbapi.getMaps(id);
			if(null==jo){
				return Response.status(200).entity("{\"error\" : \"Outcome not found\"}").build();
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
			
			System.out.println("input is "+input);
			
			//pass the id to outcome api				
			boolean status =cbapi.updateProgramOutcome(input);
			
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

	
	

}
