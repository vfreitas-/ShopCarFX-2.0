/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.pojo;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Aluno
 */
@Entity
public class VeiculoTransporte extends Veiculo
{
    @Range(min = 5000, max = 200000, message = "A Capacidade Máxima de Carga do "
            + "Veiculo, deve estar entre 5000 e 200000 KG")
    private BigDecimal capcMaxCarga;    

     //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
     /**
      * @return the capcMaxCarga
      */
     public BigDecimal getCapcMaxCarga()
     {
         return capcMaxCarga;
     }
     
     /**
      * @param capcMaxCarga the capcMaxCarga to set
      */
     public void setCapcMaxCarga(BigDecimal capcMaxCarga)
     {
         this.capcMaxCarga = capcMaxCarga;
     }
//</editor-fold>

     //<editor-fold defaultstate="collapsed" desc="hashCode equals">
     @Override
     public int hashCode()
     {
         int hash = 7;
         hash = 17 * hash + Objects.hashCode(this.capcMaxCarga);
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
         final VeiculoTransporte other = (VeiculoTransporte) obj;
         if (!Objects.equals(this.capcMaxCarga, other.capcMaxCarga))
         {
             return false;
         }
         return true;
     }
//</editor-fold>
}
