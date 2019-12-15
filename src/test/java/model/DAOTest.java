/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import model.DAO.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
/**
 *
 * @author pedago
 */
public class DAOTest {
    
    private static DataSource myDataSource;
    private static Connection myConnection ;
    private DAO dao;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
  
    @Before
    public void setUp() throws SQLException, IOException, SqlToolError {
        myDataSource = getDataSource();
        myConnection = myDataSource.getConnection();
        // On crée le schema de la base de test
        executeSQLScript(myConnection, "../listeners/comptoirs_schema_derby.sql");
        // On y met des données
        executeSQLScript(myConnection, "../listeners/comptoirs_data.sql");		

        dao = new DAO(myDataSource);
    }
    
    private void executeSQLScript(Connection connexion, String filename)  throws IOException, SqlToolError, SQLException {
        // On initialise la base avec le contenu d'un fichier de test
        String sqlFilePath = DAOTest.class.getResource(filename).getFile();
        SqlFile sqlFile = new SqlFile(new File(sqlFilePath));

        sqlFile.setConnection(connexion);
        sqlFile.execute();
        sqlFile.closeReader();		
    }
    
    // TESTS ICI
    
    @Test
    public void getClientInfosTest() throws Exception {
        Client c;
        String code = "ALFKI";
        
        c = dao.getClientInfos(code);
        
        assertEquals("030-0074321",c.getTelephone());
    }
    
    @Test
    public void getCategoriesTest() throws SQLException {
        List<Categorie> listCat = new ArrayList<>();
        
        listCat = dao.getCategories();
        
        assertEquals("Boissons", listCat.get(0).getLibelle());
        assertEquals("Viandes", listCat.get(5).getLibelle());
    }
    
    @Test
    public void getProduitsTest() throws SQLException {
        List<Produit> listProd = new ArrayList<>();
        
        listProd = dao.getProduits();
        
        assertEquals(1, listProd.get(0).getReference());
        assertEquals("Grandma's Boysenberry Spread", listProd.get(5).getNom());
    }
    
    @Test(expected=Exception.class)
    public void updateClientTestError() throws Exception {
        
        Client cBefore = dao.getClientInfos("ALFKI");
        
        dao.updateClientInfos("ALFKI", null, "Jean Bon", "Représentant(e)", "Obere Str. 57", "Berlin", null, "12209", "Allemagne", "030-0074321", "030-0076545");
        
        Client cAfter = dao.getClientInfos("ALFKI");
        
        assertFalse(cBefore.getSociete().equals("Jean Bon"));
        assertEquals(cBefore.getContact(),cAfter.getContact());
        
   
    }
    
    @Test
    public void updateClientTestSuccess() throws Exception {
        
        Client cBefore = dao.getClientInfos("ALFKI");
        
        dao.updateClientInfos("ALFKI", "Alfreds Futterkiste", "Jean Bon", "Représentant(e)", "Obere Str. 57", "Berlin", null, "12209", "Allemagne", "030-0074321", "030-0076545");
        
        Client cAfter = dao.getClientInfos("ALFKI");
        
        assertEquals(cBefore.getSociete(),cAfter.getSociete());
        assertEquals("Jean Bon", cAfter.getContact());
        
    }
    
    @Test
    public void getCommandeOfClientTest() throws Exception {
        
        Client c = dao.getClientInfos("ALFKI");
        
        List<Commande> listComm = new ArrayList<>();
        
        listComm = dao.getCommandes(c.getCode());
        
        assertEquals(10702,listComm.get(0).getNumero());
        assertEquals(4, listComm.size());
        
    }
  
    @After
    public void tearDown() throws SQLException {
        myConnection.close();
    }

    public static DataSource getDataSource() {
        org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
        ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
        ds.setUser("sa");
        ds.setPassword("sa");
        return ds;
    }


    /**
     * Test of checkLogin method, of class DAO.
     */
    @Test
    public void testCheckLogin() throws Exception {
        
        String username = "Maria Anders";
        String password = "ALFKI";
        
        dao.checkLogin(username, password);
        
        assertEquals(true, dao.checkLogin(username, password));
        
        

    }
    
    @Test
    public void testLoginFailed() throws Exception {
        
        String username = "Maria And";
        String password = "ALFKI";
        
        dao.checkLogin(username, password);
        
        assertEquals(false, dao.checkLogin(username, password));
        
        

    }


    /**
     * Test of getProduitFromCategorie method, of class DAO.
     */
    @Test
    public void testGetProduitFromCategorie() throws Exception {
        List<Produit> lp;
        int codeCat = 1 ;
        lp = dao.getProduitFromCategorie(codeCat);
        
        
        
        assertEquals("Chai", lp.get(0).getNom());

    }


    
    @Test
    public void testgetProduitSuccess() throws SQLException{
        Produit p = null;
        int reference = 1;
        
        p = dao.getProduit(reference);
        System.out.println("Produit dont la ref = "+ reference + " : " + p.getNom());
        
        
    }

    /**
     * Test of addCommande method, of class DAO.
     */
    @Test
    public void testAddCommandeSuccess() throws Exception {
        System.out.println("Commande success");
        String client = "ALFKI";
        String saisie_le = "1994-08-04";
        String envoyee_le = "1994-08-05";
        float port = 420.69F;
        String destinataire = "Gamer";
        String adresse_livraison = "420 rue de la PLS";
        String ville_livraison = "OkLand";
        String region_livraison = "Salt Lake";
        String code_postal_livrais = "69420";
        String pays_livraison = "Yikes";
        float remise = 0.0F;
        int[] produitID = {1,2,3};
        int[] quantite = {3,1,3};
        
        List<Commande> lc1 = dao.getCommandes("ALFKI");
        int stock = dao.getProduit(1).getUnites_en_stock();
        int commandees = dao.getProduit(1).getUnites_commandees();
        System.out.println("Stock av = "+stock);
        System.out.println("Unit commandees av= "+commandees);
        //System.out.println("Nouvelle commande : "+dao.getCommandes("ALFKI"));
        
        
        dao.addCommande(client, saisie_le, envoyee_le, port, destinataire, adresse_livraison, ville_livraison, region_livraison, code_postal_livrais, pays_livraison, remise,produitID,quantite);
        
        int stockAp = dao.getProduit(1).getUnites_en_stock();
        int commandeesAp = dao.getProduit(1).getUnites_commandees();
        System.out.println("Stock ap = "+stockAp);
        System.out.println("Unit commandees ap= "+commandeesAp);
        List<Commande> lc2 = dao.getCommandes("ALFKI");
        //System.out.println("Nouvelle commande : "+dao.getCommandes("ALFKI"));
        
        assertEquals(lc1.size()+1,lc2.size());
        assertEquals(stock-3, dao.getProduit(1).getUnites_en_stock());
        assertEquals(commandees+3, dao.getProduit(1).getUnites_commandees());
        
        dao.addCommande(client, saisie_le, envoyee_le, port, destinataire, adresse_livraison, ville_livraison, region_livraison, code_postal_livrais, pays_livraison, remise,produitID,quantite);
        
        List<Commande> lc3 = dao.getCommandes("ALFKI");
        assertEquals(lc1.size()+2,lc3.size());
        assertEquals(lc2.size()+1,lc3.size());
        
        assertEquals(stock-6, dao.getProduit(1).getUnites_en_stock());
        assertEquals(commandees+6, dao.getProduit(1).getUnites_commandees());

        System.out.println("----------------------------------------");     
    }    
    
    /**
     * Test of addCommande method, of class DAO.
     */
    @Test (expected = Exception.class)
    public void testAddCommandeFail() throws Exception {
        System.out.println("Commande fail");
        String client = "ALED";
        String saisie_le = "1994-08-04";
        String envoyee_le = "1994-08-05";
        float port = 420.69F;
        String destinataire = "Gamer";
        String adresse_livraison = "420 rue de la PLS";
        String ville_livraison = "OkLand";
        String region_livraison = "Salt Lake";
        String code_postal_livrais = "69420";
        String pays_livraison = "Yikes";
        float remise = 0.0F;
        int[] produitID = {1,2,3};
        int[] quantite = {3,1,3};
        
        int stock = dao.getProduit(1).getUnites_en_stock();
        int commandees = dao.getProduit(1).getUnites_commandees();
        System.out.println("Stock av = "+stock);
        System.out.println("Unit commandees av= "+commandees);

        
        
        dao.addCommande(client, saisie_le, envoyee_le, port, destinataire, adresse_livraison, ville_livraison, region_livraison, code_postal_livrais, pays_livraison, remise,produitID,quantite);
        
        
        int stockAp = dao.getProduit(1).getUnites_en_stock();
        int commandeesAp = dao.getProduit(1).getUnites_commandees();
        System.out.println("Stock ap = "+stockAp);
        System.out.println("Unit commandees ap= "+commandeesAp);

        
        assertEquals(stock, stockAp);
        assertEquals(commandees, commandees);  
        System.out.println("----------------------------------------");    
    }
    
    

    /**
     * Test of chiffAffCat method, of class DAO.
     */
    @Test
    public void testChiffAffCat() throws Exception {
        String dateDeb =  "1994-08-04";
        String dateFin = "1996-06-05";
        
        List<Pair<String,Float>> ok =null;
        try {
           ok = dao.chiffAffCat(dateDeb, dateFin);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("CA PAR CATEGORIE");
        for (int i = 0; i < ok.size(); i++) {
            System.out.println(ok.get(i));
        }
        
        System.out.println("-------------------------------------");
    }

    /**
     * Test of chiffAffPays method, of class DAO.
     */
    @Test
    public void testChiffAffPays() throws Exception {
        String dateDeb =  "1994-08-04";
        String dateFin = "1996-06-05";
        
        List<Pair<String,Float>> ok =null;
        try {
           ok = dao.chiffAffPays(dateDeb, dateFin);
            //System.out.println("Aled");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("CA PAR PAYS");
        for (int i = 0; i < ok.size(); i++) {
            System.out.println(ok.get(i));
        }
        System.out.println("-------------------------------------");
    }

    /**
     * Test of chiffAffClient method, of class DAO.
     */
    @Test
    public void testChiffAffClient() throws Exception {
        //System.out.println("rentre dans testChiffAffClient"); 
        String dateDeb =  "1994-08-04";
        String dateFin = "1996-06-05";
        
        List<Pair<String,Float>> ok =null;
        try {
           ok = dao.chiffAffClient(dateDeb, dateFin);
           
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("CA PAR CLIENT");
        float tot = 0;
        for (int i = 0; i < ok.size(); i++) {
            System.out.println(ok.get(i));
            tot += ok.get(i).getSecond();
        }
        System.out.println("Total = " + tot);
        System.out.println("-------------------------------------");
    }

    /**
     * Test of addProduit method, of class DAO.
     */
    @Test
    public void testAddProduitSuccess() throws Exception {

        String nom = "Gils";
        int fournisseur = 1;
        int categorie = 3;
        String quantite_par_unite = "Une poignée";
        float prix_unitaire = 1.5F;
        int unites_en_stock = 500;
        int unites_commandees = 1;
        int niveau_de_reappro = 20;
        int indisponible = 0;

        dao.addProduit(nom, fournisseur, categorie, quantite_par_unite, prix_unitaire, unites_en_stock, unites_commandees, niveau_de_reappro, indisponible);
        
        
        assertEquals("Gils",dao.getProduit(78).getNom());
        System.out.println("AddProduit success = "+dao.getProduit(78).getNom());
        System.out.println("----------------------------------------");
        
    }

    @Test (expected = Exception.class)
    public void testAddProduitFail() throws Exception {

        String nom = "Aled";
        int fournisseur = 1;
        int categorie = 3;
        String quantite_par_unite = "En masse";
        float prix_unitaire = 1.5F;
        int unites_en_stock = 500;
        int unites_commandees = 1;
        int niveau_de_reappro = 20;
        int indisponible = 10;

        dao.addProduit(nom, fournisseur, categorie, quantite_par_unite, prix_unitaire, unites_en_stock, unites_commandees, niveau_de_reappro, indisponible);
        
        
        //assertEquals("Gils",dao.getProduit(79).getNom());
        System.out.println("addProduit fail "+dao.getProduit(79).getNom());
        
    }    

    /**
     * Test of deleteProd method, of class DAO.
     */
    @Test //(expected = Exception.class)
    public void testDeleteProdSuccess() throws Exception {
        int ref = 1 ;
        List<Produit> lp = dao.getProduitFromCategorie(1);
        
        dao.deleteProd(ref);
        
        List<Produit> lp2 = dao.getProduitFromCategorie(1);
        assertNotEquals(lp.size(), lp2.size());
        /*
        for (Produit produit : lp) {
            System.out.println(produit.getNom());
            
        }
        System.out.println("-------------------------------------");      
        for (Produit produit : lp2) {
            System.out.println(produit.getNom());
            
        }
        System.out.println("-------------------------------------");*/
    }
    
    
    @Test
    public void testDeleteProdFail() throws Exception {
        int ref =8888 ;
        List<Produit> lp = dao.getProduitFromCategorie(1);
        
        dao.deleteProd(ref);
        
        List<Produit> lp2 = dao.getProduitFromCategorie(1);
        assertEquals(lp.size(), lp2.size());

    }

    /**
     * Test of updateProd method, of class DAO.
     */
    @Test
    public void testUpdateProdSuccess() throws Exception {
        int ref = 1;
        String nom = "Box";
        int fournisseur = 1;
        int categorie = 1;
        String quantite_par_unite = "10 packs";
        float prix_unitaire = 1.5F;
        int unites_en_stock=0;
        int unites_commandees=0;
        int niveau_de_reappro=0;
        int indisponible=1;
        
        dao.updateProd(ref, nom, fournisseur, categorie, quantite_par_unite, prix_unitaire, unites_en_stock, unites_commandees, niveau_de_reappro, indisponible);
        
        assertEquals("Box", dao.getProduit(ref).getNom());
    }
    
    @Test (expected = Exception.class)
    public void testUpdateProdFail() throws Exception {
        int ref = 1;
        String nom = "Box";
        int fournisseur = 1;
        int categorie = 1;
        String quantite_par_unite = "10 packs";
        float prix_unitaire = 1.5F;
        int unites_en_stock=0;
        int unites_commandees=0;
        int niveau_de_reappro=0;
        int indisponible=5;
        
        dao.updateProd(ref, nom, fournisseur, categorie, quantite_par_unite, prix_unitaire, unites_en_stock, unites_commandees, niveau_de_reappro, indisponible);
        
        assertEquals("Chai", dao.getProduit(ref).getNom());
    }
}
