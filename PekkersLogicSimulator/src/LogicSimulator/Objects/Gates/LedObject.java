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
import LogicSimulator.Objects.Gates.GateLogic.Led;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import java.util.Iterator;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LedObject extends GateObject {

    boolean last = false;

    public LedObject() {
        super();
        /*      movable group   */
        group = new Group();
        name = "Led";
        image = Textures.ledOff;
        gate = new Led();
        gate.setInputPin(0, new InputPin());

        inputPinObjects.add(new InputPinObject(group, 0, 12, gate.getInputPin(0), name + " PinA"));

        rectangle = new Rectangle(32, 32);
        rectangle.setFill(new ImagePattern(Textures.ledOff, 0, 0, 1, 1, true)); /* should create InputPin GateInterface (square with andGate gate boolean logic linked to pins)*/

        rectangle.setTranslateX(8);
        rectangle.setTranslateY(0);
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                Globals.main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                Globals.main.showOnConsole("Mouse exited " + name);
            }
        });

        group.getChildren().add(rectangle);
        if (inputPinObjects != null) {
            Iterator<InputPinObject> iterator = inputPinObjects.iterator();
            while (iterator.hasNext()) {
                group.getChildren().add(iterator.next().getRectangle());
            }
        }
        if (outputPinObjects != null) {
            Iterator<OutputPinObject> iterator = outputPinObjects.iterator();
            while (iterator.hasNext()) {

                group.getChildren().add(iterator.next().getRectangle());
            }
        }
        initGroup(inputPinObjects, outputPinObjects);
    }

    @Override
    public void update(long deltaTime) {
        //here we will take the data from line and render leds new status (via println())
        if (gate != null) {
            gate.update(deltaTime);
            if (gate.getDataObject() != null) {
                if (last != gate.getDataObject().getData()) {
                    if (gate.getDataObject().getData() == true) {
                        rectangle.setFill(new ImagePattern(Textures.ledOn, 0, 0, 1, 1, true)); /* should create InputPin GateInterface (square with andGate gate boolean logic linked to pins)*/

                    } else {
                        rectangle.setFill(new ImagePattern(Textures.ledOff, 0, 0, 1, 1, true));
                    }
                    last = false;
                }
                last = gate.getDataObject().getData();
            }
        }

    }

}
