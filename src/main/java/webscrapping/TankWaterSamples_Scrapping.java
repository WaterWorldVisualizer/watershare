package webscrapping;

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

import data.model.Geolocation;
import data.model.Sample;
import data.model.SampleType;

public class TankWaterSamples_Scrapping {

	private final static String URI_base = "http://www.zaragoza.es/ciudad/IMSP/";
	private final static String URI_listado = URI_base + "listado_IMSP?numpag=";
	
	//CONSTRUCTOR
	public TankWaterSamples_Scrapping(){}
	
	
	public static void main(String[] args){
		getLastData();
	}
	
	public static void getLastData(){
		try {
			
			List<Sample> sampleList = new ArrayList<Sample>();
			
			Document doc  = Jsoup.connect(URI_listado+"0").get();
			
			int numPags = Integer.parseInt(doc.getElementsByClass("cont").text().split(" ")[5].trim());
			
			for (int i=0; i<numPags; i++){
				
				doc = Jsoup.connect(URI_listado+i).get();
				
				Elements rows = doc.getElementsByTag("table").get(0).getElementsByTag("tr");
				
				for(Element row : rows){
					
					if (row.children().get(0).tagName()=="td"){
						
						String chlorine = "", ph = "";
						Sample sample = new Sample();
						sample.setType(SampleType.WATER_TANK_SAMPLE);
						
						doc = Jsoup.connect(URI_base + row.children().get(3).getElementsByTag("a").get(0).attr("href")).get();
						
						Elements data = doc.getElementById("detalle").children().get(1).children();
						
						//Fecha de la muestra del embalse
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String strFecha = data.get(1).text().split(" ")[1];
						Date fecha = null;
						fecha = sdf.parse(strFecha);
						sample.setTimeStamp(fecha);
						
						String name = getTankWaterName(data.get(2).text());
						
						Elements dataRows =  data.get(5).getElementsByTag("tr");
						
						for (Element dataRow : dataRows){

							switch (dataRow.children().get(0).text()){
							case "Cloro combinado":
								chlorine = dataRow.children().get(1).text()
										.split(" ")[0].replace(",", ".");
								break;
							case "pH":
								ph = dataRow.children().get(1).text().replace(",", ".");
								break;
							}
						}
						
						if (chlorine.compareTo("")!=0 && ph.compareTo("")!=0 && name.compareTo("")!=0){
						
							sample.setChlorine(Float.valueOf(chlorine));
							sample.setPh(Float.valueOf(ph));
							sample.setName(name);
							sample.setGeolocation(getGeolocation(name));
							
							sampleList.add(sample);
						}
					}
				}
			}
			
			Gson gson = new Gson();
			
		} catch (IOException e) {
			//Fallo JSOUP connect
			e.printStackTrace();
		} catch (ParseException e) {
			// Fallo fecha parser
			e.printStackTrace();
		}
	}
	
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
	
	private static Geolocation getGeolocation(String name){
		
		if (name.contains("Casablanca")){
			return new Geolocation(41.63615, -0.918606);
		} else if (name.contains("Valdespartera")) {
			return new Geolocation(41.62836, -0.922401);
		} else if (name.contains("Academia")) {
			return new Geolocation(41.697625, -0.877336);
		} else if (name.contains("Villarrapa")) {
			return new Geolocation(41.739999, -1.066157);
		} else if (name.contains("Garrapinillos")){
			return new Geolocation(41.682147, -1.030234);
		} else if (name.contains("Alfocea")){
			return new Geolocation(41.702785, -0.970101);
		} else {
			return null;
		}
	}
}
