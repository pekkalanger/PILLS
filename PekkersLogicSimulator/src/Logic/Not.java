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
    
	
}
