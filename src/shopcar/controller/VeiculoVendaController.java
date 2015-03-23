/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.controller;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import shopcar.pojo.Veiculo;
import shopcar.dao.JpaDAO;
import shopcar.dao.JpaDAODI;

/**
 *
 * @author info1
 */
public class VeiculoVendaController
{
    @Inject private JpaDAODI<Veiculo> daoVeiculo;
    
    public void recebeVenda(@Observes VendaMovimento eventVenda)
    {
        try
        {
            eventVenda.getVeiculo().setVendido(true);
            daoVeiculo.update(eventVenda.getVeiculo());
        } 
        catch (Exception e)
        {
            System.err.println("Não foi possível vender o Veiculo!" + e.getCause());
        }
    }
}
