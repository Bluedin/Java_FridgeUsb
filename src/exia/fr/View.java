package exia.fr;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

	private static final long serialVersionUID = -3963025861937096826L;
	public final JPanel contentPane;
	public final JLabel fieldTemperature;
	public final JLabel fieldHumidity;
	public final JLabel labelConsigne;
	public JButton buttonConsignePlus;
	public JButton buttonConsigneMinus;
	private final Action action = new actionButtonPlus();
	private final Action action_1 = new actionButtonMinus();
	private final IController controller;
	private ImagePanel AlertPanel;
	private GraphThreadController graphThread;
	private GraphTemperature graphTemperature;
	private GraphHumidity graphHumidity;
	// private DrawGraph graph;
	// private List<BufferedImage> image = newArrayList<BufferedImage>();

	public View(IController controller, double tempConsigne) {
		this.controller = controller;

		/*
		try {
			image.add(ImageIO.read(new File("image/")));
			image.add(ImageIO.read(new File("image/")));
			image.add(ImageIO.read(new File("image/")));
			image.add(ImageIO.read(new File("image/")));
		} catch (IOException ex) {
		}*/

		setTitle("Frigo GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Temp\u00E9rature : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblHumidit = new JLabel("Humidit\u00E9 : ");
		lblHumidit.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblConsigne = new JLabel("Consigne : ");
		lblConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldTemperature = new JLabel("0\u00B0C");
		fieldTemperature.setForeground(Color.RED);
		fieldTemperature.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldHumidity = new JLabel("0%");
		fieldHumidity.setForeground(Color.BLUE);
		fieldHumidity.setFont(new Font("Tahoma", Font.PLAIN, 18));

		labelConsigne = new JLabel(Double.toString(tempConsigne) + "\u00B0C");
		labelConsigne.setForeground(Color.ORANGE);
		labelConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));

		buttonConsignePlus = new JButton("+");
		buttonConsignePlus.setAction(action);
		buttonConsignePlus.setFont(new Font("Tahoma", Font.PLAIN, 18));

		buttonConsigneMinus = new JButton("-");
		buttonConsigneMinus.setAction(action_1);
		buttonConsigneMinus.setFont(new Font("Tahoma", Font.PLAIN, 18));

		// this.AlertPanel = newJPanel();
		this.AlertPanel = new ImagePanel();
		this.AlertPanel.paintComponents(AlertPanel.getGraphics());

		this.graphTemperature = new GraphTemperature("Temperature Graph");
		this.graphTemperature.setVisible(true);
		
		this.graphHumidity = new GraphHumidity("Humidity Graph");
		this.graphHumidity.setVisible(true);
		
		this.graphThread = new GraphThreadController(this.graphTemperature, this.graphHumidity);
		Thread thread = new Thread(this.graphThread);
		thread.start();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(fieldTemperature,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblHumidit, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(fieldHumidity,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblConsigne, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(buttonConsignePlus, GroupLayout.PREFERRED_SIZE, 53,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(buttonConsigneMinus, GroupLayout.PREFERRED_SIZE, 53,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(labelConsigne, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldTemperature, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHumidit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldHumidity, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(44)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonConsignePlus, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(buttonConsigneMinus, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	public JLabel getFieldTemperature() {
		return fieldTemperature;
	}

	public JLabel getFieldHumidity() {
		return fieldHumidity;
	}

	public JLabel getLabelConsigne() {
		return labelConsigne;
	}

	public void setFieldTemperature(float temperature) {
		this.fieldTemperature.setText(Float.toString(temperature) + "\u00B0C");
		this.graphThread.setInTemp(temperature);
		this.repaint();
	}
	
	public void setFieldTemperatureExt(float temperature) {
		//this.fieldTemperatureExt.setText(Float.toString(temperature) + "\u00B0C");
		this.graphThread.setExtTemp(temperature);
		this.repaint();
	}

	public void setFieldHumidity(float humidity) {
		this.fieldHumidity.setText(Float.toString(humidity) + "%");
		this.graphThread.setHumidity(humidity);
		this.repaint();
	}

	public void setLabelConsigne(float consigne) {
		this.labelConsigne.setText(Float.toString(consigne) + "\u00B0C");
		this.graphThread.setConsigne(consigne);
		this.repaint();
	}

	public void setAlertImage(int alert) {
		this.AlertPanel.setAlert(alert);
	}

	private class actionButtonPlus extends AbstractAction {
		public actionButtonPlus() {
			putValue(NAME, "+");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			controller.onConsignePlus();
		}
	}

	private class actionButtonMinus extends AbstractAction {
		public actionButtonMinus() {
			putValue(NAME, "-");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			controller.onConsigneMinus();
		}
	}
}
