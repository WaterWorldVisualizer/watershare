package rest.watershare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import data.model.*;
import dbConnection.ReadEndpointSamples;

//import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

//import webscrapping.Reservoir_Scrapping;
//import webscrapping.TankWaterSamples_Scrapping;

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
	//String json_samples;

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
				
				
//				Reservoir_Scrapping rs = new Reservoir_Scrapping();
//				return rs.getLastData();
				//json_samples = gson.toJson(samples);
				//return Response.ok().build();
				return gson.toJson(samples);
			case "water_tanks":
				br = new BufferedReader(new FileReader(new File("resources/tanks_water_data.json")));  

				samples = gson.fromJson(br, SamplesCollection.class);
				
//				TankWaterSamples_Scrapping twss = new TankWaterSamples_Scrapping();
//				return twss.getLastSample();
				//json_samples = gson.toJson(samples);
				//return Response.ok().build();
				return gson.toJson(samples);
			case "endpoints":
				br = new BufferedReader(new FileReader(new File("resources/endpoints_data.json")));  

				samples = gson.fromJson(br, SamplesCollection.class);
				
				return gson.toJson(samples);
				
				//ReadEndpointSamples res = new ReadEndpointSamples();
				
				//json_samples = res.read();
				//return Response.ok().build();
				
				//return res.read();
			default:
				//json_samples = "Layer not available";
				//return Response.status(500).build();
				return "Layer not available";
			}
		} catch (IOException ioex){
			ioex.printStackTrace();
			//return Response.status(500).build();
			return "Error service";
		}

	}

}
