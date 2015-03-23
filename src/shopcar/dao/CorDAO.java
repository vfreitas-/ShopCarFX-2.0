/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.dao;

import shopcar.pojo.Cor;

/**
 *
 * @author Aluno
 */
public class CorDAO extends JpaDAO<Cor>
{
    public CorDAO()
    {
        super(Cor.class);
    }
}
