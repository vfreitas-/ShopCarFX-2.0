/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import static shopcar.gui.PrimaryStage.RESOURCES;

/**
 *
 * @author Vitor Freitas
 */
public class TutorialPlayer extends StackPane implements IPane
{
    private final String mediaName;
    private final double xLocation;
    private final double yLocation;
    private final Image imgPlay = new Image(getClass()
            .getResourceAsStream(RESOURCES + "play.png"));
    private final Image imgPause = new Image(getClass()
            .getResourceAsStream(RESOURCES + "stop.png"));
    private boolean stopRequested = false;
    private boolean atEndOfVideo = false;
    private Stage playerStage;
    private Scene playerScene;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private VBox vbox;
    private Slider timeSlider;
    private Button btnPlay;
    private StackPane test;
    
    private Label rightTimer;
    private Label leftTimer;
    
    private Timeline slideIn;
    private Timeline slideOut;
    
    public TutorialPlayer(String mediaName, double xLocation, double yLocation)
    {
        this.yLocation = yLocation;
        this.xLocation = xLocation;
        this.mediaName = mediaName;
        init();
    }

    @Override
    public void initNodeProperties()
    {
        playerStage = new Stage();
        playerStage.initModality(Modality.APPLICATION_MODAL);
        playerStage.setTitle("ShopCar Movie Player");
        playerStage.setResizable(false);
        playerStage.setX(xLocation + 200);
        playerStage.setY(yLocation + 100);
       
        playerStage.getIcons().add(new Image(RESOURCES + "shopcar1.png"));
        
        playerScene = new Scene(this, 600, 600);
        playerScene.getStylesheets().add(PrimaryStage.STYLESHEET);
        
        playerStage.setScene(playerScene);
        playerStage.show();
    }

    @Override
    public void initComponents()
    {
        media = new Media(getClass().getResource(RESOURCES + "videos/" + mediaName).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        
        slideIn = new Timeline();  
        slideOut = new Timeline();
        
        rightTimer = new Label();
        rightTimer.setId("lbl-timer");
        leftTimer = new Label();
        leftTimer.setId("lbl-timer");
        
        vbox = new VBox(5);
        vbox.setId("fundo-padrao");
        test = new StackPane();
        timeSlider = new Slider();
        btnPlay = new Button();
        btnPlay.setGraphic(new ImageView(imgPause));
        btnPlay.setId("btn-play");
        
        final HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(leftTimer, btnPlay, rightTimer);

        vbox.getChildren().addAll(timeSlider, hbox);     
        
        getChildren().addAll(mediaView, vbox);
        
        mediaPlayer.play();
    }

    @Override
    public void initListeners()
    {
        playerStage.setOnCloseRequest(s -> 
        {
            mediaPlayer.stop();
        });
        
        mediaPlayer.setOnReady(() ->
        {
            int width = mediaPlayer.getMedia().getWidth();
            int height = mediaPlayer.getMedia().getHeight();
            
            
            playerStage.setMinHeight(height + 20);
            playerStage.setMinWidth(width+ 20);
            playerStage.setMaxHeight(height+ 20);
            playerStage.setMaxWidth(width+ 20);
            
            vbox.setMinSize(width+20, 100);
            vbox.setTranslateY(height - 100);
            
            test.setMinSize(width+20, 100);
            test.setTranslateY(height - 100);
            
            timeSlider.setMin(0.0);
            timeSlider.setValue(0.0);
            timeSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
            
            leftTimer.setText("00:00");
            rightTimer.setText
            (
                formatMills( Math.round(mediaPlayer.getTotalDuration()
                        .toMillis()))
            );
            
            
            
            loadTimelineKeyFrames(height);
  
        });
        
        mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable
                , Duration oldValue, Duration newValue) ->
        {
            timeSlider.setValue(newValue.toSeconds());
            leftTimer.setText
            (
                formatMills( Math.round(newValue
                        .toMillis()))
            );
            
        });
        
        mediaPlayer.setOnPlaying( () -> 
        {
            if(stopRequested)
            {
                mediaPlayer.pause();
                stopRequested = false;
            }
            else
                btnPlay.setGraphic(new ImageView(imgPause));
        });
        
        mediaPlayer.setOnPaused( () ->
        {
            btnPlay.setGraphic(new ImageView(imgPlay));
        });
        
        mediaPlayer.setOnEndOfMedia( () -> 
        {
            btnPlay.setGraphic(new ImageView(imgPlay));
            stopRequested = true;
            atEndOfVideo = true;
        });
        
        timeSlider.setOnMousePressed((MouseEvent event) ->
        {
            mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
        });
        
        btnPlay.setOnAction(s -> 
        {
            Status status = mediaPlayer.getStatus();
        
            if (status == Status.UNKNOWN  || status == Status.HALTED)
                return; //Do nothing.
            
            if(status == Status.PAUSED || status == Status.STOPPED ||
                    status == Status.READY)
            {
                if (atEndOfVideo) 
                {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfVideo = false;
                }
                mediaPlayer.play();
            }
            else
            {
                mediaPlayer.pause();
            }    
        });
        
        setOnMouseEntered((MouseEvent event) ->
        {
            slideIn.play();
        });
        
        setOnMouseExited((MouseEvent event) ->
        {
            slideOut.play();
        });
    }

    @Override
    public void initLayout()
    {
        
    }
    
    private void loadTimelineKeyFrames(int height)
    {
        slideOut.getKeyFrames().addAll
       (
                new KeyFrame(new Duration(0), 
                new KeyValue(vbox.translateYProperty(), height-100),
                new KeyValue(vbox.opacityProperty(), 0.95)
            ),
                new KeyFrame(new Duration(300), 
                new KeyValue(vbox.translateYProperty(), height),
                new KeyValue(vbox.opacityProperty(), 0.0)
            )  
        );

        slideIn.getKeyFrames().addAll
        (
                new KeyFrame(new Duration(0), 
                new KeyValue(vbox.translateYProperty(), height),
                new KeyValue(vbox.opacityProperty(), 0.0)
            ),
                new KeyFrame(new Duration(300), 
                new KeyValue(vbox.translateYProperty(), height-100),
                new KeyValue(vbox.opacityProperty(), 0.95)
            )
        );    
    }
    
    private String formatMills(long mills)
    {
//        return String.format("%d:%d:%d", 
//                TimeUnit.MILLISECONDS.toHours(mills),
//                TimeUnit.MILLISECONDS.toMinutes(mills),
//                TimeUnit.MILLISECONDS.toSeconds(mills)
//                );
        return (new SimpleDateFormat("mm:ss")).format(new Date(mills));
    }
}
