package exia.fr;

import java.util.ArrayList;
import java.util.List;


public class TemperatureEvolution implements ModelObserver, Runnable{

	private List<Float> temperatureInt = new ArrayList<Float>();
	private List<Float> temperatureExt = new ArrayList<Float>();
	private List<Float> temperatureConsigne = new ArrayList<Float>();
	private Model model;
	
	/**
	 * @param model
	 */
	public TemperatureEvolution(Model model) {
		model.addObserver(this);
		this.model = model;
		for(int i = 0; i<20; i++) {
			this.temperatureInt.add(model.getTemperatureInt());
			this.temperatureExt.add(model.getTemperatureExt());
			this.temperatureConsigne.add(model.getTemperatureConsigne());
		}
		
	}

	/* 
	 * @see java.lang.Runnable#run()
	 * Thread model which analyze the variation of the interior temperature,
	 * the difference between interior temperature, the exterior temperature
	 * and the consigne to set flags which allow to determine the efficiency
	 * of the modification to the temperature
	 */
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
				if(Math.abs(tempVar) < 0.2) {
					iterationStagn ++;
				}
				if(tempVar > 0) {
					iterationPos ++;
				}
			}
			if (iterationStagn >= 16 && (iterationPos <= 2 || iterationPos >= 18)) {
				noVariation = true;
			}
			if(temperatureInt.get(temperatureInt.size()-1) - temperatureConsigne.get(temperatureConsigne.size()-1) > 0 && iterationPos <= 5) {
				wrongVariation = true;
			} else if(temperatureInt.get(temperatureInt.size()-1) - temperatureConsigne.get(temperatureConsigne.size()-1) < 0 && iterationPos >= 5) {
				wrongVariation = true;
			}
			
			for(int i = 5; i>0; i--) {
				if(Math.abs(temperatureInt.get(temperatureInt.size()-i-1) - temperatureExt.get(temperatureExt.size()-i-1)) < 0.5) {
					iterationExtSame ++;
				}
			}
			
			if(iterationExtSame == 5) {
				nearTempExt = true;
			}
			
			for(int i = 5; i>0; i--) {
				if(Math.abs(temperatureInt.get(temperatureInt.size()-i-1) - temperatureConsigne.get(temperatureConsigne.size()-i-1)) > 0.5) {
					iterationConsigneGap ++;
				}
			}
			
			if(iterationConsigneGap == 5) {
				consigneGap = true;
			}
			
			
			if(consigneGap && wrongVariation && !noVariation) {
				this.model.setAlertTemp(1);
			} else if(consigneGap && nearTempExt && (wrongVariation || noVariation)) {
				this.model.setAlertTemp(2);
			} else if(consigneGap && !nearTempExt && (wrongVariation || noVariation)) {
				this.model.setAlertTemp(3);
			} else {
				this.model.setAlertTemp(0);
			}
			
		}
	}

	/* 
	 * @see exia.fr.ModelObserver#onTemperatureIntChanged(float)
	 */
	@Override
	public void onTemperatureIntChanged(float value) {
		this.temperatureInt.add(value);
	}

	/* 
	 * @see exia.fr.ModelObserver#onTemperatureExtChanged(float)
	 */
	@Override
	public void onTemperatureExtChanged(float value) {
		this.temperatureInt.add(value);
	}

	/* 
	 * @see exia.fr.ModelObserver#onHumidityChanged(float)
	 */
	@Override
	public void onHumidityChanged(float value) {
		
	}

	/* 
	 * @see exia.fr.ModelObserver#onTemperatureConsigneChanged(float)
	 */
	@Override
	public void onTemperatureConsigneChanged(float value) {
		this.temperatureInt.add(value);
	}

	/* 
	 * @see exia.fr.ModelObserver#onAlertChanged(int)
	 */
	@Override
	public void onAlertChanged(int value) {
		
	}

	/* 
	 * @see exia.fr.ModelObserver#onAlertTempChanged(int)
	 */
	@Override
	public void onAlertTempChanged(int value) {
		
	}
	
	
	
}
