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
				br = new BufferedReader(
						new FileReader(
								new File(System.getenv("OPENSHIFT_DATA_DIR")+"/resources/reservoir_water_data.json")));  

				samples = gson.fromJson(br, SamplesCollection.class);
				
				//This would be the ideal return
//				Reservoir_Scrapping rs = new Reservoir_Scrapping();
//				return rs.getLastData();
				return gson.toJson(samples);
			case "water_tanks":
				br = new BufferedReader(
						new FileReader(
								new File(System.getenv("OPENSHIFT_DATA_DIR")+"/resources/tanks_water_data.json")));  

				samples = gson.fromJson(br, SamplesCollection.class);
				//This would be the ideal return				
//				TankWaterSamples_Scrapping twss = new TankWaterSamples_Scrapping();
//				return twss.getLastSample();
				//return Response.ok().build();
				return gson.toJson(samples);
			case "endpoints":
				br = new BufferedReader(
						new FileReader(
								new File(System.getenv("OPENSHIFT_DATA_DIR")+"/resources/endpoints_data.json")));  

				samples = gson.fromJson(br, SamplesCollection.class);
				
				//This would be the ideal return
				//ReadEndpointSamples res = new ReadEndpointSamples();
				//samples = gson.fromJson(res.read(), SamplesCollection.class);
				
				//return Response.ok().build();
				return gson.toJson(samples);
			default:
				//return Response.status(500).build();
				return "Layer "+type+" not available";
			}
		} catch (IOException ioex){
			ioex.printStackTrace();
			//return Response.status(500).build();
			return ioex.getMessage();
		}

	}

}
