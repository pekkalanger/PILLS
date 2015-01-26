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

import javafx.scene.image.Image;

public class Textures {
    
    static public Image defaultCursor;
    static public Image defaultCursorActive;
    static public Image lineCursor;
    static public Image switchCursor;
    // static public Image switchCursor = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/buttoncursor.png");
    static public Image andGate;
    static public Image orGate;
    static public Image nandGate;
    static public Image norGate;
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
    switchCursor = new Image("file:res/buttoncursor.png");
    // static public Image switchCursor = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/buttoncursor.png");
    
    andGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/andgate2.png");
    
    orGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/orgate2.png");
    notGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/notgate.png");
    nandGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/nandgate2.png");
    
    norGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/norgate2.png");
    
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
