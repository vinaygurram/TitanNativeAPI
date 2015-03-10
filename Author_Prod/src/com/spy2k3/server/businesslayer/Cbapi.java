package com.spy2k3.server.businesslayer;

import org.json.JSONObject;

import com.spy2k3.server.backend.frames.dao.DAOFactory;
import com.spy2k3.server.backend.frames.dao.DAOManager;
import com.spy2k3.server.frontend.valueobjects.JsonRPCVO;
import com.spy2k3.server.frontend.valueobjects.OutcomeVO;
import com.spy2k3.server.frontend.valueobjects.ProgramOutcomeVO;
import com.spy2k3.server.frontend.valueobjects.SubOutcomeVO;

public abstract class Cbapi {
	protected DAOManager daoManager;
	
	public Cbapi(){
		init();
	}
	
	public void init(){
		DAOFactory daoFactory = new DAOFactory();
		daoManager = daoFactory.getDAOManager();
	}
	
	public abstract String addOutcome(OutcomeVO jo);
	public abstract JSONObject getOutcome(String id);
	public abstract boolean updateOutcome(String jo);
	public abstract boolean deleteOutcome(String id);
	public abstract String addSubOutcome(SubOutcomeVO jsonSubOutcome);
	public abstract boolean deleteSubOutcome(SubOutcomeVO jsonSubOutcome);
	public abstract JSONObject jsonRPC(JsonRPCVO jo);
	public abstract boolean updateProgramOutcome(String jo);
	public abstract String addProgramOutcome(ProgramOutcomeVO jo);
	public abstract JSONObject getProgramOutcome(String id);
	public abstract JSONObject getMaps(String id);
}
