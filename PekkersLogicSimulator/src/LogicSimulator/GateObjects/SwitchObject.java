/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import LogicSimulator.GateObjects.GateLogic.OutputPin;
import LogicSimulator.GateObjects.GateLogic.Switch;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SwitchObject extends GateObject{
    
    OutputPinObject outputPinObject;
    boolean toggled=false;
    //Switch gate;
    
     public SwitchObject() {
                
        /*      movable group   */
        group = new Group();
        name = "Switch";
        
        Image exitIcon = Textures.buttonCursor;
        ImageCursor imageCursor = new ImageCursor(exitIcon, -exitIcon.getWidth(), -exitIcon.getHeight());
        group.setCursor(imageCursor);
        gate = new Switch(false);
        gate.setOutputPin(0, new OutputPin());
        
        outputPinObject = new OutputPinObject(group, 32, 12, gate.getOutputPin(0), name + " PinA");
        final Line lineA = outputPinObject.connectionLineObject.line;
        
        // this should be added to a gate list which will be updated all the fucknig time
        // gate also assigned the pins

       
        
        rectangle = new Rectangle(32, 32);
        rectangle.setFill(new ImagePattern(Textures.switchOff, 0, 0, 1, 1, true)); /* should create a Gate (square with andGate gate boolean logic linked to pins)*/
        rectangle.setTranslateX(0);
        rectangle.setTranslateY(0);
        
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                
                //change the z-coordinate of the circle
                //circle.toFront();
                //Globals.main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //Globals.main.showOnConsole("Mouse exited " + name);
            }
        });
        
        group.getChildren().addAll(outputPinObject.getRectangle(), rectangle);
        
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
                    //Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    
                    //the event will be passed only to the circle which is on front
                    me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    //Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    toggled = true;
                    gate.toggle();
                    //update(true);
                    me.consume();
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified Switch");
                    //Globals.main.circleList.remove(gg); // remove the gate from the list gate all the lines attached to it
                    Globals.main.circleGroup.getChildren().remove(group);

                    if(Globals.main.circleGroup.getChildren().contains(lineA)) {
                        Globals.main.circleGroup.getChildren().remove(lineA);
                    }
                    gate=null ;
                    //Globals.main.gateObjects.remove(this);
                    System.out.println("Switch gate should be null now");
                        
                        
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
            gate.update(deltaTime);
            //System.out.println(" switch state= " + gate.getDataObject().getData());
            if(toggled){
                if(gate.getDataObject().getData()){

                 rectangle.setFill(new ImagePattern(Textures.switchOn, 0, 0, 1, 1, true)); /* should create a Gate (square with andGate led boolean logic linked to pins)*/   
                } else {
                    rectangle.setFill(new ImagePattern(Textures.switchOff, 0, 0, 1, 1, true));
                }
                toggled=false;
            }
        }
    }
    
}
