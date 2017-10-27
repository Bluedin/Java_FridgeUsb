package exia.fr;

import java.util.ArrayList;
import java.util.List;

public class Model {

	// Attributs

	/**
	 * Collection of observer
	 */
	private List<ModelObserver> list = new ArrayList<ModelObserver>();

	/**
	 * Interior temperature of the fridge in Celsius
	 */
	private float temperatureInt = 0;
	/**
	 * Exterior temperature of the fridge in Celsius
	 */
	private float temperatureExt = 0;

	/**
	 * Humidity in the fridge as int 
	 */
	private float humidityInt = 0;
	/**
	 * Consign of the fridge
	 */
	private float temperatureConsigne = 18;
	
	/**
	 * Alert for the condensation
	 * 0 is AllRight
	 * 1 is lowRisk
	 * 2 is highRisk
	 * 3 is Condensation
	 */
	private int alertCondensation = 0;
	
	/**
	 * Alert for the temperature
	 * 0 is stable
	 * 1 is ErrorPeltier
	 * 2 is doorOpen
	 * 3 is defectiveComponent
	 */
	private int alertTemp = 0;
	
	

	/**
	 * @return temperatureInt
	 */
	public float getTemperatureInt() {
		return temperatureInt;
	}

	/**
	 * @param temperatureInt
	 */
	public void setTemperatureInt(float temperatureInt) {
		if (this.temperatureInt != temperatureInt){
			this.temperatureInt = temperatureInt;
			notifyTemperatureIntChanged();
		}
	}
	
	/**
	 * @return temperatureExt
	 */
	public float getTemperatureExt() {
		return temperatureExt;
	}
	
	/**
	 * @param temperatureExt
	 */
	public void setTemperatureExt(float temperatureExt) {
		if(this.temperatureExt != temperatureExt) {
			this.temperatureExt = temperatureExt;
			notifyTemperatureExtChanged();
		}
	}

	/**
	 * @return humidity
	 */
	public float getHumidityInt() {
		return humidityInt;
	}

	/**
	 * @param humidityInt
	 */
	public void setHumidityInt(float humidityInt) {
		if (this.humidityInt != humidityInt) {
			this.humidityInt = humidityInt;
			notifyHumidityIntChanged();
		}
	}

	/**
	 * @return temperatureConsigne
	 */
	public float getTemperatureConsigne() {
		return temperatureConsigne;
	}

	/**
	 * @param temperatureConsigne
	 */
	public void setTemperatureConsigne(float temperatureConsigne) {
		if (this.temperatureConsigne != temperatureConsigne){
			this.temperatureConsigne = temperatureConsigne;
			notifyTemperatureConsigneChanged();
		}
	}
	
	/**
	 * @return alertCondensation
	 */
	public int getAlert() {
		return alertCondensation;
	}
	
	/**
	 * @param alertCondensation
	 */
	public void setAlert(int alert) {
		if(this.alertCondensation != alert) {
			this.alertCondensation = alert;
			notifyAlertChanged();
		}
	}

	/**
	 * @return alertTemp
	 */
	public int getAlertTemp() {
		return alertTemp;
	}
	
	/**
	 * @param alertTemp
	 */
	public void setAlertTemp(int alert) {
		if(this.alertTemp != alert) {
			this.alertTemp = alert;
			notifyAlertTempChanged();
		}
	}
	/**
	 * add 0.5 to the consign
	 */
	public void addTemperatureConsigne() {
		this.setTemperatureConsigne((float) (this.getTemperatureConsigne() + 0.5));
	}

	/**
	 * Subtract 0.5 to the consign
	 */
	public void decreaseTemperatureConsigne() {
		this.setTemperatureConsigne((float) (this.getTemperatureConsigne() - 0.5));
	}

	/**
	 * @param observer
	 */
	public void addObserver(ModelObserver observer) {
		list.add(observer);
	}

	/**
	 * @param observer
	 */
	public void removeObserver(ModelObserver observer) {
		list.remove(observer);
	}

	/**
	 * notify observer of temperatureInt Change
	 */
	private void notifyTemperatureIntChanged() {
		for (ModelObserver observer : list) {
			observer.onTemperatureIntChanged(this.temperatureInt);
		}
	}
	
	/**
	 * notify observer of temperatureExt Change
	 */
	private void notifyTemperatureExtChanged() {
		for(ModelObserver observer : list) {
			observer.onTemperatureExtChanged(this.temperatureExt);
		}
	}

	/**
	 * notify observer of humidity Change
	 */
	private void notifyHumidityIntChanged() {
		for (ModelObserver observer : list) {
			observer.onHumidityChanged(this.humidityInt);
		}
	}

	/**
	 * notify observer of consign Change
	 */
	private void notifyTemperatureConsigneChanged() {
		for (ModelObserver observer : list) {
			observer.onTemperatureConsigneChanged(this.temperatureConsigne);
		}
	}
	
	/**
	 * notify observer of alertCondensation Change
	 */
	private void notifyAlertChanged() {
		for (ModelObserver observer : list) {
			observer.onAlertChanged(this.alertCondensation);
		}
	}
	
	/**
	 * notify observer of alertTemp Change
	 */
	private void notifyAlertTempChanged() {
		for (ModelObserver observer : list) {
			observer.onAlertTempChanged(this.alertTemp);
		}
	}

}