package LogicSimulator;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

@Deprecated
public class SexyRectangleBuilder {
    
    final Main main;
    @Deprecated
    public SexyRectangleBuilder (final Main main) {
        this.main = main;
    }
    @Deprecated
     public Rectangle createRectangle(final Image i) {
        final Rectangle rectangle = new Rectangle(100, 100);
        rectangle.setLayoutX(100);
        rectangle.setLayoutY(10);
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));
        
        final String name = "a rectangul"; 

        rectangle.setCursor(Cursor.HAND);
        //add a mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    main.showOnConsole("Created new rectangle");
                    final Rectangle rectangle = createRectangle(i);
                    rectangle.setTranslateX(300);
                    rectangle.setTranslateY(50);
                    rectangle.toFront();
                    main.circleGroup.getChildren().add(rectangle);
                    //main.circleList.add(rectangle);
                    
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    
                    main.showOnConsole("Removed specified Rectangle");
                   //main.circleList.remove(rectangle);
                    main.circleGroup.getChildren().remove(rectangle);
                    
                }
                 me.consume(); 
            }
        });
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                 if (me.getButton() == MouseButton.PRIMARY) {
                     
                    double dragX = me.getSceneX() - main.dragAnchor.getX();
                    double dragY = me.getSceneY() - main.dragAnchor.getY();
                    //calculate new position of the circle
                    double newXPosition = main.initX + dragX;
                    double newYPosition = main.initY + dragY;
                    //if new position do not exceeds borders of the rectangle, translate to this position
                    //if ((newXPosition>=circle.getRadius()) && (newXPosition<=main.schematicWidth-circle.getRadius())) {
                        rectangle.setTranslateX(newXPosition);
                    //}
                    //if ((newYPosition>=circle.getRadius()) && (newYPosition<=main.schematicHeigth-circle.getRadius())){
                        rectangle.setTranslateY(newYPosition);
                    //}
                    main.showOnConsole(name + " was dragged (x:" + dragX + ", y:" + dragY +")");
                    
                } else if (me.getButton() == MouseButton.SECONDARY) {
                }
            }
        });
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
            }
        });
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                rectangle.toFront();
                 //when mouse is pressed, store initial position
                main.initX = rectangle.getTranslateX();
                main.initY = rectangle.getTranslateY();
                main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                main.showOnConsole("Mouse pressed above " + name);
            }
        });
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse released above " + name);
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
