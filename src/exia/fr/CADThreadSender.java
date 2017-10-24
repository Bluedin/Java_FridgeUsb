package exia.fr;

public class CADThreadSender implements Runnable{

	private ContainerConsigne listConsigne;
	
	private ICAD icad;
	
	public CADThreadSender(ICAD icad, Model model) {
		this.icad = icad;
		this.listConsigne = new ContainerConsigne(model);
	}

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
