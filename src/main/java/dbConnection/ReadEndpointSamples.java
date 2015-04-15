package dbConnection;

/*
 * AUTOR:  Daniel García Páez
 * 
 * Clase encargada de leer de la BD la predicción meteorológica para una 
 * semana concrete para un municipio concreto.
 */ 

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.naming.NamingException;

import com.google.gson.Gson;

import logic.CalculateWaterQualityIndex;
import data.model.*;

public class ReadEndpointSamples {

	Connection conn;

	public ReadEndpointSamples(){}

	public String read(){
		
		try {
			this.conn = DB_Connection.getConnection();
			
			String queryString = "SELECT longitude, latitude, take_on, address, postalcode, "
					+ "city, country, chlorine, temperature, ph "
					+ "FROM places";
			
			/* Execute query. */
			PreparedStatement prepStatement = this.conn.prepareStatement(queryString);
			ResultSet rs = prepStatement.executeQuery();
			
			List<Feature> listSamples = new ArrayList<Feature>();
			List<HeatMapSample> listHeatMapSamples = new ArrayList<HeatMapSample>();
			
			while(rs.next()){
				
				Geometry coords = new Geometry(new double[]{rs.getDouble("longitude"), rs.getDouble("latitude")});
				
				double chlorine = rs.getDouble("chlorine");
				double ph = rs.getDouble("ph");
				//double chlorine = rs.getDouble("chlorine");
				double temperature = this.calculateMockTemperature();
				Date date = new Date(rs.getDate("take_on").getTime());
				double qualityIndex = new CalculateWaterQualityIndex().calculate(ph, chlorine, temperature, "endpoint");
				String address = rs.getString("city")+" ("+rs.getString("country")+")"+rs.getString("postalcode")
						+ "<br>"+ rs.getString("address");
				
				Properties props = new Properties(SampleType.ENDPOINT_SAMPLE, "",chlorine, ph, temperature,
						date, qualityIndex, address);
				
				HeatMapSample heat_map_sample = 
						new HeatMapSample(rs.getDouble("latitude"), rs.getDouble("longitude"), qualityIndex);
				
				listSamples.add(new Feature(coords, props));
				listHeatMapSamples.add(heat_map_sample);
			}
			
			SamplesCollection samples = new SamplesCollection(listSamples, listHeatMapSamples);
			
			Gson gson = new Gson();
			
			String json_samples = gson.toJson(samples, SamplesCollection.class);
			
			FileOutputStream fos = new FileOutputStream(new File("resources/endpoints_data2.json"));
			fos.write(json_samples.getBytes());
			fos.flush();
			fos.close();
			return json_samples;
			
		} catch (SQLException | NamingException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private double calculateMockTemperature(){
		
		Random r = new Random();
		int Low = 5;
		int High = 24;
		int R = r.nextInt(High-Low) + Low;
		
		return Double.valueOf(R);
		
	}
}
