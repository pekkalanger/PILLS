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

    public Switch(boolean d) {
        super(0, 1, "Switch");
        this.dataObject = new DataObject(d);
        setOutputPin(0, new OutputPin());
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
        getOutputPin(0).setDataObject(dataObject);
        return false;
    }

    @Override
    public void toggle() {
        Globals.main.showOnConsole("Switch status: " + !getOutputPin(0).getDataObject().getData());
        dataObject.toggle();
    }

}
