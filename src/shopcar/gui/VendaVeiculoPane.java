/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Vitor Freitas
 */
public class VendaVeiculoPane extends GridPane implements IPane
{
    private Label titulo;

    public VendaVeiculoPane()
    {
        this.init();
    }

    @Override
    public void initComponents()
    {
        titulo = new Label("Venda de Veiculos");
        titulo.setId("title");
        
        setVgap(15);
        setHgap(20);
        
        add(titulo, 2, 2, 2, 2);
        add(new Separator(), 2, 4, 10, 1);
    }

    @Override
    public void initListeners()
    {
        
    }

    @Override
    public void initLayout()
    {
        
    }

    @Override
    public void initNodeProperties()
    {
        
    }
    
}
