package shopcar.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javafx.beans.property.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Mandy Grimm
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Veiculo.listAllVeiculosTypes",
            query= "SELECT DISTINCT v.class FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.listVeiculoByModelo",
            query = "SELECT v FROM Veiculo v WHERE v.modelo.modelo LIKE :mod"),
    @NamedQuery(name = "Veiculo.listVeiculoByMarca",
            query = "SELECT v FROM Veiculo v WHERE v.marca.marca LIKE :marc"),
    @NamedQuery(name = "Veiculo.listVeiculoByAno",
            query = "SELECT v FROM Veiculo v WHERE v.anoFabricacao = :ano"),
    @NamedQuery(name = "Veiculo.listVeiculoByKm",
            query = "SELECT v FROM Veiculo v WHERE v.quilometragem = :km"),
    @NamedQuery(name = "Veiculo.listVeiculoByVendido",
            query = "SELECT v FROM Veiculo v WHERE v.vendido = :vendido"),
    @NamedQuery(name = "Veiculo.listAllVeiculoPlacasNotVendido",
            query = "SELECT v.placa FROM Veiculo v WHERE v.vendido = :vendido"),
    @NamedQuery(name = "Veiculo.listAllVeiculosPlacas",
            query = "SELECT v.placa FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.listVeiculoByPlaca",
            query = "SELECT v FROM Veiculo v WHERE v.placa LIKE :placa")
})
@Inheritance
@DiscriminatorColumn(name = "tipoVeiculo")
public class Veiculo implements Serializable
{
    private static final Long serialVersionUID = 1L;
    
    public static final String ALL_VEICULOS_TYPES = "Veiculo.listAllVeiculosTypes";
    public static final String VEICULO_BY_MODELO = "Veiculo.listVeiculoByModelo";
    public static final String VEICULO_BY_MARCA = "Veiculo.listVeiculoByMarca";
    public static final String VEICULO_BY_ANO = "Veiculo.listVeiculoByAno";
    public static final String VEICULO_BY_KM = "Veiculo.listVeiculoByKm";
    public static final String VEICULO_BY_VENDIDO = "Veiculo.listVeiculoByVendido";
    public static final String ALL_VEICULOS_PLACAS_NOT_VENDIDOS = "Veiculo.listAllVeiculoPlacasNotVendido";
    public static final String ALL_VEICULOS_PLACAS = "Veiculo.listAllVeiculosPlacas";
    public static final String VEICULO_BY_PLACA = "Veiculo.listVeiculoByPlaca";
    
    @Id
    @Pattern(regexp = "[a-zA-Z]{3}-\\d{4}$", message = 
            "A Placa do Veiculo deve obedecer o formato ABC-1234!")
    private String placa;
    
    @Pattern(regexp = "^[a-zA-Z0-9]{11}[0-9]{6}$", message = "O Número de Chassi"
            + " deve possuir 17 dígitos/letras, sendo os últimos 6 o número de"
            + " série!")
    @NotNull
    private String chassi;
    
    @Range(min= 2000, max= 2014, message = "O ano de frabricação deve estar "
            + "entre 2000 e 2014!")
    @NotNull
    private Integer anoFabricacao;
    
    @NotNull
    private Integer quilometragem;
    
    @Range(min = 1000, max = 100000, message = "O Valor do Veiculo deve ser de "
            + "R$ 1.000,00 até R$ 100.000,00")
    @NotNull
    private BigDecimal valorVeiculo;
    
    @Range(min = 50, max = 1000, message = "A Potencia em Cavalos do Veiculo "
            + "deve estar entre 50 e 1000")
    @NotNull
    private Integer potenciaCV;
    
    @Size(min = 1, message = "Preencha as Cilindradas do Veiculo!")
    @NotNull
    private String cilindradas;
    
    @Size(min = 1, message = "Preencha o Tipo de Carroceria do Veiculo!")
    @NotNull
    private String carroceria;
    
    @Range(min = 1, max = 8, message = "O Veiculo deve ter entre 1 e 8 eixos!")
    @NotNull
    private Integer numeroEixos;
    
    @Range(min = 2, max = 20, message = "O Veiculo deve possuir entre 2 e 20 "
            + "marchas!")
    @NotNull
    private Integer numeroMarchas;
    
    @NotNull
    private boolean vendido;
    
    @NotNull(message = "Escolha uma Cor!")
    @ElementCollection
    @ManyToOne
    @JoinColumn(name = "corId")
    private Cor cor;
    
    @NotNull(message = "Escolha uma Marca!")
    @ElementCollection
    @ManyToOne
    @JoinColumn(name = "marcaId")
    private Marca marca;
    
    @NotNull(message = "Escolha um Modelo!")
    @ElementCollection
    @ManyToOne
    @JoinColumn(name = "modeloId")
    private Modelo modelo;
    
    /*  */
    //<editor-fold defaultstate="collapsed" desc="JavaFX Properties">
    @Transient
    private final StringProperty placaProperty = new SimpleStringProperty();
    @Transient
    private final StringProperty chassiProperty = new SimpleStringProperty();
    @Transient
    private final StringProperty cilindradasProperty = new SimpleStringProperty();
    @Transient
    private final StringProperty carroceriaProperty = new SimpleStringProperty();
    @Transient
    private final IntegerProperty anoFabricacaoProperty = new SimpleIntegerProperty();
    @Transient
    private final IntegerProperty quilometragemProperty = new SimpleIntegerProperty();
    @Transient
    private final IntegerProperty potenciaCVProperty = new SimpleIntegerProperty();
    @Transient
    private final IntegerProperty numeroEixosProperty = new SimpleIntegerProperty();
    @Transient
    private final IntegerProperty numeroMarchasProperty = new SimpleIntegerProperty();
    @Transient
    private final DoubleProperty valorVeiculoProperty = new SimpleDoubleProperty();
    /**/
//</editor-fold>
    
    /* JavaFX Properties */
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
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
     * @return the chassi
     */
    public String getChassi()
    {
        return chassi;
    }
    
    /**
     * @param chassi the chassi to set
     */
    public void setChassi(String chassi)
    {
        this.chassi = chassi;
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
     * @return the valorVeiculo
     */
    public BigDecimal getValorVeiculo()
    {
        return valorVeiculo;
    }
    
    /**
     * @param valorVeiculo the valorVeiculo to set
     */
    public void setValorVeiculo(BigDecimal valorVeiculo)
    {
        this.valorVeiculo = valorVeiculo;
    }
    
    /**
     * @return the potenciaCV
     */
    public Integer getPotenciaCV()
    {
        return potenciaCV;
    }
    
    /**
     * @param potenciaCV the potenciaCV to set
     */
    public void setPotenciaCV(Integer potenciaCV)
    {
        this.potenciaCV = potenciaCV;
    }
    
    /**
     * @return the cilindradas
     */
    public String getCilindradas()
    {
        return cilindradas;
    }
    
    /**
     * @param cilindradas the cilindradas to set
     */
    public void setCilindradas(String cilindradas)
    {
        this.cilindradas = cilindradas;
    }
    
    /**
     * @return the carroceria
     */
    public String getCarroceria()
    {
        return carroceria;
    }
    
    /**
     * @param carroceria the carroceria to set
     */
    public void setCarroceria(String carroceria)
    {
        this.carroceria = carroceria;
    }
    
    /**
     * @return the numeroEixos
     */
    public Integer getNumeroEixos()
    {
        return numeroEixos;
    }
    
    /**
     * @param numeroEixos the numeroEixos to set
     */
    public void setNumeroEixos(Integer numeroEixos)
    {
        this.numeroEixos = numeroEixos;
    }
    
    /**
     * @return the numeroMarchas
     */
    public Integer getNumeroMarchas()
    {
        return numeroMarchas;
    }
    
    /**
     * @param numeroMarchas the numeroMarchas to set
     */
    public void setNumeroMarchas(Integer numeroMarchas)
    {
        this.numeroMarchas = numeroMarchas;
    }
    
    /**
     * @return the vendido
     */
    public boolean isVendido()
    {
        return vendido;
    }
    
    /**
     * @param vendido the vendido to set
     */
    public void setVendido(boolean vendido)
    {
        this.vendido = vendido;
    }
    
    /**
     * @return the cor
     */
    public Cor getCor()
    {
        return cor;
    }
    
    /**
     * @param cor the cor to set
     */
    public void setCor(Cor cor)
    {
        this.cor = cor;
    }
    
    /**
     * @return the marca
     */
    public Marca getMarca()
    {
        return marca;
    }
    
    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca)
    {
        this.marca = marca;
    }
    
    /**
     * @return the modelo
     */
    public Modelo getModelo()
    {
        return modelo;
    }
    
    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Modelo modelo)
    {
        this.modelo = modelo;
    }
//</editor-fold>
 
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.placa);
        hash = 29 * hash + Objects.hashCode(this.chassi);
        hash = 29 * hash + Objects.hashCode(this.anoFabricacao);
        hash = 29 * hash + Objects.hashCode(this.quilometragem);
        hash = 29 * hash + Objects.hashCode(this.valorVeiculo);
        hash = 29 * hash + Objects.hashCode(this.potenciaCV);
        hash = 29 * hash + Objects.hashCode(this.cilindradas);
        hash = 29 * hash + Objects.hashCode(this.carroceria);
        hash = 29 * hash + Objects.hashCode(this.numeroEixos);
        hash = 29 * hash + Objects.hashCode(this.numeroMarchas);
        hash = 29 * hash + (this.vendido ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.cor);
        hash = 29 * hash + Objects.hashCode(this.marca);
        hash = 29 * hash + Objects.hashCode(this.modelo);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.placa, other.placa))
        {
            return false;
        }
        if (!Objects.equals(this.chassi, other.chassi))
        {
            return false;
        }
        if (!Objects.equals(this.anoFabricacao, other.anoFabricacao))
        {
            return false;
        }
        if (!Objects.equals(this.quilometragem, other.quilometragem))
        {
            return false;
        }
        if (!Objects.equals(this.valorVeiculo, other.valorVeiculo))
        {
            return false;
        }
        if (!Objects.equals(this.potenciaCV, other.potenciaCV))
        {
            return false;
        }
        if (!Objects.equals(this.cilindradas, other.cilindradas))
        {
            return false;
        }
        if (!Objects.equals(this.carroceria, other.carroceria))
        {
            return false;
        }
        if (!Objects.equals(this.numeroEixos, other.numeroEixos))
        {
            return false;
        }
        if (!Objects.equals(this.numeroMarchas, other.numeroMarchas))
        {
            return false;
        }
        if (this.vendido != other.vendido)
        {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor))
        {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca))
        {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo))
        {
            return false;
        }
        return true;
    }
//</editor-fold>
}
