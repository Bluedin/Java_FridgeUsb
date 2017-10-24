package exia.fr;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private List<BufferedImage> image = new ArrayList<BufferedImage>();
	private int alert = 0;
	
	public ImagePanel() {
		//System.out.println(System.getProperty("java.class.path"));
		//System.out.println(this.getClass().getResource("").getPath());
		//String path = this.getClass().getResource("").getPath();
		try {
			//System.out.println(path + "image/1.jpg");
			//this.image.add(ImageIO.read(new File(path + "image/1.jpg")));
			//this.image.add(ImageIO.read(new File("../image/1.jpg")));
			//System.out.println(this.image.add(ImageIO.read(new File("image/1.jpg"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(image.get(alert), 0, 0, this);
	}
	
	public void setAlert(int value) {
		this.alert = value;
		this.paintComponent(this.getGraphics());
	}
}
