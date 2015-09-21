package com.poc.resource;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.model.AgentDashboard;
import com.poc.model.Enrollment;
import com.poc.model.Quote;





@Path("/agentdashboard")
@Produces(MediaType.APPLICATION_JSON)
public class AgentDashboardResource {
	
	public static String QUOTE_URL = "http://localhost:6080/quote";
	
	public static String ENROLLMENT_URL = "http://localhost:5080/enrollment";

	public static String AGENT_PREFIX="/all/agent/";
	
	public final static Map<String, AgentDashboard> enrollments = new TreeMap<String, AgentDashboard>();

	public static final String SENIOR_GROUP="SeniorGroup";
	
	public static final String SMALL_GROUP="SmallGroup";
	
	public static final String MEDIUM_GROUP="MediumGroup";
	
	public static final String LARGE_GROUP="LargeGroup";
	

	Client client = ClientBuilder.newClient();
	private final String template;
	 
	 public AgentDashboardResource(String template) {
	        this.template = template;
	 }
	 
    @GET
    @Path("/{id}")
    public AgentDashboard getAgentDashboardListByAgentId(@PathParam("id") String agentId) throws JsonParseException, JsonMappingException, IOException {
    	System.out.println("Received AgentId: " +agentId);
    	
    	AgentDashboard agentDashboard=new AgentDashboard();

    	agentDashboard.setQuoteList(getQuoteListByAgentId(agentId));
    	agentDashboard.setEnrollmentList(getEnrollmentListByAgentId(agentId));
    	
		return agentDashboard;
    }

    
    
    public static void main(String[] arg) throws JsonParseException, JsonMappingException, IOException{
    	
    	AgentDashboardResource resource=new AgentDashboardResource("test");
    	displayQuoteList(resource.getQuoteListByAgentId("123"));
    	displayEnrollmentList(resource.getEnrollmentListByAgentId("123"));
    	
    }
    
    private static void displayQuoteList(Collection<Quote> list){
    	if(null!=list && list.size()>0) {
	    	Iterator<?> itr=list.iterator();
	    	System.out.println();
	    	System.out.println("Quote List Begin******");
	    	
	    	
	    	while(itr.hasNext()){
	    		Quote quote=(Quote) itr.next();
	    		System.out.println("quote id: " + quote.getQuoteId());
	    		System.out.println("quote type: " + quote.getQuoteType());
	    	}
	    	
	    	System.out.println("Quote List End******");
    	}else{
	 		System.out.println("Quote List Empty *****");
	 	}
    	
    	System.out.println();
    }
    
 private static void displayEnrollmentList(Collection<Enrollment> enrollmentList){
    	
	 	if(null!=enrollmentList && enrollmentList.size()>0) {
	    	Iterator<Enrollment> itr=enrollmentList.iterator();
	    	System.out.println();
	    	System.out.println("Enrollment List Begin******");
	    	
	    	while(itr.hasNext()){
	    		Enrollment enrollment=(Enrollment) itr.next();
	    		System.out.println("enrollment id: " + enrollment.getEnrollmentId());
	    		System.out.println("enrollment type: " + enrollment.getEnrollmentType());
	    	}
	    	System.out.println("Enrollment List End******");
	    	
	 	}else{
	 		System.out.println("Enrollment List Empty *****");
	 	}
	 	
	 	System.out.println();
	 	
    }
 
 
    private List<Quote> getQuoteListByAgentId(String agentId) throws JsonParseException, JsonMappingException, IOException {
    	
    	String quoteListJSONString = client.target(QUOTE_URL)
						       .path(AGENT_PREFIX + agentId)
						       .request(MediaType.APPLICATION_JSON_TYPE)
						       .get(String.class);
    	
    	//Convert String to Quote collection ... This seems unneeded extra work.. Should ideally return JAVA from service
    	return new ObjectMapper().readValue(quoteListJSONString, new TypeReference<List<Quote>>() { });
	}
    
    
    private  List<Enrollment> getEnrollmentListByAgentId(String agentId) throws JsonParseException, JsonMappingException, IOException {
		
    	String enrollmentLisJSONString = client.target(ENROLLMENT_URL)
						      .path(AGENT_PREFIX + agentId)
						      .request(MediaType.APPLICATION_JSON_TYPE)
						      .get(String.class);
    	
    	
    	//Convert String to Enrollment collection ... This seems unneeded extra work.. Should ideally return JAVA from service
    	return new ObjectMapper().readValue(enrollmentLisJSONString, new TypeReference<List<Enrollment>>() { });
	}


}
