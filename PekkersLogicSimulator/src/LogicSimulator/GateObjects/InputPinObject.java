/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import LogicSimulator.GateObjects.GateLogic.InputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class InputPinObject extends PinObject {
    
    InputPin inputPin;
    
    public InputPinObject(Group g, int x , int y, InputPin ip, String n) {
        this.x = x;
        this.y = y;
        name = n; 
        this.inputPin = ip;
        
        rectangle = new Rectangle(width, height);
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
        rectangle = createInputPinRectangle(Textures.inputPin, g, rectangle, ip, name);
    } 

    
    
}
