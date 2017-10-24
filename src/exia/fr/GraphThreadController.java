package exia.fr;

public class GraphThreadController implements Runnable{

	private float inTemp = 0;
	private float extTemp = 26;
	private float consigne = 18;
	private float humidity = 0;
	private GraphTemperature graphTemp;
	private GraphHumidity graphHumid;
	
	public GraphThreadController(GraphTemperature graphTemp, GraphHumidity graphHumid) {
		this.graphTemp = graphTemp;
		this.graphHumid = graphHumid;
	}
	
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

	public void setInTemp(float inTemp) {
		this.inTemp = inTemp;
	}

	public void setExtTemp(float extTemp) {
		this.extTemp = extTemp;
	}

	public void setConsigne(float consigne) {
		this.consigne = consigne;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	
	

}
