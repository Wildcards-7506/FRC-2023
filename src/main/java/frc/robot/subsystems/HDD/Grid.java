package frc.robot.subsystems.HDD;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Grid {
    
    public Cell H1 = new Cell();
    public Cell H2 = new Cell();
    public Cell H3 = new Cell();
    public Cell H4 = new Cell();
    public Cell H5 = new Cell();
    public Cell H6 = new Cell();
    public Cell H7 = new Cell();
    public Cell H8 = new Cell();
    public Cell H9 = new Cell();
    public Cell M1 = new Cell();
    public Cell M2 = new Cell();
    public Cell M3 = new Cell();
    public Cell M4 = new Cell();
    public Cell M5 = new Cell();
    public Cell M6 = new Cell();
    public Cell M7 = new Cell();
    public Cell M8 = new Cell();
    public Cell M9 = new Cell();
    public Cell L1 = new Cell();
    public Cell L2 = new Cell();
    public Cell L3 = new Cell();
    public Cell L4 = new Cell();
    public Cell L5 = new Cell();
    public Cell L6 = new Cell();
    public Cell L7 = new Cell();
    public Cell L8 = new Cell();
    public Cell L9 = new Cell();

    public void updateCells(){
        H1.setScored(SmartDashboard.getBoolean("H1", false));
        H2.setScored(SmartDashboard.getBoolean("H2", false));
        H3.setScored(SmartDashboard.getBoolean("H3", false));
        H4.setScored(SmartDashboard.getBoolean("H4", false));
        H5.setScored(SmartDashboard.getBoolean("H5", false));
        H6.setScored(SmartDashboard.getBoolean("H6", false));
        H7.setScored(SmartDashboard.getBoolean("H7", false));
        H8.setScored(SmartDashboard.getBoolean("H8", false));
        H9.setScored(SmartDashboard.getBoolean("H9", false));
        M1.setScored(SmartDashboard.getBoolean("M1", false));
        M2.setScored(SmartDashboard.getBoolean("M2", false));
        M3.setScored(SmartDashboard.getBoolean("M3", false));
        M4.setScored(SmartDashboard.getBoolean("M4", false));
        M5.setScored(SmartDashboard.getBoolean("M5", false));
        M6.setScored(SmartDashboard.getBoolean("M6", false));
        M7.setScored(SmartDashboard.getBoolean("M7", false));
        M8.setScored(SmartDashboard.getBoolean("M8", false));
        M9.setScored(SmartDashboard.getBoolean("M9", false));
        L1.setScored(SmartDashboard.getBoolean("L1", false));
        L2.setScored(SmartDashboard.getBoolean("L2", false));
        L3.setScored(SmartDashboard.getBoolean("L3", false));
        L4.setScored(SmartDashboard.getBoolean("L4", false));
        L5.setScored(SmartDashboard.getBoolean("L5", false));
        L6.setScored(SmartDashboard.getBoolean("L6", false));
        L7.setScored(SmartDashboard.getBoolean("L7", false));
        L8.setScored(SmartDashboard.getBoolean("L8", false));
        L9.setScored(SmartDashboard.getBoolean("L9", false));
    }

    public int getSelectedCell(){
        if (H1.getSelected()) {
            return 31;
        } else if (H2.getSelected()) {
            return 32;
        } else if (H3.getSelected()) {
            return 33;
        } else if (H4.getSelected()) {
            return 34;
        } else if (H5.getSelected()) {
            return 35;
        } else if (H6.getSelected()) {
            return 36;
        } else if (H7.getSelected()) {
            return 37;
        } else if (H8.getSelected()) {
            return 38;
        } else if (H9.getSelected()) {
            return 39;
        } else if (M1.getSelected()) {
            return 21;
        } else if (M2.getSelected()) {
            return 22;
        } else if (M3.getSelected()) {
            return 23;
        } else if (M4.getSelected()) {
            return 24;
        } else if (M5.getSelected()) {
            return 25;
        } else if (M6.getSelected()) {
            return 26;
        } else if (M7.getSelected()) {
            return 27;
        } else if (M8.getSelected()) {
            return 28;
        } else if (M9.getSelected()) {
            return 29;
        } else if (L1.getSelected()) {
            return 11;
        } else if (L2.getSelected()) {
            return 12;
        } else if (L3.getSelected()) {
            return 13;
        } else if (L4.getSelected()) {
            return 14;
        } else if (L5.getSelected()) {
            return 15;
        } else if (L6.getSelected()) {
            return 16;
        } else if (L7.getSelected()) {
            return 17;
        } else if (L8.getSelected()) {
            return 18;
        } else if (L9.getSelected()) {
            return 19;
        } else { 
            return 0;
        }
    }

}