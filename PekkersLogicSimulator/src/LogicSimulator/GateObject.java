/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

/**
 *
 * @author PEKKA
 */
public interface GateObject {
    
    public void create(String name); // instead of creating via constructor, yes?
    public void update(boolean clock);
    
}
