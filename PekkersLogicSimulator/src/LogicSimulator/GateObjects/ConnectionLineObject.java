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
package LogicSimulator.GateObjects;

import LogicSimulator.GateObjects.GateLogic.DataObject;
import LogicSimulator.GateObjects.GateLogic.Gate;
import LogicSimulator.GateObjects.GateLogic.LogicLine;
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
    Gate gate;
    Color colorOff;
    Color colorOn; 

    public ConnectionLineObject() {
        name = "A Line"; 
        line = new Line();
        logicLine = new LogicLine();
        colorOff = Color.RED;
        colorOn = Color.GREENYELLOW; 
    }
    
    public void update(Long deltaTime){
         logicLine.update(deltaTime);
    }
    
    public Line createLine(final ConnectionLineObject clo, Group g, Rectangle r, double width, double height) {

        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        //add InputPin shadow effect
        Image cursorImage = Textures.lineCursor;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        line.setCursor(imageCursor);
        line.setStartX(ClipBoard.getX() + width/2 + ClipBoard.getGroup().getTranslateX());    // + Dragboard.pinOver.setGroup.getTranslateX()
        line.setStartY(ClipBoard.getY() + height/2 + ClipBoard.getGroup().getTranslateY());    // + Dragboard.pinOver.setGroup.getTranslateY()
        line.setEndX(r.getTranslateX() + width/2 + g.getTranslateX());    // + pinOver.setGroup.getTranslateX()
        line.setEndY(r.getTranslateY() + height/2 + g.getTranslateY());  // + pinOver.setGroup.getTranslateY()
        //add InputPin mouse listeners
        Globals.main.showOnConsole("created outline");
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    Globals.main.circleGroup.getChildren().remove(line);
                    //ip.setDataObject(null);
                    if(Globals.main.logicLines.contains(logicLine)){  // -> connectionlineobject
                        logicLine.getInputPin(0).setDataObject(new DataObject(false));
                        logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                        logicLine.setInputPin(0, null);
                        logicLine.setOutputPin(0, null);
                        // logicLine.setDataObject(null);
                        logicLine=null;
                        
                        Globals.main.showOnConsole("nully");
                        Globals.main.logicLines.remove(logicLine);
                    }
                    if(Globals.main.connectionLineObjects.contains(this)){  // -> connectionlineobject
                        //logicLine.setDataObject(null);
                        //logicLine=null;
                        Globals.main.showOnConsole("nully this");
                        Globals.main.connectionLineObjects.remove(this);
                    }
                    
                    if(clo != null){
                        if(clo.logicLine != null){
                            if(clo.logicLine.getInputPin(0) != null){
                                clo.logicLine.getInputPin(0).setDataObject(new DataObject(false));
                                clo.logicLine.setInputPin(0, null);
                            } else if(clo.logicLine.getOutputPin(0) != null){
                                clo.logicLine.getOutputPin(0).setDataObject(new DataObject(false));
                                clo.logicLine.setOutputPin(0, null);
                            }
                        }
                        //clo.logicLine.setDataObject(null);
                        clo.logicLine=null;
                    }
                    if(Globals.main.connectionLineObjects.contains(clo)){  // -> connectionlineobject
                        Globals.main.showOnConsole("nully that");
                        Globals.main.connectionLineObjects.remove(clo);
                        Globals.main.logicLines.remove(clo.logicLine);
                    }
                    me.consume();
                }
            }
        });
        
        line.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                line.toFront();
                //Globals.main.showOnConsole("Mouse over " + name);
            }
        });
        line.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                
                //Globals.main.showOnConsole("Mouse exited " + name);
            }
        });
        return line;
    }
    
}