package LogicSimulator.GateObjects;

import LogicSimulator.GateObjects.GateLogic.Gate;
import LogicSimulator.Globals;
import javafx.beans.property.DoubleProperty;
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
    
    public void initGroup(final InputPinObject inputPinObjectA, final InputPinObject inputPinObjectB, final OutputPinObject outputPinObjectQ){

           
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
                    
                    if(inputPinObjectA != null){
                        inputPinObjectA.connectionLineObject.line.endXProperty().set(inputPinObjectA.x + group.getTranslateX());
                        inputPinObjectA.connectionLineObject.line.endYProperty().set(inputPinObjectA.y + group.getTranslateY());
                    } 
                    if(inputPinObjectB != null){
                        inputPinObjectB.connectionLineObject.line.endXProperty().set(inputPinObjectB.x + group.getTranslateX());
                        inputPinObjectB.connectionLineObject.line.endYProperty().set(inputPinObjectB.y + group.getTranslateY());
                    }
                    if(outputPinObjectQ != null){
                        outputPinObjectQ.connectionLineObject.line.endXProperty().set(outputPinObjectQ.x + group.getTranslateX());
                        outputPinObjectQ.connectionLineObject.line.endYProperty().set(outputPinObjectQ.y + group.getTranslateY());
                    }
                    
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
                    
                    if(null != inputPinObjectA){
                        if(Globals.main.circleGroup.getChildren().contains(inputPinObjectA.connectionLineObject.line)) {
                           //Line l = Globals.main.circleGroup.getChildren().get(Globals.main.circleGroup.getChildren().indexOf(lineA));
                            Globals.main.circleGroup.getChildren().remove(inputPinObjectA.connectionLineObject.line);
                        }
                        inputPinObjectA.connectionLineObject.logicLine = null;
                        inputPinObjectA.connectionLineObject = null;
                        inputPinObjectA.connectionLineObject2 = null;
                    }
                    if(null != inputPinObjectB){
                        if(Globals.main.circleGroup.getChildren().contains(inputPinObjectB.connectionLineObject.line)) {
                            Globals.main.circleGroup.getChildren().remove(inputPinObjectB.connectionLineObject.line);
                        }
                        inputPinObjectB.connectionLineObject.logicLine = null;
                        inputPinObjectB.connectionLineObject = null;
                        inputPinObjectB.connectionLineObject2 = null;
                    }
                    if(null != outputPinObjectQ){
                        if(Globals.main.circleGroup.getChildren().contains(outputPinObjectQ.connectionLineObject.line)) {
                            Globals.main.circleGroup.getChildren().remove(outputPinObjectQ.connectionLineObject.line);
                        }
                        outputPinObjectQ.connectionLineObject.logicLine = null;
                        outputPinObjectQ.connectionLineObject = null;
                        outputPinObjectQ.connectionLineObject2 = null;
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
