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
package LogicSimulator;

import LogicSimulator.GateObjects.AndObject;
import LogicSimulator.GateObjects.LedObject;
import LogicSimulator.GateObjects.NandObject;
import LogicSimulator.GateObjects.NotObject;
import LogicSimulator.GateObjects.OrObject;
import LogicSimulator.GateObjects.SwitchObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SideBarBuilder {
    
    final Main main;
    
    public double initX;
    public double initY;
    public Point2D dragAnchor;
    
    public SideBarBuilder (final Main main) {
        this.main = main;
    }
    
    public VBox buildSideBarWithButtons(){
        
        VBox sideBar = new VBox();
        sideBar.setSpacing(2f);
        
        /*      Create Button1                  */
        final Button butt1 = new Button();//"add and gate");
        butt1.setGraphic(new ImageView(Textures.andGate));
        butt1.setTooltip(new Tooltip("And Gate"));
        butt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new and gate");
                AndObject andObject = new AndObject();
                main.gateObjects.add(andObject);
                event.consume();
            }
        });
        /*      Create Button2                  */
        final Button butt2 = new Button();//"add or gate");
        butt2.setGraphic(new ImageView(Textures.orGate));
        butt2.setTooltip(new Tooltip("Or Gate"));
        butt2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new Or gate");
                OrObject orObject = new OrObject();
                main.gateObjects.add(orObject);
                event.consume();
            }
        });
        /*      Create Button3                  */        
        final Button butt3 = new Button();//"add not gate");
        butt3.setGraphic(new ImageView(Textures.notGate));
        butt3.setTooltip(new Tooltip("Not Gate"));
        butt3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new not gate");
                NotObject notObject = new NotObject();
                main.gateObjects.add(notObject);
                event.consume();
            }
        });
        /*      Create Button4                  */
        final Button butt4 = new Button();//"add nand gate");
        butt4.setGraphic(new ImageView(Textures.nandGate));
        butt4.setTooltip(new Tooltip("Nand Gate"));
        butt4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new nand gate");
                NandObject nandObject = new NandObject();
                main.gateObjects.add(nandObject);
                event.consume();
            }
        });      
        /*      Create Button20                  */
        final Button butt20 = new Button();//"add Switch");
        butt20.setGraphic(new ImageView(Textures.switchOn));
        butt20.setTooltip(new Tooltip("Switch"));
        butt20.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new Switch");
                SwitchObject switchObject = new SwitchObject();
                main.gateObjects.add(switchObject);
                
                event.consume();
            }
        });
        /*      Create Button21                  */
        final Button butt21 = new Button();//"add LED");
        butt21.setGraphic(new ImageView(Textures.ledOn));
        butt21.setTooltip(new Tooltip("LED"));
        butt21.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new LED");
                LedObject ledObject = new LedObject();
                main.gateObjects.add(ledObject);
                event.consume();
            }
        });
        
        /*      Create Button79                  */
        final Button butt79 = new Button();//"square");
        butt79.setGraphic(new ImageView(Textures.pinOver));
        butt79.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new skaver");
                SexyRectangleBuilder classyGateBuilderAlpha = new SexyRectangleBuilder(main);
                final Rectangle rectangle = classyGateBuilderAlpha.createRectangle(Textures.texture);
                rectangle.setTranslateX(400);
                rectangle.setTranslateY(300);
                rectangle.toFront();
                //main.schematicGroup.getChildren().add(rectangle);
                main.circleGroup.getChildren().add(rectangle);
                event.consume();
            }
        });
        
        /*      Create Button80                  */
        final Button butt80 = new Button("blu c");
        butt80.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new Blue Circle");
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
        /*      Create Button81                  */
        final Button butt81 = new Button("ora c");
        butt81.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new Orange Circle");
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
        
        /*      Create Button99                  */
        final Button butt99 = new Button("Label");
        butt99.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showOnConsole("Created new Label");
                main.circleGroup.getChildren().add(createLabel());
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
                    butt20.fire();
                } else if(ke.getCode()== KeyCode.DIGIT5){
                    butt21.fire();
                } else if(ke.getCode()== KeyCode.DIGIT6){
                    butt79.fire();
                } else {
                    return;
                }
                ke.consume();
            }
        });
        
        /*      Create Spacer1                  */
        Region spacer1 = new Region();
        VBox.setVgrow(spacer1, Priority.ALWAYS);     
        spacer1.setMaxHeight(20);
        
        /*      Create Spacer2                  */
        Region spacer2 = new Region();
        VBox.setVgrow(spacer2, Priority.ALWAYS);     
        spacer2.setMaxHeight(20);
        
        /*      add it all to the sideBar group */
        sideBar.getChildren().addAll(butt1, butt2, butt3, butt4, butt20, butt21, spacer1, butt99, spacer2, butt79, butt80, butt81);
        
        return sideBar;
    }
    
     public Label createLabel(){
        final Label label = new Label("Label");
        label.setTranslateX(0);
        label.setTranslateY(0);
        label.toFront();
        label.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                 //when mouse is pressed, store initial position
                initX = label.getTranslateX();
                initY = label.getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
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
                    //main.showOnConsole("Clicked on" + label.getText() + ", " + me.getClickCount() + "times");
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
                            //main.showOnConsole("textfield closed, Label text updated");
                        }
                    });
                    labelPromptBox.getChildren().add(okButt);
                    me.consume();
                } else if (me.getButton() == MouseButton.MIDDLE) {
                    main.circleGroup.getChildren().remove(label);
                    me.consume();
                }
            }
        });
        return label;
    }
    
    
}
