package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCSManuever;

public class PlaceMoveStop extends SequentialCommandGroup {

  public PlaceMoveStop(){
    
    addCommands(
        new AutoScore(0,-10),
        new AutoCSManuever()
      );
  }
} 