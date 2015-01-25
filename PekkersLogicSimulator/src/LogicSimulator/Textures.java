package LogicSimulator;

import javafx.scene.image.Image;

public class Textures {
    
    static public Image defaultCursor;
     static public Image defaultCursorActive;
    static public Image lineCursor;
    static public Image buttonCursor;
    // static public Image buttonCursor = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/buttoncursor.png");
    static public Image andGate;
    static public Image orGate;
    static public Image notGate;
    static public Image ledOff;
    static public Image ledOn;
    static public Image switchOff;
    static public Image switchOn;
    static public Image pinPressed;
    static public Image pinOver;
    static public Image inputPin; 
    static public Image outputPin; 
    static public Image texture;
    static public Image exitIcon;
    
    public static void init() {
    /*      uses file: when testing, and as fallback later     */
    defaultCursor = new Image("file:res/defaultcursorr.png");
    defaultCursorActive = new Image("file:res/defaultcursorractive.png");
    lineCursor = new Image("file:res/linecursor.png");
    buttonCursor = new Image("file:res/buttoncursor.png");
    // static public Image buttonCursor = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/buttoncursor.png");
    
    andGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/andgate.png");
    
    orGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/orgate.png");
    notGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/notgate.png");
    ledOff = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/ledoff.png");
    ledOn = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/ledon.png");
    switchOff = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/buttonoff.png");
    switchOn = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/buttonon.png");
    pinPressed = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/pinPressed.png");
    pinOver = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/pinOver.png");
    inputPin = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/inputpin.png");
    outputPin = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/outputpin.png");
    texture = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/texture.png");
    exitIcon = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/exiticon.png");
}

}
