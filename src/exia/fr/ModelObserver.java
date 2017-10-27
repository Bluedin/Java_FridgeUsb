package exia.fr;

/**
 * Interface to be implemented by observer of the model
 * inform of change for temperatureInt,
 * temperatureExt, humidity, consign
 * alertCondensation, alertTemp
 *
 */
public interface ModelObserver {
	/**
	 * @param value
	 */
	void onTemperatureIntChanged(float value);
	
	/**
	 * @param value
	 */
	void onTemperatureExtChanged(float value);

	/**
	 * @param value
	 */
	void onHumidityChanged(float value);

	/**
	 * @param value
	 */
	void onTemperatureConsigneChanged(float value);

	/**
	 * @param value
	 */
	void onAlertChanged(int value);
	
	/**
	 * @param value
	 */
	void onAlertTempChanged(int value);
	
}
