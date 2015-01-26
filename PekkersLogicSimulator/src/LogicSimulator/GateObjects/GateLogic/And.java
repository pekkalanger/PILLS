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
package LogicSimulator.GateObjects.GateLogic;

import LogicSimulator.Globals;


public class And implements Gate {
    boolean last = false;
    String name = "And";
    InputPin[] inputPins = new InputPin[2];
    OutputPin[] outputPins = new OutputPin[1];

    public And() {
        inputPins[0] = new InputPin();
        inputPins[1] = new InputPin();
        outputPins[0] = new OutputPin();
    }

    @Override
    public boolean update(long deltaTime) {
       if(inputPins[0] != null && inputPins[1] != null && outputPins[0] != null){
            if(inputPins[0].getDataObject().getData() == true && inputPins[1].getDataObject().getData() == true) {
                if(outputPins[0].getDataObject().getData() == false){
                    Globals.main.showOnConsole("And = " + !outputPins[0].getDataObject().getData());
                }
                outputPins[0].getDataObject().setData(true);
            } else {
                if(outputPins[0].getDataObject().getData() == true){
                    Globals.main.showOnConsole("And = " + !outputPins[0].getDataObject().getData());
                }
                outputPins[0].getDataObject().setData(false);

            }
            System.out.println("And out: " + outputPins[0].getDataObject().getData());
            return false;
        } else return true;
    }

    @Override
    public String toString(){
            return name;
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
	
}
