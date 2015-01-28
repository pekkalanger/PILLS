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
    //static public Image lineCursor;
    static public Image switchCursor;
    static public Image andGate;
    static public Image nandGate;
    static public Image orGate;
    static public Image norGate;
    static public Image xorGate;
    static public Image xnorGate;
    static public Image notGate;
    static public Image ledOff;
    static public Image ledOn;
    static public Image switchOff;
    static public Image switchOn;
    static public Image andTruth;
    static public Image nandTruth;
    static public Image orTruth;
    static public Image norTruth;
    static public Image xorTruth;
    static public Image xnorTruth;
    static public Image pinPressed;
    static public Image pinOver;
    static public Image inputPin;
    static public Image outputPin;
    static public Image texture;
    static public Image exitIcon;
    static public Image inputInfo;
    static public Image outputInfo;

    public static void init() {
        /*      uses file: when testing, and as fallback later  if https fails   */

        /*          Fallback            */
        //defaultCursor = new Image("file:res/defaultcursorr.png");
        //defaultCursorActive = new Image("file:res/defaultcursorractive.png");
        //lineCursor = new Image("file:res/linecursor.png");
        //switchCursor = new Image("file:res/buttoncursor.png");
        /*          Https               */
        /*          Cursor              */
        defaultCursor = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/defaultcursorr2.png");
        defaultCursorActive = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/defaultcursorractive.png");
        //lineCursor = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/linecursor.png");
        switchCursor = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/defaultcursor2toggle.png");

        /*          Gates               */
        andGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/andgate2.png");
        orGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/orgate2.png");
        notGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/notgate.png");
        nandGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/nandgate2.png");
        norGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/norgate2.png");
        xorGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/xorgate2.png");
        xnorGate = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/xnorgate2.png");
        //xorGate = new Image("file:res/xorgate2.png");

        /*          Truth               */
        andTruth = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/andtruth.png");
        nandTruth = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/nandtruth.png");
        orTruth = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/ortruth.png");
        norTruth = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/nortruth.png");
        xorTruth = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/xortruth.png");
        xnorTruth = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/xnortruth.png");
        /*andTruth = new Image("file:res/andtruth.png");
         nandTruth = new Image("file:res/nandtruth.png");
         orTruth = new Image("file:res/ortruth.png");
         norTruth = new Image("file:res/nortruth.png");
         xorTruth = new Image("file:res/xortruth.png");
         xnorTruth = new Image("file:res/xnortruth.png");
         */

        /*          Led                 */
        ledOff = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/ledoff.png");
        ledOn = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/ledon.png");
        //ledOff = new Image("file:res/ledoff.png");
        //ledOn = new Image("file:res/ledon.png");

        /*          Switch              */
        switchOff = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/buttonoff.png");
        switchOn = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/buttonon.png");
        //switchOff = new Image("file:res/buttonoff.png");
        //switchOn = new Image("file:res/buttonon.png");

        /*          Pins                */
        pinPressed = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/pinPressed.png");
        pinOver = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/pinOver.png");
        inputPin = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/inputpin.png");
        outputPin = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/outputpin.png");

        /*          Icons               */
        exitIcon = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/exiticon.png");

        /*          Other               */
        //inputInfo = new Image("file:res/inputinfo.png");
        //outputInfo = new Image("file:res/outputinfo.png");
        inputInfo = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/inputinfo.png");
        outputInfo = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/outputinfo.png");
        texture = new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/texture.png");

    }

}
