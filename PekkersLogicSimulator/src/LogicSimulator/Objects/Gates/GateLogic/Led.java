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

public class Led extends Gate implements GateInterface {

    DataObject dataObject;

    public Led() {
        super(1, 0);
        name = "Led";
        dataObject = new DataObject(false);
        inputPins[0] = new InputPin();
    }

    @Deprecated
    public void setLine(LogicLine l) {
        //this.inputPins[0] = l.getOutputPin(0);
    }

    @Deprecated
    public void removeLine() {
        this.inputPins[0] = null;
    }

    @Override
    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    @Override
    public DataObject getDataObject() {
        return dataObject;
    }

    @Override
    public boolean update(long deltaTime) {
        if (inputPins[0] != null) {
            if (getDataObject() != null) {
                if (last != getDataObject().getData()) {
                    Globals.main.showOnConsole("LED = " + getDataObject().getData());
                }
                dataObject = inputPins[0].getDataObject();
                if (getDataObject() != null) {
                    last = getDataObject().getData();
                }
                //System.out.print("Led status: ");
                //System.out.println(dataObject.getData());
            }
            return false;

        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void setInputPin(int pos, InputPin ip) {
        this.inputPins[pos] = ip;
    }

    @Override
    public InputPin getInputPin(int pos) {
        return inputPins[pos];
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toggle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
