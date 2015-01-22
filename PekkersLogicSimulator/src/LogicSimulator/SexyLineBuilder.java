/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author PEKKA
 */
public class SexyLineBuilder {
    final Main main;
    public SexyLineBuilder(final Main main) {
        this.main = main;
    }
          
    public Line createLine(final boolean parent) {
        //create a circle with desired name,  color and radius
        Color color = Color.DODGERBLUE;
        final String name = "Blue circle"; 

        final Line line = new Line();
        line.setStartX(111.0f);
        line.setStartY(111.0f);
        line.setEndX(222.0f);
        line.setEndY(222.0f);
        line.setStroke(Color.RED);
        line.setStrokeWidth(10);
        //add a shadow effect
        line.setCursor(Cursor.HAND);
        //add a mouse listeners
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    main.showOnConsole("Created new blueCircle");
                    final Line l = createLine(false);
                    l.setTranslateX(300);
                    l.setTranslateY(50);
                    l.toFront();
                    main.circleGroup.getChildren().add(l);
                    //mouseEvents.circleList.add(l);
                } else if (me.getButton() == MouseButton.MIDDLE && !parent) {
                    main.showOnConsole("Removed specified line");
                    //mouseEvents.circleList.remove(circle);
                    main.circleGroup.getChildren().remove(line);
                }
                 me.consume(); 
            }
        });
        line.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                 if (me.getButton() == MouseButton.PRIMARY && !parent) {
                    double dragX = me.getSceneX() - main.dragAnchor.getX();
                    double dragY = me.getSceneY() - main.dragAnchor.getY();
                    //calculate new position of the circle
                    double newXPosition = main.initX + dragX;
                    double newYPosition = main.initY + dragY;
                    //if new position do not exceeds borders of the rectangle, translate to this position
                    if ((newXPosition>=(line.getEndX()-line.getStartX())) && (newXPosition<=main.schematicWidth-(line.getEndX()-line.getStartX()))) {
                        line.setTranslateX(newXPosition);
                    }
                    if ((newYPosition>=line.getEndY()) && (newYPosition<=main.schematicHeigth-line.getEndY())){
                        line.setTranslateY(newYPosition);
                    }
                    main.showOnConsole(name + " was dragged (x:" + dragX + ", y:" + dragY +")");
                }
            }
        });
        line.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
            }
        });
        line.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
            }
        });
        line.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                main.initX = line.getTranslateX();
                main.initY = line.getTranslateY();
                main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                main.showOnConsole("Mouse pressed above " + name);
            }
        });
        line.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse released above " + name);
                if (line.getTranslateX() < (150) && line.getTranslateX() > (- 150) && line.getTranslateY() < (150) && line.getTranslateY() > (- 150)) {
                    line.setTranslateX(150);
                    line.setTranslateY(150);
                }
            }
        });
        return line;
    }
    
   
}
