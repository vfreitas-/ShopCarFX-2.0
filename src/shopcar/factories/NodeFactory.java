/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.factories;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javax.enterprise.inject.Produces;
import shopcar.qualifiers.DefaultBuilder;


public class NodeFactory
{
    @Produces @DefaultBuilder
    public Button createButton() 
    {
        return new Button();
    }
    
    @Produces @DefaultBuilder
    public TextField createTextField()
    {
        return new TextField("bla");
    }
    
    @Produces @DefaultBuilder
    public Group createGroup()
    {
        return new Group();
    }
}
