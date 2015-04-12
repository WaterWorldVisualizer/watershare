package logic;

public class CalculateWaterQualityIndex {

	private double pH;
	private double ch;
	private double temp;
	
	public CalculateWaterQualityIndex(){};
	
	public double calculate(double pH, double ch, double temp, String place){
		this.pH = pH;
		this.ch = ch;
		this.temp = temp;
		
		double quiValue = 0.0;
		
		switch(place){
			case "origin":
				return originMathModel(quiValue);
			case "endpoint":
				return endpointMathModel(quiValue);
			default:
				return 99.99;
		}	
	}
	
	//interpolacion lineal para dos valores
	private double xtoy(double x,double x0,double x1,double y0,double y1){
		
		double m = (y1-y0)/(x1-x0);

	    return (y0+m*(x-x0));
	}
	
	
	private double dattowqui(double dat, int len, double[] xarray, double[] yarray){
		
		boolean found = false;
		int i=0;
		
		for (i=0; i<len && !found; i++){
			
			if (xarray[i]<=dat && dat<=xarray[i] && !found){
				found = true;
			}
		}
		
		if (found){
			i--;
			return (this.xtoy(dat, xarray[i], xarray[i+1], yarray[i], yarray[i+1]));
		}
		return 100.0;
	}

	//ofrece resultado redondeado de la interpolacion
	private double calc_temp_wqui(double value){

		double[] xarray = new double[]{0,2.5,5,7.5,10,12.5,15,17.5,20,22.5,25,27.5,30}; 
		double[] yarray = new double[]{90,84,72,58,45,35,28,24,21,20,18,12,10};
		int cnt = xarray.length;

		return Math.round(dattowqui(value,cnt,xarray,yarray));
	}

	//ofrece resultado redondeado de la interpolacion
	private double calc_ch_wqui(double value){

		double[] xarray = new double[]{0,2.5,5,7.5,10,12.5,15,17.5,20,22.5,25,27.5,30}; 
		double[] yarray = new double[]{90,84,72,58,45,35,28,24,21,20,18,12,10};
		int cnt = xarray.length;

		return Math.round(dattowqui(value,cnt,xarray,yarray));
	}

	//ofrece resultado redondeado de la interpolacion
	private double calc_ph_wqui(double value){

		double[] xarray = new double[]{2.0,3.0,3.5,4.0, 4.1, 4.5, 4.8, 5.1, 6.2, 6.8, 7.0, 7.1, 7.2, 7.4, 7.6, 7.8, 8.0, 8.9, 9.7,10.0,10.3,10.7,10.8,11.0,11.5,12.0}; 
		double[] yarray = new double[]{2.0,4.0,6.0,9.0,10.0,15.0,20.0,30.0,60.0,83.0,88.0,90.0,92.0,93.0,92.0,90.0,84.0,52.0,26.0,20.0,15.0,11.0,10.0, 8.0, 5.0, 3.0};
		int cnt = xarray.length;

		return Math.round(dattowqui(value,cnt,xarray,yarray));
	}

	
	private double originMathModel(double value){
		return 0.5*calc_ph_wqui(this.pH) + 0.5*calc_temp_wqui(this.temp);
	}
	
	private double endpointMathModel(double value){
		return 0.1*calc_ch_wqui(this.ch) + 0.7*calc_ph_wqui(this.pH) + 0.2*calc_temp_wqui(this.temp);
	}
}
