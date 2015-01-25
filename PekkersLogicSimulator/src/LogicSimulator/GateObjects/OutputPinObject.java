package LogicSimulator.GateObjects;
import Logic.OutputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class OutputPinObject extends PinObject{
    
    OutputPin outputPin;
    public OutputPinObject(final Line line, Group g, int x , int y, OutputPin op, String n) {
        this.x = x;
        this.y = y;
        name = n; 
        this.outputPin = op;
        
        rectangle = new Rectangle(width, height);
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
        rectangle = createPinRectangle(Textures.outputPin, line, g, rectangle, outputPin, null, name);

    }
    
}
