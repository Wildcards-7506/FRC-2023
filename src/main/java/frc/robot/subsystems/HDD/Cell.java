package frc.robot.subsystems.HDD;

public class Cell {
    boolean cellSelected;
    int cellTarget;
    String cellName;
    int cellID;

    public Cell(boolean enabled, int ID, String Name, int Target){
        this.cellSelected = enabled;
        this.cellID = ID;
        this.cellName = Name;
        this.cellTarget = Target;
    }

    public boolean getSelected(){
        return cellSelected;
    }

    public String getName(){
        return cellName;
    }

    public int getID(){
        return cellID;
    }

    public int getTarget(){
        return cellTarget;
    }

    public void setSelected(boolean setpoint){
        cellSelected = setpoint;
    }
}