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
import LogicSimulator.GateObjects.GateLogic.OutputPin;
import LogicSimulator.GateObjects.GateLogic.Xnor;
import LogicSimulator.Textures;
import java.util.Iterator;
import javafx.scene.Group;

/**
 *
 * @author PEKKA
 */
public class XnorObject extends GateObject{
    
    public XnorObject() {
        super();
        group = new Group();
        name = "Xnor Gate";
        gate = new Xnor();
        gate.setInputPin(0, new InputPin());
        gate.setInputPin(1, new InputPin());
        gate.setOutputPin(0, new OutputPin());
        
        ipos.add(new InputPinObject(group, 0, 2, gate.getInputPin(0), name + " PinA"));
        ipos.add(new InputPinObject(group, 0, 22, gate.getInputPin(1), name + " PinB"));
        opos.add(new OutputPinObject(group, 40, 12, gate.getOutputPin(0), name + " PinQ"));
        
        rectangle = initRectangle(Textures.xnorGate);
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
        if (gate != null )gate.update(deltaTime);
        //lines will get updated coordinates based upon gates coords, render
    }
    
}
