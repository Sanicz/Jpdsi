/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jawaproj.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author sasha
 */
@Entity
@Table(name = "perfume")
@NamedQueries({
    @NamedQuery(name = "Perfume.findAll", query = "SELECT p FROM Perfume p"),
    @NamedQuery(name = "Perfume.findById", query = "SELECT p FROM Perfume p WHERE p.id = :id"),
    @NamedQuery(name = "Perfume.findByBrandName", query = "SELECT p FROM Perfume p WHERE p.brandName = :brandName"),
    @NamedQuery(name = "Perfume.findByPerfumeName", query = "SELECT p FROM Perfume p WHERE p.perfumeName = :perfumeName"),
    @NamedQuery(name = "Perfume.findByYearOfProduction", query = "SELECT p FROM Perfume p WHERE p.yearOfProduction = :yearOfProduction"),
    @NamedQuery(name = "Perfume.findByPerfumer", query = "SELECT p FROM Perfume p WHERE p.perfumer = :perfumer")})
public class Perfume implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "brand_name")
    private String brandName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "perfume_name")
    private String perfumeName;
    @Column(name = "year_of_production")
    private Integer yearOfProduction;
    @Size(max = 150)
    @Column(name = "perfumer")
    private String perfumer;

    public Perfume() {
    }

    public Perfume(Integer id) {
        this.id = id;
    }

    public Perfume(Integer id, String brandName, String perfumeName) {
        this.id = id;
        this.brandName = brandName;
        this.perfumeName = perfumeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPerfumeName() {
        return perfumeName;
    }

    public void setPerfumeName(String perfumeName) {
        this.perfumeName = perfumeName;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getPerfumer() {
        return perfumer;
    }

    public void setPerfumer(String perfumer) {
        this.perfumer = perfumer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfume)) {
            return false;
        }
        Perfume other = (Perfume) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jawaproj.entities.Perfume[ id=" + id + " ]";
    }
    
}
