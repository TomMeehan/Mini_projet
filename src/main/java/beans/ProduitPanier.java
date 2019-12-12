/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author Tom
 */
public class ProduitPanier implements Serializable {
    
    private int reference;
    private String categorie;
    private String nom;
    private float prix_unitaire;
    private int quantite;
    private int unites_en_stock;

    public ProduitPanier(int reference, String categorie, String nom, float prix_unitaire, int quantite, int unites_en_stock) {
        this.reference = reference;
        this.categorie = categorie;
        this.nom = nom;
        this.prix_unitaire = prix_unitaire;
        this.quantite = quantite;
        this.unites_en_stock = unites_en_stock;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getUnites_en_stock() {
        return unites_en_stock;
    }

    public void setUnites_en_stock(int unites_en_stock) {
        this.unites_en_stock = unites_en_stock;
    }
    
    
    
}
