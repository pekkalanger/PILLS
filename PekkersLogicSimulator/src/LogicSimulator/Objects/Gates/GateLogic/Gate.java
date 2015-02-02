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

import LogicSimulator.Main;

/**
 *
 * @author PEKKA
 */
public abstract class Gate implements GateInterface {

    private String name = "Unnamed";
    private boolean last = false;
    private InputPin[] inputPins;
    private OutputPin[] outputPins;

    public Gate() {
    }

    public Gate(int in, int out) {
        if (in > 0) {
            inputPins = new InputPin[in];
            for (int i = 0; i < inputPins.length; i++) {
                inputPins[i] = new InputPin();
            }
        }
        if (out > 0) {
            outputPins = new OutputPin[out];
            for (int i = 0; i < outputPins.length; i++) {
                outputPins[i] = new OutputPin();
            }
        }
    }

    public Gate(int in, int out, String name) {
        this(in, out);
        this.name = name;
    }

    public void showOutputOnConsole(int num, boolean b) {
        if (outputPins[num].getDataObject().getData() == b) {
            Main.main.showOnConsole(name + " = " + !outputPins[num].getDataObject().getData());
        }
    }

    @Override
    public boolean update(long deltaTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInputPin(int pos, InputPin ip) {
        inputPins[pos] = ip;
    }

    @Override
    public InputPin getInputPin(int pos) {
        return inputPins[pos];
    }

    @Override
    public void setOutputPin(int pos, OutputPin op) {
        outputPins[pos] = op;
    }

    @Override
    public OutputPin getOutputPin(int pos) {
        return outputPins[pos];
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public InputPin[] getInputPins() {
        return inputPins;
    }

    public void setInputPins(InputPin[] inputPins) {
        this.inputPins = inputPins;
    }

    public OutputPin[] getOutputPins() {
        return outputPins;
    }

    public void setOutputPins(OutputPin[] outputPins) {
        this.outputPins = outputPins;
    }

    @Override
    public void destroy() {
        name = "Dead";
        inputPins = null;
        outputPins = null;
    }
}
