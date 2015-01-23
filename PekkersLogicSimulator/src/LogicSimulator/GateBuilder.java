package LogicSimulator;

import Logic.And;
import Logic.InputPin;
import Logic.Not;
import Logic.Or;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GateBuilder {
        
    final Main main;
    
    public GateBuilder (final Main main) {
        this.main = main;
    }
    public double initX;
    public double initY;
    public Point2D dragAnchor;

    public Group createAndGate() {
            /*      movable group   */
        
        PinBuilder pinBuilder = new PinBuilder(main);
        Image texture = new Image("file:res/andgate.png");
        final String name = "andgate";
        
        And and = new And();
        // this should be added to a and list which will be updated all the fucknig time
        // and also assigned the pins
        
        final Rectangle pinARectangle = pinBuilder.createInputPin(0, 2, and.getPinA(), "A");//new Rectangle(8, 8);
        final Rectangle pinBRectangle = pinBuilder.createInputPin(0, 22, and.getPinB(),  "B");
        final Rectangle pinQRectangle = pinBuilder.createOutputPin(40, 12, and.getPinQ(), "Q");
        
        final Rectangle andGateRectangle = new Rectangle(32, 32);
        andGateRectangle.setFill(new ImagePattern(texture, 0, 0, 1, 1, true)); /* should create a Gate (square with texture and boolean logic linked to pins)*/
        andGateRectangle.setTranslateX(8);
        andGateRectangle.setTranslateY(0);
        andGateRectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        andGateRectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
            }
        });
        
        
        final Group gg = new Group();
        gg.getChildren().addAll(pinARectangle, pinBRectangle, pinQRectangle, andGateRectangle);
        

        gg.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                initX = gg.getTranslateX();
                initY = gg.getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //showOnConsole("Mouse pressed above " + name);
            }
        });
        gg.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 if (me.getButton() == MouseButton.PRIMARY) {
                    double dragX = me.getSceneX() - dragAnchor.getX();
                    double dragY = me.getSceneY() - dragAnchor.getY();
                    double newXPosition = initX + dragX;
                    double newYPosition = initY + dragY;
                    gg.setTranslateX(newXPosition);
                    gg.setTranslateY(newYPosition);
                    me.consume();
                }
            }
        });
        gg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                gg.toFront();
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                    me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                 /*   main.showOnConsole("Created new blueCircle");
                    final Circle c = createBlueCircle(false);
                    c.setTranslateX(300);
                    c.setTranslateY(50);
                    c.toFront();
                    main.circleGroup.getChildren().add(c);
                    main.circleList.add(c);
                    */
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    main.showOnConsole("Removed specified orangeCircle");
                    //main.circleList.remove(gg); // remove the gate from the list and all the lines attached to it
                    main.circleGroup.getChildren().remove(gg);
                    me.consume();
                }
                  
            }
        });
        
        return gg;
    }
    
    
    public Group createOrGate() {
            /*      movable group   */
        
        PinBuilder pinBuilder = new PinBuilder(main);
        Image texture = new Image("file:res/orgate.png");
        final String name = "orgate";
        
        Or or = new Or();
        
        final Rectangle pinARectangle = pinBuilder.createInputPin(0, 2, or.getPinA(), "A");//new Rectangle(8, 8);
        final Rectangle pinBRectangle = pinBuilder.createInputPin(0, 22, or.getPinB(), "B");
        final Rectangle pinQRectangle = pinBuilder.createOutputPin(40, 12, or.getPinQ(), "Q");
        
        final Rectangle orGateRectangle = new Rectangle(32, 32);
        orGateRectangle.setFill(new ImagePattern(texture, 0, 0, 1, 1, true)); /* should create a Gate (square with texture and boolean logic linked to pins)*/
        orGateRectangle.setTranslateX(8);
        orGateRectangle.setTranslateY(0);
        
        orGateRectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        orGateRectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
            }
        });
        
        
        final Group gg = new Group();
        gg.getChildren().addAll(pinARectangle, pinBRectangle, pinQRectangle, orGateRectangle);
        

        gg.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                initX = gg.getTranslateX();
                initY = gg.getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //showOnConsole("Mouse pressed above " + name);
            }
        });
        gg.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 if (me.getButton() == MouseButton.PRIMARY) {
                    double dragX = me.getSceneX() - dragAnchor.getX();
                    double dragY = me.getSceneY() - dragAnchor.getY();
                    double newXPosition = initX + dragX;
                    double newYPosition = initY + dragY;
                    gg.setTranslateX(newXPosition);
                    gg.setTranslateY(newYPosition);
                    me.consume();
                }
            }
        });
        gg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                gg.toFront();
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                    me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                 /*   main.showOnConsole("Created new blueCircle");
                    final Circle c = createBlueCircle(false);
                    c.setTranslateX(300);
                    c.setTranslateY(50);
                    c.toFront();
                    main.circleGroup.getChildren().add(c);
                    main.circleList.add(c);
                    */
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    main.showOnConsole("Removed specified orangeCircle");
                    //main.circleList.remove(gg);
                    main.circleGroup.getChildren().remove(gg);
                    me.consume();
                }
                  
            }
        });
        
        
        return gg;
    }

    public Group createNotGate() {
            /*      movable group   */
        
        PinBuilder pinBuilder = new PinBuilder(main);
        Image texture = new Image("file:res/notgate.png");
        final String name = "notgate";
        
        Not not = new Not();
        
        final Rectangle pinARectangle = pinBuilder.createInputPin(0, 12, not.getPinA(), "A");//new Rectangle(8, 8);
        final Rectangle pinQRectangle = pinBuilder.createOutputPin(40, 12, not.getPinQ(), "Q");
        
        final Rectangle notGateRectangle = new Rectangle(32, 32);
        notGateRectangle.setFill(new ImagePattern(texture, 0, 0, 1, 1, true)); /* should create a Gate (square with texture and boolean logic linked to pins)*/
        notGateRectangle.setTranslateX(8);
        notGateRectangle.setTranslateY(0);
        notGateRectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
                me.consume();
            }
        });
        notGateRectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
            }
        });
        
        
        final Group gg = new Group();
        gg.getChildren().addAll(pinARectangle, pinQRectangle, notGateRectangle);
        

        gg.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                initX = gg.getTranslateX();
                initY = gg.getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                //showOnConsole("Mouse pressed above " + name);
            }
        });
        gg.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 if (me.getButton() == MouseButton.PRIMARY) {
                    double dragX = me.getSceneX() - dragAnchor.getX();
                    double dragY = me.getSceneY() - dragAnchor.getY();
                    double newXPosition = initX + dragX;
                    double newYPosition = initY + dragY;
                    gg.setTranslateX(newXPosition);
                    gg.setTranslateY(newYPosition);
                    me.consume();
                }
            }
        });
        gg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                gg.toFront();
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                    me.consume();
                } else if (me.getButton() == MouseButton.SECONDARY) {
                 /*   main.showOnConsole("Created new blueCircle");
                    final Circle c = createBlueCircle(false);
                    c.setTranslateX(300);
                    c.setTranslateY(50);
                    c.toFront();
                    main.circleGroup.getChildren().add(c);
                    main.circleList.add(c);
                    */
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    main.showOnConsole("Removed specified orangeCircle");
                    //main.circleList.remove(gg);
                    main.circleGroup.getChildren().remove(gg);
                    me.consume();
                }
                  
            }
        });
        
        return gg;
    }
    
      public Group createAndGate345345435() {
        Image texture = new Image("file:res/andgate.png");
        Group andGateGroup = new Group(); // will contain pins and the gate
        
        final Rectangle rectangle = new Rectangle(32, 32);
        rectangle.setLayoutX(10);
        rectangle.setLayoutY(10);
        rectangle.setFill(new ImagePattern(texture, 0, 0, 1, 1, true));
        
        final String name = "a rectangul"; 

        rectangle.setCursor(Cursor.HAND);
        //add a mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    main.showOnConsole("Removed specified Gate");
                    main.circleList.remove(rectangle);
                    main.circleGroup.getChildren().remove(rectangle);
                }
                 me.consume(); 
            }
        });
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
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
            @Override
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
        
        //andGateGroup.getChildren().addAll(andGate, pinA, pinB, pinQ);
        return andGateGroup;
    }
}

