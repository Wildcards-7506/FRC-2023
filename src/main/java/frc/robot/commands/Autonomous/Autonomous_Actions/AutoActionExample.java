package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Subsystem_Commands.SubsystemCommandExample;

//These classes extend a Sequential Command group to execute a series of commands in a given order
public class AutoActionExample extends SequentialCommandGroup {

  public AutoActionExample(/*You can pass arguments in these classes to adapt to different situations - HINT: Cones vs. Cubes*/){
    
    //The following will add three commands to the scheduler, which will execute in the order provided.
    //Note that Command 2 is a Parallel Command group, which will execute multiple commands at the same time. 
    //This can be useful when you want to drive and do another action simultaneously
    addCommands(
      new SubsystemCommandExample(1),
      new ParallelCommandGroup(
        new SubsystemCommandExample(0),
        new SubsystemCommandExample(2),
        new SubsystemCommandExample(3)),
      new SubsystemCommandExample(2)
    );
  }
} 