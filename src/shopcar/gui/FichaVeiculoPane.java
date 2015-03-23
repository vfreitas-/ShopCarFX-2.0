/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialog;
import shopcar.dao.VeiculoDAO;
import static shopcar.gui.PrimaryStage.RESOURCES;
import shopcar.pojo.*;
import shopcar.pojo.Veiculo;
import shopcar.util.FichaVeiculoTableModel;

/**
 *
 * @author Vitor Freitas
 */
public class FichaVeiculoPane extends GridPane implements IPane
{
    
    private Label titulo;
    private TextField txtPlaca; 
    private ObservableList<Veiculo> veiculos;
    private VeiculoDAO daoVeiculo;
    private TableView<Veiculo> fichaTableView;
    private TableColumn<Veiculo, String> colPlaca;
    private TableColumn<Veiculo, String> colMarca;
    private TableColumn<Veiculo, BigDecimal> colModelo;
    private TableColumn<Veiculo, Boolean> colAction;
    private TableColumn colVar;
    private TableColumn colDesc;

    public FichaVeiculoPane()
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
        titulo = new Label("Ficha do Veiculo");
        titulo.setId("title");
        
        fichaTableView = new TableView<>();
        txtPlaca = new TextField();
        txtPlaca.setPrefColumnCount(8);       
        
        colPlaca = new TableColumn<>("Placa");
        colMarca = new TableColumn<>("Marca");
        colModelo = new TableColumn<>("Modelo");
        colAction = new TableColumn<>("Ações");
        
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAction.setCellValueFactory(s -> 
                new SimpleBooleanProperty(s.getValue() != null));
        colAction.setCellFactory(s -> new ButtonCell(fichaTableView));
        
        fichaTableView.getColumns().addAll(colPlaca, colMarca, colModelo
                , colAction);
        
        fichaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fichaTableView.setPlaceholder(new Label("Não há veiculos com essa placa!"));
    }

    @Override
    public void initListeners()
    {
        txtPlaca.setOnAction( s -> doSearch() );
    }

    @Override
    public void initLayout()
    {
        add(titulo, 2, 2, 20, 2);
        add(new Separator(), 2, 4, 20, 1);
        add(new Label("Placa do Veiculo: "), 2, 5, 1, 1);
        add(txtPlaca, 3, 5, 2, 1);
        add(fichaTableView, 2, 6, 20, 1);
    }
    
    private void doSearch()
    {
        try
        {
            daoVeiculo = new VeiculoDAO();
            veiculos = FXCollections.observableArrayList( daoVeiculo
                    .listVeiculoByPlaca( txtPlaca.getText() ) );
            fichaTableView.setItems( veiculos );
            colAction.setCellFactory(s -> new ButtonCell(fichaTableView));
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void update()
    {
        fichaTableView.getItems().clear();
        daoVeiculo = new VeiculoDAO();
        veiculos = FXCollections.observableArrayList( daoVeiculo
                    .getAll() );
        colAction.setCellFactory(s -> new ButtonCell(fichaTableView));
        fichaTableView.setItems( veiculos );
    }
    
    private ObservableList<FichaVeiculoTableModel> getField_ValueList(Veiculo v)
    {
        List<FichaVeiculoTableModel> subTable  = new ArrayList();
        subTable.add(new FichaVeiculoTableModel("Tipo de Veiculo", v.getClass()
                .getSimpleName()));
        subTable.add(new FichaVeiculoTableModel("Placa do Veiculo", v.getPlaca()));
        subTable.add(new FichaVeiculoTableModel("Número de Chassi", v.getChassi()));
        subTable.add(new FichaVeiculoTableModel("Ano de Fabricação", v.getAnoFabricacao()
                .toString()));
        subTable.add(new FichaVeiculoTableModel("Marca", v.getMarca().getMarca()));
        subTable.add(new FichaVeiculoTableModel("Modelo", v.getModelo().getModelo()));
        subTable.add(new FichaVeiculoTableModel("Modelo Versão", v.getModelo()
                .getVersao()));
        subTable.add(new FichaVeiculoTableModel("Cor", v.getCor().getCor()));
        subTable.add(new FichaVeiculoTableModel("Carroceria", v.getCarroceria()));
        subTable.add(new FichaVeiculoTableModel("Cilindradas", v.getCilindradas()));
        subTable.add(new FichaVeiculoTableModel("Quilometragem", v.getQuilometragem()
                .toString()));
        subTable.add(new FichaVeiculoTableModel("Potencia em CV", v.getPotenciaCV()
                .toString()));
        subTable.add(new FichaVeiculoTableModel("Número Eixos", v.getNumeroEixos()
                .toString()));
        subTable.add(new FichaVeiculoTableModel("Número Marchas", v.getNumeroMarchas()
                .toString()));
        subTable.add(new FichaVeiculoTableModel("Valor do Veiculo", v.getValorVeiculo()
                .toString()));
        
        if(v instanceof VeiculoTransporte)
            subTable.add(new FichaVeiculoTableModel("Capacidade Máxima de Carga"
                    , ((VeiculoTransporte)v).getCapcMaxCarga().toString() ));
        else if(v instanceof VeiculoPassageiro)
        {
            subTable.add(new FichaVeiculoTableModel("Número de Assentos", ((VeiculoPassageiro)v)
                    .getNumAssentos().toString()));
            subTable.add(new FichaVeiculoTableModel("Número de Portas", ((VeiculoPassageiro)v)
                    .getNumPortas().toString()));
        }
        
        return FXCollections.observableArrayList(subTable);
    }
    
    private class ButtonCell extends TableCell<Veiculo, Boolean>
    {
        final Button cellButton = new Button("Ficha");
        final Button vendaCellbutton = new Button("Vender");
        
        private final HBox hBox;
        private Veiculo selectedVeiculo;

        ButtonCell(final TableView tableView)
        {
            cellButton.setId("button-cell");
            vendaCellbutton.setId("button-cell");

            vendaCellbutton.setOnAction(s ->
            {
                try
                {
                    daoVeiculo = new VeiculoDAO();
                    
                    selectedVeiculo.setVendido(true);
                    
                    daoVeiculo.save(selectedVeiculo);
                    
                    String masthead = "Veiculo: "+ selectedVeiculo.getPlaca() +
                            " | " + " Preço: R$" + selectedVeiculo.getValorVeiculo().toString()
                            + " ";
                    boolean response;
                    response = DialogsHelper.createQuestionDialog(this.getParent()
                            , PrimaryStage.TITTLE, masthead, "Deseja Vender este"
                                    + " Veiculo?", Dialog.ACTION_YES
                            , Dialog.ACTION_YES, Dialog.ACTION_NO);
                    
                    if(response)
                    {
                        DialogsHelper.createInfoDialog(this.getParent(), PrimaryStage.TITTLE
                                , "", "O Veiculo ["+ selectedVeiculo.getPlaca() + 
                                        "] foi vendido com sucesso!");
                    }
                    else
                        return;
                    update();
                } 
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            });      
            
            cellButton.setOnAction(s ->
            {
                ObservableList<FichaVeiculoTableModel> subTableList 
                        = getField_ValueList(selectedVeiculo);
                
                TableColumn colField = new TableColumn("Campo");
                colField.setCellValueFactory(new PropertyValueFactory("field"));
                
                TableColumn colValue = new TableColumn("Valor");
                colValue.setCellValueFactory(new PropertyValueFactory("value"));
                
                TableView<FichaVeiculoTableModel> subTable = new TableView<>();
                subTable.setItems(subTableList);
                subTable.getColumns().addAll(colField, colValue);
                subTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                subTable.setPlaceholder(new Label("Não há veiculos com essa placa!"));
                
                Stage tableDialog = new Stage();
                tableDialog.initModality(Modality.APPLICATION_MODAL);
                tableDialog.getIcons().add(new Image(RESOURCES + "shopcar1.png"));
                
                VBox vBox = new VBox();
                vBox.getChildren().addAll(new Label("Veiculo"), subTable);
                vBox.setAlignment(Pos.CENTER);
                vBox.setPadding(new Insets(10));
                vBox.setPrefSize(400, 400);
                
                vBox.autosize();
                
                Scene tableDialogScene = new Scene(vBox);
                tableDialogScene.setFill(null);
                tableDialogScene.getStylesheets().add(PrimaryStage.STYLESHEET);
                
                tableDialog.setScene(tableDialogScene);
                tableDialog.show();
            });
            
            hBox = new HBox(10);
            hBox.getChildren().addAll(cellButton, vendaCellbutton);
        }
        
        @Override
        protected void updateItem(Boolean t, boolean empty) 
        {
            super.updateItem(t, empty);
            if(!empty)
            {
                setGraphic(hBox);
                selectedVeiculo = (Veiculo) getTableRow().getItem();
                try
                {
                    if(selectedVeiculo.isVendido())
                        vendaCellbutton.setDisable(true);
                    else
                        vendaCellbutton.setDisable(false);
                } 
                catch (Exception e)
                {
                    System.out.println("");
                }
            }
        }
    }
}
