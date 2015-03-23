/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.dao;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import shopcar.controller.VendaMovimento;
import shopcar.pojo.Pedido;

/**
 *
 * @author info1
 */
public class PedidoDAO
{
    @Inject private JpaDAODI<Pedido> daoPedido;
    @Inject private Pedido v;
    
    public void recebeVenda(@Observes VendaMovimento eventVenda)
    {
        try
        {
            v.setCliente(eventVenda.getCliente());
            daoPedido.save(v);
        } 
        catch (Exception e)
        {
            System.err.println("Venda deu errado!" + e.getMessage());
        }
    }
}
