package Logic;

public class Not implements Gate {

	String name = "Not";
        InputPin a = new InputPin();
        OutputPin q = new OutputPin();

	public Not() {
	}
        public void setPinA(InputPin p){
            a = p;
        }
        public void setPinQ(OutputPin p){
            q = p;
        }
        
        public InputPin getPinA(){
            return a;
        }
        public OutputPin getPinQ(){
            return q;
        }
        
	@Override
	public boolean update() {
            if(a != null && q != null){
                q.setDataObject(a.getDataObject().notted()); // this is not tested fyi
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InputPin getInputPin(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
	
}
