/* test*/
package com.poc.model;

import java.util.List;

public class AgentDashboard {

	public List<Quote> getQuoteList() {
		return quoteList;
	}


	public void setQuoteList(List<Quote> quoteList) {
		this.quoteList = quoteList;
	}


	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}


	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}


	List<Quote> quoteList;
	
	List<Enrollment> enrollmentList;
	

	public AgentDashboard(){
		
	}
	
	
	
}
