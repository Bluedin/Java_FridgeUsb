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
 * GraphHumidity
 * Construct chart for humidity
 */
public class GraphHumidity extends ApplicationFrame{
	private static final long serialVersionUID = -1L;
	private static final String TITLE = "Humidity ( Real time )";
	private DynamicTimeSeriesCollection dataset;
	private float[] newData = new float[1];

	/**
	 * Constructor
	 *
	 * @param title
	 * 
	 * create dataset
	 */
	public GraphHumidity(final String title) {
		super(title);
		dataset = new DynamicTimeSeriesCollection(3, 60, new Second());
		dataset.setTimeBase(new Second());
		dataset.addSeries(new float[1], 0, "Humidity");
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
		final JFreeChart result = ChartFactory.createTimeSeriesChart(TITLE, "Time", "Humidity", dataset, true, true,
				false);
		final XYPlot plot = result.getXYPlot();
		ValueAxis domain = plot.getDomainAxis();
		domain.setAutoRange(true);
		ValueAxis range = plot.getRangeAxis();
		range.setRange(-10, 100);
		return result;
	}

	/**
	 * Update data
	 *
	 * @param serie
	 * @param value
	 * update with new value of humidity
	 */

	public void update(float humidity) {


		dataset.advanceTime();
		newData[0] = humidity;
		dataset.appendData(newData);


	}
}
