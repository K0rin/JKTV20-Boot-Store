/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Client;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class ClientFacade extends AbstractFacade<Client>{
    
    private EntityManager em;

    public ClientFacade(Class<Client> entityClass) {
        super(entityClass);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em; //To change body of generated methods, choose Tools | Templates.
    }
    
}
