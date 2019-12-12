/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author pedago
 */
public class Commande implements Serializable{
    
    private String client;
    
    private int numero ;
    private String saisieLe;
    private String envoyee_le;
    private float port;
    private String destinataire;
    private String adresseLivraison;
    private String villeLivraison;
    private String regionLivraison;
    private String code_postalLivrais;
    private String paysLivraison;
    private float remise;

    public Commande(String client, int numero, String saisieLe, String envoyee_le, float port, String destinataire, String adresseLivraison, String villeLivraison, String regionLivraison, String code_postalLivrais, String paysLivraison, float remise) {
        this.client = client;
        this.numero = numero;
        this.saisieLe = saisieLe;
        this.envoyee_le = envoyee_le;
        this.port = port;
        this.destinataire = destinataire;
        this.adresseLivraison = adresseLivraison;
        this.villeLivraison = villeLivraison;
        this.regionLivraison = regionLivraison;
        this.code_postalLivrais = code_postalLivrais;
        this.paysLivraison = paysLivraison;
        this.remise = remise;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSaisieLe() {
        return saisieLe;
    }

    public void setSaisieLe(String saisieLe) {
        this.saisieLe = saisieLe;
    }

    public String getEnvoyee_le() {
        return envoyee_le;
    }

    public void setEnvoyee_le(String envoyee_le) {
        this.envoyee_le = envoyee_le;
    }

    public float getPort() {
        return port;
    }

    public void setPort(float port) {
        this.port = port;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getVilleLivraison() {
        return villeLivraison;
    }

    public void setVilleLivraison(String villeLivraison) {
        this.villeLivraison = villeLivraison;
    }

    public String getRegionLivraison() {
        return regionLivraison;
    }

    public void setRegionLivraison(String regionLivraison) {
        this.regionLivraison = regionLivraison;
    }

    public String getCode_postalLivrais() {
        return code_postalLivrais;
    }

    public void setCode_postalLivrais(String code_postalLivrais) {
        this.code_postalLivrais = code_postalLivrais;
    }

    public String getPaysLivraison() {
        return paysLivraison;
    }

    public void setPaysLivraison(String paysLivraison) {
        this.paysLivraison = paysLivraison;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    
    
}
