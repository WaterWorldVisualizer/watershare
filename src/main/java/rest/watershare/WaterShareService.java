package rest.watershare;

import java.io.File;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

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
		
		File json;
		
		switch(type){
		case "reservoirs":
			json = new File(resources)
			break;
		case "water_tanks":
			break;
		case "endpoints":
			break;
			
		}
		
	}

}
