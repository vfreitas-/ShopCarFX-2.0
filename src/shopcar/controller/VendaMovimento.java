/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.controller;

import shopcar.pojo.Cliente;
import shopcar.pojo.Veiculo;

/**
 *
 * @author info1
 */
public class VendaMovimento
{
    private final Veiculo veiculo;
    private final Cliente cliente;
    
    public VendaMovimento(Veiculo veiculo, Cliente cliente)
    {
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    public Veiculo getVeiculo()
    {
        return this.veiculo;
    }
    
    public Cliente getCliente()
    {
        return this.cliente;
    }
}
