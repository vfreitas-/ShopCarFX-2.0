/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.dao;

import shopcar.pojo.Marca;

/**
 *
 * @author Vitor Freitas
 */
public class MarcaDAO extends JpaDAO<Marca>
{
    public MarcaDAO()
    {
        super(Marca.class);
    }
}
