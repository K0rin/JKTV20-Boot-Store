/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keeper;

import entity.Boot;
import entity.History;
import entity.Client;
import entity.Manufactor;
import interfaces.Keeping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pupil
 */
public class File_Keeper implements Keeping{

    @Override
    public void saveBoots(List<Boot> boots) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("boots");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(boots);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "нет файла boots", ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода данных", ex);
        }
    }

    @Override
    public List<Boot> loadBoots() {
        List<Boot> loadBoots = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("boots");
            ois = new ObjectInputStream(fis);
            loadBoots = (List<Boot>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Нет файла boots", ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "класс не найден", ex);
        }
        
        return loadBoots;
    }

    @Override
    public void saveClients(List<Client> clients) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("clients");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(clients);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "нет файла clients", ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода данных", ex);
        }
    }

    @Override
    public List<Client> loadClients() {
        List<Client> loadClients = new ArrayList<>();
        
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("clients");
            ois = new ObjectInputStream(fis);
            loadClients = (List<Client>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Нет файла clients", ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "класс не найден", ex);
        }
        
        return loadClients;
    }

    @Override
    public void saveHistories(List<History> histories) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("histories");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(histories);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "нет файла histories", ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода данных", ex);
        }
    }

    @Override
    public List<History> loadHistories() {
        List<History> loadHistories = new ArrayList<>();
        
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("histories");
            ois = new ObjectInputStream(fis);
            loadHistories = (List<History>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Нет файла histories", ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "класс не найден", ex);
        }
        
        return loadHistories;
    }

    @Override
    public void saveManufactors(List<Manufactor> manufactors) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Manufactor> loadManufactors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
