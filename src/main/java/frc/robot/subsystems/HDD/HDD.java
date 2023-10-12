package frc.robot.subsystems.HDD;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.ControlConfigs.Drivers.Ryan;
import frc.robot.ControlConfigs.Drivers.Anthony;
import frc.robot.ControlConfigs.Drivers.Ricardo;
import frc.robot.commands.Autonomous.Autonomous_Routines.ChargeEngage;
import frc.robot.commands.Autonomous.Autonomous_Routines.ChargeMobility;
import frc.robot.commands.Autonomous.Autonomous_Routines.PlaceMoveStop;
import frc.robot.commands.Autonomous.Autonomous_Routines.TwoPieceAutoLeft;
import frc.robot.commands.Autonomous.Autonomous_Routines.TwoPieceAutoRight;
import frc.robot.commands.Autonomous.Autonomous_Routines.DevelopmentMode;
import frc.robot.commands.Autonomous.Autonomous_Routines.ScoreOnly;

public class HDD {  
    private static String autoModePrev = "";
    
    public static SendableChooser<SequentialCommandGroup> auto_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> driver_chooser = new SendableChooser<>();
    public static SendableChooser<PlayerConfigs> coDriver_chooser = new SendableChooser<>();

    //Auto Commands
    public static ChargeEngage chargeEngage = new ChargeEngage();
    public static ChargeMobility chargeMobility = new ChargeMobility();
    public static PlaceMoveStop placeMoveStop = new PlaceMoveStop();
    public static TwoPieceAutoLeft twoPieceLeft = new TwoPieceAutoLeft();
    public static TwoPieceAutoRight twoPieceRight = new TwoPieceAutoRight();
    public static ScoreOnly scoreOnly = new ScoreOnly();
    public static DevelopmentMode test = new DevelopmentMode();

    //Drivers options
    public static PlayerConfigs ryan = new Ryan();
    public static PlayerConfigs anthony = new Anthony();
    public static PlayerConfigs ricardo = new Ricardo();

    public static SequentialCommandGroup desiredMode;

    public static Grid grid = new Grid();

    public static void initBot(){
        // Auto choosers
        auto_chooser.setDefaultOption("Score, Don't Move", scoreOnly);
        auto_chooser.addOption("Place Move Stop", placeMoveStop);
        auto_chooser.addOption("Two Piece Left", twoPieceLeft);
        auto_chooser.addOption("Two Piece Right", twoPieceRight);
        auto_chooser.addOption("Charge Station Engage", chargeEngage);
        auto_chooser.addOption("Charge Station Mobility", chargeMobility);
        auto_chooser.addOption("Test Mode", test);

        // Driver choosers
        driver_chooser.setDefaultOption("Ryan", ryan);
        driver_chooser.addOption("Anthony", anthony);
        driver_chooser.addOption("Ricardo", ricardo);        

        // Co-Driver choosers
        coDriver_chooser.setDefaultOption("Anthony", anthony);
        coDriver_chooser.addOption("Ricardo", ricardo);
        coDriver_chooser.addOption("Ryan", ryan);        

        // Put the choosers on the dashboard
        SmartDashboard.putData(driver_chooser);
        SmartDashboard.putData(coDriver_chooser);
        SmartDashboard.putData(auto_chooser);

        SmartDashboard.putBoolean("H1", false);
        SmartDashboard.putBoolean("H2", false);
        SmartDashboard.putBoolean("H3", false);
        SmartDashboard.putBoolean("H4", false);
        SmartDashboard.putBoolean("H5", false);
        SmartDashboard.putBoolean("H6", false);
        SmartDashboard.putBoolean("H7", false);
        SmartDashboard.putBoolean("H8", false);
        SmartDashboard.putBoolean("H9", false);
        SmartDashboard.putBoolean("M1", false);
        SmartDashboard.putBoolean("M2", false);
        SmartDashboard.putBoolean("M3", false);
        SmartDashboard.putBoolean("M4", false);
        SmartDashboard.putBoolean("M5", false);
        SmartDashboard.putBoolean("M6", false);
        SmartDashboard.putBoolean("M7", false);
        SmartDashboard.putBoolean("M8", false);
        SmartDashboard.putBoolean("M9", false);
        SmartDashboard.putBoolean("L1", false);
        SmartDashboard.putBoolean("L2", false);
        SmartDashboard.putBoolean("L3", false);
        SmartDashboard.putBoolean("L4", false);
        SmartDashboard.putBoolean("L5", false);
        SmartDashboard.putBoolean("L6", false);
        SmartDashboard.putBoolean("L7", false);
        SmartDashboard.putBoolean("L8", false);
        SmartDashboard.putBoolean("L9", false);
    }

    public static void updateStartupConfig(){
        desiredMode = auto_chooser.getSelected();
        if(desiredMode.getName() != autoModePrev){
            switch (desiredMode.getName()){
                case "ScoreOnly" : 
                    Robot.drivetrain.resetOdometry(scoreOnly.pose);
                    break;
                case "PlaceMoveStop" :
                    Robot.drivetrain.resetOdometry(placeMoveStop.pose);
                    break;
                case "TwoPieceAutoLeft" : 
                    Robot.drivetrain.resetOdometry(twoPieceLeft.pose);
                    break;
                case "TwoPieceAutoRight" : 
                    Robot.drivetrain.resetOdometry(twoPieceRight.pose);
                    break;
                case "ChargeEngage" : 
                    Robot.drivetrain.resetOdometry(chargeEngage.pose);
                    break;
                case "ChargeMobility" : 
                    Robot.drivetrain.resetOdometry(chargeMobility.pose);
                    break;
                case "DevelopmentMode" : 
                    Robot.drivetrain.resetOdometry(test.pose);
                    break;
            }
            autoModePrev = desiredMode.getName();
        }
    }
}