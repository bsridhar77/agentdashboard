To Build the project ( Builds a jar not a WAR ) 

	mvn package
	
	
To Run the service
	
	java -jar target/agentdashboard-0.0.1-SNAPSHOT.jar server agentdashboardservice.yml
	
	
The Port on which the agentdashboardservice runs is configured in agentdashboardservice.yml

Fetch AgentDashboard Details

http://localhost:5085/agentdashboard/{agentId}

http://localhost:5085/agentdashboard/1
		
