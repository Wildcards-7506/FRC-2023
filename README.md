# Team 7506 2023 Robot Code
## Introduction
This repository contains 2023 Charged Up robot code for FRC Team 7506 Wildcards.  

Our robot, Name TBD, will feature:
- Field-Relative Hexacanum drive
- Red and Blue scoring autonomous modes from all areas of the community
- Five Operatioinal crane positions along with fine correction control

And more!

## Code Structure
Our operational code will be built in the following structure. As you complete work on your subsystem, please identify completed tasks.
Progress tags available are: [NOT STARTED], [IN WORK], [IN TEST], and [COMPLETE]

- Robot and Drivestation
  - Heads Down Display 
      - Camera Feed [NOT STARTED]
      - Encoder Feedback [NOT STARTED]
      - Power Panel Readouts [NOT STARTED]
      - Match Timer [NOT STARTED]
      - Driver Selections [NOT STARTED]
      - Autonomous Selections [NOT STARTED]
  - Player Configs 
      - Driver Configurations [IN WORK]
      - Crane Operator Configurations [IN WORK]
  - Autonomous
      - Path Planning [NOT STARTED]
      - Autonomous Commands [IN WORK]
      - Pathweaver Reading Procedure [COMPLETE]
  - Robot Container [IN WORK]
      - Subsystem Declarations [IN WORK]
      - Section Commands [IN WORK]
  - Robot Subsystems 
      - Drivetrain 
          - Mecanum Control [IN WORK]
          - Drop Wheel Tank Drive Control [IN WORK]
          - Orientation Snap [NOT STARTED]
          - Fine Control [NOT STARTED]
          - NOX Function [NOT STARTED]
          - Odometry [IN WORK]
      - Crane
          - Rotation Setpoints [NOT STARTED]
          - Extension Setpoints [NOT STARTED]
          - Claw Actuation [NOT STARTED]
      - Limelight
          - Automatic Cone Alignment [NOT STARTED]
      - Communication LEDs
          - Team Color (Teleop standard) [NOT STARTED]
          - Autonomous Pattern (Up to designer) [NOT STARTED]
          - Endgame Pattern (Up to designer) [NOT STARTED]
          - Solid Green - Driver collection Feedback [NOT STARTED]
          - Solid White - Cone Alignment Target Found [NOT STARTED]
