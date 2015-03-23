/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author info1
 */
@Entity
public class ItemPedido implements Serializable
{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long itemPedidoId;
    
    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;
    
    @OneToOne
    @JoinColumn(name = "veiculoPlaca")
    private Veiculo veiculo;
    
    private BigDecimal precoItem;
}
