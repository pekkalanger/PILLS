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

import LogicSimulator.Objects.Gates.GateLogic.LogicLine;
import LogicSimulator.Objects.ConnectionLineObject;
import LogicSimulator.Objects.Gates.GateObject;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static Main main;

    public int mainHeight = 768;
    public int mainWidth = mainHeight * 16 / 9;
    public int schematicWidth = mainWidth - 50;
    public int schematicHeigth = 500;
    int consoleWidth = 700;
    int consoleHeight = 200;

    public boolean rectangularSymbols = false;
    //variables for storing initial position before drag of circle
    public double initX;
    public double initY;
    public Point2D dragAnchor;

    /*      schematic objects       */ // will be nulled on New.
    public List<GateObject> gateObjects;
    public List<Line> lines;
    public List<LogicLine> logicLines;
    public List<ConnectionLineObject> connectionLineObjects;

    //create a console for logging mouse events
    final ListView<String> console = new ListView<>();

    /*     PrimaryStage and Timeline for UpdateLoop */
    public Stage primaryStage;
    Timeline timeline;

    /*          Declare groups      */
    public VBox rootGroup;          // contains menuBar and rootHBox
    public MenuBar menuBar;         // an most excelent menubar
    public HBox rootHBox;           // contains sideBar and rootVBox
    public VBox rootVBox;           // contains rectangle and console
    public VBox sideBar;            // contains sidebar items
    public Group schematicGroup;    // this is where scematicRectangle and Gates will be grouped
    public Group gateGroup;         // this one is for all gates

    public SchematicRectangle schematicRectangle;

    //create a observableArrayList of logged events that will be listed in console
    final ObservableList<String> consoleObservableList = observableArrayList();

    private void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("P.I.L.L.S pekkers incredibly logical logic simulator");
        primaryStage.setResizable(false); // this aint working so far

        rootGroup = new VBox(2);        // contains menuBar and rootHBox
        rootHBox = new HBox(2);         // contains sideBar and rootVBox
        rootHBox.setSpacing(2f);
        rootVBox = new VBox(2);         // contains rectangle and console
        rootVBox.setSpacing(5f);
        schematicGroup = new Group();      // schematicGroup (atm gateGroup)
        gateGroup = new Group();
        schematicGroup.getChildren().add(gateGroup);
        initschematic();

        MenuBarBuilder classyMenuBar = new MenuBarBuilder(this);
        menuBar = classyMenuBar.buildMenuBarWithMenus();
        rootGroup.getChildren().add(menuBar);

        SideBarBuilder classySideBarBuilder = new SideBarBuilder(this);
        sideBar = classySideBarBuilder.buildSideBarWithButtons();
        rootHBox.getChildren().add(sideBar);

        schematicRectangle = new SchematicRectangle(this);
        Rectangle schemrect = schematicRectangle.getRectangle();
        schematicGroup.getChildren().add(schemrect);
        Group grid = schematicRectangle.drawGrid(this);
        schematicGroup.getChildren().add(grid);
        grid.toBack();          // put grid behind everything
        schemrect.toBack();     // put schematic rectangle behind everything

        rootVBox.getChildren().add(schematicGroup);
        rootVBox.getChildren().add(console);
        rootHBox.getChildren().add(rootVBox);
        rootGroup.getChildren().add(rootHBox);

        Scene scene = new Scene(rootGroup, mainWidth, mainHeight);
        primaryStage.setScene(scene);
        Image defaultCursorImage = Textures.getHmImage("defaultcursorr");
        ImageCursor imageCursor = new ImageCursor(defaultCursorImage, -defaultCursorImage.getWidth(), -defaultCursorImage.getHeight());
        scene.setCursor(imageCursor);
    }

    private void initConsole() {
        //set up the console
        console.setItems(consoleObservableList);
        //console.setLayoutY(55);
        console.setPrefSize(consoleWidth, consoleHeight);
    }

    public void showOnConsole(String text) {
        //if there is 8 items in list, delete first log message, shift other logs and  add a new one to end position
        if (consoleObservableList.size() == 8) {
            consoleObservableList.remove(0);
        }
        consoleObservableList.add(text);
    }

    protected final void buildAndSetLoop() {        // this vill update everything 100 times per second / once every 1.666 seconds
        final int fps = 60; //  100fps == 100on + 100off = 200/2 = 100 hertz
        final Duration oneFrameAmt = Duration.millis(1000 / fps);  // "100 fps" should be enough.. for nao
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, new EventHandler() {
            @Override
            public void handle(Event event) {
                //System.out.println("==============START===============");
                Long delta = 0L;
                for (GateObject next : gateObjects) {
                    next.update(delta);
                }
                for (ConnectionLineObject next : connectionLineObjects) {
                    if (next != null && next.getLogicLine() != null) {
                        next.update(delta);
                    }
                }
                //if(gateObjects.size() != 0) System.out.println(gateObjects.get(0).getGroup().localToParent(0, 0));
                //System.out.println("===============END================");
                event.consume();
            }
        }); // oneFrame

        // sets the apps loop (Timeline)
        timeline = new Timeline(oneFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void initschematic() {
        if (gateGroup.getChildren().size() > 0) {
            gateGroup.getChildren().remove(0, gateGroup.getChildren().size());
        }
        /* reset all lists*/
        gateObjects = new ArrayList();
        lines = new ArrayList();
        connectionLineObjects = new ArrayList();
        logicLines = new ArrayList();
    }

    public void destroy() {
        timeline.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        main = this;
        Textures.initMap();
        initConsole();
        init(primaryStage);
        buildAndSetLoop();
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
