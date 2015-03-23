/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.util;

import java.util.Scanner;
import javax.inject.Inject;

/**
 *
 * @author Vitor Freitas
 */
public class Util
{
    @Inject private Scanner s;
    
    public Integer testInput(String pattern, String error) 
    {   
        Integer test;
        if(s.hasNext(pattern))
        {
            test = s.nextInt();
            clear();
            return test;
        }
        else
        {
            System.err.println(error);
            s.nextLine();
            return testInput(pattern, error);
        }
    }
    
    public String testInputString(String pattern, String error)
    {
        String test;
   
        if(s.hasNext(pattern))
        {
            test = s.nextLine();
            clear();
            return test;
        }
        else
        {
            System.err.println(error);
            s.next();
            return testInputString(pattern, error);
        }
    }
    
    public void clear() 
    {
        for (int i = 0; i < 60; ++i) System.out.println();
    }
    
    public String testInputString(String pattern) throws Exception
    {
        String test;
        if(s.hasNext())
        {
            test = s.nextLine();
            clear();
            return test;
        }
        else
           throw new Exception("Conteudo inválido!");
    }
}
