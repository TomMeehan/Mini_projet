/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Tom
 */
public class ProduitPanier {
    
    private int reference;
    private String categorie;
    private String nom;
    private float prix_unitaire;

    public ProduitPanier(int reference, String categorie, String nom, float prix_unitaire) {
        this.reference = reference;
        this.categorie = categorie;
        this.nom = nom;
        this.prix_unitaire = prix_unitaire;
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
    
    
    
}
