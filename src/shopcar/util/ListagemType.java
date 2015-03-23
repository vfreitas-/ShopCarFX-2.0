/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Aluno
 */
public enum ListagemType 
{
    NONE("Listar Todos"),
    ANO("Ano"),
    MARCA("Marca"),
    MODELO("Modelo"),
    QUILOMETRAGEM("Quilometragem"),
    DISPONIVEL("Disponível");
    
    private final String descricao;
    
    ListagemType(String descricao)
    {
        this.descricao = descricao;
    }
    
    public static List<ListagemType> asArray()
    {
        List<ListagemType> list = new ArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }
    
}
