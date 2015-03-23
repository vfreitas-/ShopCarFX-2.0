/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

import javafx.scene.Node;

/**
 *
 * @author Vitor Freitas
 */
public class FormStaticMethods
{
    public static void setVisableFalse(Node... nodes)
    {
        for(Node n : nodes) n.setVisible(false);
    }
    
    public static void clean(Node... nodes)
    {
        
    }
}
