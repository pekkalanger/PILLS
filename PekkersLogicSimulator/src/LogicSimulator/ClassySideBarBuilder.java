package LogicSimulator;

import LogicSimulator.GateObjects.AndObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ClassySideBarBuilder {
    
    final Main main;
    
    public double initX;
    public double initY;
    public Point2D dragAnchor;
    
    public ClassySideBarBuilder (final Main main) {
        this.main = main;
    }
    
    public VBox buildSideBarWithButtons(){
        
        VBox sideBar = new VBox();
        sideBar.setSpacing(2f);
        final GateBuilder gateBuilder = new GateBuilder();
        final Button butt1 = new Button("add and gayt");
        butt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new and gate");
                /*
                final Rectangle andGate = GateBuilder.createAndGate();
                andGate.setTranslateX(50);
                andGate.setTranslateY(50);
                andGate.toFront();
                //main.schematicGroup.getChildren().add(rectangle);
                main.circleGroup.getChildren().add(andGate);
                */
                
                AndObject andObject = new AndObject();
                
                event.consume();
            }
         });
        
        final Button butt2 = new Button("add or gayt");
        butt2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new Or gate");
                Group gg = gateBuilder.createOrGate();
                main.circleGroup.getChildren().add(gg);
        
                event.consume();
            }
         });
                
        final Button butt3 = new Button("add not gayt");
        butt3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new not gate");
                Group gg = gateBuilder.createNotGate();
                main.circleGroup.getChildren().add(gg);
        
                event.consume();
            }
         });
              
        /*      Create Button4                  */
        final Button butt4 = new Button("add blu circul");
        butt4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new blueCircle");
                SexyCircleBuilder sexyCircleBuilder = new SexyCircleBuilder(main);
                final Circle c = sexyCircleBuilder.createBlueCircle(false);
                c.setTranslateX(300);
                c.setTranslateY(50);
                c.toFront();
                main.circleGroup.getChildren().add(c);
                main.circleList.add(c);
                event.consume();
            }
        });
        /*      Create Button5                  */
        final Button butt5 = new Button("add orank circul");
        butt5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new orangeCircle");
                SexyCircleBuilder sexyCircleBuilder = new SexyCircleBuilder(main);
                final Circle c = sexyCircleBuilder.createOrangeCircle(false);
                c.setTranslateX(300);
                c.setTranslateY(150);
                c.toFront();
                main.circleGroup.getChildren().add(c);
                main.circleList.add(c);
                event.consume();
            }
         });
                /*      Create Button5                  */
        final Button butt6 = new Button("add skvaer");
        butt6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new skaver");
                ClassyRectangleBuilder classyGateBuilderAlpha = new ClassyRectangleBuilder(main);
                final Rectangle rectangle = classyGateBuilderAlpha.createRectangle(Textures.texture0);
                rectangle.setTranslateX(400);
                rectangle.setTranslateY(300);
                rectangle.toFront();
                //main.schematicGroup.getChildren().add(rectangle);
                main.circleGroup.getChildren().add(rectangle);
                event.consume();
            }
         });
        
        final Button butt7 = new Button("add Label");
        butt7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new Label");
                
                final Label label = new Label("Label");
                label.setTranslateX(0);
                label.setTranslateY(0);
                label.toFront();
                //label.setFont(Font.font(Font.getFamilies().get(22)));
                
                label.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                         //when mouse is pressed, store initial position
                        initX = label.getTranslateX();
                        initY = label.getTranslateY();
                        dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                        //showOnConsole("Mouse pressed above " + name);
                    }
                });
                
                label.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                         if (me.getButton() == MouseButton.PRIMARY) {
                            double dragX = me.getSceneX() - dragAnchor.getX();
                            double dragY = me.getSceneY() - dragAnchor.getY();
                            double newXPosition = initX + dragX;
                            double newYPosition = initY + dragY;
                            label.setTranslateX(newXPosition);
                            label.setTranslateY(newYPosition);
                            me.consume();
                        }
                    }
                });
                
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                        label.toFront();
                        if (me.getButton() == MouseButton.PRIMARY) {
                            
                        } else if (me.getButton() == MouseButton.SECONDARY) {//.PRIMARY) {
                            
                            main.showOnConsole("Clicked on" + label.getText() + ", " + me.getClickCount() + "times");
                            
                            final HBox labelPromptBox = new HBox();
                            labelPromptBox.setTranslateX(label.getTranslateX());
                            labelPromptBox.setTranslateY(label.getTranslateY());
                            main.circleGroup.getChildren().add(labelPromptBox);
                            
                            final TextField textField = new TextField();
                            textField.setPromptText(label.getText());
                            textField.setText(label.getText());
                            textField.setPrefColumnCount(10);
                            labelPromptBox.getChildren().add(textField);
                            
                            Button okButt = new Button("Ok");
                            okButt.setCancelButton(true);
                            okButt.setDefaultButton(true);
                            okButt.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent arg0) {
                                    label.setText(textField.getText());
                                    main.circleGroup.getChildren().remove(labelPromptBox);
                                    main.showOnConsole("textfield closed, Label text updated");
                                }
                            });
                            labelPromptBox.getChildren().add(okButt);
                            
                            me.consume();
                            
                        } else if (me.getButton() == MouseButton.MIDDLE) {
                            main.showOnConsole("Removed specified Label");
                            //main.circleList.remove(gg);
                            main.circleGroup.getChildren().remove(label);
                            me.consume();
                        }
                    }
                });
                main.circleGroup.getChildren().add(label);
                event.consume();
            }
         });
        
        
        /*      set keycodes to buttons                  */
        
        main.rootGroup.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if(ke.getCode()== KeyCode.DIGIT1){
                    butt1.fire();
                } else if(ke.getCode()== KeyCode.DIGIT2){
                    butt2.fire();
                } else if(ke.getCode()== KeyCode.DIGIT3){
                    butt3.fire();
                } else if(ke.getCode()== KeyCode.DIGIT4){
                    butt4.fire();
                } else if(ke.getCode()== KeyCode.DIGIT5){
                    butt5.fire();
                } else if(ke.getCode()== KeyCode.DIGIT6){
                    butt6.fire();
                } else {
                    return;
                }
                
                ke.consume();
            }
        });
        
        
        
        sideBar.getChildren().addAll(butt1, butt2, butt3, butt4, butt5, butt6, butt7);
        
        return sideBar;
    }
}
