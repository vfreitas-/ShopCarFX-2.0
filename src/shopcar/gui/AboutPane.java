/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 *
 * @author info1
 */
public class AboutPane extends GridPane implements IPane 
{
    private Label titulo;
    private Label textAbout;
    private Accordion accord;
    private TitledPane tutotialPane;
    private GridPane gridTutorial;
    private Button btnTutotialNovo;
    private Button btnTutorialListagem;
    private Button btnTutorialFicha;
    private Button btnTutorialVenda;
    
    public AboutPane()
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
        titulo = new Label("Sobre/Ajuda ShopCar 2.0");
        titulo.setId("title");
        
        accord = new Accordion();
        tutotialPane = new TitledPane();
        gridTutorial = new GridPane();
        btnTutotialNovo = new Button("Tutorial Cadastro Veiculo");
        btnTutotialNovo.setId("btn-tutorial");
        btnTutorialListagem = new Button("Tutorial Listar Veiculos");
        btnTutorialListagem.setId("btn-tutorial");
        btnTutorialFicha = new Button("Tutorial Ver Ficha do Veiculo");
        btnTutorialFicha.setId("btn-tutorial");
        btnTutorialVenda = new Button("Tutorial Vender um Veiculo");
        btnTutorialVenda.setId("btn-tutorial");
        
        gridTutorial.setVgap(15);
        gridTutorial.setHgap(10);
                
        textAbout = new Label();
        
        textAbout.setText("Esse sistema foi desenvolvido por:\n\n"
                + "Vitor Freitas Buchalla - IFSP(126276-9)\n"
                + "Laura Sirianni - IFSP(126273-4)\n\n"
                + "     E tem como objetivo proporcionar todo o conforto necessário \n"
                + "para o gerenciamento do seu negócio de Venda de Veiculos.\n\n"
                + "     Esse sistema permite o cadastramento de novos veiculos, a \n"
                + "listagem dos veiculos cadastrados (por ano, marca, modelo, \n"
                + "km, e disponibilidade), a exibição da ficha completa dos \n"
                + "veiculos e a venda dos mesmos.");
        textAbout.setWrapText(true);
        //textAbout.setEditable(false);
        textAbout.setId("lbl-about");
    
        tutotialPane.setContent(gridTutorial);
        tutotialPane.setText("Dicas/Tutoriais do Sistema");
        tutotialPane.setExpanded(false);
        accord.getPanes().add(tutotialPane);
    }

    @Override
    public void initListeners()
    {
        btnTutotialNovo.setOnAction(s -> new TutorialPlayer("tutorial_cadastro.mp4"
                , getParent().getLayoutX(), getParent().getLayoutY()));
        btnTutorialListagem.setOnAction(s -> new TutorialPlayer("tutorial_listagem.mp4"
                , getParent().getLayoutX(), getParent().getLayoutY()));
        btnTutorialFicha.setOnAction(s -> new TutorialPlayer("tutorial_ficha.mp4"
                , getParent().getLayoutX(), getParent().getLayoutY()));
        btnTutorialVenda.setOnAction(s -> new TutorialPlayer("tutorial_venda.mp4"
                , getParent().getLayoutX(), getParent().getLayoutY()));
    }

    @Override
    public void initLayout()
    {
        gridTutorial.add(btnTutotialNovo, 2, 1, 2, 1);
        gridTutorial.add(btnTutorialListagem, 4, 1, 2, 1);
        gridTutorial.add(btnTutorialFicha, 2, 2, 2, 1);
        gridTutorial.add(btnTutorialVenda, 4, 2, 2, 1);
        
        add(titulo, 2, 2, 1, 2);
        add(new Separator(), 2, 4, 1, 1);
        add(textAbout, 2, 5, 1, 2);
        add(accord, 2, 7, 1, 1);
    }
}
