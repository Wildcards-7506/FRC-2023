package frc.robot.subsystems.HDD;

public class Cell {
    boolean cellSelected = false;
    boolean cellScored = false;
    double cranePosition = 0.0;
    double wristPosition = 0.0;
    double extenderPosition = 0.0;
    int ID;

    public Cell(boolean enabled, int ID){
        this.cellSelected = enabled;
        this.ID = ID;
    }

    public boolean getSelected(){
        return cellSelected;
    }

    public boolean getScored(){
        return cellScored;
    }

    public double getcranePosition(){
        return cranePosition;
    }

    public double getwristPosition(){
        return wristPosition;
    }

    public double getextenderPosition(){
        return extenderPosition;
    }

    public int getID(){
        return ID;
    }

    public void setSelected(boolean setpoint){
        cellSelected = setpoint;
    }

    public void setScored(boolean setpoint){
        cellScored = setpoint;
    }
    public void setcranePosition(double setpoint){
        cranePosition = setpoint;
    }

    public void setwristPosition(double setpoint){
        wristPosition = setpoint;
    }
    public void setextenderPosition(double setpoint){
        extenderPosition = setpoint;
    }

}