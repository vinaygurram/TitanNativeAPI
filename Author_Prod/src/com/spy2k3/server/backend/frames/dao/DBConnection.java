package com.spy2k3.server.backend.frames.dao;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.frames.modules.gremlingroovy.GremlinGroovyModule;

public class DBConnection{
	
	//Fields
	private TitanGraph titanGraph;
	private FramedGraph<TitanGraph> framedGraph ;
	
	//Constants
	public static final String STORAGE_BACKEND = "cassandrathrift";
	//public static final String STORSGE_HOSTNAME = "10.217.221.242";
	public static final String STORSGE_HOSTNAME = "10.217.221.242";

	
	//Constructor
	public DBConnection(){
		
		init();      
	}
	
	private void init(){
		//Create a connection to the graphs
    	Configuration config = new BaseConfiguration();
    	config.setProperty("storage.backend", STORAGE_BACKEND);
    	config.setProperty("storage.hostname", STORSGE_HOSTNAME);
        titanGraph = TitanFactory.open(config);
        FramedGraphFactory factory = new FramedGraphFactory(new GremlinGroovyModule());
        framedGraph  = factory.create(titanGraph);
	}

	public TitanGraph getTitanGraph() {
		return titanGraph;
	}

	public FramedGraph<TitanGraph> getFramedGraph() {
		return framedGraph;
	}
	
	public void close(){
		titanGraph.shutdown();
	}
};
