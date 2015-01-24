/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import Logic.Gate;
import Logic.Pin;
import javafx.scene.Group;

/**
 *
 * @author PEKKA
 */
public class DragBoard {
    private static GateObject gateObject;
    private static String name;
    private static Object object;
    private static Group group;
    private static Gate gate;
    private static Pin pin;
    private static double x = -1;
    private static double y = -1;

    public DragBoard() {
        
    }
    public static void setGateObject(GateObject go){
        gateObject = go;
    }
        public static GateObject getGateObject(){
        return gateObject;
    }
    
    public static Group getGroup(){
        return group;
    }
    public static void setGroup(Group g){
        group = g;
    }
    public static String getName(){
        return name;
    }
    public static void setName(String n){
        name = n;
    }
    public static Pin getPin(){
        return pin;
    }
    public static void setPin(Pin newPin){
        pin = newPin;
    }
    public static double getX(){
        return x;
    }
    public static double getY(){
        return y;
    }
    public static void setX(double newX){
        x = newX;
    }
    public static void setY(double newY){
        y = newY;
    }
    public static void clearDragBoard(){
    name = null;
    object = null;
    group = null;
    gate = null;
    pin = null;
    x = -1;
    y = -1;
}
    
    
}
