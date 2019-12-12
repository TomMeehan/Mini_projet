/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Tom
 */
public class Panier {
    
    private ArrayList<ProduitPanier> produits;
    private int nbTotalProduits;
    private float prixTotal;
    

    public Panier(){
        produits = new ArrayList();
        nbTotalProduits = 0;
        prixTotal = 0;
    }
    
    public Panier(ArrayList<ProduitPanier> produits, int nbTotalProduits, float prixTotal) {
        this.produits = produits;
        this.nbTotalProduits = nbTotalProduits;
        this.prixTotal = prixTotal;
    }
    
    public void addProduit(ProduitPanier p){
        boolean  trouve = false;
        
        for (ProduitPanier pL : this.produits){
            if (p.getReference() == pL.getReference()){
                trouve = true;
                pL.setQuantite(pL.getQuantite() + p.getQuantite());
            }
        }
        
        if (!trouve) this.produits.add(p);

        
        nbTotalProduits += p.getQuantite();
        prixTotal += p.getPrix_unitaire() * p.getQuantite();
    }
    
    public void removeProduit(ProduitPanier p) throws Exception{
        
        boolean  trouve = false;
        
        for (ProduitPanier pL : this.produits){
            if (p.getReference() == pL.getReference()){
                trouve = true;
                this.produits.remove(pL);
            }
        }
        
        if (!trouve) throw new Exception("Produit introuvable");
    }
    

    public ArrayList<ProduitPanier> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<ProduitPanier> produits) {
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
    
    public int getNbProduits() {
        return this.produits.size();
    }
    
}
