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
package LogicSimulator.Objects;

import LogicSimulator.Objects.Gates.GateLogic.DataObject;
import LogicSimulator.Objects.Gates.GateLogic.GateInterface;
import LogicSimulator.Objects.Gates.GateLogic.LogicLine;
import LogicSimulator.ClipBoard;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ConnectionLineObject {

    final String name;
    public Line line;
    public LogicLine logicLine;
    public GateInterface gate;
    Color colorOff;
    Color colorOn;
    boolean last = false;

    public ConnectionLineObject() {
        name = "A Line";
        line = new Line();
        logicLine = new LogicLine();
        colorOff = Color.RED;
        colorOn = Color.LIGHTGREEN;
    }

    public void update(Long deltaTime) {
        logicLine.update(deltaTime);
        if (logicLine.getInputPin(0).getDataObject().getData() != last) {
            if (logicLine.getInputPin(0).getDataObject().getData() == true) {
                line.setStroke(colorOn);
            } else {
                line.setStroke(colorOff);
            }
        }
        last = logicLine.getInputPin(0).getDataObject().getData();
    }

    public Line createLine(final ConnectionLineObject clo, Group g, Rectangle r, double width, double height) {
        if (line != null) {
            line.setStroke(colorOff);
            line.setStrokeWidth(2);
            //add InputPin shadow effect
            Image cursorImage = Textures.lineCursor;
            ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
            line.setCursor(imageCursor);
            line.setStartX(ClipBoard.getX() + width / 2 + ClipBoard.getGroup().getTranslateX());    // + Dragboard.pinOver.setGroup.getTranslateX()
            line.setStartY(ClipBoard.getY() + height / 2 + ClipBoard.getGroup().getTranslateY());    // + Dragboard.pinOver.setGroup.getTranslateY()
            line.setEndX(r.getTranslateX() + width / 2 + g.getTranslateX());    // + pinOver.setGroup.getTranslateX()
            line.setEndY(r.getTranslateY() + height / 2 + g.getTranslateY());  // + pinOver.setGroup.getTranslateY()
            //add mouse listeners
            Globals.main.showOnConsole("created outline");
            line.setOnMouseClicked((MouseEvent me) -> {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);
                    //ip.setDataObject(null);
                    if (Globals.main.logicLines.contains(logicLine)) {  // -> connectionlineobject
                        logicLine.getInputPin(0).setDataObject(new DataObject(false));
                        logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                        logicLine.setInputPin(0, null);
                        logicLine.setOutputPin(0, null);
                        //logicLine.setDataObject(new DataObject(false));
                        Globals.main.showOnConsole("nully");
                        Globals.main.logicLines.remove(logicLine);
                        logicLine = null;
                    }
                    if (Globals.main.connectionLineObjects.contains(clo)) {  // -> connectionlineobject
                        //logicLine.setDataObject(null);
                        //logicLine=null;
                        Globals.main.showOnConsole("nully this");
                        Globals.main.connectionLineObjects.remove(clo);
                    }
                    if (clo != null) {
                        if (clo.logicLine != null) {
                            if (clo.logicLine.getInputPin(0) != null) {
                                clo.logicLine.getInputPin(0).setDataObject(new DataObject(false));
                                clo.logicLine.setInputPin(0, null);
                            } else if (clo.logicLine.getOutputPin(0) != null) {
                                clo.logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                                clo.logicLine.setOutputPin(0, null);
                            }
                        }
                        //clo.logicLine.setDataObject(null);
                        clo.logicLine = null;
                    }
                    me.consume();
                }
            });
            line.setOnMouseEntered((MouseEvent me) -> {
                //change the z-coordinate of the circle
                if (line != null) {
                    line.toFront();
                }
                me.consume();
            });
            line.setOnMouseExited((MouseEvent me) -> {
                //me.consume();
            });
            return line;
        } else {
            return null;
        }
    }

}