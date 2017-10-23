package exia.fr;

public class CAD {

	private Model model;
	private ICAD icad;
	
	public CAD(Model model) {
		this.model = model;
		this.icad = new Arduino(this.model);
	}
	
}
