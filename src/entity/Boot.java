/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author pupil
 */
public class Boot implements Serializable{

    private String name;
    private List<Manufactor> manufactor;
    private int price;
    private int year_done;
    private int month_done;
    private int day_done;
    
    
    public Boot() {
    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Manufactor> getManufactor() {
        return manufactor;
    }

    public void setManufactor(List<Manufactor> manufactor) {
        this.manufactor = manufactor;
    }

    public int getYear_done() {
        return year_done;
    }

    public void setYear_done(int year_done) {
        this.year_done = year_done;
    }

    public int getMonth_done() {
        return month_done;
    }

    public void setMonth_done(int month_done) {
        this.month_done = month_done;
    }

    public int getDay_done() {
        return day_done;
    }

    public void setDay_done(int day_done) {
        this.day_done = day_done;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    

    @Override
    public String toString() {
        return "Boot{" + "name=" + name + ", manufactor=" + Arrays.toString(manufactor.toArray()) + ", price=" + price + ", creationdate= " + day_done
                + "." + month_done + "."
                + year_done + '}';
    }
    
    
    
}
