/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.controller;

import shopcar.pojo.Cliente;
import shopcar.pojo.Veiculo;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author info1
 */
public class VendeCarro implements IVendeCarro
{
    @Inject private Event<VendaMovimento> eventVenda;
    
    @Override
    public void Vender(Veiculo veiculo, Cliente cliente)
    {
        eventVenda.fire(new VendaMovimento(veiculo, cliente));
    }
    
}
