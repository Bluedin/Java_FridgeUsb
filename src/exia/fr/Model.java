package exia.fr;

import java.util.ArrayList;
import java.util.List;

public class Model {

	// Attributs

	/**
	 * Ma collection d'observateurs.
	 */
	private List<ModelObserver> list = new ArrayList<ModelObserver>();

	/**
	 * Temp�rature int�rieure du r�frig�rateur. En degr�s celcius.
	 */
	private float temperatureInt = 0;

	/**
	 * Humidit� int�rieure du r�frig�rateur. En pourcentage d'humidit� relative dans
	 * l'air.
	 */
	private float humidityInt = 0;

	/**
	 * Temp�rature de consigne pour l'asservissement du r�frig�rateur. En degr�s
	 * celcius.
	 */
	private float temperatureConsigne = 18;
	
	

	

	public float getTemperatureInt() {
		return temperatureInt;
	}

	public void setTemperatureInt(float temperatureInt) {
		if (this.temperatureInt != temperatureInt){
			this.temperatureInt = temperatureInt;
			notifyTemperatureIntChanged();
		}
	}

	public float getHumidityInt() {
		return humidityInt;
	}

	public void setHumidityInt(float humidityInt) {
		if (this.humidityInt != humidityInt) {
			this.humidityInt = humidityInt;
			notifyHumidityIntChanged();
		}
	}

	public float getTemperatureConsigne() {
		return temperatureConsigne;
	}

	public void setTemperatureConsigne(float temperatureConsigne) {
		if (this.temperatureConsigne != temperatureConsigne){
			this.temperatureConsigne = temperatureConsigne;
			notifyTemperatureConsigneChanged();
		}
	}

	public void addTemperatureConsigne() {
		this.setTemperatureConsigne((float) (this.getTemperatureConsigne() + 0.5));
	}

	public void decreaseTemperatureConsigne() {
		this.setTemperatureConsigne((float) (this.getTemperatureConsigne() - 0.5));
	}

	public void addObserver(ModelObserver observer) {
		list.add(observer);
	}

	public void removeObserver(ModelObserver observer) {
		list.remove(observer);
	}

	private void notifyTemperatureIntChanged() {
		for (ModelObserver observer : list) {
			observer.onTemperatureIntChanged(this.temperatureInt);
		}
	}

	private void notifyHumidityIntChanged() {
		for (ModelObserver observer : list) {
			observer.onHumidityChanged(this.humidityInt);
		}
	}

	private void notifyTemperatureConsigneChanged() {
		for (ModelObserver observer : list) {
			observer.onTemperatureConsigneChanged(this.temperatureConsigne);
		}
	}

}