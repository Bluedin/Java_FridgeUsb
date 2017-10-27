package exia.fr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

/**
 * Controller 
 * Control interaction between the 
 * different  component of the program
 */
public class Controller implements ModelObserver, ActionListener{

	private View view;
	private Model model;
	private CAD cad;
	private Thread temperatureEvolution;

	/**
	 * constructor for controller
	 * Instantiate model, view
	 * Instantiate temperatureEvolution
	 * which analyze data from the model to create alert
	 * Start temperatureEvolution
	 * Instantiate CAD
	 */
	public Controller() {
		this.model = new Model();
		model.addObserver(this);
		JFrame.setDefaultLookAndFeelDecorated(true);
		Controller controller = this;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
				} catch (Exception e) {
					System.out.println(e);
					System.out.println("Substance Graphite failed to initialize");
				}
				view = new View(18);
				view.setVisible(true);
				view.buttonConsignePlus.addActionListener(controller);
				view.buttonConsigneMinus.addActionListener(controller);
			}
		});
		
		this.temperatureEvolution = new Thread(new TemperatureEvolution(this.model));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.temperatureEvolution.start();
		this.cad = new CAD(this.model);

		
	}

	/* 
	 * @see exia.fr.ModelObserver#onTemperatureIntChanged(float)
	 * set field temperature (interior) in view
	 */
	@Override
	public void onTemperatureIntChanged(float value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setFieldTemperature(value);
			}
		});
	}

	/* 
	 * @see exia.fr.ModelObserver#onHumidityChanged(float)
	 * set field humidity in view
	 */
	@Override
	public void onHumidityChanged(float value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setFieldHumidity(value);
			}
		});
	}
	
	/* 
	 * @see exia.fr.ModelObserver#onTemperatureExtChanged(float)
	 * set field temperatureExt in view
	 */
	@Override
	public void onTemperatureExtChanged(float value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setFieldTemperatureExt(value);
			}
		});
	}

	/* 
	 * @see exia.fr.ModelObserver#onTemperatureConsigneChanged(float)
	 * set label consign in view
	 */
	@Override
	public void onTemperatureConsigneChanged(float value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setLabelConsigne(value);
			}
		});
	}

	/* 
	 * @see exia.fr.ModelObserver#onAlertChanged(int)
	 * Pass value of alert to the view
	 */
	@Override
	public void onAlertChanged(int value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setAlertImage(value);
			}
		});
	}

	/* 
	 * @see exia.fr.ModelObserver#onAlertTempChanged(int)
	 * set the alert text in view
	 */
	@Override
	public void onAlertTempChanged(int value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				switch(value) {
				case 0 :
					view.setAlertTempText("Stable");
					break;
				case 1 :
					view.setAlertTempText("ERROR PELTIER");
					break;
				case 2 :
					view.setAlertTempText("DOOR OPEN");
					break;
				case 3 : 
					view.setAlertTempText("DEFFECTIVE COMPONENT");
					break;
				}
			}
		});
	}
	
	/* 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * get the button used in the view and add or decrease the consign
	 * in the model in consequence
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton btn = (JButton)event.getSource();
		String label = btn.getText();
		switch (label) {
		case "+":
			this.model.addTemperatureConsigne();
			break;

		case "-":
			this.model.decreaseTemperatureConsigne();
			break;
		}	
	}

}
