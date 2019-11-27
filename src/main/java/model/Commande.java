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
public class Commande {
    
    private String client;
    
    private int numero ;
    private String saisie_le;
    private String envoyee_le;
    private float port;
    private String Destinataire;
    private String adresse_livraison;
    private String ville_livraison;
    private String region_livraison;
    private String code_postal_livrais;
    private String pays_livraison;
    private float remise;

    public Commande(int numero, String client, String saisieLe, String envoyeeLe, float port, String Destinataire, String adresse_livraison, String ville_livraison, String region_livraison, String code_postal_livraison, String pays_livraison, float remise) {
        this.numero = numero;
        this.client = client;
        this.saisie_le = saisieLe;
        this.envoyee_le = envoyeeLe;
        this.port = port;
        this.Destinataire = Destinataire;
        this.adresse_livraison = adresse_livraison;
        this.ville_livraison = ville_livraison;
        this.region_livraison = region_livraison;
        this.code_postal_livrais = code_postal_livraison;
        this.pays_livraison = pays_livraison;
        this.remise = remise;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSaisieLe() {
        return saisie_le;
    }

    public void setSaisieLe(String saisieLe) {
        this.saisie_le = saisieLe;
    }

    public String getEnvoyeeLe() {
        return envoyee_le;
    }

    public void setEnvoyeeLe(String envoyeeLe) {
        this.envoyee_le = envoyeeLe;
    }

    public float getPort() {
        return port;
    }

    public void setPort(float port) {
        this.port = port;
    }

    public String getDestinataire() {
        return Destinataire;
    }

    public void setDestinataire(String Destinataire) {
        this.Destinataire = Destinataire;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public String getVille_livraison() {
        return ville_livraison;
    }

    public void setVille_livraison(String ville_livraison) {
        this.ville_livraison = ville_livraison;
    }

    public String getRegion_livraison() {
        return region_livraison;
    }

    public void setRegion_livraison(String region_livraison) {
        this.region_livraison = region_livraison;
    }

    public String getCode_postal_livrais() {
        return code_postal_livrais;
    }

    public void setCode_postal_livrais(String code_postal_livrais) {
        this.code_postal_livrais = code_postal_livrais;
    }

    public String getPays_livraison() {
        return pays_livraison;
    }

    public void setPays_livraison(String pays_livraison) {
        this.pays_livraison = pays_livraison;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }
    
}
