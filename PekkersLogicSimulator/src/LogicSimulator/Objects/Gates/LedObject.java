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

import LogicSimulator.Objects.Pin.InputPinObject;
import LogicSimulator.Objects.Gates.GateLogic.InputPin;
import LogicSimulator.Objects.Gates.GateLogic.Led;
import LogicSimulator.Textures;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LedObject extends GateObject {

    boolean last = false;
    Image gateImage2;

    public LedObject() {
        super();
        group = new Group();
        infoImage = Textures.getHmImage("ledon");
        rectangularGateSymbol = Textures.getHmImage("ledoff");
        distinctiveGateSymbol = rectangularGateSymbol;
        gateImage2 = Textures.getHmImage("ledon");
        name = "Led";
        gate = new Led(name);
        gate.setInputPin(0, new InputPin());
        inputPinObjects.add(new InputPinObject(group, 0, 12, gate.getInputPin(0), name + " PinA"));
        rectangle = new Rectangle(width, height);

        initRectangle(8, 0);
        group.getChildren().add(rectangle);
        addPinObjects();
        initGroup();
    }

    @Override
    public void update(long deltaTime) {
        if (gate != null) {
            gate.update(deltaTime);
            if (gate.getDataObject() != null) {
                if (last != gate.getDataObject().getData()) {
                    if (gate.getDataObject().getData() == true) {
                        rectangle.setFill(new ImagePattern(gateImage2, 0, 0, 1, 1, true));
                    } else {
                        rectangle.setFill(new ImagePattern(distinctiveGateSymbol, 0, 0, 1, 1, true));
                    }
                    last = false;
                }
                last = gate.getDataObject().getData();
            }
        }
    }

}
