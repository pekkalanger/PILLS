package LogicSimulator.GateObjects.GateLogic;

public class DataObject {
	
	private boolean data = false;
	
        public DataObject(boolean data) {
            this.data = data;
	}
	public boolean getData() {
            return data;
	}
	public void setData(boolean data) {
            this.data = data;
	}
        public boolean toggle(){
            setData(!getData());
            return getData();
	}
        public DataObject notted(){
            DataObject newDataObject = new DataObject(!getData());
            return newDataObject;
	}
		
}
