/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import Logic.InputPin;
import Logic.LogicLine;
import Logic.Pin;
import LogicSimulator.DragBoard;
import LogicSimulator.Globals;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
        //create a circle with desired name,  color and radius
        Color color = Color.DODGERBLUE;
        final String name = "Blue circle"; 

        //final Line line = new Line();
        
        line.setStroke(Color.RED);
        line.setStrokeWidth(1);
        //add a shadow effect
        line.setCursor(Cursor.HAND);
        //add a mouse listeners
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);
                    Globals.main.logicLines.remove(logicLine);
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
    
      
    public Rectangle createPinRectangle(final Line line, final Group g, final Rectangle rectangle, final Pin pin, final String name) {
        rectangle.setCursor(Cursor.HAND);
        //add a mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.SECONDARY) {
                    if(DragBoard.getPin() == null){
                        DragBoard.setGroup(g);
                        DragBoard.setName(name);
                        DragBoard.setPin(pin);
                        DragBoard.setX(rectangle.getTranslateX());     // + Dragboard.pin.setGroup.getTranslateX()
                        DragBoard.setY(rectangle.getTranslateY());      // + Dragboard.pin.setGroup.getTranslateY()
                        System.out.println("copied pin to dragboard");
                    } else if(DragBoard.getPin() != null){
                        System.out.println("dragboardpin contains " + DragBoard.getPin().getClass());
                        
                        if(DragBoard.getPin().getClass() != pin.getClass()) { // make sure theyt arent of the same type
                            System.out.println("pin contains " + pin.getClass());
                            System.out.println("connection made from " + DragBoard.getName() + " to " + name);
                                LogicLine logicLine = new LogicLine(DragBoard.getPin(), pin);
                                createLine(line, logicLine);
                                //g.getChildren().
                                line.setStartX(DragBoard.getX() + width/2 + DragBoard.getGroup().getTranslateX());    // + Dragboard.pin.setGroup.getTranslateX()
                                line.setStartY(DragBoard.getY() + height/2 + DragBoard.getGroup().getTranslateY());    // + Dragboard.pin.setGroup.getTranslateY()
                                line.setEndX(rectangle.getTranslateX() + width/2 + g.getTranslateX());    // + pin.setGroup.getTranslateX()
                                line.setEndY(rectangle.getTranslateY() + height/2 + g.getTranslateY());  // + pin.setGroup.getTranslateY()
                                logicLine.setPinA((Pin)DragBoard.getPin());
                                logicLine.setPinB(pin);
                                //create new Graphic LogicLine with line functionality
                                //add to lines array and schematic
                                if(!Globals.main.circleGroup.getChildren().contains(line)){
                                    Globals.main.circleGroup.getChildren().add(line);
                                    System.out.println("line did not exist in schematic");
                                }
                                DragBoard.setGroup(null);
                                DragBoard.setPin(null);
                                DragBoard.setX(-1);    //Dragboard.pin = -1 
                                DragBoard.setY(-1);
                                
                        } else if(DragBoard.getPin() == pin) {
                            System.out.println("clicked on the same pin, dragboard cleared");
                            DragBoard.setGroup(null);
                                DragBoard.setPin(null);
                                DragBoard.setX(-1);    //Dragboard.pin = -1 
                                DragBoard.setY(-1);
                        } else {
                            System.out.println("sorry bro, you cant link an" + DragBoard.getPin().getClass() + " to an " + pin.getClass());
                            DragBoard.setPin(pin);
                            DragBoard.setGroup(g);
                            DragBoard.setX(rectangle.getTranslateX());     // + Dragboard.pin.setGroup.getTranslateX()
                            DragBoard.setY(rectangle.getTranslateY());      // + Dragboard.pin.setGroup.getTranslateY()
                        }                        
                        
                    }
                    
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.PRIMARY) {
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
                Globals.main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                Globals.main.showOnConsole("Mouse exited " + name);
                me.consume();
            }
        });
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
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
