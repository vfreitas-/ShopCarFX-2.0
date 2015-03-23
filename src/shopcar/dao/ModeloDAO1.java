/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.dao;

import java.util.List;
import javax.inject.Inject;
import shopcar.pojo.Modelo;
import shopcar.dao.JpaDAO;

/**
 *
 * @author Aluno
 */
public class ModeloDAO1
{
    @Inject private JpaDAO<Modelo> daoModelo;
    
    public ModeloDAO1()  { }
    
    public List<Modelo> listAllModelosByMarca(Object value)
    {
        List<Modelo> resultList = daoModelo.getByRestriction
        (Modelo.MODELOS_BY_MARCA, "marca" , value);
        
        return resultList;
    }
}
