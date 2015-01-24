package Logic;

public class Led implements Gate{
    String name = "Led";
    public InputPin inputPin = null;
    DataObject dataObject;
    
    public Led(){
        dataObject = new DataObject(false);
    }
    
    public Led(boolean data){
        dataObject.setData(data);
    }
    
    public Led(InputPin inputPin){
        this.inputPin = inputPin;
    }

    public void setLine(LogicLine l){
       // this.inputPin = l.getOutputPin();
    }
    public void removeLine(){
        this.inputPin = null;
    }

    @Override
    public boolean update() {
        if( inputPin != null ){
            dataObject = inputPin.getDataObject();
            System.out.println("Led status:" + dataObject.getData());
            return false;
        } else return true;
    }

    public String toString(){
        return name;
    }
}
