package exia.fr;

import java.util.ArrayList;
import java.util.List;

/**
 * ContainerConsigne Class which implements ModelObserver
 * Get the consign when it changes and add it to the list 
 * in the attribute
 * To be used for CADThreadSender 
 */
public class ContainerConsigne implements ModelObserver{

	
	/**
	 * list of float containing the consign 
	 * in chronological order
	 */
	public List<Float> list = new ArrayList<Float>();
	
	/**
	 * @param model
	 * Constructor of ContainerConsigne
	 */
	public ContainerConsigne(Model model) {
		model.addObserver(this);
	}
	
	/* 
	 * @see exia.fr.ModelObserver#onTemperatureIntChanged(float)
	 */
	@Override
	public void onTemperatureIntChanged(float value) {
		
	}

	/* 
	 * @see exia.fr.ModelObserver#onTemperatureExtChanged(float)
	 */
	@Override
	public void onTemperatureExtChanged(float value) {
		
	}

	/* 
	 * @see exia.fr.ModelObserver#onHumidityChanged(float)
	 */
	@Override
	public void onHumidityChanged(float value) {
		
	}

	/* 
	 * @see exia.fr.ModelObserver#onTemperatureConsigneChanged(float)
	 * add the new consign at the end of the
	 * list in the attribute
	 */
	@Override
	public void onTemperatureConsigneChanged(float value) {
		this.list.add(value);
		
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
