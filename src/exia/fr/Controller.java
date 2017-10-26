package exia.fr;

public class Controller implements IController, ModelObserver {

	private View view;
	private Model model;
	private CAD cad;

	public Controller() {
		this.model = new Model();
		model.addObserver(this);
		this.view = new View(this, 18);
		this.cad = new CAD(this.model);

		
		this.view.setVisible(true);
	}

	@Override
	public void onTemperatureIntChanged(float value) {
		this.view.setFieldTemperature(value);
	}

	@Override
	public void onHumidityChanged(float value) {
		this.view.setFieldHumidity(value);
	}
	
	@Override
	public void onTemperatureExtChanged(float value) {
		this.view.setFieldTemperatureExt(value);
	}

	@Override
	public void onTemperatureConsigneChanged(float value) {
		this.view.setLabelConsigne(value);
	}

	@Override
	public void onAlertChanged(int value) {
		this.view.setAlertImage(value);
	}

	@Override
	public void onConsignePlus() {
		this.model.addTemperatureConsigne();
	}

	@Override
	public void onConsigneMinus() {
		this.model.decreaseTemperatureConsigne();
	}

}
