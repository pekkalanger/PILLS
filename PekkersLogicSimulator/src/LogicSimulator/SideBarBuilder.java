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

import LogicSimulator.Objects.Gates.AndObject;
import LogicSimulator.Objects.Gates.BridgeObject;
import LogicSimulator.Objects.Gates.LedObject;
import LogicSimulator.Objects.Gates.NandObject;
import LogicSimulator.Objects.Gates.NorObject;
import LogicSimulator.Objects.Gates.NotObject;
import LogicSimulator.Objects.Gates.OrObject;
import LogicSimulator.Objects.Gates.SwitchObject;
import LogicSimulator.Objects.Gates.XnorObject;
import LogicSimulator.Objects.Gates.XorObject;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SideBarBuilder {

    final Main main;

    public double initX;
    public double initY;
    public Point2D dragAnchor;

    public SideBarBuilder(final Main main) {
        this.main = main;
    }

    public VBox buildSideBarWithButtons() {
        VBox sideBar = new VBox();
        sideBar.setSpacing(2f);

        /*      Create Button1                  */
        final Button butt1 = new Button();//"add not gate");
        butt1.setGraphic(new ImageView(Textures.notGate));
        butt1.setTooltip(new Tooltip("Not Gate"));
        InfoPopup.setinfoPopup(butt1, Textures.notGate);
        butt1.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new not gate");
            NotObject notObject = new NotObject();
            main.gateObjects.add(notObject);
            event.consume();
        });
        /*      Create Button2                  */
        final Button butt2 = new Button();//"add or gate");
        butt2.setGraphic(new ImageView(Textures.orGate));
        butt2.setTooltip(new Tooltip("Or Gate"));
        InfoPopup.setinfoPopup(butt2, Textures.orTruth);
        butt2.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Or gate");
            OrObject orObject = new OrObject();
            main.gateObjects.add(orObject);
            event.consume();
        });
        /*      Create Button3                  */
        final Button butt3 = new Button();//"add and gate");
        butt3.setGraphic(new ImageView(Textures.andGate));
        butt3.setTooltip(new Tooltip("And Gate"));
        InfoPopup.setinfoPopup(butt3, Textures.andTruth);
        butt3.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new and gate");
            AndObject andObject = new AndObject();
            main.gateObjects.add(andObject);
            event.consume();
        });
        /*      Create Button4                  */
        final Button butt4 = new Button();//"add nand gate");
        butt4.setGraphic(new ImageView(Textures.nandGate));
        butt4.setTooltip(new Tooltip("Nand Gate"));
        InfoPopup.setinfoPopup(butt4, Textures.nandTruth);
        butt4.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new nand gate");
            NandObject nandObject = new NandObject();
            main.gateObjects.add(nandObject);
            event.consume();
        });
        /*      Create Button5                  */
        final Button butt5 = new Button();//"add xnor gate");
        butt5.setGraphic(new ImageView(Textures.norGate));
        butt5.setTooltip(new Tooltip("Nor Gate"));
        InfoPopup.setinfoPopup(butt5, Textures.norTruth);
        butt5.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Nor gate");
            NorObject norObject = new NorObject();
            main.gateObjects.add(norObject);
            event.consume();
        });
        /*      Create Button6                  */
        final Button butt6 = new Button();//"add or gate");
        butt6.setGraphic(new ImageView(Textures.xorGate));
        butt6.setTooltip(new Tooltip("Xor Gate"));
        InfoPopup.setinfoPopup(butt6, Textures.xorTruth);
        butt6.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Xor gate");
            XorObject xorObject = new XorObject();
            main.gateObjects.add(xorObject);
            event.consume();
        });
        /*      Create Button7                  */
        final Button butt7 = new Button();//"add xnor gate");
        butt7.setGraphic(new ImageView(Textures.xnorGate));
        butt7.setTooltip(new Tooltip("Xnor Gate"));
        InfoPopup.setinfoPopup(butt7, Textures.xnorTruth);
        butt7.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Xnor gate");
            XnorObject xnorObject = new XnorObject();
            main.gateObjects.add(xnorObject);
            event.consume();
        });
        /*      Create Button7                  */
        final Button butt8 = new Button();//"add xnor gate");
        butt8.setGraphic(new ImageView(Textures.inputPin));
        butt8.setTooltip(new Tooltip("Bridge"));
        butt8.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Bridge");
            BridgeObject bridgeObject = new BridgeObject();
            main.gateObjects.add(bridgeObject);
            event.consume();
        });
        /*      Create Button20                  */
        final Button butt20 = new Button();//"add Switch");
        butt20.setGraphic(new ImageView(Textures.switchOn));
        butt20.setTooltip(new Tooltip("Switch"));
        butt20.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Switch");
            SwitchObject switchObject = new SwitchObject();
            main.gateObjects.add(switchObject);

            event.consume();
        });
        /*      Create Button21                  */
        final Button butt21 = new Button();//"add LED");
        butt21.setGraphic(new ImageView(Textures.ledOn));
        butt21.setTooltip(new Tooltip("LED"));
        butt21.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new LED");
            LedObject ledObject = new LedObject();
            main.gateObjects.add(ledObject);
            event.consume();
        });

        /*      Create Button79                  */
        final Button butt79 = new Button();//"square");
        butt79.setGraphic(new ImageView(Textures.pinOver));
        butt79.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new skaver");
            SexyRectangleBuilder classyGateBuilderAlpha = new SexyRectangleBuilder(main);
            final Rectangle rectangle = classyGateBuilderAlpha.createRectangle(Textures.texture);
            rectangle.setTranslateX(400);
            rectangle.setTranslateY(300);
            rectangle.toFront();
            //main.schematicGroup.getChildren().add(schematicRectangle);
            main.schematicGroup.getChildren().add(rectangle);
            event.consume();
        });

        /*      Create Button80                  */
        final Button butt80 = new Button("blu c");
        butt80.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Blue Circle");
            SexyCircleBuilder sexyCircleBuilder = new SexyCircleBuilder(main);
            final Circle c = sexyCircleBuilder.createBlueCircle(false);
            c.setTranslateX(300);
            c.setTranslateY(50);
            c.toFront();
            main.schematicGroup.getChildren().add(c);
            main.circleList.add(c);
            event.consume();
        });
        /*      Create Button81                  */
        final Button butt81 = new Button("ora c");
        butt81.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Orange Circle");
            SexyCircleBuilder sexyCircleBuilder = new SexyCircleBuilder(main);
            final Circle c = sexyCircleBuilder.createOrangeCircle(false);
            c.setTranslateX(300);
            c.setTranslateY(150);
            c.toFront();
            main.schematicGroup.getChildren().add(c);
            main.circleList.add(c);
            event.consume();
        });

        /*      Create Button99                  */
        final Button butt99 = new Button("Label");
        butt99.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Label");
            main.schematicGroup.getChildren().add(createLabel());
            event.consume();
        });

        /*      set keycodes to buttons                  */
        main.rootGroup.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.DIGIT1) {
                butt1.fire();
            } else if (ke.getCode() == KeyCode.DIGIT2) {
                butt2.fire();
            } else if (ke.getCode() == KeyCode.DIGIT3) {
                butt3.fire();
            } else if (ke.getCode() == KeyCode.DIGIT4) {
                butt4.fire();
            } else if (ke.getCode() == KeyCode.DIGIT5) {
                butt5.fire();
            } else if (ke.getCode() == KeyCode.DIGIT6) {
                butt6.fire();
            } else if (ke.getCode() == KeyCode.DIGIT7) {
                butt7.fire();
            } else {
                return;
            }
            ke.consume();
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
        sideBar.getChildren().addAll(butt1, butt2, butt3, butt4, butt5, butt6, butt7, butt8, butt20, butt21, spacer1, butt99, spacer2, butt79, butt80, butt81);

        return sideBar;
    }

    public Label createLabel() {
        final Label label = new Label("Label");
        label.setTranslateX(0);
        label.setTranslateY(0);
        label.toFront();
        label.setOnMousePressed((MouseEvent me) -> {
            //when mouse is pressed, store initial position
            initX = label.getTranslateX();
            initY = label.getTranslateY();
            dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
        });

        label.setOnMouseDragged((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                double dragX = me.getSceneX() - dragAnchor.getX();
                double dragY = me.getSceneY() - dragAnchor.getY();
                double newXPosition = initX + dragX;
                double newYPosition = initY + dragY;
                label.setTranslateX(newXPosition);
                label.setTranslateY(newYPosition);
                me.consume();
            }
        });

        label.setOnMouseClicked((MouseEvent me) -> {
            label.toFront();
            if (me.getButton() == MouseButton.PRIMARY) {
            } else if (me.getButton() == MouseButton.SECONDARY) {//.PRIMARY) {
                //main.showOnConsole("Clicked on" + label.getText() + ", " + me.getClickCount() + "times");
                final HBox labelPromptBox = new HBox();
                labelPromptBox.setTranslateX(label.getTranslateX());
                labelPromptBox.setTranslateY(label.getTranslateY());
                main.schematicGroup.getChildren().add(labelPromptBox);

                final TextField textField = new TextField();
                textField.setPromptText(label.getText());
                textField.setText(label.getText());
                textField.setPrefColumnCount(10);
                labelPromptBox.getChildren().add(textField);

                Button okButt = new Button("Ok");
                okButt.setCancelButton(true);
                okButt.setDefaultButton(true);
                okButt.setOnAction((ActionEvent arg0) -> {
                    label.setText(textField.getText());
                    main.schematicGroup.getChildren().remove(labelPromptBox);
                });
                labelPromptBox.getChildren().add(okButt);
                me.consume();
            } else if (me.getButton() == MouseButton.MIDDLE) {
                main.schematicGroup.getChildren().remove(label);
                me.consume();
            }
        });
        return label;
    }

}
