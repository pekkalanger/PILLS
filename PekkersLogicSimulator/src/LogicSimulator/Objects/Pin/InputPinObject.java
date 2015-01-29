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
package LogicSimulator.Objects.Pin;

import LogicSimulator.Objects.Gates.GateLogic.InputPin;
import LogicSimulator.Textures;
import javafx.scene.Group;

public class InputPinObject extends PinObject {



    public InputPinObject(Group g, int x, int y, InputPin ip, String n) {
        super(n, x, y);
        this.inputPin = ip;
        infoImage = Textures.inputInfo;
        gateImage = Textures.inputPin;
        rectangle = createPinRectangle(this, ip, g);
    }

}
