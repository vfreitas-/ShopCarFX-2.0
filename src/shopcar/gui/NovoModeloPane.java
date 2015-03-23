/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import shopcar.dao.ModeloDAO;
import shopcar.pojo.Marca;
import shopcar.pojo.Modelo;
import shopcar.util.BeginRefresh;

/**
 *
 * @author info1
 */
public class NovoModeloPane extends GridPane implements IPane
{
    private final Marca marca;
    private final NovoVeiculoPane pane;
    private BeginRefresh refresh;
    
    private Modelo modelo;
    private ModeloDAO daoModelo;
    
    private Label titulo;
    private TextField txtModelo;
    private TextField txtVersao;
    private Button btnSalvar;
    private Button btnLimpar;
    
    private HBox hBox;
    
    public NovoModeloPane(Marca marca, NovoVeiculoPane pane)
    {
        this.marca = marca;
        this.pane = pane;
        init();
    }
    
    @Override
    public void initNodeProperties()
    {
        setId("fundo-padrao");
        setPrefSize(500, 300);
        setVgap(10);
        setHgap(20);
    }
    
    @Override
    public void initComponents()
    {
        titulo = new Label("Novo Modelo");
        txtModelo = new TextField();
        txtVersao = new TextField();
        btnSalvar = new Button("Salvar!");
        btnLimpar = new Button("Limpar!");
        
        hBox = new HBox();
        hBox.setSpacing(15);
        
        titulo.setId("title");
        btnSalvar.setId("dark-blue");
        btnLimpar.setId("dark-blue");
    }

    @Override
    public void initListeners()
    {
        btnSalvar.setOnAction(s -> saveNewModelo());
        btnLimpar.setOnAction(s_1 -> clean());
    }

    @Override
    public void initLayout()
    {
        hBox.getChildren().addAll(btnSalvar, btnLimpar);
        
        add(titulo, 1, 1, 2, 1);
        add(new Label("Marca: "), 1, 2);
        add(new Label(marca.getMarca()), 2, 2);
        add(new Label("Nome do Modelo: "), 1, 3);
        add(txtModelo, 2, 3);
        add(new Label("Versão do Modelo: "), 1, 4);
        add(txtVersao, 2, 4);
        add(hBox, 1, 6);
    }
    
    private void saveNewModelo()
    {
        try
        {
            modelo = new Modelo();
            daoModelo = new ModeloDAO();
            refresh = new BeginRefresh();
            
            modelo.setMarca(marca);
            modelo.setModelo(txtModelo.getText());
            modelo.setVersao(txtVersao.getText());
            
            daoModelo.save(modelo);
            
            refresh.addListener(pane);
            refresh.notifyListeners(modelo);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            DialogsHelper.createExceptionDialog(this, PrimaryStage.TITTLE, "", 
                    "Ocorreu um erro: " + e.getMessage(), e);
        }
    }
    
    private void clean()
    {
        txtModelo.setText("");
        txtVersao.setText("");
        txtModelo.requestFocus();
    }
}
