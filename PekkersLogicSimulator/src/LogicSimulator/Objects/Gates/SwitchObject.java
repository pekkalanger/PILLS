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
import LogicSimulator.Objects.Gates.GateLogic.OutputPin;
import LogicSimulator.Objects.Gates.GateLogic.Switch;
import LogicSimulator.Textures;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;

public class SwitchObject extends GateObject {

    boolean toggled = false;
    //Switch gate;

    public SwitchObject() {
        super();
        Image exitIcon = Textures.switchCursor;
        ImageCursor imageCursor = new ImageCursor(exitIcon, -exitIcon.getWidth(), -exitIcon.getHeight());
        group = new Group();
        group.setCursor(imageCursor);
        name = "Switch";
        image = Textures.switchOn;
        gate = new Switch(false);
        gate.setOutputPin(0, new OutputPin());
        rectangle = initRectangle(0, 0, width, height, Textures.switchOff);
        OutputPinObject outputPinObjectQ = new OutputPinObject(group, 32, 12, gate.getOutputPin(0), name + " PinA");
        outputPinObjects.add(outputPinObjectQ);
        addPinObjects();
        initGroup(inputPinObjects, outputPinObjects);
        group.getChildren().addAll(rectangle);
        group.setOnMouseClicked((MouseEvent me) -> {
            group.toFront();
            if (me.getButton() == MouseButton.SECONDARY) {
                toggled = true;
                gate.toggle();
                me.consume();
            } else if (me.getButton() == MouseButton.MIDDLE) {
                remove();
                me.consume();
            }
        });

    }
        

    @Override
    public void update(long deltaTime) {
        if (gate != null) {
            gate.update(deltaTime);
            if (toggled) {
                if (gate.getDataObject().getData()) {
                    rectangle.setFill(new ImagePattern(Textures.switchOn, 0, 0, 1, 1, true)); /* should create a GateInterface (square with andGate led boolean logic linked to pins)*/
                } else {
                    rectangle.setFill(new ImagePattern(Textures.switchOff, 0, 0, 1, 1, true));
                }
                toggled = false;
            }
        }
    }

}
