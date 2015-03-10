package com.spy2k3.server.backend.frames.dao;

import com.spy2k3.server.businesslayer.objects.Constants.Nodetype;


public abstract class OutcomeDAO implements GenericDAO {
	
	protected DBConnection dbconnection;
	protected Nodetype nodetype = Nodetype.OUTCOME;
	
	public OutcomeDAO(DBConnection connection){
		init(connection);
	}	
	public void init(DBConnection connection){
		dbconnection = connection;
	}	
}
