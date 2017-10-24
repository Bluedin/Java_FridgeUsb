package exia.fr;

public interface ModelObserver {
	void onTemperatureIntChanged(float value);

	void onHumidityChanged(float value);

	void onTemperatureConsigneChanged(float value);

	void onAlertChange(int value);
}
