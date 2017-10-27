package exia.fr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

public class Controller implements ModelObserver, ActionListener{

	private View view;
	private Model model;
	private CAD cad;
	private Thread temperatureEvolution;

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

	@Override
	public void onTemperatureIntChanged(float value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setFieldTemperature(value);
			}
		});
	}

	@Override
	public void onHumidityChanged(float value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setFieldHumidity(value);
			}
		});
	}
	
	@Override
	public void onTemperatureExtChanged(float value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setFieldTemperatureExt(value);
			}
		});
	}

	@Override
	public void onTemperatureConsigneChanged(float value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setLabelConsigne(value);
			}
		});
	}

	@Override
	public void onAlertChanged(int value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setAlertImage(value);
			}
		});
	}

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
