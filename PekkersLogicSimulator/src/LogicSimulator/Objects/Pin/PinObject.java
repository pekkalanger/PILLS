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
import LogicSimulator.Objects.Gates.GateLogic.LogicLine;
import LogicSimulator.Objects.Gates.GateLogic.OutputPin;
import LogicSimulator.ClipBoard;
import static LogicSimulator.ClipBoard.connectionLineObject;
import LogicSimulator.Objects.Gates.GateLogic.DataObject;
import LogicSimulator.Globals;
import LogicSimulator.InfoPopup;
import LogicSimulator.Objects.ConnectionLineObject;
import LogicSimulator.Textures;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public abstract class PinObject implements PInterface {

    protected InputPin inputPin;
    protected OutputPin outputPin;
    protected String name;
    protected int x; // schematicRectangle translate
    protected int y;
    protected static final int width = 8;
    protected static final int height = 8;
    protected Rectangle rectangle;
    protected Image infoImage;
    protected Image gateImage;
    protected List<ConnectionLineObject> connectionLineObjects;
    protected ConnectionLineObject connectionLineObject2;

    public PinObject(String n, int x, int y, OutputPin op) {
        this(n, x, y);
        inputPin = null;
        outputPin = op;
    }

    public PinObject(String n, int x, int y, InputPin ip) {
        this(n, x, y);
        inputPin = ip;
        outputPin = null;
    }

    public PinObject(String n, int x, int y) {
        this();
        name = n;
        this.x = x;
        this.y = y;
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
    }

    public PinObject() {
        infoImage = Textures.getHmImage("texture");
        gateImage = Textures.getHmImage("texture");
        connectionLineObjects = new ArrayList<>();
        connectionLineObject2 = new ConnectionLineObject();
        rectangle = new Rectangle(width, height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getInfoImage() {
        return infoImage;
    }

    public void setInfoImage(Image infoImage) {
        this.infoImage = infoImage;
    }

    public Image getGateImage() {
        return gateImage;
    }

    public void setGateImage(Image gateImage) {
        this.gateImage = gateImage;
    }

    public List<ConnectionLineObject> getConnectionLineObjects() {
        return connectionLineObjects;
    }

    public void setConnectionLineObjects(List<ConnectionLineObject> connectionLineObjects) {
        this.connectionLineObjects = connectionLineObjects;
    }

    public ConnectionLineObject getConnectionLineObject2() {
        return connectionLineObject2;
    }

    public void setConnectionLineObject2(ConnectionLineObject connectionLineObject2) {
        this.connectionLineObject2 = connectionLineObject2;
    }

    public void setRectangle(Rectangle r) {
        rectangle = r;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Rectangle createPinRectangle(InputPinObject ipo, final InputPin inputPin, final Group g) {
        ConnectionLineObject connectionLineObject = new ConnectionLineObject();
        connectionLineObjects.add(connectionLineObject);

        Image cursorImage = Textures.getHmImage("defaultcursorractive");
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        rectangle.setCursor(imageCursor);//rectangle.setCursor(Cursor.HAND);
        rectangle.setFill(new ImagePattern(gateImage, 0, 0, 1, 1, true));
        rectangle.setOnMouseClicked((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                if (ClipBoard.getInputPin() == null && ClipBoard.getOutputPin() == null) {
                    setDragBoard(inputPin, null, ipo, null, g);
                } else if (ClipBoard.getOutputPin() != null) {
                    if (ClipBoard.getConnectionLineObject2() != null) {
                        LogicLine ll = ClipBoard.getConnectionLineObject2().getLogicLine();
                        nullLogicLine(ll);
                        ClipBoard.setConnectionLineObject2(null);
                    }
                    LogicLine logicLine = new LogicLine();
                    logicLine.setInputPin(0, inputPin);
                    logicLine.setOutputPin(0, ClipBoard.getOutputPin());
                    ConnectionLineObject connectionLineObject3 = new ConnectionLineObject();
                    connectionLineObject3.setLogicLine(logicLine);
                    connectionLineObject3.setInputPinObjectSource(ipo);
                    connectionLineObjects.add(connectionLineObject3);
                    connectionLineObject2 = ClipBoard.getConnectionLineObject();
                    Line line = connectionLineObject3.createLine(connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                    addLine(line, logicLine);
                    if (ClipBoard.getOutputPinObject() != null && !ClipBoard.getOutputPinObject().connectionLineObjects.contains(connectionLineObject3)) {
                        ClipBoard.getOutputPinObject().connectionLineObjects.add(connectionLineObject3);
                    }
                    if (!Globals.main.connectionLineObjects.contains(connectionLineObject3)) {
                        Globals.main.connectionLineObjects.add(connectionLineObject3);
                    }
                    ClipBoard.clearDragBoard();
                } else if (ClipBoard.getInputPin() == inputPin) {
                    ClipBoard.clearDragBoard();
                } else if (ClipBoard.getInputPin() != inputPin && ClipBoard.getInputPin() != null) {
                    setDragBoard(inputPin, null, ipo, null, g);
                }
            } else if (me.getButton() == MouseButton.MIDDLE) {
                me.consume();
            }
        });
        rectangle.setOnMouseDragged((MouseEvent me) -> {
            me.consume();
        });

        setOnMousePressedReleased(gateImage);
        InfoPopup.setinfoPopup(rectangle, infoImage);
        return rectangle;
    }

    public Rectangle createPinRectangle(OutputPinObject opo, final OutputPin outputPin, final Group g) {
        ConnectionLineObject connectionLineObject = new ConnectionLineObject();
        connectionLineObjects.add(connectionLineObject);
        Image cursorImage = Textures.getHmImage("defaultcursorractive");
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        rectangle.setCursor(imageCursor);//rectangle.setCursor(Cursor.HAND);
        rectangle.setFill(new ImagePattern(gateImage, 0, 0, 1, 1, true));
        rectangle.setOnMouseClicked((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                if (ClipBoard.getInputPin() == null && ClipBoard.getOutputPin() == null) {
                    setDragBoard(null, outputPin, null, opo, g);
                } else if (ClipBoard.getInputPin() != null) {
                    if (ClipBoard.getConnectionLineObject2() != null) {
                        LogicLine ll = ClipBoard.getConnectionLineObject2().getLogicLine();
                        nullLogicLine(ll);
                        ClipBoard.setConnectionLineObject2(null);
                    }
                    LogicLine logicLine = new LogicLine();
                    logicLine.setInputPin(0, ClipBoard.getInputPin());
                    logicLine.setOutputPin(0, outputPin);
                    ConnectionLineObject connectionLineObject3 = new ConnectionLineObject();
                    connectionLineObject3.setLogicLine(logicLine);
                    connectionLineObject3.setOutputPinObjectSource(opo);
                    connectionLineObjects.add(connectionLineObject3);
                    connectionLineObject2 = ClipBoard.getConnectionLineObject();
                    Line line = connectionLineObject3.createLine(connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                    addLine(line, logicLine);
                    if (ClipBoard.getInputPinObject() != null && !ClipBoard.getInputPinObject().connectionLineObjects.contains(connectionLineObject3)) {
                        ClipBoard.getInputPinObject().connectionLineObjects.add(connectionLineObject3);
                    }
                    if (!Globals.main.connectionLineObjects.contains(connectionLineObject3)) {
                        Globals.main.connectionLineObjects.add(connectionLineObject3);
                    }
                    ClipBoard.clearDragBoard();
                } else if (ClipBoard.getOutputPin() == outputPin) {
                    ClipBoard.clearDragBoard();
                } else if (ClipBoard.getOutputPin() != outputPin && ClipBoard.getOutputPin() != null) {
                    setDragBoard(null, outputPin, null, opo, g);
                    System.out.println("sorry bro, you cant link an" + ClipBoard.getOutputPin().getClass());
                }
            } else if (me.getButton() == MouseButton.MIDDLE) {
                me.consume();
            }
        });
        rectangle.setOnMouseDragged((MouseEvent me) -> {
            me.consume();
        });

        setOnMousePressedReleased(gateImage);
        InfoPopup.setinfoPopup(rectangle, infoImage);

        return rectangle;
    }

    public void setOnMousePressedReleased(Image i) {
        rectangle.setOnMousePressed((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                rectangle.setFill(new ImagePattern(Textures.getHmImage("pinPressed"), 0, 0, 1, 1, true));
                rectangle.toFront();
                me.consume();
            }
        });
        rectangle.setOnMouseReleased((MouseEvent me) -> {
            rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
            me.consume();
        });
    }

    public void nullLogicLine(LogicLine ll) {
        if (ll != null) {
            ll.getInputPin(0).setDataObject(new DataObject(false));
            ll.getOutputPin(0).setDataObject(new DataObject(false));
            ll.setInputPin(0, new InputPin());
            ll.setOutputPin(0, new OutputPin());
            //ll = null;
        }
    }

    public void addLine(Line line, LogicLine logicLine) {
        if (line != null && !Globals.main.gateGroup.getChildren().contains(line)) {
            Globals.main.gateGroup.getChildren().add(line);
            Globals.main.logicLines.add(logicLine);
        }
    }

    public void setDragBoard(InputPin inputPin, OutputPin outputPin, InputPinObject ipo, OutputPinObject opo, Group g) {
        ClipBoard.clearDragBoard();
        ClipBoard.setInputPin(inputPin);
        ClipBoard.setOutputPin(outputPin);
        if (connectionLineObject != null) {
            ClipBoard.setLine(connectionLineObject.getLine());
        }
        ClipBoard.setGroup(g);
        ClipBoard.setName(name);
        ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
        ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
        ClipBoard.setOutputPinObject(opo);
        ClipBoard.setInputPinObject(ipo);
        ClipBoard.setConnectionLineObject(connectionLineObject);
        ClipBoard.setConnectionLineObject2(connectionLineObject2);
    }

    public void destroyConnectionLineObjects() {
        Iterator<ConnectionLineObject> ipoclo = connectionLineObjects.iterator();
        while (ipoclo.hasNext()) {
            ConnectionLineObject clo = ipoclo.next();
            clo.destroy();
        }
    }

}
