package exia.fr;

import java.util.ArrayList;
import java.util.List;

public class ContainerConsigne implements ModelObserver{

	public List<Float> list = new ArrayList<Float>();
	
	public ContainerConsigne(Model model) {
		model.addObserver(this);
	}
	
	@Override
	public void onTemperatureIntChanged(float value) {
		
	}

	@Override
	public void onTemperatureExtChanged(float value) {
		
	}

	@Override
	public void onHumidityChanged(float value) {
		
	}

	@Override
	public void onTemperatureConsigneChanged(float value) {
		this.list.add(value);
		
	}

	@Override
	public void onAlertChanged(int value) {
		
	}

	@Override
	public void onAlertTempChanged(int value) {
		
	}

}
