/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui.nodes;

import javafx.scene.control.TextField;

/**
 *
 * @author Vitor Freitas
 */
public class MaskTextField extends TextField
{
    private int maxChar;
   
    
    
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
        return text.matches("[0-9]") || "".equals(text);
    }
}
