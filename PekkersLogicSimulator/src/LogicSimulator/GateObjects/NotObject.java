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

import LogicSimulator.GateObjects.GateLogic.InputPin;
import LogicSimulator.GateObjects.GateLogic.Not;
import LogicSimulator.GateObjects.GateLogic.OutputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;

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
        gate.setOutputPin(0, new OutputPin(true));
        
        inputPinObjectA = new InputPinObject(group, 0, 12, gate.getInputPin(0), name + " PinA");
        outputPinObjectQ = new OutputPinObject(group, 40, 12, gate.getOutputPin(0), name + " PinQ");
        
        // this should be added to InputPin gate list which will be updated all the f***ing time
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
