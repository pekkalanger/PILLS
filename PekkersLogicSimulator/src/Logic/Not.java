package Logic;

public class Not implements Gate {

	String name = "Not";
        InputPin[] inputPins = new InputPin[1];
        OutputPin[] outputPins = new OutputPin[1];

	public Not() {
            inputPins[0] = new InputPin();
            outputPins[0] = new OutputPin();
	}
        
	@Override
	public boolean update() {
            if(inputPins[0] != null && outputPins[0] != null){
                outputPins[0].setDataObject(inputPins[0].getDataObject().notted()); // this is not tested fyi
		return false;
            }
            return true;
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
    
	
}
