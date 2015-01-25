
package LogicSimulator.GateObjects;

import Logic.LogicLine;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class ConnectionLineObject {
    
    Line line;
    LogicLine logicLine;

    public ConnectionLineObject() {
    
    Color colorOff = Color.RED;
    Color colorOn = Color.GREENYELLOW;
    final String name = "line"; 

    //final Line line = new Line();

    line.setStroke(colorOff);
    line.setStrokeWidth(2);
    //add InputPin shadow effect
    //line.setCursor(Cursor.HAND);
    line.setCursor(new ImageCursor(Textures.exitIcon, Textures.exitIcon.getWidth() / 2, Textures.exitIcon.getHeight() /2));
 
    //  "line"
    
    }
    
    public Line createLine(final Line line, final LogicLine logicLine) {
        //create InputPin circle with desired name,  color and radius
        Color color = Color.DODGERBLUE;
        final String name = "Blue circle"; 

        //final Line line = new Line();

        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        //add InputPin shadow effect
        line.setCursor(Cursor.HAND);
        //add InputPin mouse listeners
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);

                    if(Globals.main.logicLines.contains(logicLine)){
                        logicLine.setInputPin(0, null);
                        logicLine.setOutputPin(0, null);
                        Globals.main.logicLines.remove(logicLine);
                    }
                    me.consume();
                } 

            }
        });

        line.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                line.toFront();
                if(logicLine.getOutputPin(0).getDataObject().getData() == true){
                    line.setStroke(Color.GREEN);
                }
                if(logicLine.getOutputPin(0).getDataObject().getData() == false){
                    line.setStroke(Color.RED);
                }
                Globals.main.showOnConsole("Mouse entered " + name);
            }
        });
        line.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                Globals.main.showOnConsole("Mouse exited " + name);
            }
        });
        return line;
    }
}
