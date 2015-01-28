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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author PEKKA
 */
public class MenuBarBuilder {

    final Main main;

    private Timeline animation;

    public MenuBarBuilder(final Main main) {
        this.main = main;
    }

    public MenuBar buildMenuBarWithMenus() {
        final MenuBar menuBar = new MenuBar();

        // Prepare left-most 'File' drop-down menu
        final javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("File");
        final MenuItem fileNew = new MenuItem("New");/*       null all the lists!!!       */

        fileNew.setOnAction((ActionEvent event) -> {
            main.schematicGroup.getChildren().remove(main.gateGroup);
            main.gateGroup = new Group();
            main.schematicGroup.getChildren().add(main.gateGroup);
            /* reset all lists*/
            main.gateObjects = new ArrayList();
            main.lines = new ArrayList();
            main.connectionLineObjects = new ArrayList();
            main.logicLines = new ArrayList();
            main.circleList = new LinkedList();

            event.consume();
        });

        final MenuItem fileOpen = new MenuItem("Open");
        fileOpen.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Schematic");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("schematic", "*.schematic"));
            File file = fileChooser.showOpenDialog(main.primaryStage);
            if (file != null) {
                main.showOnConsole(file.toString());
                FileInputStream fileIn = null;
                try {
                    fileIn = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    //in.readObject();
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    try {
                        fileIn.close();
                        fileIn = null;
                        System.gc();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            event.consume();
        });

        final MenuItem fileSave = new MenuItem("Save");

        final MenuItem fileSaveAs = new MenuItem("Save As");
        fileSaveAs.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Schematic");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("schematic", "*.schematic"));
            File file = fileChooser.showSaveDialog(main.primaryStage);
            if (file != null) {
                FileOutputStream fileOut = null;
                try {
                    fileOut = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    //out.writeObject();
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    try {
                        fileOut.flush();
                        fileOut.close();
                        fileOut = null;
                        System.gc();
                        main.showOnConsole("something Saved successfully");

                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            event.consume();
        });

        final MenuItem fileExit = new MenuItem("Exit");         // quit
        fileExit.setOnAction((ActionEvent event) -> {
            System.out.println("Exiting this shi.. witty app ");
            Platform.exit();
            event.consume();
        });
        fileExit.setGraphic(new ImageView(Textures.exitIcon));

        fileMenu.getItems().addAll(fileNew, fileOpen, fileSave, fileSaveAs, new SeparatorMenuItem(), fileExit);

        // Prepare 'Extras' drop-down menu
        final javafx.scene.control.Menu extrasMenu = new javafx.scene.control.Menu("Extras");
        extrasMenu.getItems().add(new MenuItem("001"));
        extrasMenu.getItems().add(new MenuItem("002"));
        extrasMenu.getItems().add(new MenuItem("003"));

        // Prepare 'Help' drop-down menu
        final javafx.scene.control.Menu helpMenu = new javafx.scene.control.Menu("Help");

        RadioMenuItem infoPopupMenuItem = new RadioMenuItem("Info Popup");
        infoPopupMenuItem.setSelected(true);
        infoPopupMenuItem.setOnAction((ActionEvent event) -> {
            if (!InfoPopup.getEnabled()) {
                InfoPopup.setEnabled(true);
            } else if (InfoPopup.getEnabled()) {
                InfoPopup.setEnabled(false);
            }
            event.consume();
        });

        final MenuItem searchMenuItem = new MenuItem("Search");
        searchMenuItem.setDisable(true);

        final MenuItem onlineManualMenuItem = new MenuItem("Online Manual");
        onlineManualMenuItem.setVisible(false);

        final MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction((ActionEvent event) -> {
            main.showOnConsole("About Menu Item was clicked");

            final Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.setResizable(false);
            dialogStage.setTitle("About");
            Label aboutLabel = new Label("Tis tha rumored about window that yo been lookin´ fo \nleft click for moving gates and adding lines\nmiddle click for removal\nright click for toggling switches and editing labels");
            aboutLabel.setAlignment(Pos.BASELINE_CENTER);
            Button okButt = new Button("Ok");
            okButt.setCancelButton(true);
            okButt.setDefaultButton(true);
            okButt.setOnAction((ActionEvent arg0) -> {
                dialogStage.close();
            });
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.BASELINE_CENTER);
            hBox.setSpacing(40.0);
            hBox.getChildren().addAll(okButt);
            VBox vBox = new VBox();
            vBox.setSpacing(40.0);

            vBox.getChildren().addAll(aboutLabel, hBox);

            dialogStage.setScene(new Scene(vBox));
            Group gg = new Group();
            init(gg, dialogStage);
            vBox.getChildren().add(gg);
            dialogStage.show();
            animation.play();

            event.consume();
        });
        aboutMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F1, KeyCombination.SHORTCUT_ANY));

        helpMenu.getItems().addAll(infoPopupMenuItem, new SeparatorMenuItem(), searchMenuItem, onlineManualMenuItem, new SeparatorMenuItem(), aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, extrasMenu, helpMenu);
        // bind width of menu bar to width of associated stage
        menuBar.prefWidthProperty().bind(main.primaryStage.widthProperty());
        return menuBar;
    }

    private void init(Group root, Stage primaryStage) {
        root.setDepthTest(DepthTest.ENABLE);
        primaryStage.getScene().setCamera(new PerspectiveCamera());

        root.getTransforms().addAll(
                new Translate(0, 0),
                new Rotate(180, Rotate.X_AXIS)
        );
        root.getChildren().add(create3dContent());
    }

    public Node create3dContent() {
        Cube c = new Cube(33, Color.RED, 1);
        c.rx.setAngle(45);
        c.ry.setAngle(45);
        Cube c2 = new Cube(33, Color.GREEN, 1);
        c2.setTranslateX(100);
        c2.rx.setAngle(45);
        c2.ry.setAngle(45);
        //Cube c3 = new Cube(50,Color.ORANGE,1);
        //c3.setTranslateX(-100);
        //c3.rx.setAngle(45);
        //c3.ry.setAngle(45);

        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(c.ry.angleProperty(), 0d),
                        new KeyValue(c2.rx.angleProperty(), 0d)//,
                //new KeyValue(c3.rz.angleProperty(), 0d)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(c.ry.angleProperty(), 360d),
                        new KeyValue(c2.rx.angleProperty(), 360d)//,
                //new KeyValue(c3.rz.angleProperty(), 360d)
                ));
        animation.setCycleCount(Animation.INDEFINITE);

        return new Group(c, c2/*,c3*/);
    }

    public void play() {
        //animation.play();
    }

    public void stop() {
        //animation.pause();
    }

    public class Cube extends Group {

        final Rotate rx = new Rotate(0, Rotate.X_AXIS);
        final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        final Rotate rz = new Rotate(0, Rotate.Z_AXIS);

        public Cube(double size, Color color, double shade) {

            getTransforms().addAll(rz, ry, rx);
            getChildren().addAll(
                    buildrectangle2(-0.5, -0.5, 0.5, size, color, shade, 0.5, null, 0),
                    buildrectangle2(-0.5, 0, -11, size, color, shade, 0.4, Rotate.X_AXIS, 90),
                    buildrectangle2(-1, -0.5, -11, size, color, shade, 0.3, Rotate.Y_AXIS, 90),
                    buildrectangle2(0, -0.5, -11, size, color, shade, 0.2, Rotate.Y_AXIS, 90),
                    buildrectangle2(-0.5, -1.0, -11, size, color, shade, 0.1, Rotate.X_AXIS, 90),
                    buildrectangle2(-0.5, -0.5, -0.5, size, color, shade, 0, null, 0)
            );
        }
    }

    public Rectangle buildrectangle2(double x, double y, double z, double size, Color color, double shade, double shadeMult, Point3D point3D, int deg) {
        Rectangle rectangle = new Rectangle(size, size, color.deriveColor(0.0, 1.0, (1 - shadeMult * shade), 1.0)); // back face
        if (x != -11) {
            rectangle.setTranslateX(x * size);
        }
        if (y != -11) {
            rectangle.setTranslateY(y * size);
        }
        if (z != -11) {
            rectangle.setTranslateZ(z * size);
        }
        if (point3D != null) {
            rectangle.setRotationAxis(point3D);
        }
        if (deg != 0) {
            rectangle.setRotate(deg);
        }
        return rectangle;
    }

}
