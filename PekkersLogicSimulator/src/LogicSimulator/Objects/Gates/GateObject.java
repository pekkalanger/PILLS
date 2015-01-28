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
    Image image;

    public GateObject() {
        group = new Group();
        inputPinObjects = new ArrayList<>();
        outputPinObjects = new ArrayList<>();
        x = group.getTranslateX();
        y = group.getTranslateY();
    }

    public Rectangle initRectangle(double x, double y, double width, double height, Image i) {
        rectangle = new Rectangle(width, height);
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true)); /* should create InputPin GateInterface (square with andGate gate boolean logic linked to pins)*/

        rectangle.setTranslateX(x);  // move 8 to the left because of inputpins on the left
        rectangle.setTranslateY(y);
        InfoPopup.setinfoPopup(rectangle, image, i);
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
                remove();
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
                        clo.line.endXProperty().set(ipo.x + group.getTranslateX());
                        clo.line.endYProperty().set(ipo.y + group.getTranslateY());
                    } else {
                        clo.line.startXProperty().set(ipo.x + group.getTranslateX());
                        clo.line.startYProperty().set(ipo.y + group.getTranslateY());
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
                        clo.line.endXProperty().set(opo.x + group.getTranslateX());
                        clo.line.endYProperty().set(opo.y + group.getTranslateY());
                    } else {
                        clo.line.startXProperty().set(opo.x + group.getTranslateX());
                        clo.line.startYProperty().set(opo.y + group.getTranslateY());
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

    public void remove() {
        Globals.main.showOnConsole("Removed specified Gate");
        Globals.main.gateGroup.getChildren().remove(group);
        gate = null;
        if (inputPinObjects != null) {
            Iterator<InputPinObject> iterator = inputPinObjects.iterator();
            while (iterator.hasNext()) {
                InputPinObject ipo = iterator.next();
                if (Globals.main.gateGroup.getChildren().contains(ipo.connectionLineObjects.get(0).line)) {
                    //Line l = Globals.main.gateGroup.getChildren().get(Globals.main.gateGroup.getChildren().indexOf(lineA));
                    Globals.main.gateGroup.getChildren().remove(ipo.connectionLineObjects.get(0).line);
                }
                ipo.connectionLineObjects.get(0).logicLine = null;
                ipo.connectionLineObjects = null;
                ipo.connectionLineObject2 = null;
            }
        }
        if (outputPinObjects != null) {
            Iterator<OutputPinObject> iterator = outputPinObjects.iterator();
            while (iterator.hasNext()) {
                OutputPinObject opo = iterator.next();
                if (Globals.main.gateGroup.getChildren().contains(opo.connectionLineObjects.get(0).line)) {
                    Globals.main.gateGroup.getChildren().remove(opo.connectionLineObjects.get(0).line);
                }
                opo.connectionLineObjects.get(0).logicLine = null;
                opo.connectionLineObjects = null;
                opo.connectionLineObject2 = null;
            }
        }
    }

    public void destroy() {
        /* remove all the lines from this object and all other sh*t*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(long deltaTime) {
        x = group.getTranslateX();
        y = group.getTranslateY();
        if (gate != null) {
            gate.update(deltaTime);
        }
    }
}
