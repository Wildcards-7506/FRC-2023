// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants;
// import frc.robot.Robot;
// import frc.robot.ControlConfigs.PlayerConfigs;

// public class CraneTOCom extends CommandBase {
    
//     public CraneTOCom() {
//         addRequirements(Robot.crane);
//     }

//     @Override
//     public void execute() {
//         if (PlayerConfigs.openClaw) {
//             Robot.crane.setClaw(Constants.clawOpen);
//             // Robot.crane.setRoller(8);
//         //} else if (PlayerConfigs.closeClaw) Robot.crane.setRoller(-8);
//         } else {
//             Robot.crane.setClaw(Constants.clawClose);
//         }

//         if (PlayerConfigs.groundGrab) {
//             Robot.crane.setExtender(Constants.extenderGround);
//             Robot.crane.setRotator(Constants.rotatorGround);
//         } else if (PlayerConfigs.collectPos){
//             Robot.crane.setExtender(Constants.extenderCollect);
//             Robot.crane.setRotator(Constants.rotatorCollect);
//         } else if (PlayerConfigs.lowGoal) {
//             Robot.crane.setExtender(Constants.extenderMid);
//             Robot.crane.setRotator(Constants.rotatorMid);
//         } else if (PlayerConfigs.highGoal) {
//             Robot.crane.setExtender(Constants.extenderHigh);
//             Robot.crane.setRotator(Constants.rotatorHigh);
//         } else if (Robot.crane.getRotator() < Constants.rotatorCollect && Robot.crane.getExtender() > Constants.extenderCollect) {
//             Robot.crane.setRotator(Constants.rotatorGround);
//             Robot.crane.setExtender(Constants.extenderClose);
//         } else if (Robot.crane.getRotator() > Constants.rotatorCollect && Robot.crane.getExtender() > Constants.extenderCollect) {
//             Robot.crane.setRotator(Constants.rotatorHigh);
//             Robot.crane.setExtender(Constants.extenderHeightLimit);
//         } else {
//             Robot.crane.setRotator(Constants.rotatorClose);
//             Robot.crane.setExtender(Constants.extenderClose);
//         }
//     }
// }
