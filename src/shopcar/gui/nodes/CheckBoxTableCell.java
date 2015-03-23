/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcar.gui.nodes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;

/**
 *
 * @author Alunos Tomazati
 * @param <S>
 * @param <T>
 */
public class CheckBoxTableCell<S, Boolean> extends TableCell<S, Boolean> 
{
    private final CheckBox checkBox;
    private ObservableValue<Boolean> ov;

    public CheckBoxTableCell() 
    {
        this.checkBox = new CheckBox();
        this.checkBox.setAlignment(Pos.CENTER);
        this.checkBox.setDisable(true);
        
        setAlignment(Pos.CENTER);
        setGraphic(checkBox);
    } 

    @Override public void updateItem(Boolean item, boolean empty) 
    {
        super.updateItem(item, empty);
        if (empty) 
        {
            setText(null);
            setGraphic(null);
        } 
        else 
        {
            setGraphic(checkBox);
            if (ov instanceof BooleanProperty)
            {
                checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
            }
            ov = getTableColumn().getCellObservableValue(getIndex());
            if (ov instanceof BooleanProperty) 
            {
                checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
            }
        }
    }
}
