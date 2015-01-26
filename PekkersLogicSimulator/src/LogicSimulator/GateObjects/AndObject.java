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
package LogicSimulator.GateObjects;

import LogicSimulator.GateObjects.GateLogic.And;
import LogicSimulator.GateObjects.GateLogic.InputPin;
import LogicSimulator.GateObjects.GateLogic.OutputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;

public class AndObject extends GateObject{
    
    InputPinObject inputPinObjectA; // maybe use as array gate move all code to GateObject gate extend it
    InputPinObject inputPinObjectB;
    OutputPinObject outputPinObjectQ;
    
    public AndObject() {
        group = new Group();
        name = "And Gate";
        gate = new And();
        gate.setInputPin(0, new InputPin());
        gate.setInputPin(1, new InputPin());
        gate.setOutputPin(0, new OutputPin());
        
        inputPinObjectA = new InputPinObject(group, 0, 2, gate.getInputPin(0), name + " PinA");
        inputPinObjectB = new InputPinObject(group, 0, 22, gate.getInputPin(1), name + " PinB");
        outputPinObjectQ = new OutputPinObject(group, 40, 12, gate.getOutputPin(0), name + " PinQ");
        
        // this should be added to InputPin gate list which will be updated all the fucknig time
        // gate also assigned the pins

        rectangle = initRectangle(Textures.andGate);
        
        group.getChildren().addAll(inputPinObjectA.getRectangle(), inputPinObjectB.getRectangle(), outputPinObjectQ.getRectangle(), rectangle);
        x = group.getTranslateX();
        y = group.getTranslateY();
        initGroup(inputPinObjectA, inputPinObjectB, outputPinObjectQ);
    }

    
    @Override
    public void update(long deltaTime) {
        //here will the andGate gate drawing of lines be updated 
        x = group.getTranslateX();
        y = group.getTranslateY();
        //System.out.println(x + " " +y);
        if(gate != null) gate.update(deltaTime);
        //lines will get updated coordinates based upon gates coords, render
        
    }
    
}
