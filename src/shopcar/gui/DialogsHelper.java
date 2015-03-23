/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.gui;


import java.util.Optional;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author info1
 */
public class DialogsHelper
{
    public static void createInfoDialog(Object owner, String title, String masthead
            , String message)
    {
        Dialogs.create()
                    .owner(owner)
                    .title(title)
                    .masthead(masthead)
                    .message(message)
                    .showInformation();
    }
    
    public static void createErrorDialog(Object owner, String title, String masthead
            , String message)
    {
        Dialogs.create()
                    .owner(owner)
                    .title(title)
                    .masthead(masthead)
                    .message(message)
                
                    .showError();
    }
    
    public static void createExceptionDialog(Object owner, String title, String masthead
            , String message, Exception ex)
    {
        Dialogs.create()
                    .owner(owner)
                    .title(title)
                    .masthead(masthead)
                    .message(message)
                    .showException(ex);
    }
    
    public static void createInputDialog(Object owner, String title, 
            String masthead, String message)
    {
        Optional<String> response = Dialogs.create()
                    .owner(owner)
                    .title(title)
                    .masthead(masthead)
                    .message(message)
                    //.lightweight()
                    .showTextInput("Placa: ");
    }
    
    public static boolean createQuestionDialog(Object owner, String title, 
            String masthead, String message, Action returnTrueAction ,Action... action)
    {
        Action response = Dialogs.create()
                    .owner(owner)
                    .title(title)
                    .masthead(masthead)
                    .message(message)
                    .actions(action)
                    .showConfirm();

        return response == returnTrueAction;
    }
    
//    public static User createLoginDialog(Node node)
//    {
//        Dialog dlg = new Dialog(node, "Login");
//        final User user = new User();
//        final TextField txtLogin = new TextField();
//        final PasswordField txtPasswd = new PasswordField();
//        final GridPane pane = new GridPane();
//        
//        final Action actionLogin = new org.controlsfx.control.action.Action
//        (
//            s -> 
//            {
//                Dialog d = (Dialog) s.getSource();
//                d.hide();
//            }
//        );
//        
//        pane.setVgap(10);
//        pane.setHgap(15);
//        
//        pane.add(new Label("Username: "), 2, 2, 1, 1);
//        pane.add(txtLogin, 3, 2, 2, 1);
//        pane.add(new Label("Password: "), 2, 3, 1, 1);
//        pane.add(txtPasswd, 3, 3, 2, 1);
//        
//        ButtonBar.setType(actionLogin, ButtonBar.ButtonType.OK_DONE);
//        actionLogin.disabledProperty().set(true);
//        dlg.setMasthead("Entre com o seu login e senha: ");
//        dlg.setContent(pane);
//        dlg.getActions().addAll(actionLogin, Dialog.ACTION_CANCEL);
//        dlg.setClosable(false);
//        
//        Platform.runLater(() -> txtLogin.requestFocus());
//        
//        dlg.show();
//        
//        return user;
//    }
}
