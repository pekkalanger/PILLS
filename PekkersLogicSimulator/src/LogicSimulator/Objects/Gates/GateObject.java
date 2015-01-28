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
import LogicSimulator.Objects.Gates.GateLogic.GateInterface;
import LogicSimulator.Globals;
import LogicSimulator.InfoPopup;
import LogicSimulator.Objects.ConnectionLineObject;
import LogicSimulator.Textures;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class GateObject {

    List<InputPinObject> inputPinObjects;
    List<OutputPinObject> outputPinObjects;
    public double width = 32;
    public double height = 32;
    double x;
    double y;
    String name;
    Group group;
    GateInterface gate;
    Rectangle rectangle;
    protected double initX;
    protected double initY;
    protected Point2D dragAnchor;
    Image infoImage;
    Image gateImage;

    public GateObject() {
        infoImage = Textures.texture;
        gateImage = Textures.texture;
        group = new Group();
        inputPinObjects = new ArrayList<>();
        outputPinObjects = new ArrayList<>();
        x = group.getTranslateX();
        y = group.getTranslateY();
    }

    public Rectangle initRectangle(double x, double y) {
        rectangle = new Rectangle(width, height);
        rectangle.setFill(new ImagePattern(gateImage, 0, 0, 1, 1, true)); /* should create InputPin GateInterface (square with andGate gate boolean logic linked to pins)*/

        rectangle.setTranslateX(x);  // move 8 to the left because of inputpins on the left
        rectangle.setTranslateY(y);
        InfoPopup.setinfoPopup(rectangle, infoImage);
        return rectangle;
    }

    public void initGroup(final List<InputPinObject> inputPinObjects, final List<OutputPinObject> outputPinObjects) {
        group.setOnMousePressed((MouseEvent me) -> {
            //when mouse is pressed, store initial position
            initX = group.getTranslateX();
            initY = group.getTranslateY();
            dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
            //showOnConsole("Mouse pressed above " + name);
        });
        group.setOnMouseDragged((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                if (dragAnchor != null) {
                    double dragX = me.getSceneX() - dragAnchor.getX();
                    double dragY = me.getSceneY() - dragAnchor.getY();
                    double newXPosition = initX + dragX;
                    double newYPosition = initY + dragY;
                    group.setTranslateX(newXPosition);
                    group.setTranslateY(newYPosition);

                    updateLines();
                }
                me.consume();
            }
        });
        group.setOnMouseClicked((MouseEvent me) -> {
            group.toFront();
            if (me.getButton() == MouseButton.MIDDLE) {
                if (Globals.main.gateGroup.getChildren().contains(InfoPopup.infoPopup)) {
                    Globals.main.showOnConsole("remove infopopup");
                    Globals.main.gateGroup.getChildren().remove(InfoPopup.infoPopup);
                }
                destroy();
                me.consume();
            }
        });

        group.setOpacity(0.8f);
        Globals.main.gateGroup.getChildren().add(group);
    }

    public void updateLines() {
        if (inputPinObjects != null) {
            Iterator<InputPinObject> iterator = inputPinObjects.iterator();
            while (iterator.hasNext()) {
                InputPinObject ipo = iterator.next();
                Iterator<ConnectionLineObject> ipoclo = ipo.connectionLineObjects.iterator();
                while (ipoclo.hasNext()) {
                    ConnectionLineObject clo = ipoclo.next();
                    if (ipo == clo.inputPinObjectSource) {
                        clo.line.endXProperty().set(4 + ipo.x + group.getTranslateX());  // pin width 8, mid 8/2=4
                        clo.line.endYProperty().set(4 + ipo.y + group.getTranslateY());
                    } else {
                        clo.line.startXProperty().set(4 + ipo.x + group.getTranslateX());
                        clo.line.startYProperty().set(4 + ipo.y + group.getTranslateY());
                    }
                }
            }
        }
        if (outputPinObjects != null) {
            Iterator<OutputPinObject> iterator = outputPinObjects.iterator();
            while (iterator.hasNext()) {
                OutputPinObject opo = iterator.next();
                Iterator<ConnectionLineObject> opoclo = opo.connectionLineObjects.iterator();
                while (opoclo.hasNext()) {
                    ConnectionLineObject clo = opoclo.next();
                    if (opo == clo.outputPinObjectSource) {
                        clo.line.endXProperty().set(4 + opo.x + group.getTranslateX());
                        clo.line.endYProperty().set(4 + opo.y + group.getTranslateY());
                    } else {
                        clo.line.startXProperty().set(4 + opo.x + group.getTranslateX());
                        clo.line.startYProperty().set(4 + opo.y + group.getTranslateY());
                        System.out.println("output is not the same");
                    }
                }
            }
        }
    }

    public void addPinObjects() {
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
    }

    public void destroy() {
        Globals.main.showOnConsole("Removed specified Gate");
        Globals.main.gateGroup.getChildren().remove(group);
        gate = null;
        if (inputPinObjects != null) {
            Iterator<InputPinObject> ipoIterator = inputPinObjects.iterator();
            while (ipoIterator.hasNext()) {
                InputPinObject ipo = ipoIterator.next();
                if (Globals.main.gateGroup.getChildren().contains(ipo.connectionLineObjects.get(0).line)) {
                    //Line l = Globals.main.gateGroup.getChildren().get(Globals.main.gateGroup.getChildren().indexOf(lineA));
                    Globals.main.gateGroup.getChildren().remove(ipo.connectionLineObjects.get(0).line);
                }
                destroyConnectionLines(ipo.connectionLineObjects);
                //ipo.connectionLineObjects.get(0).logicLine = null;
                //ipo.connectionLineObjects = null;
                //ipo.connectionLineObject2 = null;
            }
        }
        if (outputPinObjects != null) {
            Iterator<OutputPinObject> opoIterator = outputPinObjects.iterator();
            while (opoIterator.hasNext()) {
                OutputPinObject opo = opoIterator.next();
                if (Globals.main.gateGroup.getChildren().contains(opo.connectionLineObjects.get(0).line)) {
                    Globals.main.gateGroup.getChildren().remove(opo.connectionLineObjects.get(0).line);
                }
                destroyConnectionLines(opo.connectionLineObjects);
                //opo.connectionLineObjects.get(0).logicLine = null;
                //opo.connectionLineObjects = null;
                //opo.connectionLineObject2 = null;
            }
        }
    }

    public void destroyConnectionLines(final List<ConnectionLineObject> connectionLineObjects) {
        Iterator<ConnectionLineObject> ipoclo = connectionLineObjects.iterator();
        while (ipoclo.hasNext()) {
            ConnectionLineObject clo = ipoclo.next();
            clo.destroy();
        }
    }

    public void destroy0() {
        if (inputPinObjects != null) {
            Iterator<InputPinObject> iterator = inputPinObjects.iterator();
            while (iterator.hasNext()) {
                InputPinObject ipo = iterator.next();
                destroyConnectionLines(ipo.connectionLineObjects);
            }
        }
        if (inputPinObjects != null) {
            Iterator<InputPinObject> iterator = inputPinObjects.iterator();
            while (iterator.hasNext()) {
                InputPinObject ipo = iterator.next();
                destroyConnectionLines(ipo.connectionLineObjects);
            }
        }
    }

    public void update(long deltaTime) {
        x = group.getTranslateX();
        y = group.getTranslateY();
        if (gate != null) {
            gate.update(deltaTime);
        }
    }
}
