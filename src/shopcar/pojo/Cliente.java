/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.pojo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author info1
 */
@Entity
public class Cliente implements Serializable
{
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long clienteId;
    private String nome;
    
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    /**
     * @return the clienteId
     */
    public Long getClienteId()
    {
        return clienteId;
    }
    
    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(Long clienteId)
    {
        this.clienteId = clienteId;
    }
    
    /**
     * @return the nome
     */
    public String getNome()
    {
        return nome;
    }
    
    /**
     * @param nome the nome to set
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    /**
     * @return the pedidos
     */
    public Set<Pedido> getPedidos()
    {
        return pedidos;
    }
    
    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(Set<Pedido> pedidos)
    {
        this.pedidos = pedidos;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode e equals">
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.clienteId);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.pedidos);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.clienteId, other.clienteId))
        {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome))
        {
            return false;
        }
        if (!Objects.equals(this.pedidos, other.pedidos))
        {
            return false;
        }
        return true;
    }
//</editor-fold>
}
