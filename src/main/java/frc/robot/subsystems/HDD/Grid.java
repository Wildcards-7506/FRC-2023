package frc.robot.subsystems.HDD;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grid {
    
    public Cell H1 = new Cell(false, 31, "H1");
    public Cell H2 = new Cell(false, 32, "H2");
    public Cell H3 = new Cell(false, 33, "H3");
    public Cell H4 = new Cell(false, 34, "H4");
    public Cell H5 = new Cell(false, 35, "H5");
    public Cell H6 = new Cell(false, 36, "H6");
    public Cell H7 = new Cell(false, 37, "H7");
    public Cell H8 = new Cell(false, 38, "H8");
    public Cell H9 = new Cell(false, 39, "H9");
    public Cell M1 = new Cell(false, 21, "M1");
    public Cell M2 = new Cell(false, 22, "M2");
    public Cell M3 = new Cell(false, 23, "M3");
    public Cell M4 = new Cell(false, 24, "M4");
    public Cell M5 = new Cell(false, 25, "M5");
    public Cell M6 = new Cell(false, 26, "M6");
    public Cell M7 = new Cell(false, 27, "M7");
    public Cell M8 = new Cell(false, 28, "M8");
    public Cell M9 = new Cell(false, 29, "M9");
    public Cell L1 = new Cell(false, 11, "L1");
    public Cell L2 = new Cell(false, 12, "L2");
    public Cell L3 = new Cell(false, 13, "L3");
    public Cell L4 = new Cell(false, 14, "L4");
    public Cell L5 = new Cell(false, 15, "L5");
    public Cell L6 = new Cell(false, 16, "L6");
    public Cell L7 = new Cell(false, 17, "L7");
    public Cell L8 = new Cell(false, 18, "L8");
    public Cell L9 = new Cell(false, 19, "L9");

    public void updateCells(){
        H1.setSelected(SmartDashboard.getBoolean("H1", false));
        H2.setSelected(SmartDashboard.getBoolean("H2", false));
        H3.setSelected(SmartDashboard.getBoolean("H3", false));
        H4.setSelected(SmartDashboard.getBoolean("H4", false));
        H5.setSelected(SmartDashboard.getBoolean("H5", false));
        H6.setSelected(SmartDashboard.getBoolean("H6", false));
        H7.setSelected(SmartDashboard.getBoolean("H7", false));
        H8.setSelected(SmartDashboard.getBoolean("H8", false));
        H9.setSelected(SmartDashboard.getBoolean("H9", false));
        M1.setSelected(SmartDashboard.getBoolean("M1", false));
        M2.setSelected(SmartDashboard.getBoolean("M2", false));
        M3.setSelected(SmartDashboard.getBoolean("M3", false));
        M4.setSelected(SmartDashboard.getBoolean("M4", false));
        M5.setSelected(SmartDashboard.getBoolean("M5", false));
        M6.setSelected(SmartDashboard.getBoolean("M6", false));
        M7.setSelected(SmartDashboard.getBoolean("M7", false));
        M8.setSelected(SmartDashboard.getBoolean("M8", false));
        M9.setSelected(SmartDashboard.getBoolean("M9", false));
        L1.setSelected(SmartDashboard.getBoolean("L1", false));
        L2.setSelected(SmartDashboard.getBoolean("L2", false));
        L3.setSelected(SmartDashboard.getBoolean("L3", false));
        L4.setSelected(SmartDashboard.getBoolean("L4", false));
        L5.setSelected(SmartDashboard.getBoolean("L5", false));
        L6.setSelected(SmartDashboard.getBoolean("L6", false));
        L7.setSelected(SmartDashboard.getBoolean("L7", false));
        L8.setSelected(SmartDashboard.getBoolean("L8", false));
        L9.setSelected(SmartDashboard.getBoolean("L9", false));
    }

    public Cell getSelectedCell(){
        if (H1.getSelected()) {
            return H1;
        } else if (H2.getSelected()) {
            return H2;
        } else if (H3.getSelected()) {
            return H3;
        } else if (H4.getSelected()) {
            return H4;
        } else if (H5.getSelected()) {
            return H5;
        } else if (H6.getSelected()) {
            return H6;
        } else if (H7.getSelected()) {
            return H7;
        } else if (H8.getSelected()) {
            return H8;
        } else if (H9.getSelected()) {
            return H9;
        } else if (M1.getSelected()) {
            return M1;
        } else if (M2.getSelected()) {
            return M2;
        } else if (M3.getSelected()) {
            return M3;
        } else if (M4.getSelected()) {
            return M4;
        } else if (M5.getSelected()) {
            return M5;
        } else if (M6.getSelected()) {
            return M6;
        } else if (M7.getSelected()) {
            return M7;
        } else if (M8.getSelected()) {
            return M8;
        } else if (M9.getSelected()) {
            return M9;
        } else if (L1.getSelected()) {
            return L1;
        } else if (L2.getSelected()) {
            return L2;
        } else if (L3.getSelected()) {
            return L3;
        } else if (L4.getSelected()) {
            return L4;
        } else if (L5.getSelected()) {
            return L5;
        } else if (L6.getSelected()) {
            return L6;
        } else if (L7.getSelected()) {
            return L7;
        } else if (L8.getSelected()) {
            return L8;
        } else if (L9.getSelected()) {
            return L9;
        } else { 
            return L1;
        }
    }

    public void clearPrevSelect(Cell prevID){
        if(prevID.getID() != getSelectedCell().getID()){
            prevID.setSelected(false);
            SmartDashboard.putBoolean(getSelectedCell().getName(), false);
        }
    }

}