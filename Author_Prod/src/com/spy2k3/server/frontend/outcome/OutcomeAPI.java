/**
 * 
 */
package com.spy2k3.server.frontend.outcome;


import com.spy2k3.server.businesslayer.Cbapi;
import com.spy2k3.server.businesslayer.CbapiImpl;

/**
 * @author Vinay
 * This class initializes the public api object
 */
public class OutcomeAPI extends AuthorService{
	
	//Fields
	public static Cbapi cbapi;
	public static String type;	
	
	//Constructor
	public OutcomeAPI() {
	}	
	
	public void init(){
		super.init();
		cbapi = new CbapiImpl();
		type =OUTCOME;
	}
}
