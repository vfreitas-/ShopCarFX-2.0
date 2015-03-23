/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.dao;

import java.util.List;
import shopcar.pojo.Modelo;

/**
 *
 * @author Aluno
 */
public class ModeloDAO extends JpaDAO<Modelo>
{
    public ModeloDAO()  
    { 
        super(Modelo.class);
    }
    
    public List<Modelo> listAllModelosByMarca(Object value)
    {
        List<Modelo> resultList = getByRestriction(Modelo.MODELOS_BY_MARCA,
                "marca" , value);
        
        return resultList;
    }
}
