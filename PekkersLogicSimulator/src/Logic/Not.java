package Logic;

import LogicSimulator.Globals;

public class Not implements Gate {

    String name = "Not";
    InputPin[] inputPins = new InputPin[1];
    OutputPin[] outputPins = new OutputPin[1];

    public Not() {
        inputPins[0] = new InputPin();
        outputPins[0] = new OutputPin();
    }

    @Override
    public boolean update(long deltaTime) {
       if(inputPins[0] != null && outputPins[0] != null){
            if(inputPins[0].getDataObject() != null){
                if(inputPins[0].getDataObject().getData() == true) {
                    if(outputPins[0].getDataObject() != null){
                    if(outputPins[0].getDataObject().getData() == true){
                        Globals.main.showOnConsole("Not = " + !outputPins[0].getDataObject().getData());
                    }
                    outputPins[0].getDataObject().setData(false);
                }
                } else if(inputPins[0].getDataObject().getData() == false) {
                    if(outputPins[0].getDataObject().getData() == false){
                        Globals.main.showOnConsole("Not = " + !outputPins[0].getDataObject().getData());
                    }
                    outputPins[0].getDataObject().setData(true);

                }
                //System.out.println("===============END================");
                System.out.println("Not out: " + outputPins[0].getDataObject().getData());
                
            }
            return false;
       } else return true;
    }

    @Override
    public String toString(){
            return name;
    }

    @Override
    public void setInputPin(int pos, InputPin ip) {
         inputPins[pos] = ip;
    }

    @Override
    public InputPin getInputPin(int pos) {
        return inputPins[pos];
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
        outputPins[pos] = op;
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        return outputPins[pos];
    }

    @Override
    public void setDataObject(DataObject dataObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataObject getDataObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toggle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
	
}
