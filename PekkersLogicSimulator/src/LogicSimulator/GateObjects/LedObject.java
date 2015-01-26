/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import LogicSimulator.GateObjects.GateLogic.InputPin;
import LogicSimulator.GateObjects.GateLogic.Led;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class LedObject extends GateObject{
    
    boolean last = false;
    //Led gate;
    InputPinObject inputPinObject;
    
     public LedObject() {
                
        /*      movable group   */
        group = new Group();
        name = "Led";
        
        gate = new Led();
        gate.setInputPin(0, new InputPin());
        
        inputPinObject = new InputPinObject(group, 0, 12, gate.getInputPin(0), name + " PinA");
        final Line lineA = inputPinObject.connectionLineObject.line;
        
        // this should be added to InputPin gate list which will be updated all the fucknig time
        // gate also assigned the pins

        //inputPinObject = new InputPinObject(lineA, group, 0, 12, gate.getInputPin(0), name + " PinA");
        
        rectangle = new Rectangle(32, 32);
        rectangle.setFill(new ImagePattern(Textures.ledOff, 0, 0, 1, 1, true)); /* should create InputPin Gate (square with andGate gate boolean logic linked to pins)*/
        rectangle.setTranslateX(8);
        rectangle.setTranslateY(0);
        
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                Globals.main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                Globals.main.showOnConsole("Mouse exited " + name);
            }
        });
        
        group.getChildren().addAll(inputPinObject.getRectangle(), rectangle);
        
        group.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                initX = group.getTranslateX();
                initY = group.getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //showOnConsole("Mouse pressed above " + name);
            }
        });
        group.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 if (me.getButton() == MouseButton.PRIMARY) {
                    double dragX = me.getSceneX() - dragAnchor.getX();
                    double dragY = me.getSceneY() - dragAnchor.getY();
                    double newXPosition = initX + dragX;
                    double newYPosition = initY + dragY;
                    group.setTranslateX(newXPosition);
                    group.setTranslateY(newYPosition);
                    me.consume();
                }
            }
        });
        group.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                group.toFront();
                if (me.getButton() == MouseButton.PRIMARY) {
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    
                    //the event will be passed only to the circle which is on front
                    me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    
                    //last = true;
                    //gate.getDataObject().setData(!gate.getDataObject().getData());
                    //update(delt);
                    
                    //the event will be passed only to the circle which is on front
                    me.consume();
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified Led");
                    //Globals.main.circleList.remove(gg); // remove the gate from the list gate all the lines attached to it
                    Globals.main.circleGroup.getChildren().remove(group);

                    if(Globals.main.circleGroup.getChildren().contains(lineA)) {
                        Globals.main.circleGroup.getChildren().remove(lineA);
                    }
                    gate=null ;
                    //Globals.main.gateObjects.remove(this);
                    System.out.println("Led gate should be null now");
                        
                        
                    me.consume();
                }
                  
            }
        });
        group.setOpacity(0.8f);
        Globals.main.circleGroup.getChildren().add(group);
    }

    
    @Override
    public void update(long deltaTime) {
        //here we will take the data from line and render leds new status (via println())
        if(gate != null){ 
            //System.out.println("wefwef");
            gate.update(deltaTime);
            if(gate.getDataObject() != null){
                if(last != gate.getDataObject().getData()){
                    if(gate.getDataObject().getData() == true){

                     rectangle.setFill(new ImagePattern(Textures.ledOn, 0, 0, 1, 1, true)); /* should create InputPin Gate (square with andGate gate boolean logic linked to pins)*/   
                    } else {
                        rectangle.setFill(new ImagePattern(Textures.ledOff, 0, 0, 1, 1, true));
                    }
                    last=false;
                }
                last = gate.getDataObject().getData();
            } 
        }
    }
    
    
}
