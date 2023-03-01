package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
import frc.robot.commands.Autonomous.Autonomous_Routines.PlaceMoveCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.PlaceMoveStop;
import frc.robot.commands.Autonomous.Autonomous_Routines.BlueLoadCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.BlueLoadOut;
import frc.robot.commands.Autonomous.Autonomous_Routines.BlueWallCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.BlueWallOut;
import frc.robot.commands.Autonomous.Autonomous_Routines.TestMode;
import frc.robot.commands.Autonomous.Autonomous_Routines.RedLoadCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.RedLoadOut;
import frc.robot.commands.Autonomous.Autonomous_Routines.RedWallCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.RedWallOut;

public class HDD {    
    public static SendableChooser<SequentialCommandGroup> auto_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> driver_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> coDriver_chooser = new SendableChooser<>();

    //Auto Commands
    public static PlaceMoveCharge placeMoveCharge = new PlaceMoveCharge();
    public static PlaceMoveStop placeMoveStop = new PlaceMoveStop();
    public static BlueLoadOut blueLoadOut = new BlueLoadOut();
    public static BlueLoadCharge blueLoadCharge = new BlueLoadCharge();
    public static BlueWallCharge blueWallCharge = new BlueWallCharge();
    public static BlueWallOut blueWallOut = new BlueWallOut();
    public static RedLoadCharge redLoadCharge = new RedLoadCharge();
    public static RedLoadOut redLoadOut = new RedLoadOut();
    public static RedWallCharge redWallCharge = new RedWallCharge();
    public static RedWallOut redWallOut = new RedWallOut();
    public static TestMode test = new TestMode();

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
        auto_chooser.setDefaultOption("Place Move Charge", placeMoveCharge);
        auto_chooser.addOption("Place Move Stop", placeMoveStop);
        auto_chooser.addOption("Test Mode", test);
        auto_chooser.addOption("Blue Load Out", blueLoadOut);
        auto_chooser.addOption("Blue Load Charge", blueLoadCharge);
        auto_chooser.addOption("Blue Wall Charge", blueWallCharge);
        auto_chooser.addOption("Blue Wall Out", blueWallOut);
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
        LiveWindow.disableAllTelemetry();
        LiveWindow.enableTelemetry(Robot.drivetrain.gyro);
    }

    public static void updateStartupConfig(){
        desiredMode = auto_chooser.getSelected();
    }
}
