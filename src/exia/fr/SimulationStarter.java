package exia.fr;

public class SimulationStarter implements ICAD {

	private Thread simulation;
	
	public SimulationStarter(Model model) {
		this.simulation = new Thread(new Simulation(model));
	}
	
	@Override
	public void start() {
		this.simulation.start();
	}

	@Override
	public void setConsigne(double value) {
		// TODO Auto-generated method stub
		
	}

}
