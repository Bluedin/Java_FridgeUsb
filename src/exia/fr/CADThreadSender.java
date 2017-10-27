package exia.fr;

/**
 * CAD ThreadSender class 
 * To instantiate thread which 
 * manage the sending of the consign
 * with an interval of 4 seconds
 *
 */
public class CADThreadSender implements Runnable{

	private ContainerConsigne listConsigne;
	
	private ICAD icad;
	
	/**
	 * @param icad
	 * @param model
	 */
	public CADThreadSender(ICAD icad, Model model) {
		this.icad = icad;
		this.listConsigne = new ContainerConsigne(model);
	}

	/* 
	 * @see java.lang.Runnable#run()
	 * use setConsigne with icad to send the first 
	 * consign stocked and delete it
	 * do it in a loop with an interval of 4 seconds
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!this.listConsigne.list.isEmpty()) {
				this.icad.setConsigne(this.listConsigne.list.get(0));
				this.listConsigne.list.remove(0);
			}
		}
		
	}
	
}
