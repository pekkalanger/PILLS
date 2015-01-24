/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import Logic.InputPin;
import Logic.Or;
import Logic.OutputPin;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class OrObject extends GateObject{
    
    //And gate;
    InputPinObject inputPinObjectA; // maybe use as array gate move all code to GateObject gate extend it
    InputPinObject inputPinObjectB;
    OutputPinObject outputPinObjectQ;
    
    public OrObject() {
        group = new Group();
        name = "Or Gate";
        gate = new Or();
        gate.setInputPin(0, new InputPin());
        gate.setInputPin(1, new InputPin());
        gate.setOutputPin(0, new OutputPin());
        
        final Line lineA = new Line();  // should be replaced by ConnectionLineObject
        final Line lineB = new Line();  // should be replaced by ConnectionLineObject
        final Line lineQ = new Line();  // should be replaced by ConnectionLineObject
        
        // this should be added to InputPin gate list which will be updated all the fucknig time
        // gate also assigned the pins

        inputPinObjectA = new InputPinObject(lineA, group, 0, 2, gate.getInputPin(0), name + " PinA");
        inputPinObjectB = new InputPinObject(lineB, group, 0, 22, gate.getInputPin(1), name + " PinB");
        outputPinObjectQ = new OutputPinObject(lineQ, group, 40, 12, gate.getOutputPin(0), name + " PinQ");
        
        rectangle = new Rectangle(32, 32);
        rectangle.setFill(new ImagePattern(Textures.orGate, 0, 0, 1, 1, true)); /* should create InputPin Gate (square with andGate gate boolean logic linked to pins)*/
        rectangle.setTranslateX(8);  // move 8 to the left because of inputpins on the left
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
        
       group.getChildren().addAll(inputPinObjectA.getRectangle(), inputPinObjectB.getRectangle(), outputPinObjectQ.getRectangle(), rectangle);
       
       initGroup(lineA, lineB, lineQ);
    }

    
    @Override
    public void update(boolean clock) {
        //here will the andGate gate drawing of lines be updated 
        
        if (gate != null )gate.update();
        //lines will get updated coordinates based upon gates coords, render
        
    }
    
}
