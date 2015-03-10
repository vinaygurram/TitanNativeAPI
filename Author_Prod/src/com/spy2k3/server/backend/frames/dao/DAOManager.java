package com.spy2k3.server.backend.frames.dao;

public class DAOManager {
	
	protected OutcomeDAO outcomeDao = null;
	protected DBConnection connection;
	
	public DAOManager(DBConnection connection){
		this.connection = connection;
	}
	
	public OutcomeDAO getOutComeDAO(){
		if(outcomeDao==null){
			outcomeDao = new OutcomeDAOImpl(connection);
		}
		return outcomeDao;
	}	
}
