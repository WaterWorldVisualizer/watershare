package webscrapping;

/*import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;*/
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import data.model.Feature;
import data.model.Geometry;
import data.model.HeatMapSample;
import data.model.HeatMapSampleCollection;
import data.model.Properties;
import data.model.SampleType;
import data.model.SamplesCollection;

public class TankWaterSamples_Scrapping {

	private final static String URI_base = "http://www.zaragoza.es/ciudad/IMSP/";
	private final static String URI_listado = URI_base + "listado_IMSP?numpag=";
	
	//CONSTRUCTOR
	public TankWaterSamples_Scrapping(){}
	
	
	/*  METHODS  */
	/**
	 * Get the last sample of the day
	 */
	public void getLastSample(){
		//TODO: We would need real time data in order to accomplish this
		//		Get the last sample data of the day for every tank water from the page with web scrapping
	}
	
	/**
	 *   Get all the existing samples for water tanks
	 */
	public String[] getSampleHistorial(){
		try {
			
			SampleType type = SampleType.WATER_TANK_SAMPLE;
			
			List<Feature> sampleList = new ArrayList<Feature>();
			List<HeatMapSample> heatsampleList = new ArrayList<HeatMapSample>();
			
			Document doc  = Jsoup.connect(URI_listado+"0").get();
			
			int numPags = Integer.parseInt(doc.getElementsByClass("cont").text().split(" ")[5].trim());
			
			for (int i=0; i<numPags; i++){
				
				doc = Jsoup.connect(URI_listado+i).get();
				
				Elements rows = doc.getElementsByTag("table").get(0).getElementsByTag("tr");
				
				for(Element row : rows){
					
					if (row.children().get(0).tagName()=="td"){
						
						String chlorine = "", ph = "";
						//Feature sample = new Feature();
						Properties properties = new Properties();
						
						doc = Jsoup.connect(URI_base + row.children().get(3).getElementsByTag("a").get(0).attr("href")).get();
						
						Elements data = doc.getElementById("detalle").children().get(1).children();
						
						//Fecha de la muestra del embalse
						
						String strFecha = data.get(1).text().split(" ")[1];
						if (strFecha.startsWith("2")){
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							Date fecha = null;
							fecha = sdf.parse(strFecha);
							properties.setTimeStamp(fecha);
						}
						
						String name = new String(getTankWaterName(data.get(2).text()).getBytes("UTF-8"));
						
						Elements dataRows =  data.get(5).getElementsByTag("tr");
						
						for (Element dataRow : dataRows){

							switch (dataRow.children().get(0).text()){
							case "Cloro combinado":
								chlorine = dataRow.children().get(1).text()
										.split(" ")[0].replace(",", ".");
								if (chlorine.startsWith("<")) chlorine=chlorine.substring(1);
								break;
							case "pH":
								ph = dataRow.children().get(1).text().replace(",", ".");
								break;
							}
						}
						
						if (chlorine.compareTo("")!=0 && ph.compareTo("")!=0 && name.compareTo("")!=0){
						
							properties.setType(type);
							properties.setChlorine(Float.valueOf(chlorine));
							properties.setPh(Float.valueOf(ph));
							properties.setName(name);
							
							Feature sample = new Feature(new Geometry((double[])getGeolocation(name, "coord")),properties);
							
							HeatMapSample heat_map_sample = new HeatMapSample((double)getGeolocation(name, "lat")
									, (double)getGeolocation(name, "lng"), 
									Integer.valueOf((int) Math.round((Math.random()*10))));
							
							sampleList.add(sample);
							heatsampleList.add(heat_map_sample);
						}
					}
				}
			}
			
			SamplesCollection samples = new SamplesCollection(sampleList);
			HeatMapSampleCollection heatMapSamples = new HeatMapSampleCollection(heatsampleList);
			
			Gson gson = new Gson();
			
			String[] jsons = new String[2];
			
			jsons[0] = gson.toJson(samples, SamplesCollection.class);
			jsons[1] = gson.toJson(heatMapSamples, HeatMapSampleCollection.class);
			
			/*FileOutputStream fos = new FileOutputStream(new File("resources/tank_waters_samples.json"));
			fos.write(json_samples.getBytes());
			fos.flush();
			fos.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("resources/tank_waters_heat_map.json")));
			bw.write(gson.toJson(heatMapSamples, HeatMapSampleCollection.class));
			
			bw.close();*/
			
		} catch (IOException e) {
			//Fallo JSOUP connect
			e.printStackTrace();
		} catch (ParseException e) {
			// Fallo fecha parser
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Get the tank water name
	private static String getTankWaterName(String name){
		
		String tankWaterName = "Depósito de ";
		
		if (name.contains("Casablanca")){
			tankWaterName += "Casablanca";
		} else if (name.contains("Valdespartera")) {
			tankWaterName += "Valdespartera";
		} else if (name.contains("Academia")) {
			tankWaterName += "Academia";
		} else if (name.contains("Villarrapa")) {
			tankWaterName += "Villarrapa";
		} else if (name.contains("Garrapinillos")){
			tankWaterName += "Garrapinillos";
		} else if (name.contains("Alfocea")){
			tankWaterName += "Alfocea";
		} else {
			return "";
		}
		
		return tankWaterName;
	}
	
	
	//Get the coordinates for every tank water
	private static Object getGeolocation(String name, String opt){
		
		double[] coordinates;
		
		if (name.contains("Casablanca")){
			coordinates = new double[]{41.63615, -0.918606};
		} else if (name.contains("Valdespartera")) {
			coordinates = new double[]{41.62836, -0.922401};
		} else if (name.contains("Academia")) {
			coordinates = new double[]{41.697625, -0.877336};
		} else if (name.contains("Villarrapa")) {
			coordinates = new double[]{41.739999, -1.066157};
		} else if (name.contains("Garrapinillos")){
			coordinates = new double[]{41.682147, -1.030234};
		} else if (name.contains("Alfocea")){
			coordinates = new double[]{41.702785, -0.970101};
		} else {
			return null;
		}
		
		switch(opt){
		case "lat":
			return coordinates[0];
		case "lng":
			return coordinates[1];
		case "coord":
			return coordinates;
		}
		return null;
	}
}
