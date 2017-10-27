package exia.fr;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * ImagePanel class
 * To be used as alert in the view
 */
public class ImagePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2215535843885453577L;
	private List<BufferedImage> image = new ArrayList<BufferedImage>();
	private int alert = 1;
	
	/**
	 * Constructor
	 * set the different image from src/Avertissement/*
	 */
	public ImagePanel() {
		try {
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigorest.png").getPath())));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigono.png").getPath())));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigolow.png").getPath())));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigohigh.png").getPath())));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigocondensation.png").getPath())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * Update the image
	 */
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(image.get(alert).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT), 0, 0, this);
	}
	
	/**
	 * @param value
	 * set the alert as int
	 * change the image to be shown
	 */
	public void setAlert(int value) {
		this.alert = value + 1;
		this.repaint();
	}
}
