/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;
import Logic.OutputPin;
import javafx.scene.Group;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class OutputPinObject extends PinObject{
    
    OutputPin outputPin;
    public OutputPinObject(final Main main, final Line line, Group g, int x , int y, OutputPin pin, String n) {
        this.x = x;
        this.y = y;
        name = n; 
        this.outputPin = pin;
        
        rectangle = new Rectangle(width, height);
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
        rectangle.setFill(new ImagePattern(Textures.outputPin, 0, 0, 1, 1, true));
        rectangle = createPinRectangle(main, line, g, rectangle, outputPin, name);

    }
    
}
