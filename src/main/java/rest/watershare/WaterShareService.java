package rest.watershare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import data.model.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;

/**
 * A service that manipulates contacts in an address book.
 *
 */
@Path("/watershare")
public class WaterShareService {

	/**
	 * The (shared) object. 
	 */
	//@Inject
	//ToDoList toDoList;

	/**
	 * A GET /todolist request should return the ToDo List in JSON.
	 * @return a JSON representation of the ToDo List.
	 */
	@GET
	@Path("/layer/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLayerData(@PathParam("type") String type) {

		try{	
			Gson gson = new Gson();
			BufferedReader br;
			SamplesCollection samples;

			switch(type){
			case "reservoirs":
				br = new BufferedReader(new FileReader(new File("resources/reservoir_water_data.json")));  

				samples = gson.fromJson(br, SamplesCollection.class);
				
				return gson.toJson(samples);
			case "water_tanks":
				br = new BufferedReader(new FileReader(new File("resources/tanks_water_data.json")));  

				samples = gson.fromJson(br, SamplesCollection.class);
				
				return gson.toJson(samples);
			case "endpoints":
				br = new BufferedReader(new FileReader(new File("resources/endpoints_data.json")));  

				samples = gson.fromJson(br, SamplesCollection.class);
				
				return gson.toJson(samples);
			default:
				return "Layer not available";
			}
		} catch (IOException ioex){
			ioex.printStackTrace();
			return "Service Error";
		}

	}

}
