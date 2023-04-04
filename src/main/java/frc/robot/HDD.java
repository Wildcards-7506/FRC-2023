package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.ControlConfigs.Drivers.Ryan;
import frc.robot.ControlConfigs.Drivers.Anthony;
import frc.robot.ControlConfigs.Drivers.Jayden;
import frc.robot.ControlConfigs.Drivers.Lam;
import frc.robot.ControlConfigs.Drivers.Ricardo;
import frc.robot.commands.Autonomous.Autonomous_Routines.OverTheTopCharge;
import frc.robot.commands.Autonomous.Autonomous_Routines.PlaceMoveStop;
import frc.robot.commands.Autonomous.Autonomous_Routines.TwoPieceAuto;
import frc.robot.commands.Autonomous.Autonomous_Routines.DevelopmentMode;
import frc.robot.commands.Autonomous.Autonomous_Routines.ScoreOnly;

public class HDD {    
    public static SendableChooser<SequentialCommandGroup> auto_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> driver_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> coDriver_chooser = new SendableChooser<>();

    //Auto Commands
    public static OverTheTopCharge overTop = new OverTheTopCharge();
    public static PlaceMoveStop placeMoveStop = new PlaceMoveStop();
    public static TwoPieceAuto twoPiece = new TwoPieceAuto();
    public static ScoreOnly scoreOnly = new ScoreOnly();
    public static DevelopmentMode test = new DevelopmentMode();

    //Drivers options
    public static PlayerConfigs ryan = new Ryan();
    public static PlayerConfigs anthony = new Anthony();
    public static PlayerConfigs jayden = new Jayden();
    public static PlayerConfigs lam = new Lam();
    public static PlayerConfigs ricardo = new Ricardo();

    public static SequentialCommandGroup desiredMode;

    public static void initBot(){

        // Auto choosers
        auto_chooser.setDefaultOption("Score, Don't Move", scoreOnly);
        auto_chooser.addOption("Place Move Stop", placeMoveStop);
        auto_chooser.addOption("Two Piece", twoPiece);
        auto_chooser.addOption("Charge Station", overTop);
        auto_chooser.addOption("Test Mode", test);

        // Driver choosers
        driver_chooser.setDefaultOption("Ryan", ryan);
        driver_chooser.addOption("Jayden", jayden);
        driver_chooser.addOption("Anthony", anthony);
        driver_chooser.addOption("Ricardo", ricardo);
        driver_chooser.addOption("Lam", lam);
        

        // Co-Driver choosers
        coDriver_chooser.setDefaultOption("Anthony", anthony);
        coDriver_chooser.addOption("Ricardo", ricardo);
        coDriver_chooser.addOption("Ryan", ryan);
        coDriver_chooser.addOption("Jayden", jayden);
        coDriver_chooser.addOption("Lam", lam);
        

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
