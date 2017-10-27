# Java_FridgeUsb
## 1. Preface
For our first engineering school year at the CESI EXIA, we made an automatic cooling can fridge.
The main purpose of this project was to make in practice the principles we studied and the circuits we made.
Here is a list of the constraints:
	The cooling of the fridge must be automatic and respect the user’s orders
	The fridge must avoid condensation and alert the user
	We must use an Arduino and a Java application
	The user must have a window which indicate the different values (temperature, humidity…) and a graph
	The user can change the order in an easy way
	The java code implementation must be in MVC (Model-View-Controller)
To regulate the temperature in the fridge, we wire the Arduino Uno at two sensors, a Peltier device and a fan.
## 2. Conception
<h3>1. Thermodynamic</h3>
We had to study the thermodynamic field in order to understand how to avoid the condensation on the can.
We saw that condensation depends of the pressure, the relative humidity and the temperature which are linked to the dew point. It can be calculated with the following formula:  
<i>Tdp = (237.7*(17.27*T/(237.7+T)+ln⁡(RH) ))/(17.27*(17.27*T/(237.7+T)+ln⁡(RH) ) )</i>
 where T is the temperature (°C) and RH is the relative humidity (%). 
To avoid the condensation, we need a temperature in the fridge that is superior to the temperature of the dew point. By acquiring humidity, we can use the fan to expulse the humidity excess outside of the fridge and then decrease the dew point.

<h3>2. Sensors</h3>
To check the temperature and the relative humidity within the fridge, we use a DHT22 sensor which provide to us these data.
These data are sent to the Arduino Digital pin in this order: 1byte for the integral RH part, 1 byte for the decimal RH part, 1 byte for the integral Temperature part, 1 byte for the decimal Temperature part and 1 byte for the checksum.
With a thermistor, we can acquire the outside temperature to prevent temperature changes in the fridge to regulate the PWM (Pulse Width Modulation) for the Peltier device.
To acquire the temperature with the thermistor we have to put a 10kΩ resistor beforehand and then we wire it to the analog input of the Arduino. We receive a tension and we translate it in temperature with a calculation.

<h3>3. Peltier device </h3>
The Peltier effect is the presence of heating or cooling at an electrified junction of two different conductors. When a current is made to flow through a junction between two conductors, A and B, heat may be generated or removed at the junction.
A typical Peltier heat pump involves multiple junctions in series, through which a current is driven. Some of the junctions lose heat due to the Peltier effect, while others gain heat. Thermoelectric heat pumps exploit this phenomenon, as do thermoelectric cooling devices found in refrigerators.

<h3>4. PWM (Pulse Width Modulation) </h3>
To regulate the power of the Peltier device we use the PWM pin of the Arduino. The power is alternating between 0 and 100% until the set point is reached. The PWM is easier in terms of feasibility and quicker to use than a PID (proportional–integral–derivative) controller.

<h3>5. PID</h3>
A proportional–integral–derivative controller (PID controller or three term controller) is a control loop feedback mechanism widely used in industrial control systems and a variety of other applications requiring continuously modulated control. A PID controller continuously calculates an error value e(t) as the difference between a desired setpoint and a measured process variable and applies a correction based on proportional, integral, and derivative terms (denoted P, I, and D respectively) which give their name to the controller.

<h3>6. Control engineering</h3>
The Arduino has a software in C++ that read all the data that comes on its pins and send them to the Java HMI. If the temperature in the fridge is equal or inferior to the dew point temperature calculated by the Arduino, the user receives an alert. 
<img src="https://github.com/Bluedin/Java_FridgeUsb/blob/master/Doc/ArduinoCode.JPG"> 

<h3>7. Java Frame</h3>
On this Frame we see the Temperature inside and outside the fridge, the Relative Humidity and the temperature order. We can manually change the temperature order with the buttons “+” and “-”.
<img src="https://github.com/Bluedin/Java_FridgeUsb/blob/master/Doc/HMI.JPG">
<img src="https://github.com/Bluedin/Java_FridgeUsb/blob/master/Doc/HMI2.JPG">

<h3>8. Communication Protocol Arduino to Java</h3>
Protocol of the Arduino communication with Java, Data initialize the frame. The type of values must be in float.
<i>Data;interior temperature;exterior temperature;humidity;alarm</i>
The java application sends the user orders to Arduino. The type of the value must be in float.
<i>User order</i>

<h2>3. View of the project</h2>
<img src="https://github.com/Bluedin/Java_FridgeUsb/blob/master/Doc/Fridge.jpg">
