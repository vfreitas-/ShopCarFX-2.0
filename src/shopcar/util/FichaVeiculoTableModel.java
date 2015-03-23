/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

/**
 *
 * @author info1
 */
public class FichaVeiculoTableModel
{
    private String field;
    private String value;

    public FichaVeiculoTableModel(String field, String value)
    {
        this.field = field;
        this.value = value;
    }
    
    /**
     * @return the field
     */
    public String getField()
    {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(String field)
    {
        this.field = field;
    }

    /**
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}
