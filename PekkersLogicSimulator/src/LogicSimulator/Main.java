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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    int mainWidth = 1024;
    int mainHeight = 768;
    int schematicWidth = mainWidth - 50;
    int schematicHeigth = 500;
    int consoleWidth = 700;
    int consoleHeight = 200;

    public int gridWidth = 16;
    public int gridHeight = 16;

    /*      schematic objects       */ // will be saved/laoded and nulled on New.
    public List<GateObject> gateObjects;
    public List<Line> lines;
    public List<LogicLine> logicLines;
    public List<ConnectionLineObject> connectionLineObjects;
    public LinkedList<Circle> circleList = null;

    //create a console for logging mouse events
    final ListView<String> console = new ListView<>();

    //create a schematicRectangle - (XXXpx X XXXpx) in which our circles can move
    Rectangle schematicRectangle;

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
        rootVBox = new VBox(2);         // contains schematicRectangle and console
        rootHBox.setSpacing(2f);
        rootVBox.setSpacing(5f);
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

        schematicRectangle = new Rectangle(schematicWidth, schematicHeigth);
        schematicRectangle.setStroke(Color.WHITE);
        schematicRectangle.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
            new Stop(1, Color.rgb(205, 235, 255)), new Stop(0, Color.rgb(205, 235, 255, 0.5))
        }));
        /*schematicRectangle.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
         new Stop(1, Color.rgb(156,216,255)), new Stop(0, Color.rgb(156,216,255, 0.5))
         }));
         */

        /* draw a grid on screen*/
        for (int i = 0; i < schematicWidth; i += gridWidth) {
            Line gridLine = new Line(i, 0, i, schematicRectangle.getHeight());
            gridLine.setStroke(Color.LIGHTGRAY);
            gridLine.setStrokeWidth(1);
            schematicGroup.getChildren().add(gridLine);
        }
        for (int i = 0; i < schematicHeigth; i += gridHeight) {
            Line gridLine = new Line(0, i, schematicRectangle.getWidth(), i);
            gridLine.setStroke(Color.LIGHTGRAY);
            gridLine.setStrokeWidth(1);
            schematicGroup.getChildren().add(gridLine);
        }

        // we can set mouse event to any node, also on the schematicRectangle
        schematicRectangle.setOnMouseMoved((MouseEvent me) -> {
            //me.consume();
        });
        /*
         schematicRectangle.setOnMouseClicked((MouseEvent me) -> {
         if (me.getButton() == MouseButton.PRIMARY) {
         if (ClipBoard.getGateObject() != null) {
         ClipBoard.getGateObject().getGroup().setTranslateX(me.getSceneX() - sideBar.getWidth() - 2);
         ClipBoard.getGateObject().getGroup().setTranslateY(me.getSceneY() - menuBar.getHeight() - 2);
         gateObjects.add(ClipBoard.getGateObject());
         ClipBoard.clearDragBoard();
         }
         }
         });
         */
        schematicRectangle.setOnScroll((ScrollEvent event) -> { // when moving the schematic
            double translateX = event.getDeltaX();
            double translateY = event.getDeltaY();

            // reduce the deltas for the circles to stay in the screen
            for (Circle c : circleList) {
                if (c.getTranslateX() + translateX + c.getRadius() > mainWidth) {
                    translateX = mainWidth - c.getTranslateX() - c.getRadius();
                }
                if (c.getTranslateX() + translateX - c.getRadius() < 0) {
                    translateX = -c.getTranslateX() + c.getRadius();
                }
                if (c.getTranslateY() + translateY + c.getRadius() > mainHeight) {
                    translateY = mainHeight - c.getTranslateY() - c.getRadius();
                }
                if (c.getTranslateY() + translateY - c.getRadius() < 0) {
                    translateY = -c.getTranslateY() + c.getRadius();
                }
            }

            // move the circles
            for (Circle c : circleList) {
                c.setTranslateX(c.getTranslateX() + translateX);
                c.setTranslateY(c.getTranslateY() + translateY);
            }
            // log event
            showOnConsole("Scrolled, deltaX: " + event.getDeltaX() + ", deltaY: " + event.getDeltaY());
        }
        );
        schematicGroup.getChildren()
                .add(gateGroup);
        //gateGroup.getChildren()
        schematicGroup.getChildren()
                .add(schematicRectangle);
        schematicRectangle.toBack();

        rootVBox.getChildren()
                .add(schematicGroup);
        rootVBox.getChildren()
                .add(console);

        rootHBox.getChildren()
                .add(rootVBox);
        rootGroup.getChildren()
                .add(rootHBox);

        Scene scene = new Scene(rootGroup, mainWidth, mainHeight);

        primaryStage.setScene(scene);

        rootGroup.setDepthTest(DepthTest.ENABLE);

        primaryStage.getScene()
                .setCamera(new PerspectiveCamera());

        Image defaultCursorImage = Textures.defaultCursor;
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
            }
        }); // oneFrame

        // sets the apps loop (Timeline)
        timeline = new Timeline(oneFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Textures.init();
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
