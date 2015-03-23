/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;

import javafx.scene.Node;
import org.controlsfx.control.PopOver;

/**
 *
 * @author Vitor Freitas
 */
public class NewPopOver extends PopOver
{
    private final Node node;
    
    public NewPopOver(Node contentNode)
    {
        this.node = contentNode;
        configPopOver();
    }
    
    private void configPopOver()
    {

        setContentNode(this.getNode());
        setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        setPrefSize(400, 400);
        setDetachable(false);
        setDetached(false);
        setAutoHide(true);      
    }

    /**
     * @return the node
     */
    public Node getNode()
    {
        return node;
    }
}
