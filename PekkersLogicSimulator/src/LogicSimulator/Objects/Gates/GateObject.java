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
    public int width = 32;
    public int height = 32;
    double x;
    double y;
    String name;
    Group group;
    GateInterface gate;
    Rectangle rectangle;
    protected double initX;
    protected double initY;
    protected Point2D dragAnchor;

    public GateObject() {
        inputPinObjects = new ArrayList<>();
        outputPinObjects = new ArrayList<>();
    }

    public Rectangle initRectangle(int x, int y, int width, int height, Image image) {
        rectangle = new Rectangle(width, height);
        rectangle.setFill(new ImagePattern(image, 0, 0, 1, 1, true)); /* should create InputPin GateInterface (square with andGate gate boolean logic linked to pins)*/

        rectangle.setTranslateX(x);  // move 8 to the left because of inputpins on the left
        rectangle.setTranslateY(y);

        rectangle.setOnMouseEntered((MouseEvent me) -> {
            me.consume();
        });
        rectangle.setOnMouseExited((MouseEvent me) -> {
            me.consume();
        });
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

                    if (inputPinObjects != null) {
                        Iterator<InputPinObject> iterator = inputPinObjects.iterator();
                        while (iterator.hasNext()) {
                            InputPinObject ipo = iterator.next();
                            ipo.connectionLineObjects.get(0).line.endXProperty().set(ipo.x + group.getTranslateX());
                            ipo.connectionLineObjects.get(0).line.endYProperty().set(ipo.y + group.getTranslateY());
                        }
                    }
                    if (outputPinObjects != null) {
                        Iterator<OutputPinObject> iterator = outputPinObjects.iterator();
                        while (iterator.hasNext()) {
                            OutputPinObject opo = iterator.next();
                            opo.connectionLineObjects.get(0).line.endXProperty().set(opo.x + group.getTranslateX());
                            opo.connectionLineObjects.get(0).line.endYProperty().set(opo.y + group.getTranslateY());
                        }
                    }
                }
                me.consume();
            }
        });
        group.setOnMouseClicked((MouseEvent me) -> {
            group.toFront();
            if (me.getButton() == MouseButton.PRIMARY) {
                Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                //the event will be passed only to the circle which is on front
                me.consume();
            } else if (me.getButton() == MouseButton.SECONDARY) {
            } else if (me.getButton() == MouseButton.MIDDLE) {
                Globals.main.showOnConsole("Removed specified Gate");
                //Globals.main.circleList.remove(gg); // remove the gate from the list gate all the lines attached to it
                Globals.main.circleGroup.getChildren().remove(group);
                gate = null;

                //Globals.main.gateObjects.remove(gate);
                System.out.println("gate should be null now");
                Globals.main.showOnConsole("gate should be null now");

                if (inputPinObjects != null) {
                    Iterator<InputPinObject> iterator = inputPinObjects.iterator();
                    while (iterator.hasNext()) {
                        InputPinObject ipo = iterator.next();
                        if (Globals.main.circleGroup.getChildren().contains(ipo.connectionLineObjects.get(0).line)) {
                            //Line l = Globals.main.circleGroup.getChildren().get(Globals.main.circleGroup.getChildren().indexOf(lineA));
                            Globals.main.circleGroup.getChildren().remove(ipo.connectionLineObjects.get(0).line);
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
                        if (Globals.main.circleGroup.getChildren().contains(opo.connectionLineObjects.get(0).line)) {
                            Globals.main.circleGroup.getChildren().remove(opo.connectionLineObjects.get(0).line);
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
        Globals.main.circleGroup.getChildren().add(group);
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
