package Logic;
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
    /*
    public LogicLine(Pin pinA, Pin pinB){
        this.pinA = pinA;
        this.pinB = pinB;
    }

    public void setPinA(Pin p){
        this.pinA = p;
    }
    public Pin getPinA(){
        return pinA;
    }
    public void setPinB(Pin p){
        this.pinB = p;
    }
    public Pin getPinB(){
        return pinB;
    }
    */
    
    @Override
    public boolean update(long deltaTime) {
        if(pinA != null && pinB != null){
            if(pinA instanceof InputPin){
                System.out.println(pinA.type + " kuk " + pinB.type); 
                pinA.setDataObject(pinB.getDataObject());
            }/* else if(pinB instanceof InputPin){
                pinA.setDataObject(pinB.getDataObject());
            }*/
            System.out.println("Line data A: " + pinA.getDataObject().getData());
            System.out.println("Line data B" + pinB.getDataObject().getData());
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

}
