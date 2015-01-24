/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import Logic.InputPin;
import Logic.LogicLine;
import Logic.OutputPin;
import Logic.Pin;
import LogicSimulator.DragBoard;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
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

    public void setRectangle(Rectangle r){
        rectangle = r;
    }
    
    public Rectangle getRectangle(){
        return rectangle;
    }
    
    public Line createLine(final Line line, final LogicLine logicLine) {
        //create InputPin circle with desired name,  color and radius
        Color color = Color.DODGERBLUE;
        final String name = "Blue circle"; 

        //final Line line = new Line();
        
        line.setStroke(Color.RED);
        line.setStrokeWidth(1);
        //add InputPin shadow effect
        line.setCursor(Cursor.HAND);
        //add InputPin mouse listeners
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);
                    
                    logicLine.setInputPin(0, null);
                    logicLine.setOutputPin(0, null);
                    Globals.main.logicLines.remove(logicLine);
                    //Globals.main.logicLines.remove(logicLine);
                    me.consume();
                } 
                 
            }
        });
        
        line.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
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
    
      
    public Rectangle createPinRectangle(final Image i, final Line line, final Group g, final Rectangle rectangle, final OutputPin outputPin, final InputPin inputPin, final String name) {
        
    rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
        rectangle.setCursor(Cursor.HAND);
        //add InputPin mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.SECONDARY) {
                    
                    if(DragBoard.getInputPin() == null && DragBoard.getOutputPin() == null){
                        
                        if(outputPin == null){  // this is by default a input pin then
                            DragBoard.setPin(inputPin);
                        } else {
                            DragBoard.setOutputPin(outputPin);
                        }
                        DragBoard.setLine(line);
                        DragBoard.setGroup(g);
                        DragBoard.setName(name);
                        DragBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                        DragBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                        DragBoard.printDragBoard();
                        System.out.println("copied pin to dragboard");
                    } else if(DragBoard.getInputPin() != null && inputPin == null){
                        
                        System.out.println("dragboardpin contains " + DragBoard.getInputPin().getClass());
                        //System.out.println("pin contains " + inputPin.getClass());
                        System.out.println("connection made from " + DragBoard.getName() + " to " + name);
                        LogicLine logicLine = new LogicLine();
                        logicLine.setInputPin(0, DragBoard.getInputPin());
                        logicLine.setOutputPin(0, outputPin);

                        createLine(line, logicLine);
                        line.setStartX(DragBoard.getX() + width/2 + DragBoard.getGroup().getTranslateX());    // + Dragboard.pinOver.setGroup.getTranslateX()
                        line.setStartY(DragBoard.getY() + height/2 + DragBoard.getGroup().getTranslateY());    // + Dragboard.pinOver.setGroup.getTranslateY()
                        line.setEndX(rectangle.getTranslateX() + width/2 + g.getTranslateX());    // + pinOver.setGroup.getTranslateX()
                        line.setEndY(rectangle.getTranslateY() + height/2 + g.getTranslateY());  // + pinOver.setGroup.getTranslateY()
                        
                        if(!Globals.main.circleGroup.getChildren().contains(line)){
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);
                            System.out.println("line did not exist in schematic");
                        }
                        DragBoard.clearDragBoard();
                        
                        /*
                        else {
                            System.out.println("sorry bro, you cant link an" + DragBoard.getPin().getClass() + " to an " + pin.getClass());
                            DragBoard.setPin(pin);
                            DragBoard.setGroup(g);
                            DragBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                            DragBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                            DragBoard.printDragBoard();
                        }                        
                        */
                        
                    } else if(DragBoard.getOutputPin() != null && outputPin == null){
                        LogicLine logicLine = new LogicLine();
                        logicLine.setInputPin(0, inputPin);
                        logicLine.setOutputPin(0, DragBoard.getOutputPin());
                        DragBoard.setLine(line);  // lineobject
                        createLine(line, logicLine);
                        line.setStartX(DragBoard.getX() + width/2 + DragBoard.getGroup().getTranslateX());    // + Dragboard.pinOver.setGroup.getTranslateX()
                        line.setStartY(DragBoard.getY() + height/2 + DragBoard.getGroup().getTranslateY());    // + Dragboard.pinOver.setGroup.getTranslateY()
                        line.setEndX(rectangle.getTranslateX() + width/2 + g.getTranslateX());    // + pinOver.setGroup.getTranslateX()
                        line.setEndY(rectangle.getTranslateY() + height/2 + g.getTranslateY());  // + pinOver.setGroup.getTranslateY()
                        
                        if(!Globals.main.circleGroup.getChildren().contains(line)){
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);

                            System.out.println("line did not exist in schematic");
                        }
                        DragBoard.clearDragBoard();
 
                    } else if(DragBoard.getInputPin() == inputPin || DragBoard.getOutputPin() == outputPin) {
                            System.out.println("clicked on the same pin, dragboard cleared");
                            DragBoard.clearDragBoard();
                            DragBoard.printDragBoard();
                        } 
                    
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.PRIMARY) {
                    //System.out.println(" " + pin.type + "  " + pin.getDataObject().getData());
                    //remove line from this to target
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified Rectangle");
                }
                 me.consume(); 
            }
        });
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                Globals.main.showOnConsole("you are dragging a line from " + name);
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
                Globals.main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                Globals.main.showOnConsole("Mouse exited " + name);
                me.consume();
            }
        });
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rectangle.setFill(new ImagePattern(Textures.pinPressed, 0, 0, 1, 1, true));
                rectangle.toFront();
                 //when mouse is pressed, store initial position
                Globals.main.initX = rectangle.getTranslateX();
                Globals.main.initY = rectangle.getTranslateY();
                Globals.main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                Globals.main.showOnConsole("Mouse pressed above " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                 rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
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
