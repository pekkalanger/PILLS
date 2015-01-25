package LogicSimulator.GateObjects;

import Logic.Gate;
import LogicSimulator.Globals;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GateObject {
        
    double x;
    double y;
    String name;
    Group group;
    Gate gate;
    Rectangle rectangle;
    protected double initX;
    protected double initY;
    protected Point2D dragAnchor;
    
    public void update(long deltaTime){
        if(gate != null) gate.update(deltaTime);
    }
    public Rectangle initRectangle(Image image){
        rectangle = new Rectangle(32, 32);
        rectangle.setFill(new ImagePattern(image, 0, 0, 1, 1, true)); /* should create InputPin Gate (square with andGate gate boolean logic linked to pins)*/
        rectangle.setTranslateX(8);  // move 8 to the left because of inputpins on the left
        rectangle.setTranslateY(0);

        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                //Globals.main.showOnConsole("Mouse over " + name);
                me.consume();
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //Globals.main.showOnConsole("Mouse exited " + name);
            }
        });
        return rectangle;
    }
    
    public void initGroup(final Line lineA, final Line lineB, final Line lineQ){
           
        group.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                initX = group.getTranslateX();
                initY = group.getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //showOnConsole("Mouse pressed above " + name);
            }
        });
        group.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 if (me.getButton() == MouseButton.PRIMARY) {
                    double dragX = me.getSceneX() - dragAnchor.getX();
                    double dragY = me.getSceneY() - dragAnchor.getY();
                    double newXPosition = initX + dragX;
                    double newYPosition = initY + dragY;
                    group.setTranslateX(newXPosition);
                    group.setTranslateY(newYPosition);
                    me.consume();
                }
            }
        });
        group.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                group.toFront();
                if (me.getButton() == MouseButton.PRIMARY) {
                    Globals.main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                    me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    Globals.main.showOnConsole("Removed specified orangeCircle");
                    //Globals.main.circleList.remove(gg); // remove the gate from the list gate all the lines attached to it
                    Globals.main.circleGroup.getChildren().remove(group);
                    gate=null ;
                    //Globals.main.gateObjects.remove(gate);
                    System.out.println("gate should be null now");
                    Globals.main.showOnConsole("gate should be null now");
                    //for (int i = 0; i < 3; i++) {
                    if(Globals.main.circleGroup.getChildren().contains(lineA)) {
                       //Line l = Globals.main.circleGroup.getChildren().get(Globals.main.circleGroup.getChildren().indexOf(lineA));
                        Globals.main.circleGroup.getChildren().remove(lineA);

                    }
                    if(Globals.main.circleGroup.getChildren().contains(lineB)) {
                        Globals.main.circleGroup.getChildren().remove(lineB);
                    }
                    if(Globals.main.circleGroup.getChildren().contains(lineQ)) {
                        Globals.main.circleGroup.getChildren().remove(lineQ);
                    }
                    //}
                    
                    me.consume();
                }
                  
            }
        });
        group.setOpacity(0.8f);
        Globals.main.circleGroup.getChildren().add(group);
    }
    
}
