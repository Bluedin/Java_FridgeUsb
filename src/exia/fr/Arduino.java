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

public class Arduino implements SerialPortEventListener, ICAD {

	
	// Attributs 
	
	private SerialPort serialPort;
	private BufferedReader input;
	private OutputStream output;
	
	private Model model;
	
	
	public Arduino(Model model) {
		this.model = model;
	}
	
	
	// Lecture
	
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
	
	
	
	@Override
	public void setConsigne(double value) {
		try{
			this.output = this.serialPort.getOutputStream();
		} catch(IOException e) {System.out.println();}
		try {
			this.output.write(Double.toString(value).getBytes());
		} catch (IOException e) {e.printStackTrace();}
	}
	 
	 
	 
	 @Override
	 public void start() {    
			
			// Récuperer tous les ports utilisés pour la liaison à la carte Arduino
			CommPortIdentifier serialPortId = null;
			Enumeration enumComm = CommPortIdentifier.getPortIdentifiers();
			
			// Trouver le port qui sert à la communication avec la carte Arduino
			while(enumComm.hasMoreElements())
			{
				serialPortId = (CommPortIdentifier)enumComm.nextElement();
				
				if(serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL)
				{
					break;
				}
			}
			
			// Si aucun port n'est récupéré
			if (serialPortId == null) {
				System.err.println("Erreur : Aucune Arduino connectée.");
				return;
			}
			
			// Afficher le port COM connecté
			System.out.println("Port COM connecté : " + serialPortId.getName());
			
		
			try {
				// Définir les caractéristiques de communication du port
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