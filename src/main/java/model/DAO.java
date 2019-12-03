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
                       me = new Client(Code,rs.getString("societe"),rs.getString("contact"),rs.getString("fonction"),rs.getString("adresse"),rs.getString("ville"),rs.getString("region"),rs.getString("code_postal"),rs.getString("pays"),rs.getString("telephone"),rs.getString("fax"));
                       
               }
        }
        return me;
    }

    public List<Commande> getCommandes(String client) throws SQLException{
        String sql = "SELECT * FROM Commande WHERE Client = ?";
        List<Commande> result = new ArrayList<>();
        try (Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setString(1, client);
            ResultSet rs = stmt.executeQuery();            
            
            while (rs.next()){
                int numero=rs.getInt("Numero");
                String saisie_le = rs.getString("saisie_le");
                String envoyee_le = rs.getString("envoyee_le");
                float port = rs.getFloat("Port");
                String Destinataire = rs.getString("Destinataire");
                String adresse_livraison = rs.getString("Adresse_livraison");
                String ville_livraison = rs.getString("Ville_livraison");
                String region_livraison = rs.getString("Region_livraison");
                String code_postal_livrais = rs.getString("Code_postal_livrais");
                String pays_livraison = rs.getString("Pays_livraison");
                float remise = rs.getFloat("Remise");
                
                result.add(new Commande(numero, client, saisie_le, envoyee_le, port, Destinataire, adresse_livraison, ville_livraison, region_livraison, code_postal_livrais, pays_livraison, remise));
            }
        }
        return result;
    }
  
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
                String nom = rs.getString("Nom");
                int fournisseur = rs.getInt("Fournisseur");
                int categorie = rs.getInt("Categorie");
                String quantite_par_unite = rs.getString("Quantite_par_unite");
                int prix_unitaire = rs.getInt("Prix_unitaire");
                int unites_en_stock = rs.getInt("Unites_en_stock");
                int unites_commandees = rs.getInt("Unites_commandees");
                int niveau_de_reapprovi = rs.getInt("Niveau_de_reappro");
                int indisponible = rs.getInt("Indisponible");
                
                result.add(new Produit(categorie,reference,nom,fournisseur,quantite_par_unite,prix_unitaire,unites_en_stock,unites_commandees,niveau_de_reapprovi,indisponible));
                
            }
        }
        return result;
    }
            
    public void updateClientInfos(String code,String societe,String contact,String fonction,String adresse,String ville,String region,String code_postal,String pays,String telephone,String fax) throws SQLException{
        
        String sql = "UPDATE Client SET Societe = ?, Contact = ?, Fonction = ?, Adresse = ?, Ville = ?, Region = ?, Code_postal = ?, Pays = ?, Telephone = ?, Fax = ? WHERE Code = ?";
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
    
    public void addCommande(String client, String saisie_le, String envoyee_le, String port, String destinataire, String adresse_livraison, String ville_livraison, String region_livraison, String code_postal_livrais, String pays_livraison, float remise,
                            int[] produitID, int[] quantite ) throws Exception{
    if (produitID.length != quantite.length){
                    throw new Exception("Produits != Quantite");
                }    
    String sql1 = "INSERT INTO Commande (Client,SaisieLe,EnvoyeeLe,Port,Destinataire,Adresse_livraison,Ville_Livraison,Ville_livraison,Region_livraison,Code_postal_livraison,Pays_livraison,Remise) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";    
    
    String sql2 = "INSERT INTO Ligne VALUES (?,?,?)";
    
    try (Connection connection = myDataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)){
            connection.setAutoCommit(false);
            stmt.setString(1, client);
            stmt.setString(2, saisie_le);
            stmt.setString(3, envoyee_le);
            stmt.setString(4, port);
            stmt.setString(5, destinataire);
            stmt.setString(6, adresse_livraison);
            stmt.setString(7, ville_livraison);
            stmt.setString(8, region_livraison);
            stmt.setString(9, code_postal_livrais);
            stmt.setString(10, pays_livraison);
            stmt.setFloat(11, remise);
            try {
                stmt.executeUpdate();
                
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                generatedKeys.next();
                int numLigne=generatedKeys.getInt("Numero");
                //Logger.getLogger("DAO").log(Level.INFO, "Nouvelle clé générée pour INVOICE : {0}", NumLigne);
                try (PreparedStatement ligStmt = connection.prepareStatement(sql2)) {
                    for (int produit = 0; produit < produitID.length; produit++){
                        ligStmt.clearParameters();
                        ligStmt.setInt(1, numLigne);
                        ligStmt.setInt(2, produitID[produit]);
                        ligStmt.setInt(3, quantite[produit]);

                        int n = ligStmt.executeUpdate();
                    }
                }
                connection.commit();
            } catch (Exception ex){
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
    }
  }
    
    //Fonctions admin
    public void chiffAffCat(int categorie, String dateDep, String dateFin){
        String sql = "SELECT * FROM Produit p INNER JOIN Ligne l ON p.Reference = l.Produit"
                + "                           INNER JOIN Commande c ON l.Commande = c.Numero "
                + "WHERE p.Categorie = ?";
        
        
    }
    
    public void chiffAffPays(String pays, String dateDep, String dateFin){
        
    }
    
    public void chiffAffClient(int client, String dateDep, String dateFin){
        
    }
}
