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

public class LogicLine implements GateInterface {

    private final String name = "Line";
    private InputPin pinA;
    private OutputPin pinB;

    public LogicLine() {
        pinA = new InputPin();
        pinB = new OutputPin();
    }

    @Override
    public boolean update(long deltaTime) {
        pinA.setDataObject(pinB.getDataObject());
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void setInputPin(int pos, InputPin ip) {
        pinA = ip;
    }

    @Override
    public InputPin getInputPin(int pos) {
        return pinA;
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
        pinB = op;
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        return pinB;
    }

    @Override
    public void setDataObject(DataObject dataObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataObject getDataObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toggle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        if (getInputPin(0) != null) {
            getInputPin(0).setDataObject(new DataObject(false));
            setInputPin(0, null);
        } else if (getOutputPin(0) != null) {
            getOutputPin(0).setDataObject(new DataObject(false));
            setOutputPin(0, null);
        }
    }

}
