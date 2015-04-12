package webscrapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
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

public class Reservoir_Scrapping {

	private final static String URI_base = "http://195.55.247.234/redalerta/inicio.asp";
	private final static String URI_ficha_base = "http://195.55.247.234/redalerta/ficha.asp?";


	public Reservoir_Scrapping(){

	}

	
	public static void main(String[] args){
		getLastData();
	}

	public static String[] getLastData(){
		try {

			SampleType type = SampleType.RESERVOIR_SAMPLE;

			double temp = 0.0, pH= 0.0;
			String strDate = "", value="", name="";
			List<Feature> sampleList = new ArrayList<Feature>();
			List<HeatMapSample> heatsampleList = new ArrayList<HeatMapSample>();

			Document doc  = Jsoup.connect(URI_base).get();

			Elements imgs = doc.getElementsByClass("col_izda").get(0)
					.getElementsByTag("div").get(0).getElementsByTag("img");
			
			for(Element img : imgs){
				
				if (img.id().compareTo("")!=0){
					
					String data_URL = URI_ficha_base+"estacionc="+img.id().substring(1);
					
					doc = Jsoup.connect(data_URL).get();
					
					name = doc.getElementsByClass("titFicha").get(0).text()
							.substring(doc.getElementsByClass("titFicha").get(0).text().indexOf("-") + 1 );
					
					Elements parameters_table = doc.getElementsByTag("table").get(1).getElementsByTag("tr");
					
					if (parameters_table.size()>0 && getGeolocation(img.id().substring(1), "coord")!=null){

						Properties properties = new Properties();
						strDate = parameters_table.get(0).getElementsByTag("td").get(3).text();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date date = sdf.parse(strDate);
						properties.setTimeStamp(date);
						
						for (Element row: parameters_table){

							String field = row.getElementsByTag("td").get(1).text(); 

							if (field.contains("Temperatura del agua")){
								value = row.getElementsByTag("td").get(2).text();
								if (value.compareTo("No disp.")!=0)
									temp = Double.valueOf(value.replace(",", "."));
							} else if (field.contains("pH")){
								value = row.getElementsByTag("td").get(2).text();
								if (value.compareTo("No disp.")!=0)
									pH = Double.valueOf(value.replace(",", "."));
							}
						}

						if (pH!=-9999.0){
							
							properties.setType(type);
							properties.setTemperature(temp);
							properties.setPh(pH);
							properties.setName(name);
							
							Feature sample = new Feature(new Geometry(
									(double[])getGeolocation(img.id().substring(1), "coord")), properties);
							
							HeatMapSample heat_map_sample = new HeatMapSample(
									(double)getGeolocation(img.id().substring(1), "lat"),
									(double)getGeolocation(img.id().substring(1), "lng"), 
									Integer.valueOf((int) Math.round((Math.random()*10))));
							
							sampleList.add(sample);
							heatsampleList.add(heat_map_sample);
						}
					}
				}
			}

			SamplesCollection samples = new SamplesCollection(sampleList, heatsampleList);
			//HeatMapSampleCollection heatMapSamples = new HeatMapSampleCollection(heatsampleList);
			
			Gson gson = new Gson();
			
			String[] jsons = new String[2];
			
			String json_samples = gson.toJson(samples, SamplesCollection.class);
			
			//jsons[0] = gson.toJson(samples, SamplesCollection.class);
			//jsons[1] = gson.toJson(heatMapSamples, HeatMapSampleCollection.class);
			
			FileOutputStream fos = new FileOutputStream(new File("resources/reservoir_water_data.json"));
			fos.write(json_samples.getBytes());
			fos.flush();
			fos.close();
			
			/*BufferedWriter bw = new BufferedWriter(new FileWriter(new File("resources/tank_waters_heat_map.json")));
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
	
	//Get the coordinates for every tank water
	private static Object getGeolocation(String id, String opt){

		double[] coordinates;

		switch(id){
		case "901":
			coordinates = new double[]{-2.95429550869151, 42.6895157586749};
			break;
		case "902":
			coordinates = new double[]{-1.55955964846833, 42.0289937418295};
			break;
		case "903":
			coordinates = new double[]{-1.78476898570141, 42.8978267842879};
			break;
		case "904":
			coordinates = new double[]{-0.386639641008635, 42.4743912254612};
			break;
		case "905":
			coordinates = new double[]{-0.690935351713599, 41.5681767867815};
			break;
		case "906":
			coordinates = new double[]{0.612809794770966, 41.1675965251547};
			break;
		case "907":
			coordinates = new double[]{-2.8388965822895, 42.5958968988129};
			break;
		case "908":
			coordinates = new double[]{-2.20420825067888, 42.4177055881279};
			break;
		case "909":
			coordinates = new double[]{-0.920817307039125, 41.6682241954823};
			break;
		case "910":
			coordinates = new double[]{0.497152993647646, 40.9070653733839};
			break;
		case "911":
			coordinates = new double[]{-2.89558276190818, 42.6793459994185};
			break;
		case "912":
			coordinates = new double[]{-2.52115141148609, 42.3213377232488};
			break;
		case "913":
			coordinates = new double[]{1.16417664514889, 41.9307079503356};
			break;
		case "914":
			coordinates = new double[]{-5.36303430013979, 41.6034205314065};
			break;
		case "916":
			coordinates = new double[]{0.166050476290847, 41.8906256661989};
			break;
		case "918":
			coordinates = new double[]{-1.41673301209367, 42.5180329847304};
			break;
		case "919":
			coordinates = new double[]{-0.81276601298009, 41.7347293702713};
			break;
		case "920":
			coordinates = new double[]{-1.8266740568503, 42.8982639848456};
			break;
		case "921":
			coordinates = new double[]{-1.94747876728877, 42.3758026629992};
			break;
		case "922":
			coordinates = new double[]{-3.41257156482233, 42.7372226024124};
			break;
		case "927":
			coordinates = new double[]{-0.201878404570406, 40.9318388859077};
			break;
		case "928":
			coordinates = new double[]{-0.702397532786526, 40.9528226665419};
			break;
		case "929":
			coordinates = new double[]{-1.69480806669058, 42.8000940502359};
			break;
		case "930":
			coordinates = new double[]{-1.16294230883519, 41.7975507950939};
			break;
		case "931":
			coordinates = new double[]{-2.97726421179489, 42.7049131370743};
			break;
		default:
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
