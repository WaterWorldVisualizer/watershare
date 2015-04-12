package data.model;

import java.util.List;

public class HeatMapSampleCollection {
	
	private List<HeatMapSample> data;
	
	public HeatMapSampleCollection(){};
	
	public HeatMapSampleCollection(List<HeatMapSample> features){
		this.data = features;
	}

	public List<HeatMapSample> getFeatures() {
		return data;
	}
}
