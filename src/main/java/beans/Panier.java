/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;

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
    
    public void addProduit(ProduitPanier p, boolean isUpdate){
        boolean  trouve = false;
        int nbTotal = 0;
        float prix = 0;
        
        if (this.produits.isEmpty()) {
            nbTotal += p.getQuantite();
            prix += p.getQuantite() * p.getPrix_unitaire();
        }
        
        for (ProduitPanier pL : this.produits){
            if (p.getReference() == pL.getReference()){
                trouve = true; 
                if (isUpdate){
                    pL.setQuantite(p.getQuantite());
                    pL.setUnites_en_stock(p.getUnites_en_stock()); 
                }    
                else{
                    pL.setQuantite(pL.getQuantite() + p.getQuantite());
                    pL.setUnites_en_stock(pL.getUnites_en_stock() - p.getQuantite()); 
                }
                  
                
            }
            nbTotal += pL.getQuantite();
            prix += pL.getQuantite() * pL.getPrix_unitaire();
        }
        
        if (!trouve) this.produits.add(p);
        this.nbTotalProduits = nbTotal;
        this.prixTotal = prix;
    }
    
    public void removeProduit(int reference, int quantite, float prix_unitaire) throws Exception{
        boolean  trouve = false;
        
        for (ProduitPanier pL : this.produits){
            if (reference == pL.getReference()){
                System.out.println("trouvé");
                trouve = true;
                this.produits.remove(pL);
                break;
            }
        }
        this.nbTotalProduits -= quantite;
        this.prixTotal -= prix_unitaire * quantite;
        if (!trouve) throw new Exception("Produit introuvable");
    }
    
    public void viderPanier(){
        this.produits = new ArrayList();
        this.nbTotalProduits = 0;
        this.prixTotal = 0f;
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
