// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.commands.CraneTOCom;
import frc.robot.commands.DrivetrainTOCom;
import frc.robot.commands.LEDTOCom;
import frc.robot.commands.LimelightTOCom;
import frc.robot.subsystems.Crane;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LEDs;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {  
  private SequentialCommandGroup autoMode;
  public PlayerConfigs driver;
  public PlayerConfigs coDriver;
  //Subsystem Declarations

  public static final Drivetrain drivetrain = new Drivetrain(
    Constants.LEFT_DRIVE_TRAIN_FORWARD,
    Constants.LEFT_DRIVE_TRAIN_BACK,
    Constants.RIGHT_DRIVE_TRAIN_FORWARD,
    Constants.RIGHT_DRIVE_TRAIN_BACK,
    Constants.DROP_WHEEL_LEFT,
    Constants.DROP_WHEEL_RIGHT
  );

  public static final Crane crane = new Crane(
    Constants.CRANE_ROTATION_LEAD,
    Constants.CRANE_ROTATION_FOLLOW,
    Constants.CRANE_EXTENDER,
    Constants.CRANE_STINGER,
    Constants.CRANE_WRIST
  );
  
  public static final Limelight limelight = new Limelight();

  public static final LEDs ledStrip = new LEDs(0,30);
  public static final LEDs ledEyes = new LEDs(1,18);

  //Controllers
  public static final XboxController controller0 = new XboxController(Constants.DRIVER_CONTROLLER_0);
  public static final XboxController controller1 = new XboxController(Constants.DRIVER_CONTROLLER_1);

  //Test Timer & Flag
  Timer timer = new Timer();

  public static Alliance teamColor;

  /*
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    HDD.initBot();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    drivetrain.m_drive.feed();
    SmartDashboard.putNumber("Match Time",Timer.getMatchTime());
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
    //Need LED Indicator Here
    autoMode = HDD.auto_chooser.getSelected();
    autoMode.schedule();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    teamColor = DriverStation.getAlliance();
    driver = HDD.driver_chooser.getSelected();
    coDriver = HDD.coDriver_chooser.getSelected();
    Robot.crane.setDefaultCommand(new CraneTOCom());
    Robot.drivetrain.setDefaultCommand(new DrivetrainTOCom());
    Robot.ledStrip.setDefaultCommand(new LEDTOCom());
    Robot.limelight.setDefaultCommand(new LimelightTOCom());
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    driver.getDriverConfig();
    coDriver.getCoDriverConfig();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    ledStrip.rainbow();
    if((Timer.getFPGATimestamp() + 0.5) % 5 < 0.5){
      ledEyes.blinkingEyes(DriverStation.getAlliance(),6,false);
    }else if((Timer.getFPGATimestamp() + 1.5) % 5 < 0.5){
      ledEyes.blinkingEyes(DriverStation.getAlliance(),8,false);
    } else if((Timer.getFPGATimestamp() + 2.5) % 5 < 0.5){
      ledEyes.blinkingEyes(DriverStation.getAlliance(),7,true);
    } else{
      ledEyes.blinkingEyes(DriverStation.getAlliance(),7,false);
  }
    HDD.updateStartupConfig();
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
