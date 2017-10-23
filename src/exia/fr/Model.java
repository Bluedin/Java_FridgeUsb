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
	private double temperatureInt = 0;

	/**
	 * Humidité intérieure du réfrigérateur. En pourcentage d'humidité relative dans
	 * l'air.
	 */
	private double humidityInt = 0;

	/**
	 * Température de consigne pour l'asservissement du réfrigérateur. En degrés
	 * celcius.
	 */
	private double temperatureConsigne = 0;

	private Arduino arduino;

	// Getters et Setters

	public double getTemperatureInt() {
		return temperatureInt;
	}

	public void setTemperatureInt(double temperatureInt) {
		if (this.temperatureInt == temperatureInt)
			return;
		this.temperatureInt = temperatureInt;
		notifyTemperatureIntChanged();
	}

	public double getHumidityInt() {
		return humidityInt;
	}

	public void setHumidityInt(double humidityInt) {
		if (this.humidityInt == humidityInt)
			return;
		this.humidityInt = humidityInt;
		notifyHumidityIntChanged();
	}

	public double getTemperatureConsigne() {
		return temperatureConsigne;
	}

	public void setTemperatureConsigne(double temperatureConsigne) {
		if (this.temperatureConsigne == temperatureConsigne)
			return;
		this.temperatureConsigne = temperatureConsigne;
		notifyTemperatureConsigneChanged();
	}

	public void addTemperatureConsigne() {
		this.setTemperatureConsigne(this.getTemperatureConsigne() + 0.5);
	}

	public void decreaseTemperatureConsigne() {
		this.setTemperatureConsigne(this.getTemperatureConsigne() - 0.5);
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