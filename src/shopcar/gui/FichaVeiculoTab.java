/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Vitor Freitas
 */
public class FichaVeiculoTab extends Tab implements ITab
{
    private static final String ICON_PATH = RESOURCES + "ficha.png";
    private Image img;
    

    public FichaVeiculoTab()
    {
        this.init();
    }

    @Override
    public void setTabValues()
    {
        setTooltip(new Tooltip("Ficha do Veiculo!"));
        setClosable(false);
    }
    
    @Override
    public void createGraphic()
    {
        img = new Image(getClass().getResourceAsStream(ICON_PATH));
        setGraphic(new ImageView(img));
    }
    
    @Override
    public void createContent()
    {
        setContent(new FichaVeiculoPane());
    }
}
