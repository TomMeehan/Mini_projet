/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import model.Commande;

/**
 *
 * @author Tom
 */
public class CommandeBean {
    
    private ArrayList<Commande> commandes;
    private int nbCommandes;

    public CommandeBean(){
        this.commandes = new ArrayList<>();
        this.nbCommandes = 0;
    }
    
    public CommandeBean(ArrayList<Commande> commandes) {
        this.commandes = commandes;
        this.nbCommandes = commandes.size();
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }

    public int getNbCommandes() {
        return nbCommandes;
    }

    public void setNbCommandes(int nbCommandes) {
        this.nbCommandes = nbCommandes;
    }
   
    
    
}
