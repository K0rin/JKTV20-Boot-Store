/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keeper;

import entity.Boot;
import entity.Client;
import entity.History;
import entity.Manufactor;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class BaseKeeper implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV20_botPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveBoots(List<Boot> boots) {
        
       tx.begin();
            for (int i = 0; i < boots.size(); i++) {
                if(boots.get(i).getId() == null){
                    for (int j = 0; j < boots.get(i).getManufactor().size(); j++) {
                        Manufactor manufactor = boots.get(i).getManufactor().get(j);
                        em.persist(manufactor);
                    }
                    em.persist(boots.get(i));
                }
            }
        tx.commit(); 
    }

    @Override
    public List<Boot> loadBoots() {
        List<Boot> boots = null;
        try {
            boots = em.createQuery("SELECT boot FROM Boot boot")
                .getResultList();
        }catch (Exception e){
            boots = new ArrayList<>();
        }        
        return boots;
    }

    @Override
    public void saveClients(List<Client> clients) {
        
        tx.begin();
            for (int i = 0; i < clients.size(); i++) {
                if(clients.get(i).getId() == null){
                    em.persist(clients.get(i));
                }else{
                    em.merge(clients.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Client> loadClients() {
        List<Client> clients = null;
        try{
            clients = em.createQuery("SELECT client FROM Client client").getResultList();
        }catch(Exception e){
            clients = new ArrayList<>();
        }
        return clients;
    }

    @Override
    public void saveHistories(List<History> histories) {
        
        tx.begin();
            for (int i = 0; i < histories.size(); i++) {
                if(histories.get(i).getId() == null){
                    em.persist(histories.get(i));
                }else{
                    em.merge(histories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        List<History> histories = null;
        try{
            histories = em.createQuery("SELECT history FROM History history").getResultList();
        }catch(Exception e){
            histories = new ArrayList<>();
        }
        return histories;
    }

    @Override
    public void saveManufactors(List<Manufactor> manufactors) {
        
        tx.begin();
            for (int i = 0; i < manufactors.size(); i++) {
                if(manufactors.get(i).getId() == null){
                    em.persist(manufactors.get(i));
                }else{
                    em.merge(manufactors.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Manufactor> loadManufactors() {
        List<Manufactor> manufactors = null;
        try{
            manufactors = em.createQuery("SELECT manufactor FROM Manufactor manufactor").getResultList();
        }catch(Exception e){
            manufactors = new ArrayList<>();
        }
        return manufactors;
    }
    
}
