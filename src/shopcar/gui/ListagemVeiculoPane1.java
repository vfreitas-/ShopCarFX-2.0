/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import shopcar.dao.VeiculoDAO;
import shopcar.pojo.Veiculo;
import shopcar.util.ListagemType;

/**
 *
 * @author Vitor Freitas
 */
public class ListagemVeiculoPane1 extends GridPane implements IPane
{
    private VeiculoDAO daoVeiculo;
    private ObservableList<Veiculo> veiculos;
    private ObservableList<ListagemType> listagemTypes;
    
    private Label titulo;
    private TableView<Veiculo> listaTableView;
    private TableColumn<Veiculo, String> colPlaca;
    private TableColumn<Veiculo, String> colMarca;
    private TableColumn<Veiculo, String> colModelo;
    private TableColumn<Veiculo, Integer> colKm;
    private TableColumn<Veiculo, Integer> colAnoFabricacao;
    private TableColumn<Veiculo, Boolean> colVendido;
    
    private Pagination pag;
    private ComboBox cmbSearch;
    private TextField txtSearch;
    
    private VBox vBox;
    private HBox hBox;
    
    public ListagemVeiculoPane1()
    {
        this.init();
    }

    @Override
    public void initNodeProperties()
    {
        setVgap(15);
        setHgap(20);
    }

    @Override
    public void initComponents()
    {
        listagemTypes = FXCollections.observableArrayList(ListagemType.asArray()); 
        
        titulo = new Label("Listagem de Veiculos");
        titulo.setId("title");
        
        cmbSearch = new ComboBox(listagemTypes);
        listaTableView = new TableView();
        pag = new Pagination(); 
        txtSearch = new TextField();
        hBox = new HBox();
        hBox.setSpacing(15);
        
        colPlaca = new TableColumn<>("Placa");
        colMarca = new TableColumn<>("Marca");
        colModelo = new TableColumn<>("Modelo");
        colKm = new TableColumn<>("KM");
        colAnoFabricacao = new TableColumn<>("Ano Fabric.");
        colVendido = new TableColumn<>("Vendido");
        
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colKm.setCellValueFactory(new PropertyValueFactory<>("quilometragem"));
        colAnoFabricacao.setCellValueFactory(new PropertyValueFactory<>("anoFabricacao"));
        colVendido.setCellValueFactory(new PropertyValueFactory<>("vendido"));
        
//        colVendido.setCellFactory(column -> new TableCell<Veiculo, Boolean>()
//        {
//            @Override
//            public void updateItem(Boolean check, boolean empty) 
//            {
//                super.updateItem(check, empty);
//                if (check == null || empty) 
//                {
//                    setGraphic(null);
//                } 
//                else 
//                {
//                    System.out.println("AQUii " + check);
//                    CheckBox box = new CheckBox();
//                    BooleanProperty checked = (BooleanProperty)column.getCellObservableValue(getIndex());
//                    System.out.println("dois " );
//                    box.setSelected(checked.get());
//                    box.selectedProperty().bindBidirectional(checked);
//                    setGraphic(box);
//                }
//            }
//        });  
//        colVendido.setCellFactory((TableColumn<Veiculo, Boolean> param) -> 
//        {
//            return new shopcar.gui.nodes.CheckBoxTableCell<>();
//        });
        
        colVendido.setCellFactory(CheckBoxTableCell.forTableColumn(colVendido));
        
        listaTableView.getColumns().addAll(colPlaca, colMarca, 
                colModelo, colKm ,colAnoFabricacao , colVendido);
        
        listaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        listaTableView.setPlaceholder(new Label("Não há veiculos na condição buscada!"));
        
        loadTable();
    }

    @Override
    public void initListeners()
    {
        cmbSearch.setOnAction( s -> 
        {
            doSearchCmbSearchEvent();
            txtSearch.requestFocus();
            txtSearch.selectAll();
        });
        txtSearch.setOnAction(s_1 -> doSearchTxtSearchEvent());
    }

    @Override
    public void initLayout()
    {
        hBox.getChildren().addAll(new Label("Pesquisar, por: "), cmbSearch,
                new Label("Palavra-Chave: "), txtSearch);
        
        add(titulo, 2, 2, 2, 2);
        add(new Separator(), 2, 4, 1, 1);
        add(hBox, 2, 5, 1, 1);
        add(listaTableView, 2, 6, 1, 1);
        //add(pag, 2, 7, 1, 1);
    }
    
    private void loadTable()
    {
        try
        {
            daoVeiculo = new VeiculoDAO();
            veiculos = FXCollections.observableArrayList(
                    daoVeiculo.getAll());
            listaTableView.setItems(veiculos);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void doSearchTxtSearchEvent()
    {
        try 
        {
            List<Veiculo> tmpVeiculos = new ArrayList<>();
            switch( (ListagemType) cmbSearch.getSelectionModel().getSelectedItem())
            {       
                case ANO: 
                        tmpVeiculos = txtSearch.getText().isEmpty() ? 
                                daoVeiculo.getAll() : 
                                daoVeiculo.listVeiculoByAno(Integer
                                        .parseInt( txtSearch.getText() ));
                    break;
                    
                case MARCA: tmpVeiculos = daoVeiculo
                        .listVeiculoByMarca(txtSearch.getText());
                    break;
                    
                case MODELO: tmpVeiculos = daoVeiculo
                        .listVeiculoByModelo(txtSearch.getText());
                    break;
                    
                case QUILOMETRAGEM:  
                        tmpVeiculos = txtSearch.getText().isEmpty() ? 
                                daoVeiculo.getAll() : 
                                daoVeiculo.listVeiculoByKm(Integer
                                        .parseInt( txtSearch.getText() ));
                    break;
            }
            
            veiculos = FXCollections.observableArrayList(tmpVeiculos);
            listaTableView.setItems(veiculos);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
    
    private void doSearchCmbSearchEvent()
    {
        try
        {
            List<Veiculo> tmpVeiculos = new ArrayList<>();
            switch( (ListagemType) cmbSearch.getSelectionModel().getSelectedItem())
            {
                case NONE: tmpVeiculos = daoVeiculo.getAll();
                    break;
                    
                case DISPONIVEL: tmpVeiculos = daoVeiculo.listVeiculoByVendido();
                    break;
            }
            
            veiculos = FXCollections.observableArrayList(tmpVeiculos);
            listaTableView.setItems(veiculos);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
