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

public class Switch extends Gate implements GateInterface {

    DataObject dataObject;
    private OutputPin outputPin = null;

    public Switch(boolean d) {
        super();
        name = "Switch";
        this.dataObject = new DataObject(d);
        outputPin = new OutputPin();
    }

    @Deprecated
    public void setLine(LogicLine l) {
        // this.outputPin=l.getInputPin();
    }

    @Deprecated
    public void removeLine() {
        this.outputPin = null;
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
        //toggle(); used as a generator/clock
        if (outputPin != null) {
            /*if (outputPin.getDataObject() != null) {
                System.out.println("Switch status: " + outputPin.getDataObject().getData());
            }*/
            outputPin.setDataObject(dataObject);
            return false;
        } else {
            //System.out.println("Switch status: null");
            return true;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void setInputPin(int pos, InputPin ip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InputPin getInputPin(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
        outputPin = op;
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        return outputPin;
    }

    @Override
    public void toggle() {
        Globals.main.showOnConsole("Switch status: " + !outputPin.getDataObject().getData());
        dataObject.toggle();
    }

}
