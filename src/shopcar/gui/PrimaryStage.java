
package shopcar.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PrimaryStage extends Application
{   
    protected static final String TITTLE = "# ShopCar 2.00 #";
    protected static final String RESOURCES = "/resources/";
    protected static final Integer WIDTH = 900;
    protected static final Integer HEIGHT = 650;
    protected static final String DECIMAL_FORMAT = "#,##0.00";
    protected static final String STYLESHEET = "/shopcar/gui/css/estiloFx.css";
    
    private TabPane tabPane;
    private NovoVeiculoTab novoVeiculoTab;
    private ListagemVeiculoTab listaVeiculoTab;
    private FichaVeiculoTab fichaVeiculoTab;
    private VendaVeiculoTab vendaVeiculoTab;
    private AboutTab aboutTab;
    private Tooltip tipTabNovo;
    private Tooltip tipTabLista;
    private Tooltip tipTabFicha;
    private Tooltip tipTabVenda;
    private Image img;
    private BorderPane borderPane;
    private Scene scene;
    private static Stage stage;
    
    private BoasVindasNode t;
    private Group root;
    
    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;

        initComponents();
        
        scene = new Scene(root);
        scene.getStylesheets().add(STYLESHEET);
        
        stage.setTitle(TITTLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(RESOURCES + "shopcar1.png"));
        stage.show();
        
        //Seta os tamanhos do stage
        stage.setMaxHeight(HEIGHT);
        stage.setMinHeight(HEIGHT);
        stage.setMaxWidth(WIDTH);
        stage.setMinWidth(WIDTH);
    }
    
    public void initComponents()
    {
        tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);

        root = new Group();   
        
        novoVeiculoTab = new NovoVeiculoTab();
        listaVeiculoTab = new ListagemVeiculoTab();
        fichaVeiculoTab = new FichaVeiculoTab();
        vendaVeiculoTab = new VendaVeiculoTab();
        aboutTab = new AboutTab();
        
        tabPane.getTabs().addAll(novoVeiculoTab, listaVeiculoTab, 
                fichaVeiculoTab, aboutTab);
        tabPane.getSelectionModel().select(aboutTab);
        
        tabPane.setMinSize(WIDTH, HEIGHT);
        tabPane.setMaxSize(WIDTH, HEIGHT);
        
        /* Mudar */
        t = new BoasVindasNode();
        /* Mudar */
        
        root.getChildren().addAll(tabPane, t);
        root.setAutoSizeChildren(true);
    }
}
