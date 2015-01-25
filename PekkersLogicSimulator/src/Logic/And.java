package Logic;


public class And implements Gate {
        String name = "And";
        InputPin[] inputPins = new InputPin[2];
        OutputPin[] outputPins = new OutputPin[1];
	//InputPin InputPin = null, b = null;
        //OutputPin q = null;  // InputPin,b = IN && q = OUT
	//InputPin[] inputs;
        
	public And() {
            inputPins[0] = new InputPin();
            inputPins[1] = new InputPin();
            outputPins[0] = new OutputPin();
	}
        
	@Override
	public boolean update(long deltaTime) {
           if(inputPins[0] != null && inputPins[1] != null && outputPins[0] != null){
                if(inputPins[0].getDataObject().getData() == true && inputPins[1].getDataObject().getData() == true) {
                        outputPins[0].getDataObject().setData(true);
                } else {
                        outputPins[0].getDataObject().setData(false);
                }
                System.out.println("And out: " + outputPins[0].getDataObject().getData());
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
