package frc.robot.subsystems.HDD;

public class Cell {
    
    private boolean cellSelected;
    private boolean cellScored;
    private double cranePosition;
    private double wristPosition;
    private double extenderPosition;

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