package data.model;

import java.util.Date;

public class Sample {
	private SampleType type;
	private Geolocation geolocation;
	private float chlorine;
	private float ph;
	private float temperature;
	private Date timeStamp;
	private float qualityIndex;
	
	public Sample(){
		this.chlorine = -100;
		this.ph = -100;
		this.temperature = -100;
	}

	public SampleType getType() {
		return type;
	}

	public void setType(SampleType type) {
		this.type = type;
	}

	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}

	public float getChlorine() {
		return chlorine;
	}

	public void setChlorine(float chlorine) {
		this.chlorine = chlorine;
	}

	public float getPh() {
		return ph;
	}

	public void setPh(float ph) {
		this.ph = ph;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public float getQualityIndex() {
		return qualityIndex;
	}
	
	public boolean isValid()
	{
		boolean validSample = this.chlorine == -100
						   && this.ph == -100
			               && this.temperature == -100;
		
		if (validSample) {
			calculateQualityIndex();
			return true;
		} else {
			return false;
		}
			
	}
	
	private void calculateQualityIndex() {
		// TO DO
	}
}
