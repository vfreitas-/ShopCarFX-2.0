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
public class Pedido implements Serializable
{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long pedidoId;
    
    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;
    @ElementCollection
    @OneToMany(mappedBy = "pedido")
    private Set<ItemPedido> itemPedido;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    /**
     * @return the id
     */
    public Long getId()
    {
        return pedidoId;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.pedidoId = id;
    }
    
    /**
     * @return the cliente
     */
    public Cliente getCliente()
    {
        return cliente;
    }
    
    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }
    
    /**
     * @return the itemPedido
     */
    public Set<ItemPedido> getItemPedido()
    {
        return itemPedido;
    }

    /**
     * @param itemPedido the itemPedido to set
     */
    public void setItemPedido(Set<ItemPedido> itemPedido)
    {
        this.itemPedido = itemPedido;
    }


//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="hashCode e equals">
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.pedidoId);
        hash = 23 * hash + Objects.hashCode(this.cliente);
        hash = 23 * hash + Objects.hashCode(this.getItemPedido());
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.pedidoId, other.pedidoId))
        {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente))
        {
            return false;
        }
        if (!Objects.equals(this.itemPedido, other.itemPedido))
        {
            return false;
        }
        return true;
    }
//</editor-fold>

    
}
