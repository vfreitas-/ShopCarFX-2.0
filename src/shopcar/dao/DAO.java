/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.dao;


import java.util.List;

/**
 *
 * @author info1
 * @param <T>
 */
public interface DAO<T>
{
    public void save(T entity);
    
    public void update(T entity);
    
    public void remove(T entity);
    
    public T getById(Object pk);
    
    public List<T> getAll();
    
    public T getByRestriction(String attribute, String filter);
    
    public List<T> getByRestriction(String namedQuery, String parameter, Object value);
    
    public List<T> getByRestriction(String namedQuery, String parameter, String value);
}
