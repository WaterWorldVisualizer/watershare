package data.model;

import java.util.List;

public class SamplesCollection {

	private String type = "FeatureCollection";
	private List<Feature> features;
	
	public SamplesCollection(){};
	
	public SamplesCollection(List<Feature> features){
		this.features = features;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public String getType() {
		return type;
	}
	
}
