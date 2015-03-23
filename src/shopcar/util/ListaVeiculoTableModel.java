/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

/**
 *
 * @author info1
 */
public class ListaVeiculoTableModel
{
    private String placa;
    private String marca;
    private String modelo;
    private Integer quilometragem;
    private Integer anoFabricacao;
    private String vendido;

    public ListaVeiculoTableModel(String placa, String marca, String modelo
            , Integer quilometragem, Integer anoFabricacao, String vendido)
    {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.quilometragem = quilometragem;
        this.anoFabricacao = anoFabricacao;
        this.vendido = vendido;
    }
    
    /**
     * @return the placa
     */
    public String getPlaca()
    {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    /**
     * @return the marca
     */
    public String getMarca()
    {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo()
    {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    /**
     * @return the quilometragem
     */
    public Integer getQuilometragem()
    {
        return quilometragem;
    }

    /**
     * @param quilometragem the quilometragem to set
     */
    public void setQuilometragem(Integer quilometragem)
    {
        this.quilometragem = quilometragem;
    }

    /**
     * @return the anoFabricacao
     */
    public Integer getAnoFabricacao()
    {
        return anoFabricacao;
    }

    /**
     * @param anoFabricacao the anoFabricacao to set
     */
    public void setAnoFabricacao(Integer anoFabricacao)
    {
        this.anoFabricacao = anoFabricacao;
    }

    /**
     * @return the vendido
     */
    public String getVendido()
    {
        return vendido;
    }

    /**
     * @param vendido the vendido to set
     */
    public void setVendido(String vendido)
    {
        this.vendido = vendido;
    }
}
