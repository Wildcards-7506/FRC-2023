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
import frc.robot.commands.Autonomous.Autonomous_Routines.OverTheTopCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.PlaceMoveStop;
import frc.robot.commands.Autonomous.Autonomous_Routines.LeftChargeStation;
import frc.robot.commands.Autonomous.Autonomous_Routines.RightChargeStation;
import frc.robot.commands.Autonomous.Autonomous_Routines.Left2PA;
import frc.robot.commands.Autonomous.Autonomous_Routines.Right2PA;
import frc.robot.commands.Autonomous.Autonomous_Routines.TestMode;
import frc.robot.commands.Autonomous.Autonomous_Routines.ScoreOnly;

public class HDD {    
    public static SendableChooser<SequentialCommandGroup> auto_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> driver_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> coDriver_chooser = new SendableChooser<>();

    //Auto Commands
    public static OverTheTopCharge overTop = new OverTheTopCharge();
    public static PlaceMoveStop placeMoveStop = new PlaceMoveStop();
    public static RightChargeStation rightCS = new RightChargeStation();
    public static LeftChargeStation leftCS = new LeftChargeStation();
    public static Left2PA left2PA = new Left2PA();
    public static Right2PA right2PA = new Right2PA();
    public static ScoreOnly scoreOnly = new ScoreOnly();
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
        auto_chooser.setDefaultOption("Score, Don't Move", scoreOnly);
        auto_chooser.addOption("Place Move Stop", placeMoveStop);
        auto_chooser.addOption("Test Mode", test);
        auto_chooser.addOption("Left Charge Station", leftCS);
        auto_chooser.addOption("Right Charge Station", rightCS);
        auto_chooser.addOption("Left 2 Piece", left2PA);
        auto_chooser.addOption("Right 2 Piece", right2PA);
        auto_chooser.addOption("Over The Top", overTop);

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
