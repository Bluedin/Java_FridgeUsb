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

public class ImagePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2215535843885453577L;
	private List<BufferedImage> image = new ArrayList<BufferedImage>();
	private int alert = 1;
	
	public ImagePanel() {
		//System.out.println(System.getProperty("java.class.path"));
		//System.out.println(this.getClass().getResource("").getPath());
		//String path = this.getClass().getResource("/Avertissement/frigorest.png").getPath();
		try {
			//System.out.println(path);
			//this.image.add(ImageIO.read(new File(path + "image/1.jpg")));
			//this.image.add(ImageIO.read(new File("../image/1.jpg")));
			//System.out.println(this.image.add(ImageIO.read(new File("image/1.jpg"))));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigorest.png").getPath())));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigono.png").getPath())));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigolow.png").getPath())));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigohigh.png").getPath())));
			this.image.add(ImageIO.read(new File(this.getClass().getResource("/Avertissement/frigocondensation.png").getPath())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(image.get(alert).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT), 0, 0, this);
	}
	
	public void setAlert(int value) {
		this.alert = value + 1;
		this.repaint();
	}
}
