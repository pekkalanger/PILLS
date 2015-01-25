/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import Logic.InputPin;
import Logic.Or;
import Logic.OutputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;
import javafx.scene.shape.Line;

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
        
        inputPinObjectA = new InputPinObject(group, 0, 2, gate.getInputPin(0), name + " PinA");
        inputPinObjectB = new InputPinObject(group, 0, 22, gate.getInputPin(1), name + " PinB");
        outputPinObjectQ = new OutputPinObject(group, 40, 12, gate.getOutputPin(0), name + " PinQ");
        
        final Line lineA = inputPinObjectA.connectionLineObject.line;
        final Line lineB = inputPinObjectB.connectionLineObject.line;
        final Line lineQ = outputPinObjectQ.connectionLineObject.line;
        
        // this should be added to InputPin gate list which will be updated all the fucknig time
        // gate also assigned the pins

        rectangle = initRectangle(Textures.orGate);
      
        
        group.getChildren().addAll(inputPinObjectA.getRectangle(), inputPinObjectB.getRectangle(), outputPinObjectQ.getRectangle(), rectangle);
       
        initGroup(lineA, lineB, lineQ);
    }

    
    @Override
    public void update(long deltaTime) {
        //here will the andGate gate drawing of lines be updated 
        
        if (gate != null )gate.update(deltaTime);
        //lines will get updated coordinates based upon gates coords, render
        
    }
    
}
