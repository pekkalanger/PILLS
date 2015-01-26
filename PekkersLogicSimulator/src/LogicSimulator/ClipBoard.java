/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import LogicSimulator.GateObjects.GateObject;
import Logic.Gate;
import Logic.InputPin;
import Logic.OutputPin;
import LogicSimulator.GateObjects.ConnectionLineObject;
import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 *
 * @author PEKKA
 */
public class ClipBoard {
    private static GateObject gateObject;
    public static ConnectionLineObject connectionLineObject;
    public static Line line;
    private static String name;
    private static Object object;
    private static Group group;
    private static Gate gate;
    private static OutputPin outputPin;
    private static InputPin inputPin;
    private static double x = -1;
    private static double y = -1;

    public ClipBoard() {
        
    }
    
    public static void setGateObject(GateObject go){
        gateObject = go;
    }
        public static GateObject getGateObject(){
        return gateObject;
    }
        
    public static void setConnectionLineObject(ConnectionLineObject clo){
        connectionLineObject = clo;
    }
    public static ConnectionLineObject getConnectionLineObject(){
        return connectionLineObject;
    }
    
    public static Group getGroup(){
        return group;
    }
    public static void setGroup(Group g){
        group = g;
    }
    public static Line getLine(){
        return line;
    }
    public static void setLine(Line l){
        line = l;
    }
    public static String getName(){
        return name;
    }
    public static void setName(String n){
        name = n;
    }
    public static InputPin getInputPin(){
        return inputPin;
    }
    public static void setInputPin(InputPin ip){
        inputPin = ip;
    }
        public static OutputPin getOutputPin(){
        return outputPin;
    }
    public static void setOutputPin(OutputPin op ){
        outputPin = op;
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
    public static void printDragBoard(){
        System.out.println("===========Start================");
         System.out.println(gateObject);
                 System.out.println(connectionLineObject);
          System.out.println(name);
           System.out.println(object);
            System.out.println(group);
             System.out.println(gate);
              System.out.println(inputPin);
              System.out.println(outputPin);
               System.out.println(x);
                System.out.println(y);
                System.out.println("============End=================");
    }
    public static void clearDragBoard(){
        gateObject = null;
        connectionLineObject = null;
        line = null;
        name = null;
        object = null;
        group = null;
        gate = null;
        inputPin = null;
        outputPin = null;
        x = -1;
        y = -1;
    }

    
}
