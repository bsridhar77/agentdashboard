package com.poc.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.poc.resource.AgentDashboardResource;


public class AgentDashboardApplication extends Application<AgentDashboardConfiguration> {
	    public static void main(String[] args) throws Exception {
	        new AgentDashboardApplication().run(args);
	    }

	    @Override
	    public String getName() {
	        return "AgentDashboardApplication";
	    }

	    @Override
	    public void initialize(Bootstrap<AgentDashboardConfiguration> bootstrap) {
	        // nothing to do yet
	    }

	    @Override
	    public void run(AgentDashboardConfiguration configuration,
	                    Environment environment) {
	        final AgentDashboardResource resource = new AgentDashboardResource( configuration.getTemplate());
	        environment.jersey().register(resource);
	    }
	
	
}
