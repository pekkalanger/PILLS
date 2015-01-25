/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 *
 * @author PEKKA
 */
public class SexyCircleBuilder {
    
    final Main main;
    
    public SexyCircleBuilder(final Main main) {
        this.main = main;
    }
          
    public Circle createBlueCircle(final boolean parent) {
        //create a circle with desired name,  color and radius
        Color color = Color.DODGERBLUE;
        int radius = 10;
        final String name = "Blue circle"; 
        final Circle circle = new Circle(radius, new RadialGradient(0, 0, 0.2, 0.3, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
            new Stop(0, Color.rgb(250,250,255,0.5f)),
            new Stop(1, color)
        }));
        //add a shadow effect
        circle.setEffect(new InnerShadow(7, color.darker().darker()));
        //change a cursor when it is over circle
        circle.setCursor(Cursor.HAND);
        //add a mouse listeners
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                circle.toFront();
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    main.showOnConsole("Created new blueCircle");
                    final Circle c = createBlueCircle(false);
                    c.setTranslateX(300);
                    c.setTranslateY(50);
                    c.toFront();
                    main.circleGroup.getChildren().add(c);
                    main.circleList.add(c);
                } else if (me.getButton() == MouseButton.MIDDLE && !parent) {
                    main.showOnConsole("Removed specified orangeCircle");
                    main.circleList.remove(circle);
                    main.circleGroup.getChildren().remove(circle);
                }
                 me.consume(); 
            }
        });
        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {

                 if (me.getButton() == MouseButton.PRIMARY && !parent) {
                    double dragX = me.getSceneX() - main.dragAnchor.getX();
                    double dragY = me.getSceneY() - main.dragAnchor.getY();
                    //calculate new position of the circle
                    double newXPosition = main.initX + dragX;
                    double newYPosition = main.initY + dragY;
                    //System.out.println(main.initX + " + " + me.getSceneX() + " - " + main.dragAnchor.getX());
                    //if new position do not exceeds borders of the rectangle, translate to this position
                    if ((newXPosition>=circle.getRadius()) && (newXPosition<=main.schematicWidth-circle.getRadius())) {
                        circle.setTranslateX(newXPosition);
                    }
                    if ((newYPosition>=circle.getRadius()) && (newYPosition<=main.schematicHeigth-circle.getRadius())){
                        circle.setTranslateY(newYPosition);
                    }
                    main.showOnConsole(name + " was dragged (x:" + dragX + ", y:" + dragY +")");
                } else if (me.getButton() == MouseButton.SECONDARY && !parent) {
                /*    
                    double dragX = main.dragAnchor.getX();
                    double dragY = main.dragAnchor.getY();
                    //calculate new position of the circle
                    double newXPosition = main.initX + dragX;
                    double newYPosition = main.initY + dragY;
                    
                    final Line line = new Line();
                    line.setStartX(dragX);
                    line.setStartY(dragY);
                    main.showOnConsole(" " + dragX + " " + dragY);
                    line.setEndX(newXPosition);
                    line.setEndY(newYPosition);
                    line.setStroke(Color.RED);
                    line.setStrokeWidth(10);
                    main.circleGroup.getChildren().add(line);
                    
                    */
                }
                    
            }
        });
        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
            }
        });
        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
            }
        });
        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                main.initX = circle.getTranslateX();
                main.initY = circle.getTranslateY();
                main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                main.showOnConsole("Mouse pressed above " + name);
            }
        });
        circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse released above " + name);
                if (circle.getTranslateX() < (150) && circle.getTranslateX() > (- 150) && circle.getTranslateY() < (150) && circle.getTranslateY() > (- 150)) {
                    circle.setTranslateX(150);
                    circle.setTranslateY(150);
                }
            }
        });
        return circle;
    }
    
    public Circle createOrangeCircle(final boolean parent) {
        //create a circle with desired name,  color and radius
        Color color = Color.CORAL;
        int radius = 40;
        final String name = "Orange circle"; 
        final Circle circle = new Circle(radius, new RadialGradient(0, 0, 0.2, 0.3, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
            new Stop(0, Color.rgb(250,250,255)),
            new Stop(1, color)
        }));
        //add a shadow effect
        circle.setEffect(new InnerShadow(7, color.darker().darker()));
        //change a cursor when it is over circle
        circle.setCursor(Cursor.HAND);
        //add a mouse listeners
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                circle.toFront();
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    main.showOnConsole("Created new orangeCircle");
                    final Circle c = createOrangeCircle(false);
                    c.setTranslateX(300);
                    c.setTranslateY(150);
                    c.toFront();
                    main.circleGroup.getChildren().add(c);
                    main.circleList.add(c);
                } else if (me.getButton() == MouseButton.MIDDLE && !parent) {
                    main.showOnConsole("removed specified orangeCircle");
                    main.circleList.remove(circle);
                    main.circleGroup.getChildren().remove(circle);
                }
                 me.consume(); 
            }
        });
        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                 if (me.getButton() == MouseButton.PRIMARY && !parent) {
                    double dragX = me.getSceneX() - main.dragAnchor.getX();
                    double dragY = me.getSceneY() - main.dragAnchor.getY();
                    //calculate new position of the circle
                    double newXPosition = main.initX + dragX;
                    double newYPosition = main.initY + dragY;
                    //if new position do not exceeds borders of the rectangle, translate to this position
                    if ((newXPosition>=circle.getRadius()) && (newXPosition <= main.schematicWidth-circle.getRadius())) {
                        circle.setTranslateX(newXPosition);
                    }
                    if ((newYPosition>=circle.getRadius()) && (newYPosition <= main.schematicHeigth-circle.getRadius())){
                        circle.setTranslateY(newYPosition);
                    }
                    main.showOnConsole(name + " was dragged (x:" + dragX + ", y:" + dragY +")");
                }
                /*
                if (me.getButton() == MouseButton.MIDDLE && !parent) {
                    double dragX = me.getSceneX() - mouseEvents.dragAnchor.getX();
                    double dragY = me.getSceneY() - mouseEvents.dragAnchor.getY();
                    //calculate new position of the circle
                    double newXPosition = mouseEvents.initX + dragX;
                    double newYPosition = mouseEvents.initY + dragY;
                     LineTo li = new LineTo(newXPosition, newYPosition);
                     //root.getChildren().add(li);
                }
                */
            }
        });
        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
            }
        });
        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
            }
        });
        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                main.initX = circle.getTranslateX();
                main.initY = circle.getTranslateY();
                main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                main.showOnConsole("Mouse pressed above " + name);
            }
        });
        circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse released above " + name);
            }
        });
        return circle;
    }
}
