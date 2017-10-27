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
	 * Température intérieure du réfrigérateur. En degrés celcius.
	 */
	private float temperatureInt = 0;
	
	private float temperatureExt = 0;

	/**
	 * Humidité intérieure du réfrigérateur. En pourcentage d'humidité relative dans
	 * l'air.
	 */
	private float humidityInt = 0;
	/**
	 * Température de consigne pour l'asservissement du réfrigérateur. En degrés
	 * celcius.
	 */
	private float temperatureConsigne = 18;
	
	private int alertCondensation = 0;
	
	private int alertTemp = 0;
	
	

	public float getTemperatureInt() {
		return temperatureInt;
	}

	public void setTemperatureInt(float temperatureInt) {
		if (this.temperatureInt != temperatureInt){
			this.temperatureInt = temperatureInt;
			notifyTemperatureIntChanged();
		}
	}
	
	public float getTemperatureExt() {
		return temperatureExt;
	}
	
	public void setTemperatureExt(float temperatureExt) {
		if(this.temperatureExt != temperatureExt) {
			this.temperatureExt = temperatureExt;
			notifyTemperatureExtChanged();
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
	
	public int getAlert() {
		return alertCondensation;
	}
	
	public void setAlert(int alert) {
		if(this.alertCondensation != alert) {
			this.alertCondensation = alert;
			notifyAlertChanged();
		}
	}

	public int getAlertTemp() {
		return alertTemp;
	}
	
	public void setAlertTemp(int alert) {
		if(this.alertTemp != alert) {
			this.alertTemp = alert;
			notifyAlertTempChanged();
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
	
	private void notifyTemperatureExtChanged() {
		for(ModelObserver observer : list) {
			observer.onTemperatureExtChanged(this.temperatureExt);
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
	
	private void notifyAlertChanged() {
		for (ModelObserver observer : list) {
			observer.onAlertChanged(this.alertCondensation);
		}
	}
	
	private void notifyAlertTempChanged() {
		for (ModelObserver observer : list) {
			observer.onAlertTempChanged(this.alertTemp);
		}
	}

}