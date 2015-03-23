/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcar;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import shopcar.gui.PrimaryStage;

/**
 *
 * @author Vitor Freitas
 */
public class ShopCar extends Application
{
    private Weld weld;
    
    @Override
    public void init() throws Exception
    {
       weld = new Weld();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        WeldContainer weldContainer = weld.initialize();
        weldContainer.instance().select( PrimaryStage.class ).get()
                .start( primaryStage );
    }

    @Override
    public void stop() throws Exception
    {
        weld.shutdown();
    }
    
    public static void main(String args[])
    {
        launch(args);      
    }
}
