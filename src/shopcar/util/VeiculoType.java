/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shopcar.pojo.*;

/**
 *
 * @author Vitor Freitas
 */
public enum VeiculoType
{
    MOTO("Moto", Moto.class),
    CARRO("Carro", Carro.class),
    CAMINHAO("Caminhão", Caminhao.class),
    CAMINHONETE("Caminhonete", Caminhonete.class),
    ONIBUS("Ônibus", Onibus.class);

    private final String descricao;
    private final Class<? extends Veiculo> clazz;
    
    VeiculoType(String descricao, Class<? extends Veiculo> clazz )
    {
        this.descricao = descricao;
        this.clazz = clazz;
    }

    public static List<VeiculoType> asArray()
    {
        List<VeiculoType> list = new ArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Veiculo> T createInstance()
    {
        try 
        {
            return (T) this.clazz.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) 
        {
            // there's no default constructor.
            return null;
        }
    }
}
