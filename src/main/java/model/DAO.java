/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author pedago
 */
public class DAO {
    
      	protected final DataSource myDataSource;
        
	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
    public Client getClientInfos(String Code) throws SQLException{
        ArrayList<String> AllInfos = new ArrayList();
        String sql = "SELECT * FROM Client WHERE Code = ?";
        Client me = null;
        try (Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
               stmt.setString(1, Code);
               try (ResultSet rs = stmt.executeQuery()) {
                       rs.next();
                       me = new Client(Code,rs.getString("societe"),rs.getString("contact"),rs.getString("fonction"),rs.getString("adresse"),rs.getString("ville"),rs.getString("region"),rs.getString("code_postale"),rs.getString("pays"),rs.getString("telephone"),rs.getString("fax"));
                       
               }
        }
        return me;
    }
/*    TODO 
    public List<Commande> getCommandes() throws SQLException{
        String sql = "SELECT * FROM Categorie";
        List<Commande> result = new ArrayList<>();
        try (Connection connection = myDataSource.getConnection();
            Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                
            }
        }
        return result;
    }
*/    
    public List<Categorie> getCategories() throws SQLException{
        String sql = "SELECT * FROM Categorie";
        List<Categorie> result = new ArrayList<>();
        try (Connection connection = myDataSource.getConnection();
            Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                int code = rs.getInt("Code");
                String libelle = rs.getString("Libelle");
                String Description = rs.getString("Description");
                result.add(new Categorie(code, libelle, Description));
            }
        }
        return result;
    }
    
    public List<Produit> getProduits() throws SQLException{
        String sql = "SELECT * FROM Produit";
        List<Produit> result = new ArrayList<>();
        try (Connection connection = myDataSource.getConnection();
            Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                int reference = rs.getInt("Reference");
                String nom = rs.getString("Libelle");
                int fournisseur = rs.getInt("Fournisseur");
                int categorie = rs.getInt("Categorie");
                String quantite_par_unite = rs.getString("Quantite_par_unite");
                int prix_unitaire = rs.getInt("Prix_unitaire");
                int unites_en_stock = rs.getInt("Unites_en_stock");
                int unites_commandees = rs.getInt("Unites_commandees");
                int niveau_de_reapprovi = rs.getInt("Niveau_de_reapprovi");
                int indisponible = rs.getInt("Indisponible");
                
                result.add(new Produit(categorie,reference,nom,fournisseur,quantite_par_unite,prix_unitaire,unites_en_stock,unites_commandees,niveau_de_reapprovi,indisponible));
                
            }
        }
        return result;
    }
            
    public void updateClientInfos(String code,String societe,String contact,String fonction,String adresse,String ville,String region,String code_postal,String pays,String telephone,String fax) throws SQLException{
        
        String sql = "UPDATE Client SET Societe,Contact,Fonction,Adresse,Ville,Region,Code_postal,Pays,Telephone,Fax = (?,?,?,?,?,?,?,?,?,?) WHERE Code = ?";
        try (	Connection myConnection = myDataSource.getConnection();
			PreparedStatement statement = myConnection.prepareStatement(sql)){
            
            myConnection.setAutoCommit(false);
            try{
                statement.setString(1, societe);
                statement.setString(2, contact);
                statement.setString(3, fonction);
                statement.setString(4, adresse);
                statement.setString(5, ville);
                statement.setString(6, region);
                statement.setString(7, code_postal);
                statement.setString(8, pays);
                statement.setString(9, telephone);
                statement.setString(10, fax);
                
                statement.setString(11, code);
                
                int nbUpdated = statement.executeUpdate();
                if (nbUpdated == 0) {
                    throw new SQLException();
                }
                //Pas de problème, on valide les changements 
                myConnection.commit();
                
            } catch (SQLException ex){
                myConnection.rollback();
                throw ex ;
            } finally {
                myConnection.setAutoCommit(true);
            }
        }
    }
}
