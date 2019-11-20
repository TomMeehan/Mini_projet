/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pedago
 */
public class Produit {
    
    private int categorie;
    
    private int reference;
    private String nom;
    private int fournisseur;
    private String quantite_par_unite;
    private float prix_unitaire;
    private int unites_en_stock;
    private int unites_commandees;
    private int niveau_de_reapprovisionnement;
    private int indisponible;

    public Produit(int categorie, int reference, String nom, int fournisseur, String quantite_par_unite, float prix_unitaire, int unites_en_stock, int unites_commandees, int niveau_de_reapprovisionnement, int indisponible) {
        this.categorie = categorie;
        this.reference = reference;
        this.nom = nom;
        this.fournisseur = fournisseur;
        this.quantite_par_unite = quantite_par_unite;
        this.prix_unitaire = prix_unitaire;
        this.unites_en_stock = unites_en_stock;
        this.unites_commandees = unites_commandees;
        this.niveau_de_reapprovisionnement = niveau_de_reapprovisionnement;
        this.indisponible = indisponible;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(int fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getQuantite_par_unite() {
        return quantite_par_unite;
    }

    public void setQuantite_par_unite(String quantite_par_unite) {
        this.quantite_par_unite = quantite_par_unite;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getUnites_en_stock() {
        return unites_en_stock;
    }

    public void setUnites_en_stock(int unites_en_stock) {
        this.unites_en_stock = unites_en_stock;
    }

    public int getUnites_commandees() {
        return unites_commandees;
    }

    public void setUnites_commandees(int unites_commandees) {
        this.unites_commandees = unites_commandees;
    }

    public int getNiveau_de_reapprovisionnement() {
        return niveau_de_reapprovisionnement;
    }

    public void setNiveau_de_reapprovisionnement(int niveau_de_reapprovisionnement) {
        this.niveau_de_reapprovisionnement = niveau_de_reapprovisionnement;
    }

    public int getIndisponible() {
        return indisponible;
    }

    public void setIndisponible(int indisponible) {
        this.indisponible = indisponible;
    }
    
    
    
}
