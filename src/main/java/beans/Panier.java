/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.HashMap;
import java.util.Map;
import model.Produit;

/**
 *
 * @author Tom
 */
public class Panier {
    
    private Map<Produit,Integer> produits;
    private int nbTotalProduits;
    private float prixTotal;

    public Panier(){
        produits = new HashMap();
        nbTotalProduits = 0;
        prixTotal = 0;
    }
    
    public Panier(Map<Produit, Integer> produits, int nbTotalProduits, float prixTotal) {
        this.produits = produits;
        this.nbTotalProduits = nbTotalProduits;
        this.prixTotal = prixTotal;
    }
    
    public void addProduit(Produit p, int quantite){
        
        if (produits.containsKey(p)){
            produits.put(p,produits.get(p) + quantite);
        }else{
            produits.put(p,quantite);
        }
        
        nbTotalProduits += quantite;
        prixTotal += p.getPrix_unitaire() * quantite;
    }
    
    public void removeProduit(Produit p, int quantite){
        
        if (produits.containsKey(p)){
            if (produits.get(p) - quantite <= 0){
                produits.remove(p);
            }else{
                produits.put(p,produits.get(p) - quantite);
            }
        }
    }
    

    public Map<Produit, Integer> getProduits() {
        return produits;
    }

    public void setProduits(Map<Produit, Integer> produits) {
        this.produits = produits;
    }

    public int getNbTotalProduits() {
        return nbTotalProduits;
    }

    public void setNbTotalProduits(int nbTotalProduits) {
        this.nbTotalProduits = nbTotalProduits;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }
    
}
