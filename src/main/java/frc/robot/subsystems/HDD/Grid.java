package frc.robot.subsystems.HDD;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grid {
    public boolean[] prevCells = new boolean[27];
    public Cell[] cells = new Cell[27];
    
    public Grid(){
        cells[0] = new Cell(true, 0, "L1");
        cells[1] = new Cell(false, 1, "L2");
        cells[2] = new Cell(false, 2, "L3");
        cells[3] = new Cell(false, 3, "L4");
        cells[4] = new Cell(false, 4, "L5");
        cells[5] = new Cell(false, 5, "L6");
        cells[6] = new Cell(false, 6, "L7");
        cells[7] = new Cell(false, 7, "L8");
        cells[8] = new Cell(false, 8, "L9");
        cells[9] = new Cell(false, 9, "M1");
        cells[10] = new Cell(false, 10, "M2");
        cells[11] = new Cell(false, 11, "M3");
        cells[12] = new Cell(false, 12, "M4");
        cells[13] = new Cell(false, 13, "M5");
        cells[14] = new Cell(false, 14, "M6");
        cells[15] = new Cell(false, 15, "M7");
        cells[16] = new Cell(false, 16, "M8");
        cells[17] = new Cell(false, 17, "M9");
        cells[18] = new Cell(false, 18, "H1");
        cells[19] = new Cell(false, 19, "H2");
        cells[20] = new Cell(false, 20, "H3");
        cells[21] = new Cell(false, 21, "H4");
        cells[22] = new Cell(false, 22, "H5");
        cells[23] = new Cell(false, 23, "H6");
        cells[24] = new Cell(false, 24, "H7");
        cells[25] = new Cell(false, 25, "H8");
        cells[26] = new Cell(false, 26, "H9");
    }
    
    public Cell selectedCell = cells[0];
    public Cell previousCell = cells[0];

    public void checkStateChange(int ID){
        cells[ID].setSelected(SmartDashboard.getBoolean(cells[ID].getName(), false));
        if(cells[ID].getSelected() != prevCells[ID] & cells[ID].getSelected() == true){
            selectedCell = cells[ID];
        }
        prevCells[ID] = cells[ID].getSelected();
    }

    public void updateCells(){
        for (int i = 0; i < 27; i++){
            checkStateChange(i);
        }
        if (previousCell.getID() != selectedCell.getID()){
            SmartDashboard.putBoolean(previousCell.getName(), false);
        }
        previousCell = selectedCell;
    }

    public Cell getSelectedCell(){
        return selectedCell;
    }

}