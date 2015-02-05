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
import LogicSimulator.InfoPopup;
import LogicSimulator.Main;
import LogicSimulator.Objects.ConnectionLineObject;
import LogicSimulator.SchematicRectangle;
import LogicSimulator.Textures;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class GateObject {

    protected List<InputPinObject> inputPinObjects;
    protected List<OutputPinObject> outputPinObjects;
    protected double width = 32;
    protected double height = 32;
    protected double x;
    protected double y;
    protected String name;
    protected Group group;
    protected GateInterface gate;
    protected Rectangle rectangle;
    protected double initX;
    protected double initY;
    protected Point2D dragAnchor;
    protected Image infoImage;
    protected Image distinctiveGateSymbol;
    protected Image rectangularGateSymbol;

    public GateObject(double x, double y) {
        this.inputPinObjects = new ArrayList<>();
        this.outputPinObjects = new ArrayList<>();
        width = 32;
        height = 32;
        this.x = x;
        this.y = y;
        this.name = "unnamed";
        this.group = new Group();
        this.gate = null;
        this.rectangle = null;
        this.initX = 0;
        this.initY = 0;
        this.dragAnchor = Point2D.ZERO;
        this.infoImage = Textures.getHmImage("noimage");
        this.distinctiveGateSymbol = Textures.getHmImage("noimage");
        this.rectangularGateSymbol = Textures.getHmImage("noimage");
    }

    public GateObject() {
        this(0, 0);
    }

    public Rectangle initRectangle(double x, double y) {
        rectangle = new Rectangle(width, height);
        rectangle.setCursor(Cursor.CLOSED_HAND);
        if (Main.main.rectangularSymbols) {
            rectangle.setFill(new ImagePattern(rectangularGateSymbol, 0, 0, 1, 1, true));
        } else {
            rectangle.setFill(new ImagePattern(distinctiveGateSymbol, 0, 0, 1, 1, true)); /* should create InputPin GateInterface (square with andGate gate boolean logic linked to pins)*/

        }
        rectangle.setTranslateX(x);  // move 8 to the left because of inputpins on the left
        rectangle.setTranslateY(y);
        InfoPopup.setinfoPopup(rectangle, infoImage);
        return rectangle;
    }

    public void initGroup() {
        group.setOnMousePressed((MouseEvent me) -> {
            //when mouse is pressed, store initial position
            initX = group.getTranslateX();
            initY = group.getTranslateY();
            dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
        });
        group.setOnMouseDragged((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                if (dragAnchor != null) {
                    double dragX = me.getSceneX() - dragAnchor.getX();
                    double dragY = me.getSceneY() - dragAnchor.getY();
                    double newXPosition = initX + dragX;
                    double newYPosition = initY + dragY;
                    if (newXPosition > 0 && newXPosition < (Main.main.schematicWidth - 32)) {
                        group.setTranslateX(newXPosition - (newXPosition % SchematicRectangle.gridWidth));
                    }
                    if (newYPosition > 0 && newYPosition < (Main.main.schematicHeigth - 32)) {
                        group.setTranslateY((newYPosition - newYPosition % SchematicRectangle.gridHeight));
                    }
                    updateLines();
                }
                me.consume();
            }
        });
        group.setOnMouseClicked((MouseEvent me) -> {
            group.toFront();
            if (me.getButton() == MouseButton.MIDDLE) {
                InfoPopup.resetInfoPopup();
                destroy();
                me.consume();
            }
        });
        group.setOnScroll((ScrollEvent event) -> {
            if (event.getDeltaY() < 0) {
                group.setRotate(group.getRotate() + 22.50);
            } else if (event.getDeltaY() > 0) {
                //group.getTransforms().add(new Rotate(45, 20, 16));
                group.setRotate(group.getRotate() - 22.50);
            }

            System.out.println(group.localToParent(0, 0));//Point2D p2d = 
        });
        group.setOpacity(0.8f);
        Main.main.gateGroup.getChildren().add(group);
    }

    public void updateLines() {
        if (inputPinObjects != null) {
            Iterator<InputPinObject> iterator = inputPinObjects.iterator();
            while (iterator.hasNext()) {
                InputPinObject ipo = iterator.next();
                Iterator<ConnectionLineObject> ipoclo = ipo.getConnectionLineObjects().iterator();
                while (ipoclo.hasNext()) {
                    ConnectionLineObject clo = ipoclo.next();
                    if (ipo == clo.getInputPinObjectSource() && clo.getLine() != null) {
                        clo.getLine().endXProperty().set(4 + ipo.getX() + group.getTranslateX());  // pin width 8, mid 8/2=4
                        clo.getLine().endYProperty().set(4 + ipo.getY() + group.getTranslateY());
                    } else if (clo.getLine() != null) {
                        clo.getLine().startXProperty().set(4 + ipo.getX() + group.getTranslateX());
                        clo.getLine().startYProperty().set(4 + ipo.getY() + group.getTranslateY());
                    }
                }
            }
        }
        if (outputPinObjects != null) {
            Iterator<OutputPinObject> iterator = outputPinObjects.iterator();
            while (iterator.hasNext()) {
                OutputPinObject opo = iterator.next();
                Iterator<ConnectionLineObject> opoclo = opo.getConnectionLineObjects().iterator();
                while (opoclo.hasNext()) {
                    ConnectionLineObject clo = opoclo.next();
                    if (opo == clo.getOutputPinObjectSource() && clo.getLine() != null) {
                        clo.getLine().endXProperty().set(4 + opo.getX() + group.getTranslateX());
                        clo.getLine().endYProperty().set(4 + opo.getY() + group.getTranslateY());
                    } else if (clo.getLine() != null) {
                        clo.getLine().startXProperty().set(4 + opo.getX() + group.getTranslateX());
                        clo.getLine().startYProperty().set(4 + opo.getY() + group.getTranslateY());
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
        Main.main.showOnConsole("Removed " + name);
        //System.out.println(Main.main.gateGroup.getChildren().size());
        Main.main.gateGroup.getChildren().remove(group);
        Main.main.gateObjects.remove(this);
        gate.destroy();
        gate = null;
        if (inputPinObjects != null) {
            Iterator<InputPinObject> ipoIterator = inputPinObjects.iterator();
            while (ipoIterator.hasNext()) {
                InputPinObject ipo = ipoIterator.next();
                if (Main.main.gateGroup.getChildren().contains(ipo.getConnectionLineObjects().get(0).getLine())) {
                    Main.main.gateGroup.getChildren().remove(ipo.getConnectionLineObjects().get(0).getLine());
                }
                ipo.destroyConnectionLineObjects();
            }
        }
        if (outputPinObjects != null) {
            Iterator<OutputPinObject> opoIterator = outputPinObjects.iterator();
            while (opoIterator.hasNext()) {
                OutputPinObject opo = opoIterator.next();
                if (Main.main.gateGroup.getChildren().contains(opo.getConnectionLineObjects().get(0).getLine())) {
                    Main.main.gateGroup.getChildren().remove(opo.getConnectionLineObjects().get(0).getLine());
                }
                opo.destroyConnectionLineObjects();
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

    public List<InputPinObject> getInputPinObjects() {
        return inputPinObjects;
    }

    public void setInputPinObjects(List<InputPinObject> inputPinObjects) {
        this.inputPinObjects = inputPinObjects;
    }

    public List<OutputPinObject> getOutputPinObjects() {
        return outputPinObjects;
    }

    public void setOutputPinObjects(List<OutputPinObject> outputPinObjects) {
        this.outputPinObjects = outputPinObjects;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GateInterface getGate() {
        return gate;
    }

    public void setGate(GateInterface gate) {
        this.gate = gate;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
