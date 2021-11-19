/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author pupil
 */
@Entity
public class Boot implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private List<Manufactor> manufactor;
    private int price;
    private int year_done;
    private int month_done;
    private int day_done;
    
    
    public Boot() {
    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.manufactor);
        hash = 83 * hash + this.price;
        hash = 83 * hash + this.year_done;
        hash = 83 * hash + this.month_done;
        hash = 83 * hash + this.day_done;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Boot other = (Boot) obj;
        if (this.price != other.price) {
            return false;
        }
        if (this.year_done != other.year_done) {
            return false;
        }
        if (this.month_done != other.month_done) {
            return false;
        }
        if (this.day_done != other.day_done) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.manufactor, other.manufactor)) {
            return false;
        }
        return true;
    }
    
    
    
}
