package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.ControlConfigs.Drivers.Ryan;
import frc.robot.ControlConfigs.Drivers.Anthony;
import frc.robot.ControlConfigs.Drivers.Shannon;
import frc.robot.ControlConfigs.Drivers.Chantell;
import frc.robot.ControlConfigs.Drivers.Jayden;
import frc.robot.ControlConfigs.Drivers.Lam;
import frc.robot.ControlConfigs.Drivers.Mentor;
import frc.robot.ControlConfigs.Drivers.Thao;
import frc.robot.ControlConfigs.Drivers.Vasquez;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineBlueCenterCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineBlueCenterOut;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineBlueLoadCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineBlueLoadOut;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineBlueWallCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineBlueWallOut;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineExample;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineRedCenterCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineRedCenterOut;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineRedLoadCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineRedLoadOut;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineRedWallCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.AutoRoutineRedWallOut;

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
    public static AutoRoutineExample test = new AutoRoutineExample();

    //Drivers options
    public static PlayerConfigs ryan = new Ryan();
    public static PlayerConfigs anthony = new Anthony();
    public static PlayerConfigs shannon = new Shannon();
    public static PlayerConfigs chantell = new Chantell();
    public static PlayerConfigs jayden = new Jayden();
    public static PlayerConfigs lam = new Lam();
    public static PlayerConfigs thao = new Thao();
    public static PlayerConfigs vasquez = new Vasquez();
    public static PlayerConfigs mentor = new Mentor();

    public static SequentialCommandGroup desiredMode;

    public static void initBot(){

        // Auto choosers
        auto_chooser.setDefaultOption("Test Mode", test);
        auto_chooser.addOption("Blue Center Charge", blueCenterCharge);
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
        driver_chooser.addOption("Chantell", chantell);
        driver_chooser.addOption("Jayden", jayden);
        driver_chooser.addOption("Lam", lam);
        driver_chooser.addOption("Thao", thao);
        driver_chooser.addOption("Vasquez", vasquez);
        driver_chooser.addOption("Mentor", mentor);

        // Co-Driver choosers
        coDriver_chooser.setDefaultOption("Anthony", anthony);
        coDriver_chooser.addOption("Ryan", ryan);
        coDriver_chooser.addOption("Shannon", shannon);
        coDriver_chooser.addOption("Chantell", chantell);
        coDriver_chooser.addOption("Jayden", jayden);
        coDriver_chooser.addOption("Lam", lam);
        coDriver_chooser.addOption("Thao", thao);
        coDriver_chooser.addOption("Vasquez", vasquez);
        coDriver_chooser.addOption("Mentor", mentor);

        // Put the choosers on the dashboard
        SmartDashboard.putData(driver_chooser);
        SmartDashboard.putData(coDriver_chooser);
        SmartDashboard.putData(auto_chooser);
        
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
