package Logic;

public class Switch implements Gate{
        
        DataObject dataObject;
        private String name = "Switch";
	private OutputPin outputPin = null;
        
	public Switch(boolean d){
            this.dataObject = new DataObject(d);
	}
	
	public void setLine(LogicLine l){
           // this.outputPin=l.getInputPin();
	}
        public void removeLine(){
            this.outputPin=null;
	}
	
	public boolean toggle(){
            return dataObject.toggle();
	}
    
        public void setDataObject(DataObject dataObject) {
            this.dataObject = dataObject;
        }

        public DataObject getDataObject() {
            return dataObject;
        }

	@Override
	public boolean update() {
            if( outputPin != null ){
		outputPin.setDataObject(dataObject);
		return false;
            } else return true;
	}
	
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
}
