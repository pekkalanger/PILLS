
package LogicSimulator.GateObjects;

import Logic.DataObject;
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
    Color colorOff;
    Color colorOn; 

    public ConnectionLineObject() {
        name = "A Line"; 
        line = new Line();
        logicLine = new LogicLine();
        colorOff = Color.RED;
        colorOn = Color.GREENYELLOW; 
    }
    
    public void update(Long deltaTime){
         logicLine.update(deltaTime);
    }
    
    public Line createOutLine(final OutputPin op, final ConnectionLineObject clo, Group g, Rectangle r, double width, double height) {
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
        Globals.main.showOnConsole("created outline");
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);
                    //ip.setDataObject(null);
                    if(Globals.main.logicLines.contains(logicLine)){  // -> connectionlineobject
                        logicLine.getInputPin(0).setDataObject(new DataObject(false));
                        logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                        logicLine.setInputPin(0, null);
                        logicLine.setOutputPin(0, null);
                        // logicLine.setDataObject(null);
                        logicLine=null;
                        
                        Globals.main.showOnConsole("nully");
                        Globals.main.logicLines.remove(logicLine);
                    }
                    if(Globals.main.connectionLineObjects.contains(this)){  // -> connectionlineobject
                        //logicLine.setDataObject(null);
                        //logicLine=null;
                        Globals.main.showOnConsole("nully this");
                        Globals.main.connectionLineObjects.remove(this);
                    }
                    
                    if(clo != null){
                        if(clo.logicLine != null){
                            if(clo.logicLine.getInputPin(0) != null){
                                clo.logicLine.getInputPin(0).setDataObject(new DataObject(false));
                                clo.logicLine.setInputPin(0, null);
                            } else if(clo.logicLine.getOutputPin(0) != null){
                                clo.logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                                clo.logicLine.setOutputPin(0, null);
                            }
                        }
                        //clo.logicLine.setDataObject(null);
                        clo.logicLine=null;
                    }
                    if(Globals.main.connectionLineObjects.contains(clo)){  // -> connectionlineobject
                        Globals.main.showOnConsole("nully that");
                        Globals.main.connectionLineObjects.remove(clo);
                        Globals.main.logicLines.remove(clo.logicLine);
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
        Globals.main.showOnConsole("created inline");
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);
                    //ip.setDataObject(null);
                    if(Globals.main.logicLines.contains(logicLine)){  // -> connectionlineobject
                        logicLine.getInputPin(0).setDataObject(new DataObject(false));
                        logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                        logicLine.setInputPin(0, null);
                        logicLine.setOutputPin(0, null);
                        // logicLine.setDataObject(null);
                        logicLine=null;
                        
                        Globals.main.showOnConsole("nully");
                        Globals.main.logicLines.remove(logicLine);
                    }
                    if(Globals.main.connectionLineObjects.contains(this)){  // -> connectionlineobject
                        //logicLine.setDataObject(null);
                        //logicLine=null;
                        Globals.main.showOnConsole("nully this");
                        Globals.main.connectionLineObjects.remove(this);
                    }
                    
                    if(clo != null){
                        if(clo.logicLine != null){
                            if(clo.logicLine.getInputPin(0) != null){
                                clo.logicLine.getInputPin(0).setDataObject(new DataObject(false));
                                clo.logicLine.setInputPin(0, null);
                            } else if(clo.logicLine.getOutputPin(0) != null){
                                clo.logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                                clo.logicLine.setOutputPin(0, null);
                            }
                        }
                        //clo.logicLine.setDataObject(null);
                        clo.logicLine=null;
                    }
                    if(Globals.main.connectionLineObjects.contains(clo)){  // -> connectionlineobject
                        Globals.main.showOnConsole("nully that");
                        Globals.main.connectionLineObjects.remove(clo);
                        Globals.main.logicLines.remove(clo.logicLine);
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