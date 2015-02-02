/*
 * Copyright (C) 2015 pekka
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
import LogicSimulator.Objects.Gates.FullAdderObject;
import LogicSimulator.Objects.Gates.GateObject;
import LogicSimulator.Objects.Gates.LedObject;
import LogicSimulator.Objects.Gates.NandObject;
import LogicSimulator.Objects.Gates.NorObject;
import LogicSimulator.Objects.Gates.NotObject;
import LogicSimulator.Objects.Gates.OrObject;
import LogicSimulator.Objects.Gates.SwitchObject;
import LogicSimulator.Objects.Gates.XnorObject;
import LogicSimulator.Objects.Gates.XorObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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

/**
 *
 * @author pekka
 */
public class OldSideBarBuilder {

    final Main main;

    public double initX;
    public double initY;
    public Point2D dragAnchor;

    public OldSideBarBuilder(final Main main) {
        this.main = main;
    }

    public VBox buildSideBarWithButtons() {
        VBox sideBar = new VBox();
        //sideBar.setSpacing(2f);
        //sideBar.setPrefWidth(50);
        //sideBar.maxWidth(50);

        /*      Create Button1                  */
        final Button butt1 = new Button();//"add not gate");
        butt1.setGraphic(new ImageView(Textures.getHmImage("notgate")));
        butt1.setTooltip(new Tooltip("Not Gate"));
        InfoPopup.setinfoPopup(butt1, Textures.getHmImage("notgate"));
        butt1.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new not gate");
            //CommandManager.commandManager.executeCommand(new AddGateCommand(new NotObject()));
            NotObject notObject = new NotObject();
            //ClipBoard.setGateObject(notObject);
            main.gateObjects.add(notObject);
            event.consume();
        });
        /*      Create Button2                  */

        final Button butt2 = new Button();//"add or gate");
        butt2.setGraphic(new ImageView(Textures.getHmImage("orgate")));
        butt2.setTooltip(new Tooltip("Or Gate"));
        InfoPopup.setinfoPopup(butt2, Textures.getHmImage("ortruth"));
        butt2.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Or gate");
            OrObject orObject = new OrObject();
            main.gateObjects.add(orObject);
            event.consume();
        });
        /*      Create Button3                  */

        final Button butt3 = new Button();//"add and gate");
        butt3.setGraphic(new ImageView(Textures.getHmImage("andgate")));
        butt3.setTooltip(new Tooltip("And Gate"));
        InfoPopup.setinfoPopup(butt3, Textures.getHmImage("andtruth"));
        butt3.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new and gate");
            AndObject andObject = new AndObject();
            main.gateObjects.add(andObject);
            event.consume();
        });
        /*      Create Button4                  */

        final Button butt4 = new Button();//"add nand gate");
        butt4.setGraphic(new ImageView(Textures.getHmImage("nandgate")));
        butt4.setTooltip(new Tooltip("Nand Gate"));
        InfoPopup.setinfoPopup(butt4, Textures.getHmImage("nandtruth"));
        butt4.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new nand gate");
            NandObject nandObject = new NandObject();
            main.gateObjects.add(nandObject);
            event.consume();
        });
        /*      Create Button5                  */

        final Button butt5 = new Button();//"add xnor gate");
        butt5.setGraphic(new ImageView(Textures.getHmImage("norgate")));
        butt5.setTooltip(new Tooltip("Nor Gate"));
        InfoPopup.setinfoPopup(butt5, Textures.getHmImage("nortruth"));
        butt5.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Nor gate");
            NorObject norObject = new NorObject();
            main.gateObjects.add(norObject);
            event.consume();
        });
        /*      Create Button6                  */

        final Button butt6 = new Button();
        butt6.setGraphic(new ImageView(Textures.getHmImage("xorgate")));
        butt6.setTooltip(new Tooltip("Xor Gate"));
        InfoPopup.setinfoPopup(butt6, Textures.getHmImage("xortruth"));
        butt6.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Xor gate");
            XorObject xorObject = new XorObject();
            main.gateObjects.add(xorObject);
            event.consume();
        });
        /*      Create Button7                  */

        final Button butt7 = new Button();//"add xnor gate");
        butt7.setGraphic(new ImageView(Textures.getHmImage("xnorgate")));
        butt7.setTooltip(new Tooltip("Xnor Gate"));
        InfoPopup.setinfoPopup(butt7, Textures.getHmImage("xnortruth"));
        butt7.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Xnor gate");
            XnorObject xnorObject = new XnorObject();
            main.gateObjects.add(xnorObject);
            event.consume();
        });
        /*      Create Button8                  */

        final Button butt8 = new Button();
        butt8.setGraphic(new ImageView(Textures.getHmImage("inputpin")));
        butt8.setTooltip(new Tooltip("Bridge"));
        InfoPopup.setinfoPopup(butt8, Textures.getHmImage("inputpin"));
        butt8.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Bridge");
            BridgeObject bridgeObject = new BridgeObject();
            main.gateObjects.add(bridgeObject);
            event.consume();
        });
        /*      Create Button9                  */

        final Button butt9 = new Button();//"add not gate");    /// FULLADDER
        butt9.setDisable(true);
        //butt9.setVisible(false);
        butt9.setGraphic(new ImageView(Textures.getHmImage("noimage")));
        butt9.setTooltip(new Tooltip("Full Adder"));
        InfoPopup.setinfoPopup(butt9, Textures.getHmImage("ortruth"));
        butt9.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Full Adder");
            FullAdderObject fullAdder1 = new FullAdderObject();
            main.gateObjects.add(fullAdder1);
            event.consume();
        });
        /*      Create Button20                  */

        final Button butt20 = new Button();//"add Switch");
        butt20.setGraphic(new ImageView(Textures.getHmImage("switchon")));
        butt20.setTooltip(new Tooltip("Switch"));
        InfoPopup.setinfoPopup(butt20, Textures.getHmImage("switchon"));
        butt20.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Switch");
            SwitchObject switchObject = new SwitchObject();
            main.gateObjects.add(switchObject);

            event.consume();
        });
        /*      Create Button21                  */

        final Button butt21 = new Button();//"add LED");
        butt21.setGraphic(new ImageView(Textures.getHmImage("ledon")));
        butt21.setTooltip(new Tooltip("LED"));
        InfoPopup.setinfoPopup(butt21, Textures.getHmImage("ledon"));
        butt21.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new LED");
            LedObject ledObject = new LedObject();
            main.gateObjects.add(ledObject);
            event.consume();
        });


        /*      Create Button99                  */
        final Button butt99 = new Button("Label");
        butt99.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Label");
            main.gateGroup.getChildren().add(createLabel());
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


        /*      add it all to the sideBar group */
        sideBar.getChildren().addAll(butt1, butt2, butt3, butt4, butt5, butt6, butt7, butt8, butt9, butt20, butt21, spacer1, butt99);

        return sideBar;
    }

    public Button createButton(Class<? extends GateObject> g, String name, String gateName, String gateTruth) {
        /*      Create Button                  */
        final Button button = new Button();
        button.setGraphic(new ImageView(Textures.getHmImage(gateName)));
        button.setTooltip(new Tooltip(name));
        InfoPopup.setinfoPopup(button, Textures.getHmImage(gateTruth));
        button.setOnAction((ActionEvent event) -> {
            try {
                main.showOnConsole("Created new " + name);
                main.gateObjects.add(g.newInstance());
                event.consume();
            } catch (InstantiationException ex) {
                Logger.getLogger(SideBarBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SideBarBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return button;
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
                if (newXPosition > 0 && newXPosition < (Main.main.schematicWidth - 32)) {
                    label.setTranslateX(newXPosition - (newXPosition % SchematicRectangle.gridWidth));
                }
                if (newYPosition > 0 && newYPosition < (Main.main.schematicHeigth - 32)) {
                    label.setTranslateY((newYPosition - newYPosition % SchematicRectangle.gridHeight));
                }
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
                main.gateGroup.getChildren().add(labelPromptBox);

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
                    main.gateGroup.getChildren().remove(labelPromptBox);
                });
                labelPromptBox.getChildren().add(okButt);
                me.consume();
            } else if (me.getButton() == MouseButton.MIDDLE) {
                main.gateGroup.getChildren().remove(label);
                me.consume();
            }
        });
        return label;
    }

    public class AddGateCommand implements Command {

        public AddGateCommand() {
        }

        public void redo() {
        }

        public void undo() {
        }

        @Override
        public void execute() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
