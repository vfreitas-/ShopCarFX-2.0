/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.pojo;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.*;


/**
 *
 * @author Mandy Grimm
 */
@Entity
public class Marca implements Serializable
{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Integer marcaId;
    private String marca;
    @ElementCollection
    @OneToMany(mappedBy = "marca")
    private List<Veiculo> veiculos;
    @ElementCollection
    @OneToMany(mappedBy = "marca")
    private List<Modelo> modelos;

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.marcaId);
        hash = 59 * hash + Objects.hashCode(this.marca);
        hash = 59 * hash + Objects.hashCode(this.veiculos);
        hash = 59 * hash + Objects.hashCode(this.modelos);
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
        final Marca other = (Marca) obj;
        if (!Objects.equals(this.marcaId, other.marcaId))
        {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca))
        {
            return false;
        }
        if (!Objects.equals(this.veiculos, other.veiculos))
        {
            return false;
        }
        if (!Objects.equals(this.modelos, other.modelos))
        {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() 
    {
        return marca;
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Gets and Lists">
    public Integer getMarcaId()
    {
        return marcaId;
    }
    
    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
  
    public List<Veiculo> getVeiculo() {
        return veiculos;
    }
    
    public void setVeiculo(List<Veiculo> veiculo) {
        this.veiculos = veiculo;
    }
   
    public List<Modelo> getModelo() {
        return modelos;
    }
    
    public void setModelo(List<Modelo> modelo) {
        this.modelos = modelo;
    }

//</editor-fold>

}
