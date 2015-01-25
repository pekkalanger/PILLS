package LogicSimulator.GateObjects;

import Logic.InputPin;
import Logic.LogicLine;
import Logic.OutputPin;
import LogicSimulator.DragBoard;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import com.sun.corba.se.pept.encoding.InputObject;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class PinObject {
    
    String name;
    int x; // rectangle translate
    int y;
    int width = 8;
    int height = 8;
    protected Rectangle rectangle;
    
    ConnectionLineObject connectionLineObject;
    ConnectionLineObject connectionLineObject2;

    public PinObject() {
        connectionLineObject = new ConnectionLineObject();
    }

    public void setRectangle(Rectangle r){
        rectangle = r;
    }
    
    public Rectangle getRectangle(){
        return rectangle;
    }
         
    public Rectangle createPinRectangle(final Image i, final Group g, final Rectangle rectangle, final OutputPin outputPin, final InputPin inputPin, final String name) {
        
        connectionLineObject = new ConnectionLineObject();
        Image cursorImage = Textures.defaultCursorActive;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        rectangle.setCursor(imageCursor);
        
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
        //rectangle.setCursor(Cursor.HAND);
        //add InputPin mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY){//SECONDARY) {
                    //rectangle.setFill(new ImagePattern(Textures.pinPressed, 0, 0, 1, 1, true));
                    //rectangle.toFront();
                    
                    if(DragBoard.getInputPin() == null && DragBoard.getOutputPin() == null){
                        
                        if(outputPin == null){  // this is by default a input pin then
                            DragBoard.setInputPin(inputPin);
                        } else {
                            DragBoard.setOutputPin(outputPin);
                        }
                        DragBoard.setLine(connectionLineObject.line);
                        DragBoard.setConnectionLineObject(connectionLineObject);
                        
                        DragBoard.setGroup(g);
                        DragBoard.setName(name);
                        DragBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                        DragBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                        DragBoard.printDragBoard();
                        System.out.println("copied pin to dragboard");
                    } else if(DragBoard.getInputPin() != null && inputPin == null){
                        LogicLine logicLine = new LogicLine();
                        logicLine.setInputPin(0, DragBoard.getInputPin());
                        logicLine.setOutputPin(0, outputPin);
                        connectionLineObject.logicLine = logicLine;
                        //createLine(line, logicLine);
                        connectionLineObject2 = DragBoard.getConnectionLineObject();
                        Line line = connectionLineObject.createInLine(inputPin, connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                        //createLine(line, logicLine);
                        
                        if(!Globals.main.circleGroup.getChildren().contains(line)){
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);
                            System.out.println("line did not exist in schematic");
                        }
                        if(!Globals.main.connectionLineObjects.contains(connectionLineObject)){
                            Globals.main.connectionLineObjects.add(connectionLineObject);
                            System.out.println("line did not exist in schematic");
                        }
                        DragBoard.clearDragBoard();
                      
                    } else if(DragBoard.getOutputPin() != null && outputPin == null){
                        LogicLine logicLine = new LogicLine();
                        logicLine.setInputPin(0, inputPin);
                        logicLine.setOutputPin(0, DragBoard.getOutputPin());
                        connectionLineObject.logicLine = logicLine;
                        //createLine(line, logicLine);
                        connectionLineObject2 = DragBoard.getConnectionLineObject();
                        Line line = connectionLineObject.createOutLine(outputPin, connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                        //createLine(line, logicLine);
                        
                        if(!Globals.main.circleGroup.getChildren().contains(line)){
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);
                            System.out.println("line did not exist in schematic");
                        }
                        if(!Globals.main.connectionLineObjects.contains(connectionLineObject)){
                            Globals.main.connectionLineObjects.add(connectionLineObject);
                            System.out.println("line did not exist in schematic");
                        }
                        DragBoard.clearDragBoard();
 
                    } else if(DragBoard.getInputPin() == inputPin && DragBoard.getOutputPin() == outputPin) {

                            System.out.println("clicked on the same pin, dragboard cleared");
                            DragBoard.clearDragBoard();
                            DragBoard.printDragBoard();
                    } else if(DragBoard.getInputPin() != inputPin && DragBoard.getInputPin() != null && outputPin == null){
                        
                            DragBoard.clearDragBoard();
                            DragBoard.setInputPin(inputPin);
                            DragBoard.setLine(connectionLineObject.line);
                            DragBoard.setGroup(g);
                            DragBoard.setName(name);
                            DragBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                            DragBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                            DragBoard.printDragBoard();
                            System.out.println("sorry bro, you cant link an" + DragBoard.getInputPin().getClass());
                    } else if(DragBoard.getOutputPin() != outputPin && DragBoard.getOutputPin() != null && inputPin == null){
                        
                            DragBoard.clearDragBoard();
                            DragBoard.setOutputPin(outputPin);
                            DragBoard.setLine(connectionLineObject.line);
                            DragBoard.setGroup(g);
                            DragBoard.setName(name);
                            DragBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                            DragBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                            DragBoard.printDragBoard();
                            System.out.println("sorry bro, you cant link an" + DragBoard.getOutputPin().getClass());


                    }
                    
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                    //} else if (me.getButton() == MouseButton.PRIMARY) {
                    //System.out.println(" " + pin.type + "  " + pin.getDataObject().getData());
                    //remove line from this to target
                    
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    
                    Globals.main.showOnConsole("Nothing happened");
                    me.consume();
                }
            }
        });
        
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
               // Globals.main.showOnConsole("you are dragging a line from " + name);
                me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                     
                }
                
            }
        });
        
        
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
                //Globals.main.showOnConsole("Mouse over " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                //Globals.main.showOnConsole("Mouse exited " + name);
                me.consume();
            }
        });
        
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // if (me.getButton() == MouseButton.SECONDARY) {
                if (me.getButton() == MouseButton.PRIMARY) {
                rectangle.setFill(new ImagePattern(Textures.pinPressed, 0, 0, 1, 1, true));
                rectangle.toFront();
                 //when mouse is pressed, store initial position
                //Globals.main.initX = rectangle.getTranslateX();
                //Globals.main.initY = rectangle.getTranslateY();
                //Globals.main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //Globals.main.showOnConsole("Mouse pressed above " + name);
                
                me.consume();
                 }
            }
        });
        
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
                 rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                     me.consume();
                //Globals.main.showOnConsole("Mouse released above " + name);
                /*if (rectangle.getTranslateX() < (150) && rectangle.getTranslateX() > (- 150) && rectangle.getTranslateY() < (150) && rectangle.getTranslateY() > (- 150)) {
                    rectangle.setTranslateX(150);
                    rectangle.setTranslateY(150);
                }
                */
            }
        });
        return rectangle;
    }
    
}
