package frc.robot.ControlConfigs.Drivers;

import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class Jayden extends PlayerConfigs{

    public void getDriverConfig() {

        //Driving and rotation
        xMovement = Robot.controller0.getLeftX();
        yMovement = Robot.controller0.getLeftY();
        turnMovement = Robot.controller0.getRightX();
        modeSwitch = Robot.controller0.getTriangleButton();
        snapZero = Robot.controller0.getPOV() == 0;
        snap180 = Robot.controller0.getPOV() == 180;

        //Signal object
        
    }
}
