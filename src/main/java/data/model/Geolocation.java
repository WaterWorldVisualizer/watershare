package data.model;

public class Geolocation {
	private String type;
	private float latitude;
	private float longitude;
	
	public Geolocation()
	{
		type = "Point";
	}
	
	public Geolocation(float latitude, float longitude)
	{
		this.type = "Point";
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getType() {
		return type;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
}
