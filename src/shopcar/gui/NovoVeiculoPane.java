/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import jfxtras.labs.scene.control.BigDecimalField;
import shopcar.controller.NovoVeiculoController;
import shopcar.controller.Validator;
import shopcar.dao.VeiculoDAO;
import shopcar.gui.nodes.NumberTextField;
import shopcar.gui.nodes.NumberTextFieldTest;
import shopcar.pojo.*;
import shopcar.properties.VeiculoProperty;
import shopcar.util.RefreshListener;
import shopcar.util.VeiculoSuperType;
import static shopcar.util.VeiculoSuperType.PASSAGEIRO;
import static shopcar.util.VeiculoSuperType.TRANSPORTE;
import shopcar.util.VeiculoType;

/**
 *
 * @author Vitor Freitas
 */
public class NovoVeiculoPane extends GridPane implements IPane, RefreshListener
{   
    private final VeiculoProperty vp = new VeiculoProperty();

    private Veiculo veiculo;
    private VeiculoDAO daoVeiculo;
    private Validator<Veiculo> validaVeiculo;
    
    private Marca marca;
    private Modelo modelo;
    private Cor cor;
    
    private NovoVeiculoController controller;
    
    private Label titulo;
    private Button salvar;
    private Button limpar;
    private Button addMarca;
    private Button addModelo;
    private Button addCor;
    
    private HBox hBox;
    
    private Accordion accord;
    private TitledPane paneGerais;
    private TitledPane paneComuns;
    private TitledPane paneEspecificas;
    private GridPane gridGerais;
    private GridPane gridComuns;
    
    private ComboBox<Marca> cmbMarcas;
    private ComboBox<Modelo> cmbModelos;
    private ComboBox<Cor> cmbCores;
    private ComboBox<VeiculoType> cmbTipos;
    private TextField txtPlaca;
    private TextField txtChassi;
    private TextField txtCilindradas;
    private TextField txtCarroceria;
    private BigDecimalField spinnerQuilometragem;
    private BigDecimalField spinnerPotenciaCV;
    private BigDecimalField spinnerNumeroEixos;
    private BigDecimalField spinnerNumeroMarchas;
    private BigDecimalField spinnerAnoFabricacao;
    private NumberTextField nmbAnoFabricacao;
    private BigDecimalField spinnerCapcMaxCarga;
    private BigDecimalField spinnerNumAssentos;
    private BigDecimalField spinnerNumPortas;
    private BigDecimalField spinnerValor;
    
    private NumberTextFieldTest t;
   
    private ObservableList<Marca> marcas;
    private ObservableList<Modelo> modelos;
    private List<Modelo> listModelos;
    private ObservableList<Cor> cores;  
    private ObservableList<VeiculoType> veiculoTypes;
            
    
    private NewPopOver popMarca;
    private NewPopOver popModelo;
    private NewPopOver popCor;
    private NewPopOver warningPlaca;
    
    private boolean isPlacaRight;
    
    public NovoVeiculoPane()
    {
        init(); 
    }
    
    @Override
    public void initNodeProperties()
    {
        setVgap(10);
        setHgap(20); 
    }
    
    @Override
    public void initComponents()
    {  
        veiculoTypes = FXCollections.observableArrayList(VeiculoType.asArray());
        
        accord = new Accordion();
        paneGerais = new TitledPane();
        paneGerais.setText("Dados Gerais do Veiculo");
        paneComuns = new TitledPane();
        paneComuns.setText("Dados Comuns do Veiculo");
        paneEspecificas = new TitledPane();
        paneEspecificas.setText("Dados Especificos do Veiculo");
        
        cmbTipos = new ComboBox(veiculoTypes);
        cmbMarcas = new ComboBox();
        cmbModelos = new ComboBox();
        cmbCores = new ComboBox();
        txtPlaca = new TextField();
        txtPlaca.setPromptText("ABC-1234");
        txtChassi = new TextField();
        txtChassi.setPromptText("9BGRD08X04G117974");
        txtCilindradas = new TextField();
        txtCilindradas.setPromptText("1500cc/1.5L");
        txtCarroceria = new TextField();
        txtCarroceria.setPromptText("Sedan/Pick-Up/Hatchback");
        nmbAnoFabricacao = new NumberTextField();
        nmbAnoFabricacao.setBegin(2000);
        
        spinnerQuilometragem = new BigDecimalField(BigDecimal.ZERO,
                new BigDecimal(10000), NumberFormat.getInstance());
        spinnerNumeroEixos = new BigDecimalField(BigDecimal.ZERO);
        spinnerNumeroMarchas = new BigDecimalField(BigDecimal.ZERO); 
        spinnerPotenciaCV = new BigDecimalField(BigDecimal.ZERO
                , new BigDecimal(100), NumberFormat.getInstance());
        spinnerAnoFabricacao = new BigDecimalField(new BigDecimal(2012));
        spinnerValor = new BigDecimalField(BigDecimal.ZERO, 
                new BigDecimal(1000), new DecimalFormat(PrimaryStage.DECIMAL_FORMAT));
        
        spinnerNumAssentos = new BigDecimalField(BigDecimal.ZERO);//!
        spinnerNumPortas = new BigDecimalField(BigDecimal.ZERO);//!
        spinnerCapcMaxCarga = new BigDecimalField(BigDecimal.ZERO, //!
                new BigDecimal(5000), new DecimalFormat(PrimaryStage.DECIMAL_FORMAT));
        
        salvar = new Button();
        salvar.setDisable(true);
        limpar = new Button();
        limpar.setText("Limpar!");
        limpar.setId("dark-blue");
        
        addMarca = new Button();
        addMarca.setId("btn-add");
        addModelo = new Button();
        addModelo.setId("btn-add");
        addModelo.setDisable(true);
        addCor = new Button();
        addCor.setId("btn-add");
        
        popMarca = new NewPopOver(new NovaMarcaPane(this));
        
        popCor = new NewPopOver(new NovaCorPane(this));  
        
        warningPlaca = new NewPopOver(new WarningPane("Já existe um Veiculo com"
                + " essa Placa!"));
        warningPlaca.setDetachable(false);
        warningPlaca.setDetached(false);
        warningPlaca.setAutoHide(true);
        
        hBox = new HBox();
        hBox.setSpacing(15);
        hBox.getChildren().addAll(salvar, limpar);

        titulo = new Label("Novo Veiculo");
        titulo.setId("title");
        
        accord.getPanes().addAll(paneGerais, paneComuns, paneEspecificas);
        accord.setExpandedPane(paneGerais);

        carregaDados();
    }
    
    @Override
    public void initListeners()
    {
        salvar.setOnAction( s -> saveVeiculo() );
        limpar.setOnAction(s_1 -> clean());
        
        addMarca.setOnAction(s_2 -> popMarca.show(addMarca));
        
        addModelo.setOnAction(s_3 -> 
        {
            popModelo = new NewPopOver(new NovoModeloPane
                (
                    (Marca) cmbMarcas.getSelectionModel().getSelectedItem(),
                    this
                ));
            popModelo.show(addModelo);
        });
        
        addCor.setOnAction(s_4 -> popCor.show(addCor));
        
        cmbMarcas.setOnAction(s_6 -> updateModelos());
        
        cmbTipos.setOnAction(s_7 -> veiculoTypeChanged());
        
        txtPlaca.setOnKeyReleased((EventHandler<KeyEvent>) s -> txtPlacaKeyEvent(s));
        
        paneGerais.expandedProperty().addListener((ObservableValue<? extends Boolean> observable
                , Boolean oldValue, Boolean newValue) ->
        {
            if(newValue)
                txtPlaca.requestFocus();
        });
    }
    
    @Override
    public void initLayout()
    {        
        /* Dados Gerais TitledPane */
        gridGerais = new GridPane();
        gridGerais.setVgap(10);
        gridGerais.setHgap(20);
        gridGerais.add(new Label("Placa do Veiculo: "), 2, 1);
        gridGerais.add(txtPlaca, 3, 1);
        gridGerais.add(new Label("Tipo de Veiculo: "), 2, 2);
        gridGerais.add(cmbTipos, 3, 2);
        gridGerais.add(new Label("Marca do Veiculo: "), 2, 3);
        gridGerais.add(cmbMarcas, 3, 3);
        gridGerais.add(addMarca, 4, 3);
        gridGerais.add(new Label("Modelo do Veiculo: "), 2, 4);
        gridGerais.add(cmbModelos, 3, 4);
        gridGerais.add(addModelo, 4, 4);
        gridGerais.add(new Label("Cor do Veiculo: "), 2, 5);
        gridGerais.add(cmbCores, 3, 5);
        gridGerais.add(addCor, 4, 5);
        gridGerais.add(new Label("Chassi: "), 2, 6);
        gridGerais.add(txtChassi, 3, 6);
        paneGerais.setContent(gridGerais);                
        
        /* Dados Comuns TitledPane */
        gridComuns = new GridPane();
        gridComuns.setVgap(10);
        gridComuns.setHgap(20);
        gridComuns.add(new Label("Ano de Fabricação: "), 2, 2);
        gridComuns.add(nmbAnoFabricacao, 3, 2);
        gridComuns.add(new Label("Quilometragem: "), 2, 3);
        gridComuns.add(spinnerQuilometragem, 3, 3, 1, 1);
        gridComuns.add(new Label("Potencia em CV: "), 2, 4);
        gridComuns.add(spinnerPotenciaCV, 3, 4, 1, 1);
        gridComuns.add(new Label("Cilindradas: "), 2, 5);
        gridComuns.add(txtCilindradas, 3,5, 1, 1);
        gridComuns.add(new Label("Carroceria: "), 2, 6);
        gridComuns.add(txtCarroceria, 3, 6, 1, 1);
        gridComuns.add(new Label("Numero de Marchas: "), 2, 7);
        gridComuns.add(spinnerNumeroMarchas, 3, 7, 1, 1);
        gridComuns.add(new Label("Numero de Eixos: "), 2, 8);
        gridComuns.add(spinnerNumeroEixos, 3, 8, 1, 1);
        gridComuns.add(new Label("Valor do Veiculo: "), 2, 9);
        gridComuns.add(spinnerValor, 3, 9);
        paneComuns.setContent(gridComuns);
        
        add(titulo, 2, 2, 20, 2);
        add(new Separator(), 2, 4, 20, 1);
        add(accord, 2, 5, 20, 1);
        
        salvar.setText("Salvar!");
        salvar.setId("dark-blue");
        add(hBox, 3, 7, 3, 1);
    }
    
    @Override
    public void objectHasBeenSaved(Object obj)
    {
        DialogsHelper.createInfoDialog(this, PrimaryStage.TITTLE, null, 
                obj.getClass().getSimpleName() + " cadastrado(a) com sucesso!\n"
                        + "O Formulário foi atualizado!");
        if(obj.getClass() == Marca.class)
            updateMarcas();
        else if(obj.getClass() == Cor.class)
            updateCores();
        else if(obj.getClass() == Modelo.class)
            updateModelos();
    }
    
    private void carregaDados()
    {
        try
        {
            controller = new NovoVeiculoController();
            marcas = FXCollections.observableArrayList(controller.getAllMarcas()); 
            cmbMarcas.setItems(marcas);
            cores = FXCollections.observableArrayList(controller.getAllCores());
            cmbCores.setItems(cores);
            updateModelos();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void updateMarcas()
    {
        try
        {
            controller = new NovoVeiculoController();
            marcas = FXCollections.observableArrayList(controller.getAllMarcas()); 
            cmbMarcas.getSelectionModel().clearSelection();
            cmbMarcas.setItems(marcas);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void updateCores()
    {
        try
        {
            controller = new NovoVeiculoController();
            cores = FXCollections.observableArrayList(controller.getAllCores()); 
            cmbCores.getSelectionModel().clearSelection();
            cmbCores.setItems(cores);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void updateModelos()
    {
        
        if(cmbMarcas.getSelectionModel().getSelectedItem() == null)
                addModelo.setDisable(true);
            else
                addModelo.setDisable(false);
        
        try
        {
            modelos = FXCollections.observableArrayList(controller
                    .getModelosByMarca(cmbMarcas
                    .getSelectionModel().getSelectedItem()));
            
            cmbModelos.getItems().clear();
            cmbModelos.getSelectionModel().clearSelection();
            cmbModelos.setItems(modelos);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void txtPlacaKeyEvent(KeyEvent e)
    {
        isPlacaRight = true;
        if(txtPlaca.getText().length() > 8)
        {
            txtPlaca.setText(txtPlaca.getText().substring(0, 8));
            txtPlaca.positionCaret(8);
            warningPlaca.hide();
        }
        else if(txtPlaca.getText().length() == 8)
        {
            if(txtPlaca.getText().matches("[a-zA-Z]{3}-\\d{4}$"))
            {
                System.out.println(txtPlaca.getText());
                daoVeiculo = new VeiculoDAO();
                if(daoVeiculo.testPlaca(txtPlaca.getText().toUpperCase()))
                {
                    txtPlaca.setStyle("-fx-border-color: red;");
                    warningPlaca.show(txtPlaca);
                    isPlacaRight = false;
                }
                else
                {
                    txtPlaca.setStyle("-fx-border-color: green;");
                    isPlacaRight = true;
                }
            }
        }
        else
        {
            txtPlaca.setStyle("-fx-border-color: transparent;");
            warningPlaca.hide();
        }
    }
    
    private void saveVeiculo()
    {
        if(cmbTipos.getValue() == null)
        {
           DialogsHelper.createErrorDialog(this, PrimaryStage.TITTLE, null, 
                    "Escolha o Tipo de Veiculo!"); 
           return;
        }
        try
        {
            daoVeiculo = new VeiculoDAO();
            validaVeiculo = new Validator<>();
            
            if(isPlacaRight)
                veiculo.setPlaca(txtPlaca.getText().toUpperCase());
            else
            {
                DialogsHelper.createErrorDialog(this, PrimaryStage.TITTLE
                        , "", "Já existe um Veiculo com essa Placa!");
                return;
            }
            veiculo.setChassi(txtChassi.getText().toUpperCase());
            veiculo.setCarroceria(txtCarroceria.getText());
            veiculo.setCilindradas(txtCilindradas.getText());
            veiculo.setAnoFabricacao( Integer.parseInt(nmbAnoFabricacao.getText()) );
            veiculo.setCor( (Cor) cmbCores.getSelectionModel()
                    .getSelectedItem());
            veiculo.setMarca( (Marca) cmbMarcas.getSelectionModel()
                    .getSelectedItem());
            veiculo.setModelo( (Modelo) cmbModelos.getSelectionModel()
                    .getSelectedItem());
            veiculo.setNumeroEixos( spinnerNumeroEixos.getNumber()
                    .intValueExact() );
            veiculo.setNumeroMarchas( spinnerNumeroMarchas.getNumber()
                    .intValueExact());
            veiculo.setPotenciaCV( spinnerPotenciaCV.getNumber()
                    .intValueExact() );
            veiculo.setQuilometragem( spinnerQuilometragem.getNumber()
                    .intValueExact() );
            veiculo.setValorVeiculo( spinnerValor.getNumber() );
            
            if(veiculo instanceof VeiculoTransporte)
                ((VeiculoTransporte)veiculo).setCapcMaxCarga(spinnerCapcMaxCarga
                        .getNumber());
            else if(veiculo instanceof VeiculoPassageiro)
            {
                ((VeiculoPassageiro)veiculo).setNumAssentos(spinnerNumAssentos
                        .getNumber().intValueExact());
                ((VeiculoPassageiro)veiculo).setNumPortas(spinnerNumPortas
                        .getNumber().intValueExact());
            }
            
            String errors;
            errors = validaVeiculo.Validate(veiculo);
            if(errors != null)
            {
                DialogsHelper.createErrorDialog(this, PrimaryStage.TITTLE
                        , "Há alguns erros no formulário!", errors);
                return;
            }
            
            daoVeiculo.save(veiculo);
            
            DialogsHelper.createInfoDialog(this, PrimaryStage.TITTLE, null, 
                    "Veiculo cadastrado com sucesso!");
            
        } 
        catch (Exception e)
        {
            DialogsHelper.createExceptionDialog(this, PrimaryStage.TITTLE, "", 
                    "Ocorreu um erro: " + e.getMessage(), e);
        }
 
    }

    private void clean()
    {
        cmbTipos.setValue(null);
        cmbModelos.setValue(null);
        addModelo.setDisable(true);
        cmbMarcas.setValue(null);
        cmbCores.setValue(null);
        txtPlaca.setText("");
        txtPlaca.setStyle("-fx-border-color: transparent;");
        txtChassi.setText("");
        txtCarroceria.setText("");
        txtCilindradas.setText("");
        spinnerPotenciaCV.setNumber(BigDecimal.ZERO);
        spinnerQuilometragem.setNumber(BigDecimal.ZERO);
        spinnerNumeroEixos.setNumber(BigDecimal.ZERO);
        spinnerNumeroMarchas.setNumber(BigDecimal.ZERO);
        spinnerValor.setNumber(BigDecimal.ZERO);
        spinnerNumAssentos.setNumber(BigDecimal.ZERO);
        spinnerNumPortas.setNumber(BigDecimal.ZERO);
        spinnerCapcMaxCarga.setNumber(BigDecimal.ZERO);
        nmbAnoFabricacao.setText("2000");
        salvar.setDisable(true);
        
        accord.setExpandedPane(paneGerais);
        paneGerais.requestFocus();
    }
    
    private void veiculoTypeChanged()
    {   
        VeiculoType type = (VeiculoType) cmbTipos.getSelectionModel().getSelectedItem();
        veiculo = type.createInstance();
        
        setEspeficGridContent();
        
        salvar.setDisable(false);
        System.out.println(veiculo.getClass());
    }
    
    private void setEspeficGridContent()
    {
        GridPane gridEspc = new GridPane();
        gridEspc.setVgap(10);
        gridEspc.setHgap(20);
        
        VeiculoSuperType type = VeiculoSuperType.getVeiculoSuperType(veiculo);
        switch(type)
        {
            case TRANSPORTE:
                gridEspc.add(new Label("Capacidade Máxima de Carga(kg): "), 2, 2);
                gridEspc.add(spinnerCapcMaxCarga, 3, 2);
                break;
                
            case PASSAGEIRO:
                gridEspc.add(new Label("Número de Assentos: "), 2, 2);
                gridEspc.add(spinnerNumAssentos, 3, 2);
                gridEspc.add(new Label("Número de Portas: "), 2, 3);
                gridEspc.add(spinnerNumPortas, 3, 3);
                break;
                
            case MOTOCICLETA:
                break;
        }

        paneEspecificas.setContent(gridEspc);    
    }
    
    private void setNodeVisibleOn(Node n)
    {
        n.setVisible(true);
    }   
}
