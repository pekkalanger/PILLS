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
package LogicSimulator.Objects;

import LogicSimulator.Objects.Gates.GateLogic.And;
import LogicSimulator.Objects.Gates.GateObject;
import LogicSimulator.Objects.Pin.InputPinObject;
import LogicSimulator.Textures;
import javafx.scene.Group;

/**
 *
 * @author PEKKA
 */
public class ProbulatorIn extends GateObject {

    public ProbulatorIn() {
        super();
        group = new Group();
        name = "And Gate";
        gate = new And(name);
        infoImage = Textures.getHmImage("andtruth");
        gateImage = Textures.getHmImage("andgate");
        inputPinObjects.add(new InputPinObject(group, 0, 2, gate.getInputPin(0), name + " PinA"));
        group.getChildren().add(rectangle);
        addPinObjects();
        initGroup();
    }

}
