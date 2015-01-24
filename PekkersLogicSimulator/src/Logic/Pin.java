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
        //public String type="undefined";
        private DataObject dataObject;

        public Pin(){
        dataObject = new DataObject(false);
        }
        public void setDataObject(DataObject dataObject){
            this.dataObject = dataObject;
        }
        public DataObject getDataObject(){
            return dataObject;
        }
    
}
