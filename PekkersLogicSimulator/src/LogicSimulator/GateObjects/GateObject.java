/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import Logic.And;
import Logic.Gate;
import javafx.geometry.Point2D;
import javafx.scene.Group;
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
    
    public void update(boolean clock){
    }
    
}
