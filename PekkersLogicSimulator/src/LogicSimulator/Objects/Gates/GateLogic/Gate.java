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
package LogicSimulator.Objects.Gates.GateLogic;

import LogicSimulator.Globals;

/**
 *
 * @author PEKKA
 */
public abstract class Gate {

    String name = "unnamed";
    boolean last = false;
    InputPin[] inputPins;
    OutputPin[] outputPins;

    public Gate() {

    }

    public Gate(int in, int out) {
        if (in > 0) {
            inputPins = new InputPin[in];
        }
        if (out > 0) {
            outputPins = new OutputPin[out];
        }
    }

    public void showOutputOnConsole(int num, boolean b) {
        if (outputPins[num].getDataObject().getData() == b) {
            Globals.main.showOnConsole(name + " = " + !outputPins[num].getDataObject().getData());
        }
    }
}
