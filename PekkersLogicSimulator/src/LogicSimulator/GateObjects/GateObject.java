/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import Logic.Gate;
import LogicSimulator.Globals;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class GateObject {
        
    String name;
    Group group;
    Gate gate;
    Rectangle rectangle;
    protected double initX;
    protected double initY;
    protected Point2D dragAnchor;
    
    public void update(long deltaTime){
        if(gate != null) gate.update(deltaTime);
    }
    
    public void initGroup(final Line lineA, final Line lineB, final Line lineQ){
           
        
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
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified orangeCircle");
                    //Globals.main.circleList.remove(gg); // remove the gate from the list gate all the lines attached to it
                    Globals.main.circleGroup.getChildren().remove(group);
                    gate=null ;
                    //for (int i = 0; i < 3; i++) {
                        if(Globals.main.circleGroup.getChildren().contains(lineA)) {
                           //Line l = Globals.main.circleGroup.getChildren().get(Globals.main.circleGroup.getChildren().indexOf(lineA));
                            Globals.main.circleGroup.getChildren().remove(lineA);
                            
                        }
                        if(Globals.main.circleGroup.getChildren().contains(lineB)) {
                            Globals.main.circleGroup.getChildren().remove(lineB);
                        }
                        if(Globals.main.circleGroup.getChildren().contains(lineQ)) {
                            Globals.main.circleGroup.getChildren().remove(lineQ);
                        }
                    //}
                    
                    me.consume();
                }
                  
            }
        });
        group.setOpacity(0.8f);
        Globals.main.circleGroup.getChildren().add(group);
    }
    
}
