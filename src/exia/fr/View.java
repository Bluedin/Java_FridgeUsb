package exia.fr;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.FlowLayout;
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

import javax.swing.SwingConstants;

/**
 * View class
 * Contains the data of the fridge,
 * buttons to change the consign, 
 * buttons to show the graphs,
 * alert image for condensation changeable
 * alert for error in operation 
 */
public class View extends JFrame {

	private static final long serialVersionUID = -3963025861937096826L;
	public final JPanel contentPane;
	public final JLabel fieldTemperatureInt;
	public final JLabel fieldTemperatureExt;
	public final JLabel fieldHumidity;
	public final JLabel labelConsigne;
	public final JLabel lblAlertTemp;
	public JButton buttonConsignePlus;
	public JButton buttonConsigneMinus;
	private final Action action = new actionButtonPlus();
	private final Action action_1 = new actionButtonMinus();
	public JButton TemperatureGraph;
	public JButton HumidityGraph;
	private final Action action_2 = new actionGraphTemperature();
	private final Action action_3 = new actionGraphHumidity();
	private GraphThreadController graphThread;
	private GraphTemperature graphTemperature;
	private GraphHumidity graphHumidity;
	private ImagePanel alertPanel;
	// private DrawGraph graph;
	// private List<BufferedImage> image = newArrayList<BufferedImage>();

	/**
	 * Constructor
	 * @param tempConsigne
	 */
	public View(double tempConsigne) {

		/*
		 * try { image.add(ImageIO.read(new File("image/"))); image.add(ImageIO.read(new
		 * File("image/"))); image.add(ImageIO.read(new File("image/")));
		 * image.add(ImageIO.read(new File("image/"))); } catch (IOException ex) { }
		 */

		
		setTitle("Frigo GUI");
		getContentPane().setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTemperatureInt = new JLabel("Interior Temperature : ");
		lblTemperatureInt.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblHumidit = new JLabel("Humidity : ");
		lblHumidit.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblConsigne = new JLabel("Consigne : ");
		lblConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblTemperatureExt = new JLabel("Exterior Temperature :");
		lblTemperatureExt.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldTemperatureInt = new JLabel("0\u00B0C");
		fieldTemperatureInt.setForeground(Color.RED);
		fieldTemperatureInt.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldTemperatureExt = new JLabel("0\u00B0C");
		fieldTemperatureExt.setForeground(Color.PINK);
		fieldTemperatureExt.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldHumidity = new JLabel("0%");
		fieldHumidity.setForeground(Color.BLUE);
		fieldHumidity.setFont(new Font("Tahoma", Font.PLAIN, 18));

		labelConsigne = new JLabel(Double.toString(tempConsigne) + "\u00B0C");
		labelConsigne.setForeground(Color.ORANGE);
		labelConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblAlertTemp = new JLabel("Stable");
		lblAlertTemp.setHorizontalAlignment(SwingConstants.CENTER);;
		lblAlertTemp.setForeground(Color.RED);
		lblAlertTemp.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAlertTemp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		

		buttonConsignePlus = new JButton("+");
		buttonConsignePlus.setAction(action);
		buttonConsignePlus.setFont(new Font("Tahoma", Font.PLAIN, 18));

		buttonConsigneMinus = new JButton("-");
		buttonConsigneMinus.setAction(action_1);
		buttonConsigneMinus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		// AlertPanel = new ImagePanel();
		// AlertPanel.paintComponents(AlertPanel.getGraphics());

		this.graphTemperature = new GraphTemperature("Temperature Graph");
		graphTemperature.setLocation(this.getLocation().x+this.getWidth(), this.getLocation().y);
		// this.graphTemperature.setVisible(true);

		this.graphHumidity = new GraphHumidity("Humidity Graph");
		graphHumidity.setLocation(this.getLocation().x+this.getWidth(), this.getLocation().y+graphTemperature.getHeight());
		// this.graphHumidity.setVisible(true);

		this.graphThread = new GraphThreadController(this.graphTemperature, this.graphHumidity);
		Thread thread = new Thread(this.graphThread);
		thread.start();

		alertPanel = new ImagePanel();
		alertPanel.repaint();

		TemperatureGraph = new JButton("Temperature Graph");
		TemperatureGraph.setAction(action_2);

		HumidityGraph = new JButton("Humidity Graph");
		HumidityGraph.setAction(action_3);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblHumidit, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTemperatureExt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblConsigne, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTemperatureInt))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(buttonConsignePlus, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(buttonConsigneMinus, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
								.addComponent(labelConsigne, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addComponent(fieldHumidity, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
								.addComponent(fieldTemperatureInt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
								.addComponent(fieldTemperatureExt))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(alertPanel, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(TemperatureGraph, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(HumidityGraph, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblAlertTemp, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(alertPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTemperatureInt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(fieldTemperatureInt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTemperatureExt)
								.addComponent(fieldTemperatureExt))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHumidit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(fieldHumidity, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(buttonConsignePlus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(buttonConsigneMinus, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.UNRELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAlertTemp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(HumidityGraph, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(TemperatureGraph, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * @return field Temperature
	 */
	public JLabel getFieldTemperature() {
		return fieldTemperatureInt;
	}

	/**
	 * @return Field Humidity
	 */
	public JLabel getFieldHumidity() {
		return fieldHumidity;
	}

	/**
	 * @return label consign
	 */
	public JLabel getLabelConsigne() {
		return labelConsigne;
	}

	/**
	 * @param temperature
	 */
	public void setFieldTemperature(float temperature) {
		this.fieldTemperatureInt.setText(Float.toString(temperature) + "\u00B0C");
		this.graphThread.setInTemp(temperature);
		this.repaint();
	}

	/**
	 * @param temperature
	 */
	public void setFieldTemperatureExt(float temperature) {
		this.fieldTemperatureExt.setText(Float.toString(temperature) + "\u00B0C");
		this.graphThread.setExtTemp(temperature);
		this.repaint();
	}

	/**
	 * @param humidity
	 */
	public void setFieldHumidity(float humidity) {
		this.fieldHumidity.setText(Float.toString(humidity) + "%");
		this.graphThread.setHumidity(humidity);
		this.repaint();
	}

	/**
	 * @param consigne
	 */
	public void setLabelConsigne(float consigne) {
		this.labelConsigne.setText(Float.toString(consigne) + "\u00B0C");
		this.graphThread.setConsigne(consigne);
		this.repaint();
	}

	/**
	 * @param alert
	 */
	public void setAlertImage(int alert) {
		this.alertPanel.setAlert(alert);
	}

	/**
	 * @param alert
	 */
	public void setAlertTempText(String alert) {
		this.lblAlertTemp.setText(alert);
	}

	/**
	 * Action for button plus
	 *
	 */
	private class actionButtonPlus extends AbstractAction {
		public actionButtonPlus() {
			putValue(NAME, "+");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	/**
	 * Action for button minus
	 *
	 */
	private class actionButtonMinus extends AbstractAction {
		public actionButtonMinus() {
			putValue(NAME, "-");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class actionGraphTemperature extends AbstractAction {
		public actionGraphTemperature() {
			putValue(NAME, "Show Temperature Graph");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			if(graphTemperature.isVisible()) {
				graphTemperature.setVisible(false);
			}else {
				graphTemperature.setVisible(true);
			}
		}
	}

	private class actionGraphHumidity extends AbstractAction {
		public actionGraphHumidity() {
			putValue(NAME, "Show Humidity Graph");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			if(graphHumidity.isVisible()) {
				graphHumidity.setVisible(false);
			}else {
				graphHumidity.setVisible(true);
			}
		}
	}
}
