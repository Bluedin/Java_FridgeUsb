package exia.fr;

/**
 * SimulationStarter class
 * Instantiate and start the thread
 * for the simulation
 */
public class SimulationStarter implements ICAD {

	private Thread simulationThread;
	private Simulation simulation;
	
	/**
	 * Constructor
	 * @param model
	 */
	public SimulationStarter(Model model) {
		this.simulation = new Simulation(model);
		this.simulationThread = new Thread(this.simulation);
	}
	
	/* 
	 * @see exia.fr.ICAD#start()
	 * Start 
	 */
	@Override
	public void start() {
		this.simulationThread.start();
	}

	@Override
	public void setConsigne(double value) {
		this.simulation.setConsigne(value);
	}

}
