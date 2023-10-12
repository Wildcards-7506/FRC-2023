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
import frc.robot.commands.CraneTeleopCommand;
import frc.robot.commands.DrivetrainTeleopCommand;
import frc.robot.commands.LEDTeleopCommand;
import frc.robot.commands.LimelightRotatorTeleopCom;
import frc.robot.commands.LimelightTeleopCommand;
import frc.robot.commands.PinchersTeleopCommand;
import frc.robot.subsystems.Crane;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LimelightRotator;
import frc.robot.subsystems.Pinchers;
import frc.robot.subsystems.HDD.HDD;
import frc.robot.subsystems.LEDs;
import frc.robot.util.Logger;

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

  public static final DriveSubsystem drivetrain = new DriveSubsystem();

  public static final Crane crane = new Crane(
    Constants.CANID.CRANE_ROTATION_LEAD,
    Constants.CANID.CRANE_ROTATION_FOLLOW,
    Constants.CANID.CRANE_EXTENDER,
    Constants.CANID.CRANE_STINGER,
    Constants.CANID.CRANE_WRIST
  );
  
  public static final Pinchers pinchers = new Pinchers(
    Constants.CANID.PINCH_LEFT,
    Constants.CANID.PINCH_RIGHT
  );
  public static final Limelight limelight = new Limelight();

  public static final LimelightRotator ll_rotator = new LimelightRotator(Constants.CANID.LIMELIGHT_ROTATOR);

  public static final LEDs ledSystem = new LEDs(0,48);

  //Controllers
  public static final XboxController controller0 = new XboxController(Constants.IOConstants.DRIVER_CONTROLLER_0);
  public static final XboxController controller1 = new XboxController(Constants.IOConstants.DRIVER_CONTROLLER_1);

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
    Logger.info("SYSTEM","Robot Started");
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
    //SmartDashboard.putNumber("Match Time",Timer.getMatchTime());
    HDD.updateStartupConfig();
    HDD.grid.updateCells();
    HDD.grid.clearPrevSelect(HDD.grid.getSelectedCell());
  }

  @Override
  public void autonomousInit() {
    Logger.info("SYSTEM","Autonomous Program " + HDD.desiredMode.getName() + " Started");
    teamColor = DriverStation.getAlliance();
    CommandScheduler.getInstance().cancelAll();
    //Need LED Indicator Here
    autoMode = HDD.auto_chooser.getSelected();
    autoMode.schedule();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    ledSystem.rainbow();
    ledSystem.solidEyes(15, teamColor);
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    Logger.info("SYSTEM","Teleop Started");
    CommandScheduler.getInstance().cancelAll();
    teamColor = DriverStation.getAlliance();
    driver = HDD.driver_chooser.getSelected();
    coDriver = HDD.coDriver_chooser.getSelected();
    Robot.crane.setDefaultCommand(new CraneTeleopCommand());
    Robot.drivetrain.setDefaultCommand(new DrivetrainTeleopCommand());
    Robot.ledSystem.setDefaultCommand(new LEDTeleopCommand());
    Robot.limelight.setDefaultCommand(new LimelightTeleopCommand());
    Robot.pinchers.setDefaultCommand(new PinchersTeleopCommand());
    Robot.ll_rotator.setDefaultCommand(new LimelightRotatorTeleopCom());
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
  public void disabledInit() {
    Logger.info("SYSTEM", "Robot Disabled");
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    ledSystem.rainbow();
    if((Timer.getFPGATimestamp() + 0.5) % 5 < 0.5){
      ledSystem.blinkingEyes(DriverStation.getAlliance(),3,false);
    }else if((Timer.getFPGATimestamp() + 1.5) % 5 < 0.5){
      ledSystem.blinkingEyes(DriverStation.getAlliance(),5,false);
    } else if((Timer.getFPGATimestamp() + 2.5) % 5 < 0.5){
      ledSystem.blinkingEyes(DriverStation.getAlliance(),4,true);
    } else{
      ledSystem.blinkingEyes(DriverStation.getAlliance(),4,false);
    }
    HDD.updateStartupConfig();
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
