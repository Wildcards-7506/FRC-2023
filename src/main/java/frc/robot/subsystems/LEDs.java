package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase{
    private AddressableLED ledString;
    private AddressableLEDBuffer ledBuffer;

    // Store what the last hue of the first pixel is
    private int m_rainbowFirstPixelHue;

    public LEDs(int PWMPort, int BufferSize){
        ledString = new AddressableLED(PWMPort);
        ledBuffer = new AddressableLEDBuffer(BufferSize);

        ledString.setLength(BufferSize);
        ledString.setData(ledBuffer);
        ledString.start();
    }

    public void update() {
      ledString.setData(ledBuffer);
    }

    public void rainbow() {
        // For every pixel
        for (var i = 18; i < 48; i++) {
          // Calculate the hue - hue is easier for rainbows because the color
          // shape is a circle so only one value needs to precess
          final var hue = (m_rainbowFirstPixelHue + (i * 180 / 30)) % 180;
          // Set the value
          ledBuffer.setHSV(i, hue, 255, 255);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;
        update();
    }

    public void solid(int hue, int sat, int val) {
        // For every pixel
        for (var i = 18; i < 48; i++) {
          // Set the value
          ledBuffer.setHSV(i, hue, sat, val);
        }
        update();
    }

    public void teamColor(Alliance isRed) {
        // For every pixel
        for (var i = 18; i < 48; i++) {
          // Set the value
          int hue = (isRed == Alliance.Red) ? 0 : 120;
          ledBuffer.setHSV(i, hue, 255, 255);
        }
        update();
    }

    public void solidEyes(int hue, Alliance isRed) {
      // For every pixel
      int irisHue = (isRed == Alliance.Red) ? 0 : 120;
      for (var i = 0; i < 18; i++) {
        // Set the value
        ledBuffer.setHSV(i, hue, 255,255);
      }
      ledBuffer.setHSV(7, irisHue, 255, 255);
      ledBuffer.setHSV(10, irisHue, 255, 255);
      update();
    }

    public void blinkingEyes(Alliance isRed, int irisLoc, boolean blink){
      int hue = (isRed == Alliance.Red) ? 0 : 120;
      for (var i = 0; i < 18; i++) {
        // Set the value
        ledBuffer.setHSV(i, 255, 0, 255);
      }
      ledBuffer.setHSV(irisLoc, hue, 255, 255);
      ledBuffer.setHSV(irisLoc + 3, hue, 255, 255);
      if(blink){
        for (var i = 0; i < 18; i++) {
          // Set the value
          ledBuffer.setHSV(i, 255, 0, 0);
        }
      }
      update();
    }
}