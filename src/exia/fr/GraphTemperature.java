package exia.fr;

import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * GraphTemperature
 * Construct graph for temperatureInt,
 * temperatureExt, consign
 */
public class GraphTemperature extends ApplicationFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Intern Temperature, Extern Temperature, Consigne ( Real time )";
	private DynamicTimeSeriesCollection dataset;
	private float[] newData = new float[3];

	/**
	 * Constructor
	 *
	 * @param title
	 * create dataset for Intern Temperature
	 * create dataset for Extern Temperature
	 * create dataset for Consign
	 */
	public GraphTemperature(final String title) {
		super(title);
		dataset = new DynamicTimeSeriesCollection(3, 60, new Second());
		dataset.setTimeBase(new Second());
		dataset.addSeries(new float[1], 0, "Intern Temperature");
		dataset.addSeries(new float[1], 1, "Extern Temperature");
		dataset.addSeries(new float[1], 2, "Consigne");
		JFreeChart chart = createChart(dataset);

		this.add(new ChartPanel(chart), BorderLayout.CENTER);
		this.setDefaultCloseOperation(ApplicationFrame.HIDE_ON_CLOSE);
		this.pack();

	}

	/**
	 * @param dataset
	 * @return JFreeChart
	 * create Chart
	 */
	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(TITLE, "Time", "Temperature", dataset, true, true,
				false);
		final XYPlot plot = result.getXYPlot();
		ValueAxis domain = plot.getDomainAxis();
		domain.setAutoRange(true);
		ValueAxis range = plot.getRangeAxis();
		range.setRange(-10, 50);
		return result;
	}

	/**
	 * Update data
	 *
	 * @param serie
	 * @param value
	 * Update with inTemperature, extTemperature,
	 * consigne
	 */

	public void update(float inTemp, float exTemp, float consigne) {

		dataset.advanceTime();
		
		newData[0] = inTemp;
		dataset.appendData(newData);
		newData[1] = exTemp;
		dataset.appendData(newData);
		newData[2] = consigne;
		dataset.appendData(newData);


	}

}