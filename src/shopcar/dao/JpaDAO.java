/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author vFreitas
 * @param <T> The type T 
 */
public class JpaDAO<T> implements DAO<T>, Serializable
{
    private static final String UNIT_NAME = "shopcar";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIT_NAME);
    /* The EntityManager of my connection */
    private static EntityManager em;
    /* The class to be persist */
    private final Class<T> classe;
   
    /* Builder */
    /**
    *
    * @author info1
    * @param classe The class to that will represent T
    */
    public JpaDAO(Class<T> classe)
    {
        this.classe = classe;
    }
    
    public Class<T> getClasse()
    {
        return this.classe;
    }

    public static EntityManager createEM()
    {
        //return em == null ? em = factory.createEntityManager() : em;
        if(em == null)
            em = factory.createEntityManager();
        return em;
        
    }
    
    public EntityManager getEntityManager()
    {
        return this.em;
    }
    
    public void getFlush()
    {
        getEntityManager().flush();
    }
    
    public void close()
    {
    }
    
    @Override
    public void save(T entity)
    {
        try
        {
            createEM().getTransaction().begin();
            createEM().persist(entity);
            createEM().getTransaction().commit();
        } 
        catch (Exception e)
        {
            if(createEM().getTransaction().isActive())
                createEM().getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity)
    {
        createEM().getTransaction().begin();
        createEM().merge(entity);
        createEM().getTransaction().commit();
    }

    @Override
    public void remove(T entity)
    {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T getById(Object pk)
    {
        return createEM().find(this.classe, pk);
    }

    @Override
    public List<T> getAll()
    {
        List<T> resultList = (List<T>) createEM().createQuery("select e from " + classe.getSimpleName() + " e").getResultList();
        return resultList;
    }

    @Override
    public T getByRestriction(String attribute, String filter)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<T> getByRestriction(String namedQuery, String parameter, Object value)
    {
        List <T> resultList = (List<T>) createEM().createNamedQuery(namedQuery)
                .setParameter(parameter, value)
                .getResultList();System.out.println("getByRestriction Object");
        return resultList;
    }
    
    @Override
    public List<T> getByRestriction(String namedQuery, String parameter, String value)
    {
        List <T> resultList = (List<T>) createEM().createNamedQuery(namedQuery)
                .setParameter(parameter, "%" +value + "%")
                .getResultList();System.out.println("getByRestriction String");
        return resultList;
    }
}
