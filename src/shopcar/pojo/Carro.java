/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.pojo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;


/**
 *
 * @author Mandy Grimm
 */
@Entity
public class Carro extends VeiculoPassageiro implements Serializable
{
    private static final Long serialVersionUID = 1L;
}
