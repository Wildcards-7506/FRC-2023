package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.simulation.AddressableLEDSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase{
    private AddressableLED m_led;
    private AddressableLEDBuffer m_ledBuffer;

    private AddressableLEDSim m_led_sim;
    // Store what the last hue of the first pixel is
    private int m_rainbowFirstPixelHue;

    //Shooter constructor - creates a shooter in robot memory
    public LEDs(int pwmPort, int bufferSize){
        m_led = new AddressableLED(pwmPort);
        m_ledBuffer = new AddressableLEDBuffer(bufferSize);

        m_led_sim = new AddressableLEDSim(m_led);
        m_led_sim.setLength(bufferSize);

        m_led.setLength(bufferSize);
        m_led.setData(m_ledBuffer);
        m_led.start();
    }

    public void update() {
        m_led.setData(m_ledBuffer);
    }

    public void rainbow() {
        // For every pixel
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          // Calculate the hue - hue is easier for rainbows because the color
          // shape is a circle so only one value needs to precess
          final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
          // Set the value
          m_ledBuffer.setHSV(i, hue, 255, 255);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;
        update();
    }

    public void solid(int hue, int sat, int val) {
        // For every pixel
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          // Set the value
          m_ledBuffer.setHSV(i, hue, sat, val);
        }
        update();
    }

    public void teamColor(Alliance isRed) {
        // For every pixel
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          // Set the value
          int hue = (isRed == Alliance.Red) ? 0 : 120;
          m_ledBuffer.setHSV(i, hue, 255, 255);
        }
        update();
    }

    public void solidEyes(int hue, Alliance isRed) {
      // For every pixel
      int irisHue = (isRed == Alliance.Red) ? 0 : 120;
      for (var i = 0; i < m_ledBuffer.getLength(); i++) {
        // Set the value
        m_ledBuffer.setHSV(i, hue, 255,255);
      }
      m_ledBuffer.setHSV(7, irisHue, 255, 255);
      m_ledBuffer.setHSV(10, irisHue, 255, 255);
      update();
    }

    public void blinkingEyes(Alliance isRed, int irisLoc, boolean blink){
      int hue = (isRed == Alliance.Red) ? 0 : 120;
      for (var i = 0; i < 18; i++) {
        // Set the value
        m_ledBuffer.setHSV(i, 255, 0, 255);
      }
      m_ledBuffer.setHSV(irisLoc, hue, 255, 255);
      m_ledBuffer.setHSV(irisLoc + 3, hue, 255, 255);
      if(blink){
        for (var i = 6; i < 12; i++) {
          // Set the value
          m_ledBuffer.setHSV(i, 255, 0, 0);
        }
      }
      update();
    }
}