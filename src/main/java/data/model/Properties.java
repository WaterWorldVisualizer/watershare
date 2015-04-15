package data.model;

import java.util.Date;

public class Properties {

	private SampleType type;
	private String name;
	private double chlorine;
	private double ph;
	private double temperature;
	private Date timeStamp;
	private double qualityIndex;
	private String address;
	
	public Properties(){};
	
	public Properties(SampleType type, String name, double chlorine, 
			double ph, double temperature, Date timeStamp, double qIndex, String address){
		
		this.type = type;
		this.name = name;
		this.chlorine = chlorine;
		this.ph = ph;
		this.temperature = temperature;
		this.timeStamp = timeStamp;
		this.qualityIndex = qIndex;
		this.address = address;
	}

	public SampleType getType() {
		return type;
	}

	public void setType(SampleType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getChlorine() {
		return chlorine;
	}

	public void setChlorine(double chlorine) {
		this.chlorine = chlorine;
	}

	public double getPh() {
		return ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double getQualityIndex() {
		return qualityIndex;
	}

	public void setQualityIndex(double qualityIndex) {
		this.qualityIndex = qualityIndex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
