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
package LogicSimulator.Objects.Gates;

import LogicSimulator.Objects.Gates.GateLogic.And;
import LogicSimulator.Objects.Gates.GateLogic.FullAdder;
import LogicSimulator.Objects.Gates.GateLogic.InputPin;
import LogicSimulator.Objects.Gates.GateLogic.OutputPin;
import LogicSimulator.Objects.Pin.InputPinObject;
import LogicSimulator.Objects.Pin.OutputPinObject;
import LogicSimulator.Textures;
import javafx.scene.Group;

/**
 *
 * @author PEKKA
 */
public class FullAdderObject extends GateObject {

    public FullAdderObject() {
        super();
        group = new Group();
        name = "Full Adder";
        infoImage = Textures.texture;
        gateImage = Textures.andGate;
        gate = new FullAdder();       //fullAdder
        gate.setInputPin(0, new InputPin());
        gate.setInputPin(1, new InputPin());
        gate.setInputPin(2, new InputPin());
        gate.setOutputPin(0, new OutputPin());
        gate.setOutputPin(1, new OutputPin());
        inputPinObjects.add(new InputPinObject(group, 0, 10, gate.getInputPin(0), name + " PinA"));
        inputPinObjects.add(new InputPinObject(group, 0, 30, gate.getInputPin(1), name + " PinB"));
        inputPinObjects.add(new InputPinObject(group, 20, 0, gate.getInputPin(1), name + " PinCin"));
        outputPinObjects.add(new OutputPinObject(group, 40, 20, gate.getOutputPin(0), name + " PinSum"));
        outputPinObjects.add(new OutputPinObject(group, 20, 40, gate.getOutputPin(0), name + " PinCout"));
        rectangle = initRectangle(8, 8);
        group.getChildren().add(rectangle);
        addPinObjects();
        initGroup(inputPinObjects, outputPinObjects);

    }

}
