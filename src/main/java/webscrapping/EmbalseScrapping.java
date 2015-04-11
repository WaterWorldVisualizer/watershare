package webscrapping;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data.model.Muestra;

public class EmbalseScrapping {

	private final static String URI = "http://www.zaragoza.es/ciudad/IMSP/listado_IMSP?numpag=";
	
	//CONSTRUCTOR
	public EmbalseScrapping(){}
	
	
	public static void main(String[] args){
	
		getLastData();
	}
	
	public static void getLastData(){
		try {
			
			Document doc  = Jsoup.connect(URI+"0").get();
			
			int numPags = Integer.parseInt(doc.getElementsByClass("cont").text().split(" ")[5].trim());
			
			for (int i=0; i<numPags; i++){
				
				doc = Jsoup.connect(URI+i).get();
				
				Elements rows = doc.getElementsByTag("table").get(0).getElementsByTag("tr");
				
				for(Element row : rows){
					
					if (row.children().get(0).tagName()=="td"){
						Muestra m = new Muestra();
						
						//Fecha de la muestra del embalse
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String strFecha = row.children().get(0).text();
						Date fecha = null;
						fecha = sdf.parse(strFecha);
						m.setTimestamp(fecha);
						
						
					}
					
					
				}
				
			}
			
		} catch (IOException e) {
			//Fallo JSOUP connect
			e.printStackTrace();
		} catch (ParseException e) {
			// Fallo fecha parser
			e.printStackTrace();
		}
	}
}
