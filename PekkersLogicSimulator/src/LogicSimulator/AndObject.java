// should contain a group with a gate and its pins
// the pins should contain a line which will be updated on move
// should contain all data for easy usage

package LogicSimulator;

import Logic.And;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class AndObject implements GateObject{

    Group group;
    And and;
    Rectangle gateRectangle;
    InputPinObject pinAObject; // maybe use as array and move all code to GateObject and extend it
    InputPinObject pinBObject;
    OutputPinObject pinQObject;
    
    
    public AndObject(){
        //init stuff
    }
    
    @Override
    public void create(String name) {
        //init stuff
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(boolean clock) {
        //here will the andGate and drawing of lines be updated 
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}
