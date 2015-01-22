/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author PEKKA
 */

//public interface Pin {
//}

public abstract class Pin { 
        private DataObject dataObject = null;

        public void setDataObject(DataObject dataObject){
            this.dataObject = dataObject;
        }
        public DataObject getDataObject(){
            return dataObject;
        }
    
}
