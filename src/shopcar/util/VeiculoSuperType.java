/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

import shopcar.pojo.Veiculo;
import shopcar.pojo.VeiculoMotocicleta;
import shopcar.pojo.VeiculoPassageiro;
import shopcar.pojo.VeiculoTransporte;

/**
 *
 * @author Vitor Freitas
 */
public enum VeiculoSuperType
{
    TRANSPORTE("VeiculoTransporte"),
    PASSAGEIRO("VeiculoPassageiro"),
    MOTOCICLETA("VeiculoMotocicleta");
    
    private final String descricao;

    private VeiculoSuperType(String descricao)
    {
        this.descricao = descricao;
    }
    
    public static VeiculoSuperType getVeiculoSuperType(Veiculo v)
    {
        if(v instanceof VeiculoTransporte)
            return TRANSPORTE;
        else if(v instanceof VeiculoPassageiro)
            return PASSAGEIRO;
        else if(v instanceof VeiculoMotocicleta)
            return MOTOCICLETA;
        return null;
    }
}
