package exia.fr;

/**
 * Simulation class
 * Simulate the data to send
 * temperatureInt, temperatureExt, humidity, alert
 */
public class Simulation implements Runnable{

	private Model model;
	private double inTemperature;
	private double extTemperature;
	private double humidity;
	private double consigne;
	private int alert;
	private java.text.DecimalFormat df = new java.text.DecimalFormat("0.#");
	
	/**
	 * @param model
	 * set the different base data 
	 */
	public Simulation(Model model) {
		this.model = model;
		this.inTemperature = 25;
		this.extTemperature = 25;
		this.humidity = 70;
		this.consigne = 18;
		this.alert = 0;
	}
	
	/**
	 * @return the inTemperature as double
	 */
	public double getInTemperature() {
		return this.inTemperature;
	}
	
	/**
	 * @return the extTemperature as double
	 */
	public double getExtTemperature() {
		return this.extTemperature;
	}
	
	/**
	 * @return the humidity as double 
	 */
	public double getHumidity() {
		return this.humidity;
	}
	
	/**
	 * @return the alert as int
	 */
	public int getAlert() {
		return this.alert;
	}
	
	/**
	 * @param consigne
	 * set consigne attribute
	 */
	public void setConsigne(double consigne) {
		this.consigne = consigne;
	}
	
	/**
	 * simulate one row of data
	 */
	public void randomizer() {
		double diffTempInOut = Math.abs(this.extTemperature - this.inTemperature);
		if(diffTempInOut < 1) {
			diffTempInOut = 1;
		}
		if(inTemperature - this.consigne > 0) {
			this.inTemperature = this.getInTemperature() - 0.5 / diffTempInOut;
		}else {
			this.inTemperature = this.getInTemperature() + 0.5;
		}
		if((int) (Math.random() * 2) == 1) {
			this.extTemperature = this.getExtTemperature() + Math.random() * 0.5;
		}else {
			this.extTemperature = this.getExtTemperature() - Math.random() * 0.5;
		}
		if((int) (Math.random() * 2) == 1) {
			this.humidity = this.getHumidity() + Math.random() * 0.5;
		}else {
			this.humidity = this.getHumidity() - Math.random() * 0.5;
		}
		double ptRosee = 237.7 * (17.27 * this.inTemperature / (237.7 + this.inTemperature) + Math.log(this.humidity/100)) 
				/ (17.27  - (17.27 * this.inTemperature / (237.7 + this.inTemperature) + Math.log(this.humidity/100)));
		double diffCondens = this.inTemperature - ptRosee;
		
		//System.out.println("PtRosee : " + ptRosee + " - Difference : " + diffCondens);
		if(diffCondens > 5) {
			this.alert = 0;
		}else if (diffCondens > 3){
			this.alert = 1;
		}else if (diffCondens > 2) {
			this.alert = 2;
		}else if(diffCondens <= 1){
			this.alert = 3;
		}
	}

	/* 
	 * @see java.lang.Runnable#run()
	 * do one simulation of data and 
	 * send it to the model
	 */
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.randomizer();
			this.model.setTemperatureInt(Float.parseFloat(df.format(this.inTemperature).replaceAll(",", ".")));
			this.model.setTemperatureExt(Float.parseFloat(df.format(this.extTemperature).replaceAll(",", ".")));
			this.model.setHumidityInt(Float.parseFloat(df.format(this.humidity).replaceAll(",", ".")));
			this.model.setAlert(this.alert);
		}
	}

	
	
}
