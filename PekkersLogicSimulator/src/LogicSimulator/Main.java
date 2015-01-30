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
import java.util.LinkedList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private WebView webView;
    private static final int SPLASH_WIDTH = 676;
    private static final int SPLASH_HEIGHT = 227;

    public int mainWidth = 1024;
    public int mainHeight = 768;
    public int schematicWidth = mainWidth - 50;
    public int schematicHeigth = 500;
    int consoleWidth = 700;
    int consoleHeight = 200;

    /*      schematic objects       */ // will be saved/laoded and nulled on New.
    public List<GateObject> gateObjects;
    public List<Line> lines;
    public List<LogicLine> logicLines;
    public List<ConnectionLineObject> connectionLineObjects;
    public LinkedList<Circle> circleList = null;

    //create a console for logging mouse events
    final ListView<String> console = new ListView<>();

    //create a rectangle - (XXXpx X XXXpx) in which our circles can move
    public SchematicRectangle schematicRectangle;

    //variables for storing initial position before drag of circle
    public double initX;
    public double initY;
    public Point2D dragAnchor;
    public MenuBar menuBar;
    public Group schematicGroup;
    public Group gateGroup;  // "the schematic"  will use gateGroup later on
    public VBox rootGroup;
    public VBox rootVBox;
    public VBox sideBar;
    public HBox rootHBox;
    public Stage primaryStage;
    Timeline timeline;

    //create a observableArrayList of logged events that will be listed in console
    final ObservableList<String> consoleObservableList = FXCollections.observableArrayList();

    {
        //set up the console
        console.setItems(consoleObservableList);
        //console.setLayoutY(55);
        console.setPrefSize(consoleWidth, consoleHeight);
    }

    private void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
        gateObjects = new ArrayList();
        lines = new ArrayList();
        connectionLineObjects = new ArrayList<>();
        circleList = new LinkedList<>();
        logicLines = new LinkedList<>();
        lines = new LinkedList<>();

        primaryStage.setTitle("P.I.L.L.S pekkers incredibly logical logic simulator");
        primaryStage.setResizable(false); // this aint working so far

        rootGroup = new VBox(2);        // contains menuBar and rootHBox

        rootHBox = new HBox(2);         // contains sideBar and rootVBox
        rootVBox = new VBox(2);         // contains rectangle and console
        rootHBox.setSpacing(2f);
        rootVBox.setSpacing(5f);
        //rootHBox.set
        //sideBar = new VBox();         // contains sidebar items
        //menuBar = new MenuBar();      // a most excelent menubar
        gateGroup = new Group();   // where schematicGroup and lineGroup? r comin  
        schematicGroup = new Group();      // schematicGroup (atm gateGroup)

        MenuBarBuilder classyMenuBar = new MenuBarBuilder(this);
        menuBar = classyMenuBar.buildMenuBarWithMenus();
        rootGroup.getChildren().add(menuBar);

        SideBarBuilder classySideBarBuilder = new SideBarBuilder(this);
        sideBar = classySideBarBuilder.buildSideBarWithButtons();

        rootHBox.getChildren().add(sideBar);

        schematicGroup.getChildren().add(gateGroup);
        schematicRectangle = new SchematicRectangle(this);
        rootVBox.getChildren().add(schematicGroup);
        rootVBox.getChildren().add(console);
        rootHBox.getChildren().add(rootVBox);
        rootGroup.getChildren().add(rootHBox);

        Scene scene = new Scene(rootGroup, mainWidth, mainHeight);

        //rootGroup.setDepthTest(DepthTest.ENABLE);
        primaryStage.setScene(scene);
        //primaryStage.getScene().setCamera(new PerspectiveCamera());

        Image defaultCursorImage = Textures.getHmImage("defaultcursorr");
        ImageCursor imageCursor = new ImageCursor(defaultCursorImage, -defaultCursorImage.getWidth(), -defaultCursorImage.getHeight());
        scene.setCursor(imageCursor);

    }

    public void showOnConsole(String text) {
        //if there is 8 items in list, delete first log message, shift other logs and  add a new one to end position
        if (consoleObservableList.size() == 8) {
            consoleObservableList.remove(0);
        }
        consoleObservableList.add(text);
    }

    protected final void buildAndSetLoop() {        // this vill update everything 100 times per second / once every 1.666 seconds
        final int fps = 50; //  if toggle then 100on + 100off = 200/2 hertz
        //int i;
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
                //System.out.println("===============END================");
                event.consume();
            }
        }); // oneFrame

        // sets the apps loop (Timeline)
        timeline = new Timeline(oneFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Textures.initMap();

        Globals.main = this;
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
