package exia.fr;

public class SimulationStarter implements ICAD {

	private Thread simulationThread;
	private Simulation simulation;
	
	public SimulationStarter(Model model) {
		this.simulation = new Simulation(model);
		this.simulationThread = new Thread(this.simulation);
	}
	
	@Override
	public void start() {
		this.simulationThread.start();
	}

	@Override
	public void setConsigne(double value) {
		this.simulation.setConsigne(value);
	}

}
