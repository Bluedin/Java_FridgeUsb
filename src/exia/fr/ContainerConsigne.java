package exia.fr;

import java.util.ArrayList;
import java.util.List;

public class ContainerConsigne implements ModelObserver{

	public List<Double> list = new ArrayList<Double>();
	
	@Override
	public void onTemperatureIntChanged(double value) {
		
	}

	@Override
	public void onHumidityChanged(double value) {
		
	}

	@Override
	public void onTemperatureConsigneChanged(double value) {
		this.list.add(value);
		
	}

	@Override
	public void onAlertChange(int value) {
		
	}

}
