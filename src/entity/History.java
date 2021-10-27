/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pupil
 */
public class History implements Serializable{
    private Boot boot;
    private Client client;
    private Date sellingDate;
    private int capital = 0;

    public History() {
    }

    public Boot getBoot() {
        return boot;
    }

    public void setBoot(Boot boot) {
        this.boot = boot;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(Date sellingDate) {
        this.sellingDate = sellingDate;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "History{" + "boot=" + boot + ", client=" + client + ", sellingDate=" + sellingDate + ", capital=" + capital + '}';
    }
    
    
}
