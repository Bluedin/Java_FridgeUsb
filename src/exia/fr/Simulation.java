package exia.fr;

public class Simulation implements Runnable{

	private Model model;
	private double temperature;
	private double humidity;
	private int alert;
	private java.text.DecimalFormat df = new java.text.DecimalFormat("0.#");
	
	public Simulation(Model model) {
		this.model = model;
		this.temperature = 23;
		this.humidity = 30;
	}
	
	public double getTemperature() {
		return this.temperature;
	}
	
	public double getHumidity() {
		return this.humidity;
	}
	
	public int getAlert() {
		return this.alert;
	}
	
	public void randomizer() {
		if((int) (Math.random() * 2) == 1) {
			this.temperature = this.getTemperature() + Math.random() * 0.5;
		}else {
			this.temperature = this.getTemperature() - Math.random() * 0.5;
		}
		if((int) (Math.random() * 2) == 1) {
			this.humidity = this.getHumidity() + Math.random() * 0.5;
		}else {
			this.humidity = this.getHumidity() - Math.random() * 0.5;
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.randomizer();
			this.model.setTemperatureInt(Float.parseFloat(df.format(this.temperature).replaceAll(",", ".")));
			this.model.setHumidityInt(Float.parseFloat(df.format(this.humidity).replaceAll(",", ".")));
		}
	}

	
	
}
