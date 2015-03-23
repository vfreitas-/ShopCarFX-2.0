/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui.nodes;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author info1
 */
public class NumberTextFieldTest extends HBox
{
    private Integer begin;
    private String newRegex;
    private final VBox vBox;

    public NumberTextFieldTest()
    {
        setSpacing(0);
        vBox = new VBox(0);
        vBox.getChildren().addAll(new ArrowButton(), new ArrowButton());
        Field a = new Field();
        getChildren().addAll(a, vBox);
        
        setPrefSize(a.getWidth(), a.getHeight());
        setMaxHeight(a.getHeight());
        setMaxWidth(a.getWidth());
        setMinHeight(a.getHeight());
        setMinWidth(a.getWidth());
    }
    
    class Field extends TextField
    {
         @Override
        public void replaceText(int start, int end, String text)
        {
            if(validate(text))
                super.replaceText(start, end, text);
        }

        @Override
        public void replaceSelection(String text)
        {
            if(validate(text))
                super.replaceSelection(text);
        }

        public boolean validate(String text)
        {
    //        if(getNewRegex().isEmpty())
    //            return text.matches("[0-9]") || "".equals(text);
    //        else
    //            return text.matches(getNewRegex()) || "".equals(text);
            return text.matches("[0-9]") || "".equals(text);
        }
    }
    
    class ArrowButton extends Button
    {
        
    }

    /**
     * @return the begin
     */
    public Integer getBegin()
    {
        return begin;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(Integer begin)
    {
        //setText(begin.toString());
    }

    /**
     * @return the newRegex
     */
    public String getNewRegex()
    {
        return newRegex;
    }

    /**
     * @param newRegex the newRegex to set
     */
    public void setNewRegex(String newRegex)
    {
        this.newRegex = newRegex;
    }
}
