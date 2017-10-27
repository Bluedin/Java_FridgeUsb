package exia.fr;

/**
 * GraphThreadController class
 * To be instantiated as Thread
 * Update the graphs
 *
 */
public class GraphThreadController implements Runnable{

	private float inTemp = 0;
	private float extTemp = 26;
	private float consigne = 18;
	private float humidity = 0;
	private GraphTemperature graphTemp;
	private GraphHumidity graphHumid;
	
	/**
	 * Constuctor
	 * 
	 * @param graphTemp
	 * @param graphHumid
	 */
	public GraphThreadController(GraphTemperature graphTemp, GraphHumidity graphHumid) {
		this.graphTemp = graphTemp;
		this.graphHumid = graphHumid;
	}
	
	/* 
	 * @see java.lang.Runnable#run()
	 * update the graphs every second with 
	 * the data in the object GraphThreadController
	 * attribute
	 * The attribute are updated when there is a change in the model
	 * otherwise the data sent is the same
	 * Assure a continuous update of the chart
	 */
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.graphTemp.update(inTemp, extTemp, consigne);
			this.graphHumid.update(humidity);
		}
	}

	/**
	 * @param inTemp
	 */
	public void setInTemp(float inTemp) {
		this.inTemp = inTemp;
	}

	/**
	 * @param extTemp
	 */
	public void setExtTemp(float extTemp) {
		this.extTemp = extTemp;
	}

	/**
	 * @param consigne
	 */
	public void setConsigne(float consigne) {
		this.consigne = consigne;
	}

	/**
	 * @param humidity
	 */
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	
	

}
