package frc.robot.subsystems.HDD;

public class Cell {
    
    private boolean cellSelected = false;
    private boolean cellScored = false;
    private double cranePosition = 0.0;
    private double wristPosition = 0.0;
    private double extenderPosition = 0.0;

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