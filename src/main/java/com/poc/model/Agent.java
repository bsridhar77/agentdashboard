/* test*/
package com.poc.model;

public class Agent {

	
	String agentId;
	
	String agentName;
	

	public Agent(){
		
	}
	public Agent(String agentId, String agentName){
		this.agentId=agentId;
		this.agentName=agentName;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
}
