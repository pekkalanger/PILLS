package LogicSimulator.GateObjects.GateLogic;
 /*
    draw line from PinA.pos to PinB.pos
*/
public class LogicLine implements Gate{
    private final String name = "Line";
    private InputPin pinA ;
    private OutputPin pinB ;
    
    public LogicLine(){
        pinA = new  InputPin();
        pinB = new OutputPin();
    }
    
    @Override
    public boolean update(long deltaTime) {
        if(pinA != null && pinB != null){
                //System.out.println(pinA.type + " kuk " + pinB.type); 
                pinA.setDataObject(pinB.getDataObject());
            /* else if(pinB instanceof InputPin){
                pinA.setDataObject(pinB.getDataObject());
            }*/
           // System.out.println("Line data A: " + pinA.getDataObject().getData());
           // System.out.println("Line data B" + pinB.getDataObject().getData());
            return false;
        } else {
            
            if(pinA != null)System.out.println("Line bit: A" );
            if(pinB != null)System.out.println("Line bit: B" );
            return true;
        
        }
    }
    
    @Override
    public String toString(){
        return name;
    }

    @Override
    public void setInputPin(int pos, InputPin ip) {
         pinA = ip;
    }

    @Override
    public InputPin getInputPin(int pos) {
        return pinA;
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
        pinB = op;
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        return pinB;
    }

    @Override
    public void setDataObject(DataObject dataObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataObject getDataObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toggle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
