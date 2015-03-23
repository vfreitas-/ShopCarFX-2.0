/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.pojo;

import java.util.Objects;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Aluno
 */
@Entity
public class VeiculoPassageiro extends Veiculo
{
    @Range(min = 2, max = 60, message = "O Veiculo deve possuir entre 2 e 60"
            + " assentos!")
    private Integer numAssentos;
    @Range(min = 2, max = 4, message = "O Veiculo deve possuir entre 2 e 4 portas!")
    private Integer numPortas;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    /**
     * @return the numAssentos
     */
    public Integer getNumAssentos()
    {
        return numAssentos;
    }
    
    /**
     * @param numAssentos the numAssentos to set
     */
    public void setNumAssentos(Integer numAssentos)
    {
        this.numAssentos = numAssentos;
    }
    
    /**
     * @return the numPortas
     */
    public Integer getNumPortas()
    {
        return numPortas;
    }
    
    /**
     * @param numPortas the numPortas to set
     */
    public void setNumPortas(Integer numPortas)
    {
        this.numPortas = numPortas;
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="hashCode e equals">
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.numAssentos);
        hash = 31 * hash + Objects.hashCode(this.numPortas);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final VeiculoPassageiro other = (VeiculoPassageiro) obj;
        if (!Objects.equals(this.numAssentos, other.numAssentos))
        {
            return false;
        }
        if (!Objects.equals(this.numPortas, other.numPortas))
        {
            return false;
        }
        return true;
    }
//</editor-fold>
}
