package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase{
    private AddressableLED strip_led;
    private AddressableLEDBuffer strip_ledBuffer;
    private AddressableLED eye_led;
    private AddressableLEDBuffer eye_ledBuffer;

    // Store what the last hue of the first pixel is
    private int m_rainbowFirstPixelHue;

    public LEDs(int stripPWMPort, int stripBufferSize, int eyePWMPort, int eyeBufferSize){
        strip_led = new AddressableLED(stripPWMPort);
        strip_ledBuffer = new AddressableLEDBuffer(stripBufferSize);

        strip_led.setLength(stripBufferSize);
        strip_led.setData(strip_ledBuffer);
        strip_led.start();

        // eye_led = new AddressableLED(eyePWMPort);
        // eye_ledBuffer = new AddressableLEDBuffer(eyeBufferSize);

        // eye_led.setLength(eyeBufferSize);
        // eye_led.setData(eye_ledBuffer);
        // eye_led.start();
    }

    public void update() {
      strip_led.setData(strip_ledBuffer);
      //eye_led.setData(eye_ledBuffer);
    }

    public void rainbow() {
        // For every pixel
        for (var i = 0; i < strip_ledBuffer.getLength(); i++) {
          // Calculate the hue - hue is easier for rainbows because the color
          // shape is a circle so only one value needs to precess
          final var hue = (m_rainbowFirstPixelHue + (i * 180 / strip_ledBuffer.getLength())) % 180;
          // Set the value
          strip_ledBuffer.setHSV(i, hue, 255, 255);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;
        update();
    }

    public void solid(int hue, int sat, int val) {
        // For every pixel
        for (var i = 0; i < strip_ledBuffer.getLength(); i++) {
          // Set the value
          strip_ledBuffer.setHSV(i, hue, sat, val);
        }
        update();
    }

    public void teamColor(Alliance isRed) {
        // For every pixel
        for (var i = 0; i < strip_ledBuffer.getLength(); i++) {
          // Set the value
          int hue = (isRed == Alliance.Red) ? 0 : 120;
          strip_ledBuffer.setHSV(i, hue, 255, 255);
        }
        update();
    }

    public void solidEyes(int hue, Alliance isRed) {
      // For every pixel
      int irisHue = (isRed == Alliance.Red) ? 0 : 120;
      for (var i = 0; i < eye_ledBuffer.getLength(); i++) {
        // Set the value
        eye_ledBuffer.setHSV(i, hue, 255,255);
      }
      eye_ledBuffer.setHSV(7, irisHue, 255, 255);
      eye_ledBuffer.setHSV(10, irisHue, 255, 255);
      update();
    }

    public void blinkingEyes(Alliance isRed, int irisLoc, boolean blink){
      int hue = (isRed == Alliance.Red) ? 0 : 120;
      for (var i = 0; i < 18; i++) {
        // Set the value
        eye_ledBuffer.setHSV(i, 255, 0, 255);
      }
      eye_ledBuffer.setHSV(irisLoc, hue, 255, 255);
      eye_ledBuffer.setHSV(irisLoc + 3, hue, 255, 255);
      if(blink){
        for (var i = 0; i < 18; i++) {
          // Set the value
          eye_ledBuffer.setHSV(i, 255, 0, 0);
        }
      }
      update();
    }
}