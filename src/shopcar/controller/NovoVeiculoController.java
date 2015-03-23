/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.controller;

import java.util.List;
import shopcar.dao.CorDAO;
import shopcar.dao.MarcaDAO;
import shopcar.dao.ModeloDAO;
import shopcar.pojo.Cor;
import shopcar.pojo.Marca;
import shopcar.pojo.Modelo;

/**
 *
 * @author info1
 */
public class NovoVeiculoController
{
    private final MarcaDAO daoMarca;
    private final CorDAO daoCor;
    private final ModeloDAO daoModelo;
    
    public NovoVeiculoController()
    {
        daoMarca = new MarcaDAO();
        daoCor = new CorDAO();
        daoModelo = new ModeloDAO();
    }
    
    public List<Marca> getAllMarcas()
    {
        return daoMarca.getAll();
    }
    
    public List<Cor> getAllCores()
    {
        return daoCor.getAll();
    }
    
    public List<Modelo> getModelosByMarca(Object value)
    {
        return daoModelo.listAllModelosByMarca(value);
    }
}
