
package LogicSimulator.GateObjects;

import Logic.Gate;
import Logic.InputPin;
import Logic.LogicLine;
import Logic.OutputPin;
import LogicSimulator.DragBoard;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ConnectionLineObject {
    final String name; 
    public Line line;
    public LogicLine logicLine;
    Gate gate;

    public ConnectionLineObject() {
        name = "A Line"; 
        line = new Line();
        logicLine = new LogicLine();
    }
    public Line createOutLine(final OutputPin op, final ConnectionLineObject clo, Group g, Rectangle r, double width, double height) {
        
        
        Color colorOff = Color.RED;
        Color colorOn = Color.GREENYELLOW;; 
    
        //create InputPin circle with desired name,  color and radius
        Color color = Color.DODGERBLUE;
        
        //final Line line = new Line();
        
        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        //add InputPin shadow effect
        Image cursorImage = Textures.lineCursor;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        line.setCursor(imageCursor);
        line.setCursor(imageCursor);
        line.setStartX(DragBoard.getX() + width/2 + DragBoard.getGroup().getTranslateX());    // + Dragboard.pinOver.setGroup.getTranslateX()
        line.setStartY(DragBoard.getY() + height/2 + DragBoard.getGroup().getTranslateY());    // + Dragboard.pinOver.setGroup.getTranslateY()
        line.setEndX(r.getTranslateX() + width/2 + g.getTranslateX());    // + pinOver.setGroup.getTranslateX()
        line.setEndY(r.getTranslateY() + height/2 + g.getTranslateY());  // + pinOver.setGroup.getTranslateY()
        //add InputPin mouse listeners
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);
                    //op.setDataObject(null);
                    if(Globals.main.logicLines.contains(logicLine)){  // -> connectionlineobject
                        logicLine.setInputPin(0, null);
                        logicLine.setOutputPin(0, null);
                        
                        Globals.main.logicLines.remove(logicLine);
                        
                    }
                    if(Globals.main.connectionLineObjects.contains(this)){  // -> connectionlineobject
                        logicLine.setDataObject(null);
                        logicLine=null;
                        Globals.main.connectionLineObjects.remove(this);
                    }
                    if(Globals.main.connectionLineObjects.contains(clo)){  // -> connectionlineobject
                        clo.logicLine.setDataObject(null);
                        clo.logicLine=null;
                        Globals.main.connectionLineObjects.remove(clo);
                    }
                    me.consume();
                } 
                 
            }
        });
        
        line.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                line.toFront();
                //Globals.main.showOnConsole("Mouse over " + name);
            }
        });
        line.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                
                //Globals.main.showOnConsole("Mouse exited " + name);
            }
        });
        return line;
    }
    
    public Line createInLine(final InputPin ip, final ConnectionLineObject clo, Group g, Rectangle r, double width, double height) {
        
        
        Color colorOff = Color.RED;
        Color colorOn = Color.GREENYELLOW;; 
    
        //create InputPin circle with desired name,  color and radius
        Color color = Color.DODGERBLUE;
        
        //final Line line = new Line();
        
        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        //add InputPin shadow effect
        Image cursorImage = Textures.lineCursor;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        line.setCursor(imageCursor);
        line.setCursor(imageCursor);
        line.setStartX(DragBoard.getX() + width/2 + DragBoard.getGroup().getTranslateX());    // + Dragboard.pinOver.setGroup.getTranslateX()
        line.setStartY(DragBoard.getY() + height/2 + DragBoard.getGroup().getTranslateY());    // + Dragboard.pinOver.setGroup.getTranslateY()
        line.setEndX(r.getTranslateX() + width/2 + g.getTranslateX());    // + pinOver.setGroup.getTranslateX()
        line.setEndY(r.getTranslateY() + height/2 + g.getTranslateY());  // + pinOver.setGroup.getTranslateY()
        //add InputPin mouse listeners
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);
                    //ip.setDataObject(null);
                    if(Globals.main.logicLines.contains(logicLine)){  // -> connectionlineobject
                        logicLine.setInputPin(0, null);
                        logicLine.setOutputPin(0, null);
                        Globals.main.logicLines.remove(logicLine);
                        
                    }
                    if(Globals.main.connectionLineObjects.contains(this)){  // -> connectionlineobject
                        logicLine.setDataObject(null);
                        logicLine=null;
                        Globals.main.connectionLineObjects.remove(this);
                    }
                    if(Globals.main.connectionLineObjects.contains(clo)){  // -> connectionlineobject
                        clo.logicLine.setDataObject(null);
                        clo.logicLine=null;
                        Globals.main.connectionLineObjects.remove(clo);
                    }
                    me.consume();
                } 
                 
            }
        });
        
        line.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                line.toFront();
                //Globals.main.showOnConsole("Mouse over " + name);
            }
        });
        line.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                
                //Globals.main.showOnConsole("Mouse exited " + name);
            }
        });
        return line;
    }
}