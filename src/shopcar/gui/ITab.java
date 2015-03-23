/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

/**
 *
 * @author Vitor Freitas
 */
public interface ITab 
{
    public static final String RESOURCES = "/resources/";
    
     default void init()
     {
        setTabValues();
        createGraphic();
        createContent();
     }
     
     public void setTabValues();
     
     public void createGraphic();
     
     public void createContent();
}
