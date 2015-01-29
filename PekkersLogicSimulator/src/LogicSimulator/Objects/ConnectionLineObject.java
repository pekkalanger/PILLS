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
import LogicSimulator.Objects.Gates.GateLogic.LogicLine;
import LogicSimulator.ClipBoard;
import LogicSimulator.Globals;
import LogicSimulator.Objects.Pin.InputPinObject;
import LogicSimulator.Objects.Pin.OutputPinObject;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ConnectionLineObject {

    final String name;
    public Line line;
    public LogicLine logicLine;
    Color colorOff;
    Color colorOn;
    boolean last = false;
    public InputPinObject inputPinObjectSource = null;
    public OutputPinObject outputPinObjectSource = null;

    public ConnectionLineObject() {
        name = "A Line";
        line = new Line();
        line.setStrokeWidth(3);
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

    public void destroy() {
        if (Globals.main.gateGroup.getChildren().contains(line)) {
            Globals.main.gateGroup.getChildren().remove(line);
            line = null;
        }
        if (Globals.main.logicLines.contains(logicLine)) {  // -> connectionlineobject
            Globals.main.logicLines.remove(logicLine);
        }
        if (Globals.main.connectionLineObjects.contains(this)) {  // -> connectionlineobject
            Globals.main.connectionLineObjects.remove(this);
        }
        if (logicLine != null) {
            logicLine.destroy();
            logicLine = null;
        }
        //boolean last = false;
        //inputPinObjectSource = null;
        //outputPinObjectSource = null;
    }

    public Line createLine(final ConnectionLineObject clo, Group g, Rectangle r, double width, double height) {
        if (line != null) {

            //add InputPin shadow effect
            //Image cursorImage = Textures.lineCursor;
            //ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
            //line.setCursor(imageCursor);
            line.setStartX(width / 2 + ClipBoard.getX() + ClipBoard.getGroup().getTranslateX());    // + Dragboard.pinOver.setGroup.getTranslateX()
            line.setStartY(height / 2 + ClipBoard.getY() + ClipBoard.getGroup().getTranslateY());    // + Dragboard.pinOver.setGroup.getTranslateY()
            line.setEndX(width / 2 + r.getTranslateX() + g.getTranslateX());    // + pinOver.setGroup.getTranslateX()
            line.setEndY(height / 2 + r.getTranslateY() + g.getTranslateY());  // + pinOver.setGroup.getTranslateY()
            //add mouse listeners
            Globals.main.showOnConsole("Created New Line");
            line.setOnMouseClicked((MouseEvent me) -> {
                if (me.getButton() == MouseButton.MIDDLE) {
                    destroy();
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

            if (logicLine.getInputPin(0).getDataObject().getData() == true) {
                line.setStroke(colorOn);
            } else {
                line.setStroke(colorOff);
            }
            return line;
        } else {
            return null;
        }
    }

}
