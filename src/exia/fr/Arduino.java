package exia.fr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * Arduino class 
 * Communicate with the arduino
 */
public class Arduino implements SerialPortEventListener, ICAD {

	
	// Attributs 
	
	private SerialPort serialPort;
	private BufferedReader input;
	private OutputStream output;
	private Model model;
	
	
	/**
	 * @param model
	 * set model attribute
	 */
	public Arduino(Model model) {
		this.model = model;
	}
	
	
	// Lecture
	
	/* 
	 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
	 * get the data from arduino
	 * parse the data
	 * set the data in the model
	 */
	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.getEventType() != SerialPortEvent.DATA_AVAILABLE) {
			return;
		}
		
		try {
			
			while(!input.ready());
			
			String inputLine = input.readLine();
			if(inputLine.matches("^Data.*")) {
				String[] data = inputLine.split(";");
				
				
				float temperatureInt = Float.parseFloat(data[1]);
				float temperatureExt = Float.parseFloat(data[2]);
				float humidityInt = Float.parseFloat(data[3]);
				int alert = Integer.parseInt(data[4]);
	
				this.model.setTemperatureInt(temperatureInt);
				this.model.setTemperatureExt(temperatureExt);
				this.model.setHumidityInt(humidityInt);
			}else {
				System.out.println(inputLine);
			}
			

			
		} catch (Exception ex) {
			System.err.println("Erreur de lecture : " + ex.getMessage());
		}
		
	}
	
	
	
	/* 
	 * @see exia.fr.ICAD#setConsigne(double)
	 * send the consign to the arduino 
	 */
	@Override
	public void setConsigne(double value) {
		try{
			this.output = this.serialPort.getOutputStream();
		} catch(IOException e) {System.out.println();}
		try {
			this.output.write(Double.toString(value).getBytes());
		} catch (IOException e) {e.printStackTrace();}
	}
	 
	 
	 
	 /* 
	 * @see exia.fr.ICAD#start()
	 * set the configuration of the arduino
	 * and of the protocol
	 */
	@Override
	 public void start() {    
			
			// R�cuperer tous les ports utilis�s pour la liaison � la carte Arduino
			CommPortIdentifier serialPortId = null;
			Enumeration enumComm = CommPortIdentifier.getPortIdentifiers();
			
			// Trouver le port qui sert � la communication avec la carte Arduino
			while(enumComm.hasMoreElements())
			{
				serialPortId = (CommPortIdentifier)enumComm.nextElement();
				
				if(serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL)
				{
					break;
				}
			}
			
			// Si aucun port n'est r�cup�r�
			if (serialPortId == null) {
				System.err.println("Erreur : Aucune Arduino connect�e.");
				return;
			}
			
			// Afficher le port COM connect�
			System.out.println("Port COM connect� : " + serialPortId.getName());
			
		
			try {
				// D�finir les caract�ristiques de communication du port
		        this.serialPort = (SerialPort) serialPortId.open("Main", 2000);
		        this.serialPort.setSerialPortParams(9600,
		                SerialPort.DATABITS_8,
		                SerialPort.STOPBITS_1,
		                SerialPort.PARITY_NONE);
		
		        // Ouvrir les flux de communication
		        this.input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		        this.output = this.serialPort.getOutputStream();


		        this.serialPort.addEventListener(this);
		        this.serialPort.notifyOnDataAvailable(true);
		        
		        this.output.write(0);
		        
		    } catch (Exception e) {
		        System.err.println(e.toString());
		    }

	    }

	
}