#include "DHT.h"
#define DHTPIN 5     
#define DHTTYPE DHT22  
char inputBuffer[10];
int ExtPin = 6;
int alert = 0;
int Peltier = 3;
float consigne = 18;

int ThermistorPin = 0;
float diode;
int Vo;
float R1 = 10000;
float logR2, R2, T;
float c1 = 1.009249522e-03, c2 = 2.378405444e-04, c3 = 2.019202697e-07;

DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(9600);
  pinMode(ExtPin, OUTPUT);
  pinMode(Peltier, OUTPUT);
  dht.begin();
  delay(2000);
}

void loop() {

  delay(1000);
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();
  // Read temperature as Fahrenheit (isFahrenheit = true)
  float f = dht.readTemperature(true);
  
  Vo = analogRead(ThermistorPin);
  R2 = R1 * (1023.0 / (float)Vo - 1.0);
  logR2 = log(R2);
  T = (1.0 / (c1 + c2*logR2 + c3*logR2*logR2*logR2));
  T = T - 273.15;

  // Check if any reads failed and exit early (to try again).
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Compute heat index in Fahrenheit (the default)
  float hif = dht.computeHeatIndex(f, h);
  // Compute heat index in Celsius (isFahreheit = false)
  float hic = dht.computeHeatIndex(t, h, false);

  float ptRosee = 237.7 * (17.27 * t / (237.7 + t) + log(h/100)) / (17.27 - (17.27 * t / (237.7 + t) + log(h/100)));

  int ecart = t-ptRosee;

  if(t<ptRosee+1){
    alert = 3;
  digitalWrite(ExtPin, HIGH);  
  }else{    
    switch(ecart){
      case 1 :
        alert = 2;
        digitalWrite(ExtPin, HIGH);
        break;
      case 2 :
        alert = 1;
        digitalWrite(ExtPin, LOW);
        break;
      default :
        alert = 0;
        digitalWrite(ExtPin, LOW);
        break;
    }
  }
  
  Serial.print("Data;");
  Serial.print(t);
  Serial.print(";");
  Serial.print(T);
  Serial.print(";");
  Serial.print(h);
  Serial.print(";");
  Serial.println(alert);

  

  

  if(Serial.available()>0){
    Serial.readBytes(inputBuffer, 10);
    String inString = (String) inputBuffer;
    delay(1000);
    consigne = inString.toFloat() ;
  }

  if(consigne < t){
    digitalWrite(Peltier, HIGH);
  }else{
    digitalWrite(Peltier, LOW);
  }
}
