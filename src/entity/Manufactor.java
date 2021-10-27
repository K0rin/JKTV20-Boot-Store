/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author pupil
 */
public class Manufactor implements Serializable{
    private String name;
    private String country;
    private String city;

    public Manufactor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public String toString(){
        return "Manufactor{" + "name=" + name + ", country=" + country + ", city=" + city + '}';
    }
}
