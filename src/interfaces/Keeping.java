/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Boot;
import entity.History;
import entity.Client;
import entity.Manufactor;
import java.util.List;

/**
 *
 * @author pupil
 */
public interface Keeping {
    
    public void saveBoots(List<Boot> boots);
    public List<Boot> loadBoots();
    public void saveClients(List<Client> clients);
    public List<Client> loadClients();
    public void saveHistories(List<History> histories);
    public List<History> loadHistories();
    public void saveManufactors(List<Manufactor> manufactors);
    public List<Manufactor> loadManufactors();
}
