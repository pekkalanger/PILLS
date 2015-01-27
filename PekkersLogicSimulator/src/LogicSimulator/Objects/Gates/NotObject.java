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

import LogicSimulator.Objects.Pin.OutputPinObject;
import LogicSimulator.Objects.Pin.InputPinObject;
import LogicSimulator.Objects.Gates.GateLogic.InputPin;
import LogicSimulator.Objects.Gates.GateLogic.Not;
import LogicSimulator.Objects.Gates.GateLogic.OutputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;

public class NotObject extends GateObject {

    public NotObject() {
        super();
        group = new Group();
        name = "Not Gate";
        image = Textures.notGate;
        gate = new Not();
        gate.setInputPin(0, new InputPin());
        gate.setOutputPin(0, new OutputPin(true));
        inputPinObjects.add(new InputPinObject(group, 0, 12, gate.getInputPin(0), name + " PinA"));
        outputPinObjects.add(new OutputPinObject(group, 40, 12, gate.getOutputPin(0), name + " PinQ"));
        rectangle = initRectangle(8, 0, width, height, Textures.notGate);
        group.getChildren().add(rectangle);
        addPinObjects();
        initGroup(inputPinObjects, outputPinObjects);
    }

}
