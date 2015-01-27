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

public class DataObject {
	
	private boolean data = false;
	
        public DataObject(boolean d) {
            this.data = d;
	}
	public boolean getData() {
            return data;
	}
	public void setData(boolean data) {
            this.data = data;
	}
        public boolean toggle(){
            setData(!getData());
            return getData();
	}
        public DataObject notted(){
            DataObject newDataObject = new DataObject(!getData());
            return newDataObject;
	}
		
}
