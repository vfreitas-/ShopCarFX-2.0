///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package shopcar.factories;
//
//import javax.enterprise.inject.Produces;
//import shopcar.pojo.Caminhao;
//import shopcar.pojo.Caminhonete;
//import shopcar.pojo.Carro;
//import shopcar.pojo.Moto;
//import shopcar.pojo.Onibus;
//import shopcar.pojo.Veiculo;
//import shopcar.util.VeiculoType;
//
///**
// *
// * @author info1
// */
//public class VeiculoFactory
//{
//    private VeiculoType veiculoType;
//    
//    @Produces
//    @VeiculoType(VeiculoType.VEICULO)
//    public Veiculo createVeiculo()
//    {
//        return new Veiculo();
//    }
//    
//    @Produces
//    @VeiculoType(VeiculoType.MOTO)
//    public Veiculo createMoto()
//    {
//        return new Moto();
//    }
//    
//    @Produces
//    @VeiculosType(VeiculoType.CARRO)
//    public Veiculo createCarro()
//    {
//        return new Carro();
//    }
//    
//    @Produces
//    @VeiculosType(VeiculoType.ONIBUS)
//    public Veiculo createOnibus()
//    {
//        return new Onibus();
//    }
//    
//    @Produces
//    @VeiculosType(VeiculoType.CAMINHAO)
//    public Veiculo createCaminhao()
//    {
//        return new Caminhao();
//    }
//    
//    @Produces
//    @VeiculosType(VeiculoType.CAMINHONETE)
//    public Veiculo createCaminhonete()
//    {
//        return new Caminhonete();
//    }
//}
