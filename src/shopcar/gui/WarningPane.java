/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author info1
 */
public class WarningPane extends GridPane implements IPane
{
    private Label text;
    
    public WarningPane(String text)
    {
        init();
        this.text.setText(text);
    }
    
    @Override
    public void initNodeProperties()
    {
        setId("fundo-padrao");
        setPadding(new Insets(20));
    }

    @Override
    public void initComponents()
    {
        text = new Label();
    }

    @Override
    public void initListeners()
    {
        
    }

    @Override
    public void initLayout()
    {
        add(text, 1, 1, 1, 1);
    }
    
}
