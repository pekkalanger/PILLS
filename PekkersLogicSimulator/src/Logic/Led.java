package Logic;

import LogicSimulator.Globals;

public class Led implements Gate{
    boolean last=false;
    String name = "Led";
    public InputPin[] inputPins = new InputPin[1];
    DataObject dataObject;
    
    public Led(){
        dataObject = new DataObject(false);
        inputPins[0] = new InputPin();
    }

    public void setLine(LogicLine l){
       // this.inputPin = l.getOutputPin();
    }
    public void removeLine(){
        this.inputPins[0] = null;
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
        if( inputPins[0] != null ){
              
            if(last != getDataObject().getData()){
                Globals.main.showOnConsole("LED = " + getDataObject().getData()); 
            }
            dataObject = inputPins[0].getDataObject();
            if(getDataObject() == null){
                last = getDataObject().getData();
            }
            System.out.print("Led status: ");
            System.out.println(dataObject.getData());
            return false;
            
        } else return true;
    }

    
    @Override
    public String toString(){
        return name;
    }

    @Override
    public void setInputPin(int pos, InputPin ip) {
        this.inputPins[pos] = ip;
    }

    @Override
    public InputPin getInputPin(int pos) {
        return inputPins[pos];
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toggle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
