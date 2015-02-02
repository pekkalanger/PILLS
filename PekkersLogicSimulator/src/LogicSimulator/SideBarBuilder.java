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

public class SideBarBuilder {

    private final Main main;

    private double initX;
    private double initY;
    private Point2D dragAnchor;

    public SideBarBuilder(final Main main) {
        this.main = main;
    }

    public VBox buildSideBarWithButtons() {
        VBox sideBar = new VBox();
        //sideBar.setSpacing(2f);
        //sideBar.setPrefWidth(50);
        //sideBar.maxWidth(50);

        /*      Create Button1                      */
        final Button butt1 = createGateButton(NotObject.class, "Not Gate", "notgate", "notgate");

        /*      Create Button2                      */
        final Button butt2 = createGateButton(OrObject.class, "Or Gate", "orgate", "ortruth");

        /*      Create Button3                      */
        final Button butt3 = createGateButton(AndObject.class, "And Gate", "andgate", "andtruth");

        /*      Create Button4                      */
        final Button butt4 = createGateButton(NandObject.class, "Nand Gate", "nandgate", "nandtruth");

        /*      Create Button5                      */
        final Button butt5 = createGateButton(NorObject.class, "Nor Gate", "norgate", "nortruth");

        /*      Create Button6                      */
        final Button butt6 = createGateButton(XorObject.class, "Xor Gate", "xorgate", "xortruth");

        /*      Create Button7                      */
        final Button butt7 = createGateButton(XnorObject.class, "Xnor Gate", "xnorgate", "xnortruth");

        /*      Create Button8                      */
        final Button butt8 = createGateButton(BridgeObject.class, "Bridge", "inputpin", "inputpin");

        /*      Create Button9                      */
        final Button butt9 = createGateButton(FullAdderObject.class, "Full Adder", "noimage", "noimage");
        butt9.setDisable(true);

        /*      Create Button20                     */
        final Button butt20 = createGateButton(SwitchObject.class, "Switch", "switchon", "switchon");

        /*      Create Button21                     */
        final Button butt21 = createGateButton(LedObject.class, "LED", "ledon", "ledon");

        /*      Create Button90                     */
        final Button butt90 = new Button("Label");
        butt90.setTooltip(new Tooltip("Label"));
        butt90.setOnAction((ActionEvent event) -> {
            main.showOnConsole("Created new Label");
            main.gateGroup.getChildren().add(createLabel());
            event.consume();
        });

        /*      set keycodes to buttons             */
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

        /*      Create Spacer1                      */
        Region spacer1 = new Region();
        VBox.setVgrow(spacer1, Priority.ALWAYS);
        spacer1.setMaxHeight(20);

        /*      add it all to the sideBar group     */
        sideBar.getChildren().addAll(butt1, butt2, butt3, butt4, butt5, butt6, butt7, butt8, butt9, butt20, butt21, spacer1, butt90);

        return sideBar;
    }

    public Button createGateButton(Class<? extends GateObject> g, String name, String gateName, String gateTruth) {
        /*      Create Button                       */
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
                if (newXPosition > 0 && newXPosition < (Globals.main.schematicWidth - 32)) {
                    label.setTranslateX(newXPosition - (newXPosition % SchematicRectangle.gridWidth));
                }
                if (newYPosition > 0 && newYPosition < (Globals.main.schematicHeigth - 32)) {
                    label.setTranslateY((newYPosition - newYPosition % SchematicRectangle.gridHeight));
                }
                me.consume();
            }
        });
        label.setOnMouseClicked((MouseEvent me) -> {
            label.toFront();
            if (me.getButton() == MouseButton.PRIMARY) {
                // primary click wont work on me >:)
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
