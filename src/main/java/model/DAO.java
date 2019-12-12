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
        String sql = "SELECT * FROM Client WHERE Code = ?";
        Client me = null;
        
        try (Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
               stmt.setString(1, Code);
               try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()){
                        
                        
                        String societe = rs.getString("societe");    
                        String contact = rs.getString("contact");
                        String fonction = rs.getString("fonction");
                        String adresse = rs.getString("adresse");
                        String ville = rs.getString("ville");
                        String region = rs.getString("region");
                        String code_postal = rs.getString("code_postal");
                        String pays = rs.getString("pays");
                        String telephone = rs.getString("telephone");
                        String fax = rs.getString("fax");
                        me = new Client(Code,societe,contact,fonction,adresse,ville,region,code_postal,pays,telephone,fax);
                    }
                                         
               }catch (Exception e){
                   System.out.println(e.getMessage());
               }
        }
        return me;
    }
    
    public Produit getProduit(int ref) throws SQLException{
        Produit p = null;

        String sql = "SELECT * FROM Produit WHERE Reference = ?";
        
        String sql2 = "SELECT * FROM Categorie WHERE code = ?";

        try(Connection conn = myDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(sql2)){
            stmt.setInt(1, ref);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    
                int codeCat = rs.getInt("Categorie");
                
                stmt2.setInt(1,codeCat);
                
                ResultSet rsCat = stmt2.executeQuery();
                
                rsCat.next();
                
                int code = rsCat.getInt("Code");
                String libelle = rsCat.getString("Libelle");
                String desc = rsCat.getString("Description");
                
                Categorie cat = new Categorie(code,libelle,desc);

                String nom = rs.getString("Nom");
                int fournisseur =rs.getInt("Fournisseur");
                String Quantite_p_u = rs.getString("Quantite_par_unite");
                float prix = rs.getFloat("Prix_Unitaire");
                int stock = rs.getInt("Unites_en_stock");
                int commandees = rs.getInt("Unites_commandees");
                int reappro = rs.getInt("Niveau_de_reappro");
                int indispo = rs.getInt("Indisponible");

                p = new Produit(cat, ref, nom, fournisseur, Quantite_p_u, prix, stock, commandees, reappro, indispo);
                    
                }
            }
        }
        return p;
    }
    
    public boolean checkLogin(String username, String password) throws SQLException,Exception {
        String sql = "SELECT * FROM Client WHERE CONTACT = ? AND Code = ?";
        
        try (Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
               stmt.setString(1, username);
               stmt.setString(2, password);
               try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
               }
        }
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
        String sql2 = "SELECT * FROM Categorie WHERE code = ?";
        
        List<Produit> result = new ArrayList<>();
        
        try (Connection connection = myDataSource.getConnection();
            Statement stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement(sql2); ){
            ResultSet rs = stmt.executeQuery(sql);
            
            
            while (rs.next()){
                
                int codeCat = rs.getInt("Categorie");
                
                pstmt.setInt(1,codeCat);
                
                ResultSet rsCat = pstmt.executeQuery();
                
                rsCat.next();
                
                int code = rsCat.getInt("Code");
                String libelle = rsCat.getString("Libelle");
                String desc = rsCat.getString("Description");
                
                Categorie categorie = new Categorie(code,libelle,desc);
                
                int reference = rs.getInt("Reference");
                String nom = rs.getString("Nom");
                int fournisseur = rs.getInt("Fournisseur");
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
    
    public List<Produit> getProduitFromCategorie(int codeCat) throws SQLException{
        String sql = "SELECT * FROM Produit WHERE categorie = ?";
        String sql2 = "SELECT * FROM Categorie WHERE code = ?";
        
        List<Produit> result = new ArrayList<>();
        
        try (Connection connection = myDataSource.getConnection();
            PreparedStatement pstmt1 = connection.prepareStatement(sql);
            PreparedStatement pstmt2 = connection.prepareStatement(sql2); ){
            
            pstmt1.setInt(1,codeCat);
            
            ResultSet rs = pstmt1.executeQuery();
                
            pstmt2.setInt(1,codeCat);

            ResultSet rsCat = pstmt2.executeQuery();

            rsCat.next();

            while (rs.next()){
                int code = rsCat.getInt("Code");
                String libelle = rsCat.getString("Libelle");
                String desc = rsCat.getString("Description");
                
                Categorie categorie = new Categorie(code,libelle,desc);
                
                int reference = rs.getInt("Reference");
                String nom = rs.getString("Nom");
                int fournisseur = rs.getInt("Fournisseur");
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
                    throw new SQLException("Erreur lors de la mise à jour, code invalide");
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
    
    public int addCommande(String client, String saisie_le, String envoyee_le, String port, String destinataire, String adresse_livraison, String ville_livraison, String region_livraison, String code_postal_livrais, String pays_livraison, float remise,
                            int[] produitID, int[] quantite ) throws Exception{
    if (produitID.length != quantite.length){
                    throw new Exception("Produits != Quantite");
                }    
    String sql1 = "INSERT INTO Commande (Client,saisie_le,Envoyee_le,Port,Destinataire,Adresse_livraison,Ville_livraison,Region_livraison,Code_postal_livrais,Pays_livraison,Remise) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";    
    
    String sql2 = "INSERT INTO Ligne VALUES (?,?,?)";
    
    String sql3 = "UPDATE Produit SET Unites_en_stock = Unites_en_stock + ?, Unites_Commandees = Unites_en_stock - ? WHERE Reference = ?";
    int updtTransa=-1;
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
                int updt = stmt.executeUpdate();
                if (updt==0){
                    throw new SQLException("Erreur lors de la creation");
                            }
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                generatedKeys.next();
                int numLigne=generatedKeys.getInt(1);
                //Logger.getLogger("DAO").log(Level.INFO, "Nouvelle clé générée pour INVOICE : {0}", NumLigne);
                try (PreparedStatement ligStmt = connection.prepareStatement(sql2)) {
                    try (PreparedStatement transa = connection.prepareStatement(sql3)){
                        
                        for (int produit = 0; produit < produitID.length; produit++){
                        ligStmt.clearParameters();
                        System.out.println("\n -- " + produit + " : " + numLigne + "," + produitID[produit] + "," + quantite[produit]);
                        
                        ligStmt.setInt(1, numLigne);
                        ligStmt.setInt(2, produitID[produit]);
                        ligStmt.setInt(3, quantite[produit]);

                        int n = ligStmt.executeUpdate();
                        
                        transa.setInt(1,quantite[produit]);
                        transa.setInt(2,quantite[produit]);
                        transa.setInt(3,produitID[produit]);
                        
                        updtTransa = transa.executeUpdate();
                        if (updtTransa == 0){
                            throw new SQLException("Quantité demandée supérieure au stock");
                        }
                        }
                    }
                    
                }
                connection.commit();
                } catch (SQLException ex){
                connection.rollback();
                throw ex;
                
                    } finally {
                connection.setAutoCommit(true);
                    }
        }
    
    return updtTransa;
    }
    
        public List<String> getPays() throws SQLException{
        String sql = "SELECT * FROM Client";
        
        List<String> tousPays = new ArrayList();
        
        try (Connection connection = myDataSource.getConnection();
            Statement stmt = connection.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                tousPays.add(rs.getString("Pays"));
            }
               
        }
        return tousPays ;  
    } 
    
        
    //Fonctions admin
    public List<Pair<String,Integer>> chiffAffCat(String dateDep, String dateFin) throws SQLException{
        /*String sql = "SELECT (Produit.Prix_unitaire*Produit.Unites_commandees) AS Chiffre_affaire FROM ((Produit INNER JOIN Ligne ON Produit.Reference = Ligne.Produit) t "
                + " INNER JOIN Commande ON t.Commande = Commande.Numero) x"
                + " WHERE x.Categorie = ? AND Commande.SaisieLe BETWEEN ? AND ?";*/
        
        String sql = "SELECT (Produit.Prix_unitaire*Produit.Unites_commandees) AS Chiffre_affaire FROM Produit,Ligne,Commande "
                + "WHERE Produit.Reference = Ligne.Produit AND Ligne.Commande = Commande.Numero AND "
                + "Produit.Categorie = ? AND Commande.Saisie_le BETWEEN ? AND ?";
        
        
        float result = 0;
        List<Categorie> listeCat=null;
        List<Pair<String,Integer>> chiffAffCat =  new ArrayList();
                
        try (Connection connection = myDataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            
            listeCat = this.getCategories();
            
            
            for (Categorie cat : listeCat) {
                pstmt.clearParameters();
                pstmt.setInt(1,cat.getCode());
                pstmt.setString(2,dateDep);
                pstmt.setString(3,dateFin);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()){
                    float chiffreAff=rs.getFloat("Chiffre_affaire");                
                    result  += chiffreAff;

                }

                chiffAffCat.add(new Pair(cat.getLibelle(),result));
            }

        }
        return chiffAffCat;
        
        
    }
    
    public List<Pair<String,Integer>> chiffAffPays(String dateDep, String dateFin) throws SQLException{
        /*
        String sql = "SELECT Prix_unitaire*Unites_commandees AS Chiffre d'affaire FROM Produit p INNER JOIN Ligne l ON p.Reference = l.Produit"
                + "                           INNER JOIN Commande c ON l.Commande = c.Numero "
                + "WHERE c.pays = ? AND c.SaisieLe BETWEEN ? AND ?";*/
        
        String sql = "SELECT (Produit.Prix_unitaire*Produit.Unites_commandees) AS Chiffre_affaire FROM Produit,Ligne,Commande "
                + "WHERE Produit.Reference = Ligne.Produit AND Ligne.Commande = Commande.Numero AND "
                + "Client.Pays = ? AND Commande.Saisie_le BETWEEN ? AND ?";
        
        
        float result = 0;
        List<String> listePays=null;
        List<Pair<String,Integer>> chiffAffPays =  new ArrayList();
                
        try (Connection connection = myDataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            
            listePays = this.getPays();
            //System.out.println(listePays.get(0));
            
            for (String pays : listePays) {
                pstmt.clearParameters();
                pstmt.setString(1,pays);
                pstmt.setString(2,dateDep);
                pstmt.setString(3,dateFin);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()){
                    float chiffreAff=rs.getFloat("Chiffre_affaire");                
                    result  += chiffreAff;

                }

                chiffAffPays.add(new Pair(pays,result));
            }

        }
        return chiffAffPays;
        
        
    }
    
    public float chiffAffClient(int client, String dateDep, String dateFin) throws SQLException{
        String sql = "SELECT Prix_unitaire*Unites_commandees AS Chiffre d'affaire FROM Produit p INNER JOIN Ligne l ON p.Reference = l.Produit"
                + "                           INNER JOIN Commande c ON l.Commande = c.Numero "
                + "WHERE c.Client = ? AND c.SaisieLe BETWEEN ? AND ?";
        float result = 0;
        try (Connection connection = myDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setInt(1,client);
            stmt.setString(2,dateDep);
            stmt.setString(3,dateFin);
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                float chiffreAff=rs.getFloat("Chiffre d'affaire");                
                result  += chiffreAff;
                
            }
        }
        return result;
    }
    
    public void addProduit(String nom,int fournisseur,int categorie,String quantite_par_unite, float prix_unitaire,int unites_en_stock,int unites_commandees,int niveau_de_reappro,int indisponible) throws SQLException{
        String sql = "INSERT INTO Produit(Nom,Fournisseur,Categorie,Quantite_par_unite,Prix_unitaire,Unites_en_stock,Unites_commandees,Niveau_de_reappro,Indisponible) VALUES (?,?,?,?,?,?,?,?,?) ";

        try (Connection connection = myDataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            connection.setAutoCommit(false);
            stmt.setString(1, nom);
            stmt.setInt(2, fournisseur);

            int updt = stmt.executeUpdate();

            if (updt == 0) {
                throw new SQLException("Echec de la création du produit");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys();){
                if ( !generatedKeys.next()){
                    throw new SQLException("Echec de la création du produit");
                }
           connection.commit();
         } catch (SQLException ex){
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
        
    }
    
    public void deleteProd(int reference) throws SQLException{
        
        String sql =   "DELETE FROM Produit WHERE Reference = ?";
        
        try (Connection connection = myDataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            stmt.setInt(1, reference);

            try {
                int updt = stmt.executeUpdate();
                if (updt==0){
                    throw new SQLException("Produit inexistant");
                }
                connection.commit();
            }   catch (Exception ex){
                connection.rollback();
                }   finally {
                connection.setAutoCommit(true);
                }
        }
    }
    
   public void updateProd(int ref,String nom,int fournisseur,int categorie,String quantite_par_unite, float prix_unitaire,int unites_en_stock,int unites_commandees,int niveau_de_reappro,int indisponible) throws SQLException{
               String sql = "UPDATE Produit SET Nom = ?, Fournisseur = ?,Categorie = ?,Quantite_par_unite = ?, Prix_unitaire = ?,Unites_en_stock = ?,Unites_commandees = ?, Niveau_de_reappro = ?, Indisponible = ? WHERE Reference = ?";
               
        try (	Connection myConnection = myDataSource.getConnection();
                PreparedStatement statement = myConnection.prepareStatement(sql)){
            
            myConnection.setAutoCommit(false);
            try{
                statement.setString(1, nom);
                statement.setInt(2, fournisseur);
                statement.setInt(3, categorie);
                statement.setString(4, quantite_par_unite);
                statement.setFloat(5, prix_unitaire);
                statement.setInt(6, unites_en_stock);
                statement.setInt(7, unites_commandees);
                statement.setInt(8, niveau_de_reappro);
                statement.setInt(9, indisponible);

                statement.setInt(10, ref);
                
                int nbUpdated = statement.executeUpdate();
                if (nbUpdated == 0) {
                    throw new SQLException("Erreur lors de la mise à jour, produit invalide");
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
   
public class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        int hashFirst = first != null ? first.hashCode() : 0;
        int hashSecond = second != null ? second.hashCode() : 0;

        return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return 
            ((  this.first == otherPair.first ||
                ( this.first != null && otherPair.first != null &&
                  this.first.equals(otherPair.first))) &&
             (  this.second == otherPair.second ||
                ( this.second != null && otherPair.second != null &&
                  this.second.equals(otherPair.second))) );
        }

        return false;
    }

    @Override
    public String toString()
    { 
           return "(" + first + ", " + second + ")"; 
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}
}
