package LogicSimulator.GateObjects.GateLogic;

public class OutputPin extends Pin{
    public String type="output";
    
    public OutputPin(){
        super();
    }
    public OutputPin(boolean d){
        super(d);
    }
}
