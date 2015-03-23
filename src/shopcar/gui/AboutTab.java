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
import static shopcar.gui.ITab.RESOURCES;

/**
 *
 * @author info1
 */
public class AboutTab extends Tab implements ITab
{
    private static final String ICON_PATH = RESOURCES + "about.png";
    private Image img;
    
    public AboutTab()
    {
        init();
    }
    
    @Override
    public void setTabValues()
    {
        setTooltip(new Tooltip("Sobre/Ajuda ShopCar 2.0"));
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
        setContent(new AboutPane());
    }
    
}
