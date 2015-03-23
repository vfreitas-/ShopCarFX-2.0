/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author info1
 */
public class BoasVindasNode extends Group implements IPane
{
    private final static String TITLE = "Bem Vindo ao ShopCar 2.0!";
    private final static String MSG = "Esperamos que se sinta confortavel "
            + "com o nosso sistema!\n";
    private Label title;
    private Label msg;
    private Button go;
    private VBox vBox;
    private Rectangle rec1;
    private BorderPane rightPane;
    private BorderPane leftPane;
    private BorderPane topPane;
    private BorderPane downPane;
    private BorderPane centerPane;
    
    public BoasVindasNode()
    {
        this.init();
    }

    @Override
    public void initNodeProperties()
    {
        
    }
    
    @Override
    public void initComponents()
    {
        title = new Label(TITLE);
        title.setId("title_3");   
        msg = new Label(MSG);
        msg.setId("msg-inicial");
        go = new Button("Go!");
        go.setId("dark-blue");
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);
        
        rightPane = new BorderPane();
        rightPane.setPrefSize(PrimaryStage.WIDTH, PrimaryStage.HEIGHT);
        rightPane.setMinSize(0, 0);
        rightPane.setScaleX(1);
        rightPane.setId("fundo-padrao");
        leftPane = new BorderPane();
        leftPane.setPrefSize(PrimaryStage.WIDTH, PrimaryStage.HEIGHT);
        leftPane.setMinSize(0, 0);
        leftPane.setScaleX(1);
        leftPane.setId("fundo-padrao");
        topPane = new BorderPane();
        topPane.setPrefSize(PrimaryStage.WIDTH, PrimaryStage.HEIGHT);
        topPane.setMinSize(0, 0);
        topPane.setScaleX(1);
        topPane.setId("fundo-padrao");
        downPane = new BorderPane();
        downPane.setPrefSize(PrimaryStage.WIDTH, PrimaryStage.HEIGHT);
        downPane.setMinSize(0, 0);
        downPane.setScaleX(1);
        downPane.setId("fundo-padrao");
        centerPane = new BorderPane();
        centerPane.setPrefSize(PrimaryStage.WIDTH, PrimaryStage.HEIGHT);
        centerPane.setMinSize(0, 0);
        centerPane.setScaleX(1);
        centerPane.setId("fundo-padrao");
        centerPane.setCenter(vBox);
        
        
        final DropShadow drop = new DropShadow
        (BlurType.GAUSSIAN, Color.WHITE, 5, 1 , 1, 1);
        final Reflection r = new Reflection();
        r.setFraction(0.5f);
        r.setBottomOpacity(0.5);
        drop.setInput(r);
        title.setEffect(drop);
        
        
        
        vBox.getChildren().addAll(title, msg, go);

        getChildren().addAll(downPane, leftPane, topPane, rightPane, centerPane);


    }  
    
    @Override
    public void initListeners()
    {
        go.setOnAction(s -> slide());
    }

    @Override
    public void initLayout()
    {
        
    }
    
    public void slide()
    {
        try
        {
            final Timeline t = new Timeline();
            t.setCycleCount(1);
            t.setAutoReverse(true);
            
            final KeyValue kvLeft = new KeyValue(leftPane.translateXProperty()
                    , -PrimaryStage.WIDTH);
            final KeyValue kvLeftOpac = new KeyValue(leftPane.opacityProperty(), 0);
            final KeyValue kvTop = new KeyValue(topPane.translateYProperty()
                    , -PrimaryStage.HEIGHT);
            final KeyValue kvTopOpac = new KeyValue(topPane.opacityProperty(), 0);
            final KeyValue kvDown = new KeyValue(downPane.translateYProperty()
                    , PrimaryStage.HEIGHT);
            final KeyValue kvDownOpac = new KeyValue(downPane.opacityProperty(), 0);
            final KeyValue kvRight = new KeyValue(rightPane.translateXProperty()
                    , PrimaryStage.WIDTH);
            final KeyValue kvRightOpac = new KeyValue(rightPane.opacityProperty(), 0);
            final KeyValue kvCenter = new KeyValue(centerPane.opacityProperty(), -10); 
            
            final KeyFrame kf = new KeyFrame(Duration.millis(2500), kvRight, kvRightOpac
                    , kvLeft, kvTop, kvDown, kvLeftOpac, kvTopOpac, kvDownOpac, kvCenter);
            
            final KeyValue kv = new KeyValue(centerPane.translateXProperty(), -PrimaryStage.WIDTH);
            final KeyFrame kf2 = new KeyFrame(Duration.millis(100), kv);
            
            t.getKeyFrames().addAll(kf);
            t.play();
            
            t.setOnFinished(s -> 
            {
                t.getKeyFrames().remove(kf);
                t.getKeyFrames().add(kf2);
                t.play();
            });
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
