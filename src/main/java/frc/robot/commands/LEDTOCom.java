// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.ControlConfigs.PlayerConfigs;
// import frc.robot.Robot;

// public class LEDTOCom extends CommandBase{
    
//     public LEDTOCom(){
//         addRequirements(Robot.ledSystem);
//     }
    
//     @Override
//     public void execute (){
//         //Strip
//         if (Robot.limelight.getTV() == 1 & 
//             (PlayerConfigs.highGoal || PlayerConfigs.lowGoal || PlayerConfigs.align)){
//             if (Math.abs(Robot.limelight.getTX()) < 2){
//                 Robot.ledSystem.solid(60,255,255);
//             } else {
//                 Robot.ledSystem.solid(90,255,255);
//             }
//         } else if ( PlayerConfigs.collectPos | PlayerConfigs.groundGrab) {
//             if(Robot.crane.getStingerCurrent() > 10){
//                 Robot.ledSystem.solid(60,255,255);
//             } else {
//                 Robot.ledSystem.rainbow();
//             }
//         } else {
//             Robot.ledSystem.teamColor(Robot.teamColor);
//         }

//         //Eyes
//         if(PlayerConfigs.signalCone){
//             Robot.ledSystem.solidEyes(30, Robot.teamColor); 
//         } else if(PlayerConfigs.signalCube){
//             Robot.ledSystem.solidEyes(150, Robot.teamColor); 
//         }
//     } 
// }
