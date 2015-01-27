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
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import java.util.Iterator;
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
        group = new Group();
        name = "Switch";
        image = Textures.switchOn;
        Image exitIcon = Textures.switchCursor;
        ImageCursor imageCursor = new ImageCursor(exitIcon, -exitIcon.getWidth(), -exitIcon.getHeight());
        group.setCursor(imageCursor);
        gate = new Switch(false);
        gate.setOutputPin(0, new OutputPin());

        OutputPinObject outputPinObjectQ = new OutputPinObject(group, 32, 12, gate.getOutputPin(0), name + " PinA");
        outputPinObjects.add(outputPinObjectQ);

        rectangle = initRectangle(0, 0, width, height, Textures.switchOff);

        initGroup(inputPinObjects, outputPinObjects);

        group.getChildren().addAll(outputPinObjectQ.getRectangle(), rectangle);

        group.setOnMouseClicked((MouseEvent me) -> {
            group.toFront();
            if (me.getButton() == MouseButton.PRIMARY) {
                //me.consume();
            } else if (me.getButton() == MouseButton.SECONDARY) {
                toggled = true;
                gate.toggle();
                me.consume();
            } else if (me.getButton() == MouseButton.MIDDLE) {
                Globals.main.showOnConsole("Removed specified Gate");
                //Globals.main.circleList.remove(gg); // remove the gate from the list gate all the lines attached to it
                Globals.main.schematicGroup.getChildren().remove(group);
                gate = null;

                //Globals.main.gateObjects.remove(gate);
                System.out.println("gate should be null now");
                Globals.main.showOnConsole("gate should be null now");

                if (outputPinObjects != null) {
                    Iterator<OutputPinObject> iterator = outputPinObjects.iterator();
                    while (iterator.hasNext()) {
                        OutputPinObject opo = iterator.next();
                        if (Globals.main.schematicGroup.getChildren().contains(opo.connectionLineObjects.get(0).line)) {
                            Globals.main.schematicGroup.getChildren().remove(opo.connectionLineObjects.get(0).line);
                        }
                        opo.connectionLineObjects.get(0).logicLine = null;
                        opo.connectionLineObjects = null;
                        opo.connectionLineObject2 = null;
                    }
                }
                me.consume();
            }
        });

        group.setOpacity(0.8f);

        //Globals.main.schematicGroup.getChildren().add(group);
    }

    @Override
    public void update(long deltaTime) {
        //here we will take the data from line and render leds new status (via println())
        if (gate != null) {
            gate.update(deltaTime);
            //System.out.println(" switch state= " + gate.getDataObject().getData());
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
