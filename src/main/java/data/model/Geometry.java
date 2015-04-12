package data.model;

public class Geometry {
	
	private String type = "Point";
	private double[] coordinates = new double[2];
	
	public Geometry(){};
	
	public Geometry(double[] coordinates){
		this.coordinates = coordinates;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}
}
