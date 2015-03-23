/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui.nodes;

import javafx.scene.control.TextField;

/**
 *
 * @author info1
 */
public class NumberTextField extends TextField
{
    private Integer begin;
    private String newRegex;
    
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
        setText(begin.toString());
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
