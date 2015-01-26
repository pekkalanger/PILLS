/*
 * Copyright (C) 2015 PEKKA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package LogicSimulator;

import LogicSimulator.GateObjects.GateObject;
import LogicSimulator.GateObjects.GateLogic.Gate;
import LogicSimulator.GateObjects.GateLogic.InputPin;
import LogicSimulator.GateObjects.GateLogic.OutputPin;
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
