package exia.fr;

public class Controller implements IController, ModelObserver {

	private View view;
	private Model model;

	public Controller() {
		this.model = new Model();
		model.addObserver(this);

		this.view = new View(this, 18);
		this.view.setVisible(true);
	}

	@Override
	public void onTemperatureIntChanged(double value) {
		this.view.setFieldTemperature(Double.toString(value));
	}

	@Override
	public void onHumidityChanged(double value) {
		this.view.setFieldHumidity(Double.toString(value));
	}

	@Override
	public void onTemperatureConsigneChanged(double value) {
		this.view.setLabelConsigne(Double.toString(value));
	}

	@Override
	public void onAlertChange(int value) {
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
