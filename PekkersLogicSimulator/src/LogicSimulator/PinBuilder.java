/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import Logic.InputPin;
import Logic.Line;
import Logic.OutputPin;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class PinBuilder {
        
    final Main main;
    
    public PinBuilder (final Main main) {
        this.main = main;
    }
    
    public Rectangle createInputPin(int x , int y, InputPin inputPin, String n) {
        Image texture = new Image("file:res/inputpin.png");          // hardcoded inputpin texture
        final Rectangle rectangle = new Rectangle(8, 8);
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
        rectangle.setFill(new ImagePattern(texture, 0, 0, 1, 1, true));
        
        //Line line = new Line();
        final String name = "InputPin " + n; 

        rectangle.setCursor(Cursor.HAND);
        //add a mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.SECONDARY) {
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    main.showOnConsole("Removed specified Rectangle");
                }
                 me.consume(); 
            }
        });
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                main.showOnConsole("you are dragging a line from " + name);
                me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                }
                
            }
        });
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
                me.consume();
            }
        });
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rectangle.toFront();
                 //when mouse is pressed, store initial position
                main.initX = rectangle.getTranslateX();
                main.initY = rectangle.getTranslateY();
                main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                main.showOnConsole("Mouse pressed above " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //main.showOnConsole("Mouse released above " + name);
                /*if (rectangle.getTranslateX() < (150) && rectangle.getTranslateX() > (- 150) && rectangle.getTranslateY() < (150) && rectangle.getTranslateY() > (- 150)) {
                    rectangle.setTranslateX(150);
                    rectangle.setTranslateY(150);
                }
                */
            }
        });
        return rectangle;
    }
    
     public Rectangle createOutputPin(int x , int y, OutputPin outputPin, String n) {
        Image texture = new Image("file:res/outputpin.png");          // hardcoded outputpin texture
        final Rectangle rectangle = new Rectangle(8, 8);
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
        rectangle.setFill(new ImagePattern(texture, 0, 0, 1, 1, true));
        
        final String name = "OutputPin " + n; 

        rectangle.setCursor(Cursor.HAND);
        //add a mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    //remove line from this to target
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    main.showOnConsole("Removed specified Rectangle");
                }
                 me.consume(); 
            }
        });
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                main.showOnConsole("you are dragging a line from " + name);
                me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                }
                
            }
        });
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
                me.consume();
            }
        });
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rectangle.toFront();
                 //when mouse is pressed, store initial position
                main.initX = rectangle.getTranslateX();
                main.initY = rectangle.getTranslateY();
                main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                main.showOnConsole("Mouse pressed above " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //main.showOnConsole("Mouse released above " + name);
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
