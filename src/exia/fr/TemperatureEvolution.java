package exia.fr;

import java.util.ArrayList;
import java.util.List;

public class TemperatureEvolution implements ModelObserver, Runnable{

	private List<Float> temperatureInt = new ArrayList<Float>();
	private List<Float> temperatureExt = new ArrayList<Float>();
	private List<Float> temperatureConsigne = new ArrayList<Float>();
	
	public TemperatureEvolution(Model model) {
		model.addObserver(this);
		this.temperatureInt.add(model.getTemperatureInt());
		this.temperatureExt.add(model.getTemperatureExt());
		this.temperatureConsigne.add(model.getTemperatureConsigne());
	}

	@Override
	public void run() {
		List<Float> variationTempInt = new ArrayList<Float>();
		List<Float> variationTempIntFilt = new ArrayList<Float>();
		boolean first;
		boolean consigneGap;
		boolean nearTempExt;
		boolean wrongVariation;
		boolean noVariation;
		float temp;
		float tempSec;
		int iterationStagn = 0;
		int iterationPos = 0;
		int iterationExtSame = 0;
		int iterationConsigneGap = 0; 
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			consigneGap = false;
			nearTempExt = false;
			wrongVariation = false;
			noVariation = false;
			
			while(temperatureInt.size()>20) {
				temperatureInt.remove(0);
			}
			while(temperatureExt.size()>20) {
				temperatureExt.remove(0);
			}
			while(temperatureConsigne.size()>20) {
				temperatureConsigne.remove(0);
			}
			variationTempInt.clear();
			variationTempIntFilt.clear();
			first = false;
			temp = 0;
			tempSec = 0;
			for(float temperature : temperatureInt) {
				if(first) {
					variationTempInt.add(temp - temperature);
				}else {
					first = true;
				}
				temp = temperature;
			}

			first = false;
			for(float tempInt : variationTempInt) {
				if(first) {
					variationTempIntFilt.add((temp+tempSec+tempInt)/3);
				}else {
					variationTempIntFilt.add(tempInt);
					tempSec = tempInt;
					first = true;
				}
				temp = tempSec;
				tempSec = tempInt;
			}

			iterationStagn = 0;
			iterationPos = 0;
			iterationExtSame = 0;
			iterationConsigneGap = 0;
			for(float tempVar : variationTempIntFilt) {
				if(Math.abs(tempVar) < 0.5) {
					iterationStagn ++;
				}
				if(tempVar > 0) {
					iterationPos ++;
				}
			}
			if (iterationStagn >= 16 && (iterationPos <= 2 || iterationPos >= 8)) {
				noVariation = true;
			}
			if(temperatureInt.get(temperatureInt.size()) - temperatureConsigne.get(temperatureConsigne.size()) > 0 && iterationPos >= 5) {
				wrongVariation = true;
			}
			
			for(int i = 5; i>0; i--) {
				if(Math.abs(temperatureInt.get(temperatureInt.size()-i) - temperatureExt.get(temperatureExt.size()-i)) < 0.5) {
					iterationExtSame ++;
				}
			}
			
			if(iterationExtSame == 5) {
				nearTempExt = true;
			}
			
			for(int i = 5; i>0; i--) {
				if(Math.abs(temperatureInt.get(temperatureInt.size()-i) - temperatureConsigne.get(temperatureConsigne.size()-i)) > 0.5) {
					iterationConsigneGap ++;
				}
			}
			
			if(iterationConsigneGap == 5) {
				consigneGap = true;
			}
			
		}
	}

	@Override
	public void onTemperatureIntChanged(float value) {
		this.temperatureInt.add(value);
	}

	@Override
	public void onTemperatureExtChanged(float value) {
		this.temperatureInt.add(value);
	}

	@Override
	public void onHumidityChanged(float value) {
		
	}

	@Override
	public void onTemperatureConsigneChanged(float value) {
		this.temperatureInt.add(value);
	}

	@Override
	public void onAlertChanged(int value) {
		
	}
	
	
	
}
