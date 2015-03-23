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
import javax.persistence.Entity;

/**
 *
 * @author Mandy Grimm
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Modelo.listAllModelosByMarca",
        query = "SELECT m FROM Modelo m WHERE m.marca = :marca")})
public class Modelo implements Serializable
{
    private static final Long serialVersionUID = 1L;
    
    public static final String MODELOS_BY_MARCA = "Modelo.listAllModelosByMarca";
    
    @Id
    @GeneratedValue
    private Integer modeloId;
    private String modelo;
    private String versao;
    @ElementCollection
    @OneToMany(mappedBy = "modelo")
    private List<Veiculo> veiculo;
    @ElementCollection
    @ManyToOne
    @JoinColumn(name = "marcaid")
    private Marca marca;

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.modeloId);
        hash = 47 * hash + Objects.hashCode(this.modelo);
        hash = 47 * hash + Objects.hashCode(this.versao);
        hash = 47 * hash + Objects.hashCode(this.veiculo);
        hash = 47 * hash + Objects.hashCode(this.marca);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Modelo other = (Modelo) obj;
        if (!Objects.equals(this.modeloId, other.modeloId)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.versao, other.versao)) {
            return false;
        }
        if (!Objects.equals(this.veiculo, other.veiculo)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return  modeloId + " - " + modelo + " " + versao.split("\\s+")[0];
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gets and Lists">
  
    public Integer getModeloId() {
        return modeloId;
    }
    
    public void setModeloId(Integer modeloId) {
        this.modeloId = modeloId;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getVersao() {
        return versao;
    }
    
    public void setVersao(String versao) {
        this.versao = versao;
    }

    public List<Veiculo> getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(List<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }

    public Marca getMarca() {
        return marca;
    }
    
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

//</editor-fold>
}
