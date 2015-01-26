package LogicSimulator.GateObjects;

import LogicSimulator.GateObjects.GateLogic.InputPin;
import LogicSimulator.GateObjects.GateLogic.LogicLine;
import LogicSimulator.GateObjects.GateLogic.OutputPin;
import LogicSimulator.ClipBoard;
import LogicSimulator.Globals;
import LogicSimulator.Textures;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class PinObject {
    
    String name;
    int x; // rectangle translate
    int y;
    int width = 8;
    int height = 8;
    protected Rectangle rectangle;
    
    ConnectionLineObject connectionLineObject;
    ConnectionLineObject connectionLineObject2;

    public PinObject() {
        connectionLineObject = new ConnectionLineObject();
    }

    public void setRectangle(Rectangle r){
        rectangle = r;
    }
    
    public Rectangle getRectangle(){
        return rectangle;
    }
         
    public Rectangle createPinRectangle(final Image i, final Group g, final Rectangle rectangle, final OutputPin outputPin, final InputPin inputPin, final String name) {
        
        connectionLineObject = new ConnectionLineObject();
        Image cursorImage = Textures.defaultCursorActive;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        rectangle.setCursor(imageCursor);
        
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
        //rectangle.setCursor(Cursor.HAND);
        //add InputPin mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY){
                    if(ClipBoard.getInputPin() == null && ClipBoard.getOutputPin() == null){
                        if(outputPin == null){  // this is by default a input pin then
                            ClipBoard.setInputPin(inputPin);
                        } else {
                            ClipBoard.setOutputPin(outputPin);
                        }
                        ClipBoard.setLine(connectionLineObject.line);
                        ClipBoard.setConnectionLineObject(connectionLineObject);
                        
                        ClipBoard.setGroup(g);
                        ClipBoard.setName(name);
                        ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                        ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                        ClipBoard.printDragBoard();
                        System.out.println("copied pin to dragboard");
                    } else if(ClipBoard.getInputPin() != null && inputPin == null){
                        LogicLine logicLine = new LogicLine();
                        logicLine.setInputPin(0, ClipBoard.getInputPin());
                        logicLine.setOutputPin(0, outputPin);
                        connectionLineObject.logicLine = logicLine;
                        //createLine(line, logicLine);
                        connectionLineObject2 = ClipBoard.getConnectionLineObject();
                        Line line = connectionLineObject.createLine(connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                        if(connectionLineObject2 != null) connectionLineObject2.line = line;
                        //createLine(line, logicLine);
                        
                        if(!Globals.main.circleGroup.getChildren().contains(line)){
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);
                            System.out.println("line did not exist in schematic");
                        }
                        if(!Globals.main.connectionLineObjects.contains(connectionLineObject)){
                            Globals.main.connectionLineObjects.add(connectionLineObject);
                            System.out.println("line did not exist in schematic");
                        }
                        ClipBoard.clearDragBoard();
                      
                    } else if(ClipBoard.getOutputPin() != null && outputPin == null){
                        LogicLine logicLine = new LogicLine();
                        logicLine.setInputPin(0, inputPin);
                        logicLine.setOutputPin(0, ClipBoard.getOutputPin());
                        connectionLineObject.logicLine = logicLine;
                        //createLine(line, logicLine);
                        connectionLineObject2 = ClipBoard.getConnectionLineObject();
                        Line line = connectionLineObject.createLine(connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                        if(connectionLineObject2 != null) connectionLineObject2.line = line;
                        //createLine(line, logicLine);
                        
                        if(!Globals.main.circleGroup.getChildren().contains(line)){
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);
                            System.out.println("line did not exist in schematic");
                        }
                        if(!Globals.main.connectionLineObjects.contains(connectionLineObject)){
                            Globals.main.connectionLineObjects.add(connectionLineObject);
                            System.out.println("line did not exist in schematic");
                        }
                        ClipBoard.clearDragBoard();
 
                    } else if(ClipBoard.getInputPin() == inputPin && ClipBoard.getOutputPin() == outputPin) {

                            System.out.println("clicked on the same pin, dragboard cleared");
                            ClipBoard.clearDragBoard();
                            ClipBoard.printDragBoard();
                    } else if(ClipBoard.getInputPin() != inputPin && ClipBoard.getInputPin() != null && outputPin == null){
                        
                            ClipBoard.clearDragBoard();
                            ClipBoard.setInputPin(inputPin);
                            ClipBoard.setLine(connectionLineObject.line);
                            ClipBoard.setGroup(g);
                            ClipBoard.setName(name);
                            ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                            ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                            ClipBoard.printDragBoard();
                            System.out.println("sorry bro, you cant link an" + ClipBoard.getInputPin().getClass());
                    } else if(ClipBoard.getOutputPin() != outputPin && ClipBoard.getOutputPin() != null && inputPin == null){
                        
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
                    
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                    //} else if (me.getButton() == MouseButton.PRIMARY) {
                    //System.out.println(" " + pin.type + "  " + pin.getDataObject().getData());
                    //remove line from this to target
                    
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    
                    Globals.main.showOnConsole("Nothing happened");
                    me.consume();
                }
            }
        });
        
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
               // Globals.main.showOnConsole("you are dragging a line from " + name);
                me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                     
                }
                
            }
        });
        
        
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
                //Globals.main.showOnConsole("Mouse over " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                //Globals.main.showOnConsole("Mouse exited " + name);
                me.consume();
            }
        });
        
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // if (me.getButton() == MouseButton.SECONDARY) {
                if (me.getButton() == MouseButton.PRIMARY) {
                rectangle.setFill(new ImagePattern(Textures.pinPressed, 0, 0, 1, 1, true));
                rectangle.toFront();
                 //when mouse is pressed, store initial position
                //Globals.main.initX = rectangle.getTranslateX();
                //Globals.main.initY = rectangle.getTranslateY();
                //Globals.main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //Globals.main.showOnConsole("Mouse pressed above " + name);
                
                me.consume();
                 }
            }
        });
        
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
                 rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                     me.consume();
                //Globals.main.showOnConsole("Mouse released above " + name);
                /*if (rectangle.getTranslateX() < (150) && rectangle.getTranslateX() > (- 150) && rectangle.getTranslateY() < (150) && rectangle.getTranslateY() > (- 150)) {
                    rectangle.setTranslateX(150);
                    rectangle.setTranslateY(150);
                }
                */
            }
        });
        return rectangle;
    }
    
    public Rectangle createInputPinRectangle(final Image i, final Group g, final Rectangle rectangle, final InputPin inputPin, final String name) {
        
        connectionLineObject = new ConnectionLineObject();
        Image cursorImage = Textures.defaultCursorActive;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        rectangle.setCursor(imageCursor);
        
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
        //rectangle.setCursor(Cursor.HAND);
        //add InputPin mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY){
                    if(ClipBoard.getInputPin() == null && ClipBoard.getOutputPin() == null){
                        if(inputPin != null){  // this is by default a input pin then
                            ClipBoard.setInputPin(inputPin);
                        } 
                        ClipBoard.setLine(connectionLineObject.line);
                        ClipBoard.setConnectionLineObject(connectionLineObject);
                        
                        ClipBoard.setGroup(g);
                        ClipBoard.setName(name);
                        ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                        ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                        ClipBoard.printDragBoard();
                        System.out.println("copied pin to dragboard");
                    } else if(ClipBoard.getOutputPin() != null){
                        LogicLine logicLine = new LogicLine();
                        logicLine.setInputPin(0, inputPin);
                        logicLine.setOutputPin(0, ClipBoard.getOutputPin());
                        connectionLineObject.logicLine = logicLine;
                        //createLine(line, logicLine);
                        connectionLineObject2 = ClipBoard.getConnectionLineObject();
                        Line line = connectionLineObject.createLine(connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                        if(connectionLineObject2 != null) connectionLineObject2.line = line;
                        //createLine(line, logicLine);
                        
                        if(!Globals.main.circleGroup.getChildren().contains(line)){
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);
                            System.out.println("line did not exist in schematic");
                        }
                        if(!Globals.main.connectionLineObjects.contains(connectionLineObject)){
                            Globals.main.connectionLineObjects.add(connectionLineObject);
                            System.out.println("line did not exist in schematic");
                        }
                        ClipBoard.clearDragBoard();
 
                    } else if(ClipBoard.getInputPin() == inputPin) {

                            System.out.println("clicked on the same pin, dragboard cleared");
                            ClipBoard.clearDragBoard();
                            ClipBoard.printDragBoard();
                    } else if(ClipBoard.getInputPin() != inputPin && ClipBoard.getInputPin() != null){
                        
                            ClipBoard.clearDragBoard();
                            ClipBoard.setInputPin(inputPin);
                            ClipBoard.setLine(connectionLineObject.line);
                            ClipBoard.setGroup(g);
                            ClipBoard.setName(name);
                            ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                            ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                            ClipBoard.printDragBoard();
                            System.out.println("sorry bro, you cant link an" + ClipBoard.getInputPin().getClass());
                    }
                    
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                    //} else if (me.getButton() == MouseButton.PRIMARY) {
                    //System.out.println(" " + pin.type + "  " + pin.getDataObject().getData());
                    //remove line from this to target
                    
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    
                    Globals.main.showOnConsole("Nothing happened");
                    me.consume();
                }
            }
        });
        
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
               // Globals.main.showOnConsole("you are dragging a line from " + name);
                me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                     
                }
                
            }
        });
        
        
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
                //Globals.main.showOnConsole("Mouse over " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                //Globals.main.showOnConsole("Mouse exited " + name);
                me.consume();
            }
        });
        
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // if (me.getButton() == MouseButton.SECONDARY) {
                if (me.getButton() == MouseButton.PRIMARY) {
                rectangle.setFill(new ImagePattern(Textures.pinPressed, 0, 0, 1, 1, true));
                rectangle.toFront();
                 //when mouse is pressed, store initial position
                //Globals.main.initX = rectangle.getTranslateX();
                //Globals.main.initY = rectangle.getTranslateY();
                //Globals.main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //Globals.main.showOnConsole("Mouse pressed above " + name);
                
                me.consume();
                 }
            }
        });
        
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
                 rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                     me.consume();
                //Globals.main.showOnConsole("Mouse released above " + name);
                /*if (rectangle.getTranslateX() < (150) && rectangle.getTranslateX() > (- 150) && rectangle.getTranslateY() < (150) && rectangle.getTranslateY() > (- 150)) {
                    rectangle.setTranslateX(150);
                    rectangle.setTranslateY(150);
                }
                */
            }
        });
        return rectangle;
    }
    
    
    public Rectangle createOutputPinRectangle(final Image i, final Group g, final Rectangle rectangle, final OutputPin outputPin, final String name) {
        
        connectionLineObject = new ConnectionLineObject();
        Image cursorImage = Textures.defaultCursorActive;
        ImageCursor imageCursor = new ImageCursor(cursorImage, -cursorImage.getWidth(), -cursorImage.getHeight());
        rectangle.setCursor(imageCursor);
        
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
        //rectangle.setCursor(Cursor.HAND);
        //add InputPin mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY){
                    if(ClipBoard.getInputPin() == null && ClipBoard.getOutputPin() == null){
                        if(outputPin != null){  // this is by default a input pin then
                            ClipBoard.setOutputPin(outputPin);
                        }
                        ClipBoard.setLine(connectionLineObject.line);
                        ClipBoard.setConnectionLineObject(connectionLineObject);
                        
                        ClipBoard.setGroup(g);
                        ClipBoard.setName(name);
                        ClipBoard.setX(rectangle.getTranslateX());     // + Dragboard.pinOver.setGroup.getTranslateX()
                        ClipBoard.setY(rectangle.getTranslateY());      // + Dragboard.pinOver.setGroup.getTranslateY()
                        ClipBoard.printDragBoard();
                        System.out.println("copied pin to dragboard");
                    } else if(ClipBoard.getInputPin() != null){
                        LogicLine logicLine = new LogicLine();
                        logicLine.setInputPin(0, ClipBoard.getInputPin());
                        logicLine.setOutputPin(0, outputPin);
                        connectionLineObject.logicLine = logicLine;
                        //createLine(line, logicLine);
                        connectionLineObject2 = ClipBoard.getConnectionLineObject();
                        Line line = connectionLineObject.createLine(connectionLineObject2, g, rectangle, rectangle.getWidth(), rectangle.getHeight());
                        if(connectionLineObject2 != null) connectionLineObject2.line = line;
                        //createLine(line, logicLine);
                        
                        if(!Globals.main.circleGroup.getChildren().contains(line)){
                            Globals.main.circleGroup.getChildren().add(line);
                            Globals.main.logicLines.add(logicLine);
                            System.out.println("line did not exist in schematic");
                        }
                        if(!Globals.main.connectionLineObjects.contains(connectionLineObject)){
                            Globals.main.connectionLineObjects.add(connectionLineObject);
                            System.out.println("line did not exist in schematic");
                        }
                        ClipBoard.clearDragBoard();
                      
                    }  else if(ClipBoard.getOutputPin() == outputPin) {

                            System.out.println("clicked on the same pin, dragboard cleared");
                            ClipBoard.clearDragBoard();
                            ClipBoard.printDragBoard();
                    } else if(ClipBoard.getOutputPin() != outputPin && ClipBoard.getOutputPin() != null){
                        
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
                    
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                    //} else if (me.getButton() == MouseButton.PRIMARY) {
                    //System.out.println(" " + pin.type + "  " + pin.getDataObject().getData());
                    //remove line from this to target
                    
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    
                    Globals.main.showOnConsole("Nothing happened");
                    me.consume();
                }
            }
        });
        
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
               // Globals.main.showOnConsole("you are dragging a line from " + name);
                me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                     
                }
                
            }
        });
        
        
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
                //Globals.main.showOnConsole("Mouse over " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                //Globals.main.showOnConsole("Mouse exited " + name);
                me.consume();
            }
        });
        
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // if (me.getButton() == MouseButton.SECONDARY) {
                if (me.getButton() == MouseButton.PRIMARY) {
                rectangle.setFill(new ImagePattern(Textures.pinPressed, 0, 0, 1, 1, true));
                rectangle.toFront();
                 //when mouse is pressed, store initial position
                //Globals.main.initX = rectangle.getTranslateX();
                //Globals.main.initY = rectangle.getTranslateY();
                //Globals.main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //Globals.main.showOnConsole("Mouse pressed above " + name);
                
                me.consume();
                 }
            }
        });
        
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                // rectangle.setFill(new ImagePattern(Textures.pinOver, 0, 0, 1, 1, true));
                 rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
                     me.consume();
                //Globals.main.showOnConsole("Mouse released above " + name);
                /*if (rectangle.getTranslateX() < (150) && rectangle.getTranslateX() > (- 150) && rectangle.getTranslateY() < (150) && rectangle.getTranslateY() > (- 150)) {
                    rectangle.setTranslateX(150);
                    rectangle.setTranslateY(150);
                }
                */
            }
        });
        return rectangle;
    }


}
