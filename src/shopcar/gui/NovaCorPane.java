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
import shopcar.dao.CorDAO;
import shopcar.pojo.Cor;
import shopcar.util.BeginRefresh;

/**
 *
 * @author info1
 */
public class NovaCorPane extends GridPane implements IPane
{
    private final NovoVeiculoPane pane;
    
    private BeginRefresh refresh;
    
    private Cor cor;
    private CorDAO daoCor;
    
    private Label titulo;
    private TextField txtCor;
    private Button btnSalvar;
    private Button btnLimpar;
    
    private HBox hBox;
    
    public NovaCorPane(NovoVeiculoPane pane)
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
        titulo = new Label("Nova Cor");
        txtCor = new TextField();
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
        btnSalvar.setOnAction(s -> saveNewCor());
        btnLimpar.setOnAction(s_1 -> clean());
    }

    @Override
    public void initLayout()
    {
        hBox.getChildren().addAll(btnSalvar, btnLimpar);
        
        add(titulo, 1, 1);
        add(new Label("Nome da Cor: "), 1, 2);
        add(txtCor, 2, 2);
        add(hBox, 1, 6);
    }
    
    private void saveNewCor()
    {
        try
        {
            cor = new Cor();
            daoCor = new CorDAO();
            refresh = new BeginRefresh();

            cor.setCor(txtCor.getText());

            daoCor.save(cor);

            refresh.addListener(pane);
            refresh.notifyListeners(cor); 
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
        txtCor.setText("");
        txtCor.requestFocus();
    }
}
