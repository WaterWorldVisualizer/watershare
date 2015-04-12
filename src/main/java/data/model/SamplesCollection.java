package data.model;

import java.util.List;

public class SamplesCollection {

	private String type = "FeatureCollection";
	private List<Feature> features;
	private List<HeatMapSample> heatMapData;
	
	public SamplesCollection(){};
	
	public SamplesCollection(List<Feature> features, List<HeatMapSample> heatMapData){
		this.features = features;
		this.heatMapData = heatMapData;
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

	public List<HeatMapSample> getHeatMapData() {
		return heatMapData;
	}

	public void setHeatMapData(List<HeatMapSample> heatMapData) {
		this.heatMapData = heatMapData;
	}
	
}
