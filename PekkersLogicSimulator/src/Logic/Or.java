package Logic;

public class Or implements Gate {

	String name = "Or";
	InputPin[] inputPins = new InputPin[2];
        OutputPin[] outputPins = new OutputPin[1];
        
	public Or(){
            inputPins[0] = new InputPin();
            inputPins[1] = new InputPin();
            outputPins[0] = new OutputPin();
	}
        
	@Override
	public boolean update(long deltaTime) {
            
            if(inputPins[0] != null && inputPins[1] != null && outputPins[0] != null){
		if(inputPins[0].getDataObject().getData() == true || inputPins[1].getDataObject().getData() == true) {
			outputPins[0].getDataObject().setData(true);
		} else {
			outputPins[0].getDataObject().setData(false);
		}
                System.out.println("Or: " + outputPins[0].getDataObject().getData());
		return false;
            } else return true;
	}
	
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
	
}
