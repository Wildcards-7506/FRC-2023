package frc.robot.subsystems.HDD;

public class Cell {
    boolean cellSelected = false;
    String cellName;
    int ID;

    public Cell(boolean enabled, int ID, String Name){
        this.cellSelected = enabled;
        this.ID = ID;
        this.cellName = Name;
    }

    public boolean getSelected(){
        return cellSelected;
    }

    public String getName(){
        return cellName;
    }

    public int getID(){
        return ID;
    }

    public void setSelected(boolean setpoint){
        cellSelected = setpoint;
    }
}