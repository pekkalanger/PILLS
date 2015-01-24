/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import Logic.InputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class InputPinObject extends PinObject {
    
    InputPin inputPin;
    
    public InputPinObject(final Line line, Group g, int x , int y, InputPin ip, String n) {
        this.x = x;
        this.y = y;
        name = n; 
        this.inputPin = ip;
        
        rectangle = new Rectangle(width, height);
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
        rectangle.setFill(new ImagePattern(Textures.inputPin, 0, 0, 1, 1, true));
        rectangle = createPinRectangle(line, g, rectangle, ip, name);
    } 

    
    
}
