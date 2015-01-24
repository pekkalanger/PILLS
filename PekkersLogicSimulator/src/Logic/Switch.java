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
}
