package data.model;

import java.awt.Point;
import java.util.Date;

public class Muestra {

	private String tipo;
	private Point geolocalizacion;
	private double cloro;
	private double pH;
	private double temperatura;
	private Date timestamp;
	
	
	public Muestra(){};
	
	public Muestra(String tipo, Point geolocalizacion, double cloro, double pH,
			double temperatura, Date timestamp) {
		
		super();
		
		this.tipo = tipo;
		this.geolocalizacion = geolocalizacion;
		this.cloro = cloro;
		this.pH = pH;
		this.temperatura = temperatura;
		this.timestamp = timestamp;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Point getGeolocalizacion() {
		return geolocalizacion;
	}

	public void setGeolocalizacion(Point geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}

	public double getCloro() {
		return cloro;
	}

	public void setCloro(double cloro) {
		this.cloro = cloro;
	}

	public double getpH() {
		return pH;
	}

	public void setpH(double pH) {
		this.pH = pH;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	
	
}
