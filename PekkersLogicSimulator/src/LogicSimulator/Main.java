package LogicSimulator;

import java.util.LinkedList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    
    LinkedList<Circle> circleList = null;
    int mainWidth = 1024;
    int mainHeight = 768;
    int schematicWidth = mainWidth-100;
    int schematicHeigth = 500;
    int consoleWidth = 700;
    int consoleHeight = 200;

    
    //create a console for logging mouse events
    final ListView<String> console = new ListView<String>();
    
    //create a observableArrayList of logged events that will be listed in console
    final ObservableList<String> consoleObservableList = FXCollections.observableArrayList();{
        //set up the console
        console.setItems(consoleObservableList);
        //console.setLayoutY(55);
        console.setPrefSize(consoleWidth, consoleHeight);
    }
 
    //create a rectangle - (XXXpx X XXXpx) in which our circles can move
    Rectangle rectangle;
    
    //variables for storing initial position before drag of circle
    public double initX;
    public double initY;
    public Point2D dragAnchor;
    public MenuBar menuBar;
    public Group schematicGroup;
    public Group circleGroup;  // "the scetch"
    public VBox rootGroup;
    public VBox rootVBox;
    public VBox sideBar;
    public HBox rootHBox;
    public Stage primaryStage;
    Timeline timeline;
    
    private void init(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Pekkers Boolean Logic Simulator 2015"); 
        primaryStage.setResizable(false); // this aint working so far
        
        rootGroup = new VBox(2);        // contains menuBar and rootHBox
        rootHBox = new HBox(2);         // contains sideBar and rootVBox
        rootVBox = new VBox(2);         // contains rectangle and console
        rootHBox.setSpacing(1f);
        rootVBox.setSpacing(5f);
        //sideBar = new VBox();         // contains sidebar items
        //menuBar = new MenuBar();      // a most excelent menubar
        schematicGroup = new Group();   // where gateGroup and lineGroup? r comin  
        circleGroup = new Group();      // gateGroup (atm circleGroup)
        
        circleList = new LinkedList<Circle>();

        
        ClassyMenuBarBuilder classyMenuBar = new ClassyMenuBarBuilder(this);
        menuBar = classyMenuBar.buildMenuBarWithMenus();
        rootGroup.getChildren().add(menuBar);
        
        ClassySideBarBuilder classySideBarBuilder = new ClassySideBarBuilder(this);
        sideBar = classySideBarBuilder.buildSideBarWithButtons();
        rootHBox.getChildren().add(sideBar);
        
        rectangle = new Rectangle(schematicWidth, schematicHeigth);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
                new Stop(1, Color.rgb(156,216,255)), new Stop(0, Color.rgb(156,216,255, 0.5))
            }));
        
        // we can set mouse event to any node, also on the rectangle
        rectangle.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //log mouse move to console, method listed below
                showOnConsole("Mouse moved, x: " + me.getX() + ", y: " + me.getY() );
            }
        });

        rectangle.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override public void handle(ScrollEvent event) {
                double translateX = event.getDeltaX();
                double translateY = event.getDeltaY();

                // reduce the deltas for the circles to stay in the screen
                for (Circle c : circleList) {
                    if (c.getTranslateX() + translateX + c.getRadius() > mainWidth) {
                        translateX = mainWidth - c.getTranslateX() - c.getRadius();
                    }
                    if (c.getTranslateX() + translateX - c.getRadius() < 0) {
                        translateX = - c.getTranslateX() + c.getRadius();
                    }
                    if (c.getTranslateY() + translateY + c.getRadius() > mainHeight) {
                        translateY = mainHeight - c.getTranslateY() - c.getRadius();
                    }
                    if (c.getTranslateY() + translateY - c.getRadius() < 0) {
                        translateY = - c.getTranslateY() + c.getRadius();
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
        });
        
        schematicGroup.getChildren().add(rectangle);
        rectangle.toBack();
        schematicGroup.getChildren().add(circleGroup);

        rootVBox.getChildren().add(schematicGroup);
        rootVBox.getChildren().add(console);
        
        rootHBox.getChildren().add(rootVBox);
        rootGroup.getChildren().add(rootHBox);

        primaryStage.setScene(new Scene(rootGroup, mainWidth,mainHeight));
    }
    
    public void showOnConsole(String text) {
         //if there is 8 items in list, delete first log message, shift other logs and  add a new one to end position
         if (consoleObservableList.size()==11){
            consoleObservableList.remove(0);
         }
         consoleObservableList.add(text);
    }

    protected final void buildAndSetLoop() {
        final int fps = 100;
        final Duration oneFrameAmt = Duration.millis(1000/fps);  // "100 fps" should be enough.. for nao
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
            new EventHandler() {
                @Override
                public void handle(Event event) {
                    //showOnConsole("kuket");
                    // update actors
                    //updateSprites();
                }
        }); // oneFrame

        // sets the apps loop (Timeline)
        timeline = new Timeline(oneFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
      
    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        buildAndSetLoop();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}