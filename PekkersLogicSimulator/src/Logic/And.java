package Logic;


public class And implements Gate {
        String name = "And";
        InputPin[] inputPins;
        OutputPin[] outputPins;
	//InputPin a = null, b = null;
        //OutputPin q = null;  // a,b = IN && q = OUT
	//InputPin[] inputs;
        
	public And() {
            inputPins = new InputPin[2];
            outputPins = new OutputPin[1];
	}
        
        /**
        * will set pinA = p. 
        * The p argument must specify an absolute {@link InputPin}. 
        * this will set a to p
        */
        /*
        public void setPinA(InputPin p){
            a = p;
        }
        public void setPinB(InputPin p){
            b = p;
        }
        public void setPinQ(OutputPin p){
            q = p;
        }
        
        public InputPin getPinA(){
            return a;
        }
        public InputPin getPinB(){
            return b;
        }
        public OutputPin getPinQ(){
            return q;
        }
        */
        
	@Override
	public boolean update() {
        /*    if(a != null && b != null && q != null){
                if(a.getDataObject().getData() == true && b.getDataObject().getData() == true) {
                        q.getDataObject().setData(true);
                } else {
                        q.getDataObject().setData(false);
                }
                return false;
            } else return true;
	}
	
        @Override
	public String toString(){
		return name;
                */ return false;
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
