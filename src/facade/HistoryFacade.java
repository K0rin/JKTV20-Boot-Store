/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Boot;
import entity.History;
import java.util.List;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class HistoryFacade extends AbstractFacade<History>{
    
    private EntityManager em;

    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em; //To change body of generated methods, choose Tools | Templates.
    }
    
    public History findHistorybySoldBoot(Boot boot){
        return (History) getEntityManager().createQuery(
                "SELECT history FROM History history WHERE history.boot = :boot")
                .setParameter("boot", boot).getSingleResult();
        
    }
    
    public List<History> findHistoryWithSoldBoots() {
        return getEntityManager().createQuery("SELECT h FROM History h").getResultList();
    }
    
}
