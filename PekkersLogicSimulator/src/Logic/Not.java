package Logic;

public class Not implements Gate {

	String name = "Not";
        InputPin i = new InputPin();
        OutputPin o = new OutputPin();

	public Not() {
	}
        
	@Override
	public boolean update() {
            if(i != null && o != null){
                o.setDataObject(i.getDataObject().notted()); // this is not tested fyi
		return false;
            }
            return true;
        }
	
        @Override
	public String toString(){
		return name;
	}
    
	
}
