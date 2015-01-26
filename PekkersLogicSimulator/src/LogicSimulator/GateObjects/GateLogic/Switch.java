package LogicSimulator.GateObjects.GateLogic;

import LogicSimulator.Globals;

public class Switch implements Gate{
        
    DataObject dataObject;
    String name = "Switch";
    private OutputPin outputPin = null;
    
    public Switch(boolean d){
        this.dataObject = new DataObject(d);
        outputPin = new OutputPin();
    }

    public void setLine(LogicLine l){
       // this.outputPin=l.getInputPin();
    }
    public void removeLine(){
        this.outputPin=null;
    }

    @Override
    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    @Override
    public DataObject getDataObject() {
        return dataObject;
    }

    @Override
    public boolean update(long deltaTime) {
        //toggle(); used as a generator/clock
        if( outputPin != null ){
            if(outputPin.getDataObject() != null)
                System.out.println("Switch status: " + outputPin.getDataObject().getData() );
            outputPin.setDataObject(dataObject);
            return false;
        } else {
            System.out.println("Switch status: null");
            return true;
        }
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public void setInputPin(int pos, InputPin ip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InputPin getInputPin(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
       outputPin = op;
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        return outputPin;
    }

    @Override
    public void toggle(){
        Globals.main.showOnConsole("Switch status: " + !outputPin.getDataObject().getData() );
        dataObject.toggle();
    }
    
}
