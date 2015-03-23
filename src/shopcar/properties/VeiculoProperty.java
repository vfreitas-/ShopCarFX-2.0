/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcar.properties;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shopcar.pojo.Veiculo;

public class VeiculoProperty 
{
    private Veiculo veiculo = new Veiculo();
    
    private final StringProperty chassi = new SimpleStringProperty()
    {
        @Override
        public void set(String v)    
        {
            super.set(v);
            getVeiculo().setChassi(v);
        }
    };

    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() 
    {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) 
    {
        this.veiculo = veiculo;
    }
    
    public final String getChassi()
    {
        return this.chassi.get();
    }
    
    public final void setChassi(String chassi)
    {
        this.chassi.set(chassi);
    }
    
    public StringProperty chassiProperty()
    {
        return this.chassi;
    }
    
}
