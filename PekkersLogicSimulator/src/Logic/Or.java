package Logic;

public class Or implements Gate {

	String name = "Or";
	InputPin a,b;
        OutputPin q;
        
	public Or(){
	}
        
	@Override
	public boolean update() {
            if(a != null && b != null && q != null){
		if(a.getDataObject().getData() == true || b.getDataObject().getData() == true) {
			q.getDataObject().setData(true);
		} else {
			q.getDataObject().setData(false);
		}
		return false;
            } else return true;
	}
	
	public String toString(){
		return name;
	}
	
}
