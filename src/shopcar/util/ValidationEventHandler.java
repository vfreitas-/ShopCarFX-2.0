///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package shopcar.util;
//
//import java.beans.EventHandler;
//import java.util.Set;
//import javafx.scene.Node;
//import javafx.scene.control.TextInputControl;
//import javafx.scene.input.KeyEvent;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//
//public class ValidationEventHandler<T> extends EventHandler<KeyEvent> 
//{
// 
//    private Class<T> beanClass;
//    private String propertyName;
//    private static Validator validator;
//    
// 
//    public ValidationEventHandler(Class<T> beanClass, String propertyName)
//    {
//        this.beanClass = beanClass;
//        this.propertyName = propertyName;
//        
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
// 
//    public void handle(KeyEvent event) 
//    {
//        String text = (
//                (TextInputControl)event.getTarget()
//                ).getText();
//        Set<ConstraintViolation<T>> violations = 
//            validator.validateValue(beanClass, propertyName, text);
// 
//        if (!violations.isEmpty())
//            ((Node)event.getTarget()).setStyle("-fx-border-color: red");
//        else
//            ((Node)event.getTarget()).setStyle("-fx-border-color: null");
//    }
//}
