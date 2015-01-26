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
import java.util.Iterator;
import javafx.scene.Group;

public class NotObject extends GateObject{
    
    public NotObject() {
        super();
        group = new Group();
        name = "Not Gate";
        gate = new Not();
        gate.setInputPin(0, new InputPin());
        gate.setOutputPin(0, new OutputPin(true));
        
        ipos.add(new InputPinObject(group, 0, 12, gate.getInputPin(0), name + " PinA"));
        opos.add(new OutputPinObject(group, 40, 12, gate.getOutputPin(0), name + " PinQ"));
        
        rectangle = initRectangle(Textures.notGate);
        group.getChildren().add(rectangle);
        if(ipos != null){
            Iterator<InputPinObject> iterator = ipos.iterator();
            while (iterator.hasNext()) {
                group.getChildren().add(iterator.next().getRectangle());
            }
        }
        if(opos != null){
            Iterator<OutputPinObject> iterator = opos.iterator();
            while (iterator.hasNext()) {
                group.getChildren().add(iterator.next().getRectangle());
            }
        }
                            
        //group.getChildren().addAll(ipos.getRectangle(), inputPinObjectB.getRectangle(), outputPinObjectQ.getRectangle(), rectangle);
        x = group.getTranslateX();
        y = group.getTranslateY();
        initGroup(ipos, opos);
    }

    
    @Override
    public void update(long deltaTime) {
        //here will the andGate gate drawing of lines be updated 
        
        if(gate != null) gate.update(deltaTime);
        //lines will get updated coordinates based upon gates coords, render
        
    }
    
}
