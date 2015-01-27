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
import LogicSimulator.Objects.Gates.GateLogic.DataObject;
import LogicSimulator.Globals;
import LogicSimulator.Objects.ConnectionLineObject;
import LogicSimulator.Textures;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class PinObject {

    String name;
    public int x; // rectangle translate
    public int y;
    static final int width = 8;
    static final int height = 8;
    protected Rectangle rectangle;

    public List<ConnectionLineObject> connectionLineObjects;
    public ConnectionLineObject connectionLineObject2;

    public PinObject() {
        x = 0;
        y = 0;
        connectionLineObjects = new ArrayList<>();
        connectionLineObject2 = new ConnectionLineObject();
    }

    public void setRectangle(Rectangle r) {
        rectangle = r;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    /*      this one seems to be acting fine*/
    public Rectangle createPinRectangle(InputPinObject ipo, final Image i, final Group g, final Rectangle rectangle, final InputPin inputPin, final String name) {
        ConnectionLineObject connectionLineObject = new ConnectionLineObject();
        connectionLineObjects.add(connectionLineObject);

        Image cursorImage = Textures.defaultCursorActive;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        rectangle.setCursor(imageCursor);//rectangle.setCursor(Cursor.HAND);
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
        rectangle.setOnMouseClicked((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                if (ClipBoard.getInputPin() == null && ClipBoard.getOutputPin() == null) {
                    if (inputPin != null) {  // this is by default a input pin then
                        ClipBoard.setInputPin(inputPin);
                    }

                    ClipBoard.setInputPinObject(ipo);
                    ClipBoard.setLine(connectionLineObject.line);
                    ClipBoard.setConnectionLineObject(connectionLineObject);
                    ClipBoard.setConnectionLineObject2(connectionLineObject2);
                    ClipBoard.setGroup(g);
                    ClipBoard.setName(name);
                    ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                    ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                    ClipBoard.printDragBoard();
                    System.out.println("copied pin to dragboard");
                } else if (ClipBoard.getOutputPin() != null) {

                    if (ClipBoard.getConnectionLineObject2() != null) {

                        LogicLine ll = ClipBoard.getConnectionLineObject2().logicLine;
                        if (ll != null) {
                            ll.getInputPin(0).setDataObject(new DataObject(false));
                            ll.getOutputPin(0).setDataObject(new DataObject(false));
                            ll.setInputPin(0, new InputPin());
                            ll.setOutputPin(0, new OutputPin());
                            // logicLine.setDataObject(null);
                            ll = null;
                        }
                        ClipBoard.setConnectionLineObject2(null);
                    }
                    LogicLine logicLine = new LogicLine();
                    logicLine.setInputPin(0, inputPin);
                    logicLine.setOutputPin(0, ClipBoard.getOutputPin());
                    ConnectionLineObject connectionLineObject3 = new ConnectionLineObject();
                    connectionLineObjects.add(connectionLineObject3);
                    connectionLineObject3.logicLine = logicLine;
                    connectionLineObject2 = ClipBoard.getConnectionLineObject();
                    Line line = connectionLineObject3.createLine(connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                    /*if (connectionLineObject2 != null) {
                     connectionLineObject2.line = line;
                     }*/
                    if (line != null && !Globals.main.circleGroup.getChildren().contains(line)) {
                        Globals.main.circleGroup.getChildren().add(line);
                        Globals.main.logicLines.add(logicLine);
                        System.out.println("line did not exist in schematic");
                    }
                    if (!Globals.main.connectionLineObjects.contains(connectionLineObject3)) {
                        Globals.main.connectionLineObjects.add(connectionLineObject3);
                        System.out.println("line did not exist in schematic");
                    }

                    ClipBoard.clearDragBoard();
                } else if (ClipBoard.getInputPin() == inputPin) {
                    System.out.println("clicked on the same pin, dragboard cleared");
                    ClipBoard.clearDragBoard();
                    ClipBoard.printDragBoard();
                } else if (ClipBoard.getInputPin() != inputPin && ClipBoard.getInputPin() != null) {
                    ClipBoard.clearDragBoard();
                    ClipBoard.setInputPin(inputPin);
                    ClipBoard.setLine(connectionLineObject.line);
                    ClipBoard.setGroup(g);
                    ClipBoard.setName(name);
                    ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                    ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                    ClipBoard.printDragBoard();
                    System.out.println("sorry bro, you cant link it to a sametype pin");
                }
            } else if (me.getButton() == MouseButton.MIDDLE) {
                Globals.main.showOnConsole("Nothing happened");
                me.consume();
            }
        });
        rectangle.setOnMouseDragged((MouseEvent me) -> {
            me.consume();
        });
        rectangle.setOnMouseEntered((MouseEvent me) -> {
            rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
            me.consume();
        });
        rectangle.setOnMouseExited((MouseEvent me) -> {
            rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
            me.consume();
        });
        rectangle.setOnMousePressed((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                rectangle.setFill(new ImagePattern(Textures.pinPressed, 0, 0, 1, 1, true));
                rectangle.toFront();
                me.consume();
            }
        });
        rectangle.setOnMouseReleased((MouseEvent me) -> {
            rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
            me.consume();
        });
        return rectangle;
    }

    public Rectangle createPinRectangle(OutputPinObject opo, final Image i, final Group g, final Rectangle rectangle, final OutputPin outputPin, final String name) {
        ConnectionLineObject connectionLineObject = new ConnectionLineObject();
        connectionLineObjects.add(connectionLineObject);

        Image cursorImage = Textures.defaultCursorActive;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        rectangle.setCursor(imageCursor);//rectangle.setCursor(Cursor.HAND);
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
        rectangle.setOnMouseClicked((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                if (ClipBoard.getInputPin() == null && ClipBoard.getOutputPin() == null) {
                    if (outputPin != null) {  // this is by default a input pin then
                        ClipBoard.setOutputPin(outputPin);
                    }
                    ClipBoard.setOutputPinObject(opo);
                    ClipBoard.setLine(connectionLineObject.line);
                    ClipBoard.setConnectionLineObject(connectionLineObject);
                    ClipBoard.setConnectionLineObject2(connectionLineObject2);
                    ClipBoard.setGroup(g);
                    ClipBoard.setName(name);
                    ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                    ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                    ClipBoard.printDragBoard();
                    System.out.println("copied pin to dragboard");
                } else if (ClipBoard.getInputPin() != null) {
                    if (ClipBoard.getConnectionLineObject2() != null) {
                        LogicLine ll = ClipBoard.getConnectionLineObject2().logicLine;
                        if (ll != null) {
                            ll.getInputPin(0).setDataObject(new DataObject(false));
                            ll.getOutputPin(0).setDataObject(new DataObject(false));
                            ll.setInputPin(0, new InputPin());
                            ll.setOutputPin(0, new OutputPin());
                            // logicLine.setDataObject(null);
                            ll = null;
                        }
                        ClipBoard.setConnectionLineObject2(null);
                    }
                    LogicLine logicLine = new LogicLine();
                    logicLine.setInputPin(0, ClipBoard.getInputPin());
                    logicLine.setOutputPin(0, outputPin);
                    ConnectionLineObject connectionLineObject3 = new ConnectionLineObject();
                    connectionLineObjects.add(connectionLineObject3);
                    connectionLineObject3.logicLine = logicLine;
                    connectionLineObject2 = ClipBoard.getConnectionLineObject();
                    Line line = connectionLineObject3.createLine(connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                    /*if (connectionLineObject2 != null) {
                     connectionLineObject2.line = line;
                     }
                     */
                    /*ConnectionLineObject connectionLineObject2 = ClipBoard.getConnectionLineObject();
                     if(connectionLineObject2 != null){
                     connectionLineObject2.line = null;
                     if(connectionLineObject2.logicLine != null){
                     connectionLineObject2.logicLine.getInputPin(0).setDataObject(new DataObject(false));
                     connectionLineObject2.logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                     connectionLineObject2.logicLine.setInputPin(0, null);
                     connectionLineObject2.logicLine.setOutputPin(0, null);
                     connectionLineObject2.logicLine=null;
                     }
                     }*/
                    if (line != null) {
                        if (!Globals.main.circleGroup.getChildren().contains(line)) {
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);
                            System.out.println("line did not exist in schematic");
                        }
                    }
                    if (!Globals.main.connectionLineObjects.contains(connectionLineObject3)) {
                        Globals.main.connectionLineObjects.add(connectionLineObject3);
                        System.out.println("line did not exist in schematic");
                    }
                    ClipBoard.clearDragBoard();
                } else if (ClipBoard.getOutputPin() == outputPin) {
                    System.out.println("clicked on the same pin, dragboard cleared");
                    ClipBoard.clearDragBoard();
                    ClipBoard.printDragBoard();
                } else if (ClipBoard.getOutputPin() != outputPin && ClipBoard.getOutputPin() != null) {
                    ClipBoard.clearDragBoard();
                    ClipBoard.setOutputPin(outputPin);
                    ClipBoard.setLine(connectionLineObject.line);
                    ClipBoard.setGroup(g);
                    ClipBoard.setName(name);
                    ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                    ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                    ClipBoard.printDragBoard();
                    System.out.println("sorry bro, you cant link an" + ClipBoard.getOutputPin().getClass());
                }
            } else if (me.getButton() == MouseButton.MIDDLE) {
                Globals.main.showOnConsole("Nothing happened");
                me.consume();
            }
        });
        rectangle.setOnMouseDragged((MouseEvent me) -> {
            me.consume();
        });
        rectangle.setOnMouseEntered((MouseEvent me) -> {
            rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
            me.consume();
        });
        rectangle.setOnMouseExited((MouseEvent me) -> {
            rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
            me.consume();
        });
        rectangle.setOnMousePressed((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                rectangle.setFill(new ImagePattern(Textures.pinPressed, 0, 0, 1, 1, true));
                rectangle.toFront();
                me.consume();
            }
        });
        rectangle.setOnMouseReleased((MouseEvent me) -> {
            rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
            me.consume();
        });
        return rectangle;
    }

}
