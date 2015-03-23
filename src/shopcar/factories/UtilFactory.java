/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.factories;

import java.util.*;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import shopcar.qualifiers.MyArrayList;

/**
 *
 * @author info1
 * @param <T>
 */
public class UtilFactory<T,I>
{  
    @Produces
    public Scanner createScanner()
    {
        return new Scanner(System.in);
    }
    
    public void closeScanner(@Disposes Scanner scan)
    {
        scan.close();
    }
    
    @Produces
    @MyArrayList
    public ArrayList<T> createArrayString()
    {
        return new ArrayList<>();
    }
    
    @Produces
    public StringBuilder createStringBuilder()
    {
        return new StringBuilder();
    }
    
    @Produces
    public HashMap createHash()
    {
        return new HashMap<>();
    }
    
    @Produces 
    public HashSet<T> createSet()
    {
        return new HashSet<>();
    }
    
    @Produces
    public ThreadLocal<T> createThreadLocal()
    {
        return new ThreadLocal<>();
    }
}
