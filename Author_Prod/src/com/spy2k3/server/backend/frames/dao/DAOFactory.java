package com.spy2k3.server.backend.frames.dao;


public class DAOFactory {
	
	private DBConnection connection = null;
	private DAOManager daoManager = null;
	
	public DAOFactory(){
		init();
	}
	
	private void init(){
	        
	};
	
	public DBConnection getConnection(){
		if(this.connection==null){
			connection = new DBConnection();
		}
		return connection;
	}
	
	public DAOManager getDAOManager(){
		if(this.daoManager==null){
			System.out.println("comehere");
			connection = getConnection();
			daoManager = new DAOManager(connection);
		}
		return daoManager;
	}
}
