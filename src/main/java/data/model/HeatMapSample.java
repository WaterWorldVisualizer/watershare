package data.model;

public class HeatMapSample {

	private double lat;
	private double lng;
	private int cnt;
	
	public HeatMapSample(){};
	
	public HeatMapSample(double lat, double lng, Double cnt) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.cnt = cnt.intValue();
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
	
	
}
