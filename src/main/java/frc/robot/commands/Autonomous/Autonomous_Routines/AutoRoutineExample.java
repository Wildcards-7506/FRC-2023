package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoClawPosition;

public class AutoRoutineExample extends SequentialCommandGroup {

  public AutoRoutineExample(){
    
    addCommands(     
          new AutoClawPosition(-8, true)
      );
  }
} 