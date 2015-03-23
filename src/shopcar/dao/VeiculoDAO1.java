/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.dao;

import java.util.List;
import javax.inject.Inject;
import shopcar.pojo.Veiculo;
import shopcar.qualifiers.MyArrayList;

/**
 *
 * @author Mandy Grimm
 */
public class VeiculoDAO1
{
    @Inject private JpaDAODI<Veiculo> daoVeiculo;
    @Inject @MyArrayList private List<String> placas;
    
    //<editor-fold defaultstate="collapsed" desc="Selects de Listagens">
    public List<Veiculo> listVeiculoByVendido()
    {
       List<Veiculo> resultList = daoVeiculo.getByRestriction
        (Veiculo.VEICULO_BY_VENDIDO, "vendido", false);
        return resultList;
    }
    
    public List<Veiculo> listVeiculoByModelo(String modelo)
    {
        List<Veiculo> resultList = daoVeiculo.getByRestriction
        (Veiculo.VEICULO_BY_MODELO, "mod", modelo);
        return resultList;
    }
    
    public List<Veiculo> listVeiculoByMarca(String marca)
    {
        List<Veiculo> resultList = daoVeiculo.getByRestriction
        (Veiculo.VEICULO_BY_MARCA, "marc", marca);
        return resultList;
    }
    
    public List<Veiculo> listVeiculoByKm(Integer km)
    {
        List<Veiculo> resultList = daoVeiculo.getByRestriction
        (Veiculo.VEICULO_BY_KM, "km", km);
        return resultList;
    }
    
    public List<Veiculo> listVeiculoByAno(Integer ano)
    {
        List<Veiculo> resultList = daoVeiculo.getByRestriction
        (Veiculo.VEICULO_BY_ANO, "ano", ano);
        return resultList;
    }
    
    public boolean testPlaca(String placa)
    {
        try
        {
            placas = daoVeiculo.getEntityManager()
                .createNamedQuery(Veiculo.ALL_VEICULOS_PLACAS)
                .getResultList();
        } 
        catch (Exception e)
        {
            System.err.println("Houve erro ao listar a placas! " + e.getMessage());
        }
        
        for(String s : placas) 
            if(s.equals(placa)) return true;
        return false;
    }
    
    public boolean testPlacaVendido(String placa)
    {
        try
        {
            placas = daoVeiculo.getEntityManager()
                    .createNamedQuery(Veiculo.ALL_VEICULOS_PLACAS_NOT_VENDIDOS)
                    .setParameter("vendido", false)
                    .getResultList();
        } 
        catch (Exception e)
        {
            System.err.println("Houve erro ao listar a placas! " + e.getMessage());
        }
        
        for(String s : placas)
            if(s.equals(placa)) return true;
        return false;
    }
//</editor-fold>
}