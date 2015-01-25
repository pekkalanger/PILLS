/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator.GateObjects;

import Logic.InputPin;
import Logic.Not;
import Logic.OutputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 *
 * @author PEKKA
 */
public class NotObject extends GateObject{
    
    //And gate;
    InputPinObject inputPinObjectA; // maybe use as array gate move all code to GateObject gate extend it
    InputPinObject inputPinObjectB;
    OutputPinObject outputPinObjectQ;
    
    public NotObject() {
        group = new Group();
        name = "Not Gate";
        gate = new Not();
        gate.setInputPin(0, new InputPin());
        gate.setOutputPin(0, new OutputPin());
        
        inputPinObjectA = new InputPinObject(group, 0, 12, gate.getInputPin(0), name + " PinA");
        outputPinObjectQ = new OutputPinObject(group, 40, 12, gate.getOutputPin(0), name + " PinQ");
        
        // this should be added to InputPin gate list which will be updated all the fucknig time
        // gate also assigned the pins

        
        rectangle = initRectangle(Textures.notGate);

        
        group.getChildren().addAll(inputPinObjectA.getRectangle(), outputPinObjectQ.getRectangle(), rectangle);
       
        initGroup(inputPinObjectA, null, outputPinObjectQ);
    }

    
    @Override
    public void update(long deltaTime) {
        //here will the andGate gate drawing of lines be updated 
        
        if(gate != null) gate.update(deltaTime);
        //lines will get updated coordinates based upon gates coords, render
        
    }
    
}
