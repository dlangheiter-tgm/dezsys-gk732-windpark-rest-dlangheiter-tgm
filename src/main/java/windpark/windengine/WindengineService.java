package windpark.windengine;

import org.springframework.stereotype.Service;

import windpark.model.WindengineData;

@Service
public class WindengineService {
	
	public String getGreetings( String inModule ) {
        return "Greetings from " + inModule;
    }

    public WindengineData getWindengineData( String inWindengineID ) {
    	
    	WindengineSimulation simulation = new WindengineSimulation();
        return simulation.getData( inWindengineID );
        
    }
    

    
}