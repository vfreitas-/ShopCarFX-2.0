/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.scene.layout.GridPane;

/**
 *
 * @author Vitor Freitas
 */
public interface IPane
{
    default void init()
    {
        initNodeProperties();
        initComponents();
        initListeners();
        initLayout();
    }
    
    void initNodeProperties();
    
    void initComponents();
    
    void initListeners();
    
    void initLayout();
}
