package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Modes.AutoRoutineBlueCenterCharge;
import frc.robot.commands.Autonomous.Modes.AutoRoutineBlueCenterOut;
import frc.robot.commands.Autonomous.Modes.AutoRoutineBlueLoadOut;
import frc.robot.commands.Autonomous.Modes.AutoRoutineBlueLoadCharge;
import frc.robot.commands.Autonomous.Modes.AutoRoutineBlueWallCharge;
import frc.robot.commands.Autonomous.Modes.AutoRoutineBlueWallOut;
import frc.robot.commands.Autonomous.Modes.AutoRoutineRedCenterCharge;
import frc.robot.commands.Autonomous.Modes.AutoRoutineRedCenterOut;
import frc.robot.commands.Autonomous.Modes.AutoRoutineRedLoadCharge;
import frc.robot.commands.Autonomous.Modes.AutoRoutineRedLoadOut;
import frc.robot.commands.Autonomous.Modes.AutoRoutineRedWallCharge;
import frc.robot.commands.Autonomous.Modes.AutoRoutineRedWallOut;
import frc.robot.ControlConfigs.Drivers.Ryan;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.ControlConfigs.Drivers.Anthony;
import frc.robot.ControlConfigs.Drivers.Shannon;

public class HDD {    
    public static SendableChooser<SequentialCommandGroup> auto_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> driver_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> coDriver_chooser = new SendableChooser<>();

    //Field display to Shuffleboard
    public static Field2d m_field;
    public static Field2d logo;

    //Auto Commands
    public static AutoRoutineBlueCenterCharge blueCenterCharge = new AutoRoutineBlueCenterCharge();
    public static AutoRoutineBlueCenterOut blueCenterOut = new AutoRoutineBlueCenterOut();
    public static AutoRoutineBlueLoadOut blueLoadOut = new AutoRoutineBlueLoadOut();
    public static AutoRoutineBlueLoadCharge blueLoadCharge = new AutoRoutineBlueLoadCharge();
    public static AutoRoutineBlueWallCharge blueWallCharge = new AutoRoutineBlueWallCharge();
    public static AutoRoutineBlueWallOut blueWallOut = new AutoRoutineBlueWallOut();
    public static AutoRoutineRedCenterCharge redCenterCharge = new AutoRoutineRedCenterCharge();
    public static AutoRoutineRedCenterOut redCenterOut = new AutoRoutineRedCenterOut();
    public static AutoRoutineRedLoadCharge redLoadCharge = new AutoRoutineRedLoadCharge();
    public static AutoRoutineRedLoadOut redLoadOut = new AutoRoutineRedLoadOut();
    public static AutoRoutineRedWallCharge redWallCharge = new AutoRoutineRedWallCharge();
    public static AutoRoutineRedWallOut redWallOut = new AutoRoutineRedWallOut();

    //Drivers options
    public static PlayerConfigs ryan = new Ryan();
    public static PlayerConfigs anthony = new Anthony();
    public static PlayerConfigs shannon = new Shannon();
    
    //public static AutoRoutineExample example = new AutoRoutineExample();

    public static SequentialCommandGroup desiredMode;
    public static SequentialCommandGroup prevMode;


    public static void initBot(){

        // Auto choosers
        auto_chooser.setDefaultOption("Blue Center Charge", blueCenterCharge);
        auto_chooser.addOption("Blue Center Out", blueCenterOut);
        auto_chooser.addOption("Blue Load Out", blueLoadOut);
        auto_chooser.addOption("Blue Load Charge", blueLoadCharge);
        auto_chooser.addOption("Blue Wall Charge", blueWallCharge);
        auto_chooser.addOption("Blue Wall Out", blueWallOut);
        auto_chooser.addOption("Red Center Charge", redCenterCharge);
        auto_chooser.addOption("Red Center Out", redCenterOut);
        auto_chooser.addOption("Red Load Charge", redLoadCharge);
        auto_chooser.addOption("Red Loud Out", redLoadOut);
        auto_chooser.addOption("Red Wall Charge", redWallCharge);
        auto_chooser.addOption("Red Wall Out", redWallOut);

        // Driver choosers
        driver_chooser.setDefaultOption("Ryan", ryan);
        driver_chooser.addOption("Anthony", anthony);
        driver_chooser.addOption("Shannon", shannon);

        // Co-Driver choosers
        coDriver_chooser.setDefaultOption("Anthony", anthony);
        coDriver_chooser.addOption("Ryan", ryan);
        coDriver_chooser.addOption("Shannon", shannon);

        // Put the choosers on the dashboard
        SmartDashboard.putData(auto_chooser);
        //m_chooser.setDefaultOption("Example", example);
        //m_chooser.addOption("Additional Mode", DesiredMode.ADDITIONAL_MODE);

        // Put the choosers on the dashboard
        //SmartDashboard.putData(m_chooser);
        SmartDashboard.putNumber("Custom X",4);
        SmartDashboard.putNumber("Custom Y",4.1);
        SmartDashboard.putNumber("Custom Angle",0.0);
        SmartDashboard.putNumber("Startup Time",1.5);
        SmartDashboard.putData(driver_chooser);
        SmartDashboard.putData(coDriver_chooser);
        
        // Create and push Field2d to SmartDashboard.
        m_field = new Field2d();

        SmartDashboard.putData(m_field);
        LiveWindow.disableAllTelemetry();
        LiveWindow.enableTelemetry(Robot.drivetrain.gyro);
    }

    public static void updateStartupConfig(){
        desiredMode = auto_chooser.getSelected();
    }
}
