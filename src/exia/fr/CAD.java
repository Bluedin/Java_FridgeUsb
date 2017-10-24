package exia.fr;

public class CAD {

	private Model model;
	private ICAD icad;
	private Thread cadThreadSender;
	
	public CAD(Model model) {
		this.model = model;
		this.icad = new Arduino(this.model);
		//this.icad = new SimulationStarter(this.model);
		this.icad.start();
		this.cadThreadSender = new Thread(new CADThreadSender(this.icad, this.model));
		this.cadThreadSender.start();
	}
	
	
}
