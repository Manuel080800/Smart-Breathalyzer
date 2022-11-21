#define BLYNK_TEMPLATE_ID "TMPLumjIUOKO"
#define BLYNK_DEVICE_NAME "Alcoholimetro Inteligente"
#define BLYNK_AUTH_TOKEN "gJaJLqj7II-X8UvJ060vceCFmmjzd-gy"

#define BLYNK_PRINT Serial

#include <Wire.h>
#include <WiFiClient.h>
#include <ESP8266WiFi.h>
#include <Adafruit_MLX90614.h>
#include <BlynkSimpleEsp8266.h>

char auth[] = BLYNK_AUTH_TOKEN;
char ssid[] = "HUAWEI";
char pass[] = "12345678";
char host[] = "192.168.43.52";

BlynkTimer timer;
Adafruit_MLX90614 mlx = Adafruit_MLX90614();

bool blynk = false;
int alcohol = A0;
int button = D4;
double calibration_temperature = 2.36;

int push;
double valueArcohol;
double calibration_alcohol;
double valueTemperatureAmbiente;
double valueTemperatureObjective;

void sendDataBlynk()
{
  double valueArcohol = analogRead(alcohol) - calibration_alcohol;
  double valueTemperatureAmbiente = mlx.readAmbientTempC();
  double valueTemperatureObjective = mlx.readObjectTempC() + calibration_temperature;
  Blynk.virtualWrite(V0, valueArcohol);
  Blynk.virtualWrite(V1, valueTemperatureObjective);
  Serial.println("Send data at Blynk");
}

void setup()
{
    Serial.begin(9600);
    pinMode(button, INPUT);

    if (digitalRead(button) == HIGH) {
        blynk = true;
    }

    Serial.print("Calibration sensor arcohol");
    double middle = 0;
    for (int i = 0; i < 100; i++) {
      middle = middle + analogRead(alcohol);
      Serial.print(".");
      delay(200);
    }
    calibration_alcohol = middle/100;
    Serial.println("");
    Serial.print("Value of calibration is: " + String(calibration_alcohol));
    mlx.begin();

    if (blynk) {
        Blynk.begin(auth, ssid, pass);
        timer.setInterval(1000L, sendDataBlynk);
    } else {
        Serial.println("");
        Serial.print("Connect a ");
        Serial.println(ssid);
        WiFi.mode(WIFI_STA);
        delay(100);
        WiFi.begin(ssid, pass);
        while (WiFi.status() != WL_CONNECTED) {
          delay(100);
          Serial.print(".");
        }
        Serial.println("");
        Serial.println(WiFi.localIP());
    }
}

void loop()
{
    if (blynk){
        Blynk.run();
        timer.run();
    } else {
        double valueArcohol = analogRead(alcohol) - calibration_alcohol;
        double valueTemperatureAmbiente = mlx.readAmbientTempC();
        double valueTemperatureObjective = mlx.readObjectTempC() + calibration_temperature;

        if (digitalRead(button) == HIGH) {
            Serial.println("Push on ON");
            WiFiClient client;
            Serial.print("Conneting to " + String(host));
            if (!client.connect(host, 2121)) {
                Serial.println(".");
                return;
            }
            Serial.println("Sending message to server");
            client.print(String(valueArcohol) + ":" + String(valueTemperatureObjective) + ":" + String(valueTemperatureAmbiente));
            Serial.println("Clossing connection with the server");
            client.flush();
            client.stop();
            delay(500);
        } else {
            delay(100);
        }

        Serial.println(String(valueArcohol) + ":" + String(valueTemperatureObjective) + ":" + String(valueTemperatureAmbiente));
    }
}


