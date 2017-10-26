package exia.fr;

public interface ModelObserver {
	void onTemperatureIntChanged(float value);
	
	void onTemperatureExtChanged(float value);

	void onHumidityChanged(float value);

	void onTemperatureConsigneChanged(float value);

	void onAlertChanged(int value);
	
}
