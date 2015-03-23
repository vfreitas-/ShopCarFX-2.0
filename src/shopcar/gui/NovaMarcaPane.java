/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import shopcar.dao.MarcaDAO;
import shopcar.pojo.Marca;
import shopcar.util.BeginRefresh;

/**
 *
 * @author info1
 */
public class NovaMarcaPane extends GridPane implements IPane
{
    private final NovoVeiculoPane pane;
    private BeginRefresh refresh;
    
    private Marca marca;
    private MarcaDAO daoMarca;

    private Label titulo;
    private TextField txtMarca;
    private Button btnSalvar;
    private Button btnLimpar;
    
    private HBox hBox;
    
    public NovaMarcaPane(NovoVeiculoPane pane)
    {
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
        titulo = new Label("Nova Marca");
        txtMarca = new TextField();
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
        btnSalvar.setOnAction(s -> saveNewMarca());
        btnLimpar.setOnAction(s_1 -> clean());
    }

    @Override
    public void initLayout()
    {
        hBox.getChildren().addAll(btnSalvar, btnLimpar);

        add(titulo, 1, 1, 2, 1);
        add(new Label("Nome da Marca: "), 1, 2);
        add(txtMarca, 2, 2);
        add(hBox, 1, 6);
    }

    public void saveNewMarca()
    {
        try 
        {
            marca = new Marca();
            daoMarca = new MarcaDAO();
            refresh = new BeginRefresh();

            marca.setMarca(txtMarca.getText());

            daoMarca.save(marca);
            
            refresh.addListener(pane);
            refresh.notifyListeners(marca);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            DialogsHelper.createExceptionDialog(this, PrimaryStage.TITTLE, "", 
                    "Ocorreu um erro: " + e.getMessage(), e);
        }
    }
    
    public void clean()
    {
        txtMarca.setText("");
        txtMarca.requestFocus();
    }
}
