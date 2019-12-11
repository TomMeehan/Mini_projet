/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
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
    public void testgetProduit() throws SQLException{
        Produit p = null;
        int reference = 1;
        
        p = dao.getProduit(reference);
        System.out.println(p.getNom());
        
        
    }

    /**
     * Test of addCommande method, of class DAO.
     */
    @Test
    public void testAddCommande() throws Exception {
        System.out.println("addCommande");
        String client = "";
        String saisie_le = "";
        String envoyee_le = "";
        String port = "";
        String destinataire = "";
        String adresse_livraison = "";
        String ville_livraison = "";
        String region_livraison = "";
        String code_postal_livrais = "";
        String pays_livraison = "";
        float remise = 0.0F;
        int[] produitID = null;
        int[] quantite = null;
        DAO instance = null;
        instance.addCommande(client, saisie_le, envoyee_le, port, destinataire, adresse_livraison, ville_livraison, region_livraison, code_postal_livrais, pays_livraison, remise, produitID, quantite);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chiffAffCat method, of class DAO.
     */
    @Test
    public void testChiffAffCat() throws Exception {
        System.out.println("chiffAffCat");
        int categorie = 0;
        String dateDep = "";
        String dateFin = "";
        DAO instance = null;
        float expResult = 0.0F;
        float result = instance.chiffAffCat(categorie, dateDep, dateFin);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chiffAffPays method, of class DAO.
     */
    @Test
    public void testChiffAffPays() throws Exception {
        System.out.println("chiffAffPays");
        String pays = "";
        String dateDep = "";
        String dateFin = "";
        DAO instance = null;
        float expResult = 0.0F;
        float result = instance.chiffAffPays(pays, dateDep, dateFin);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chiffAffClient method, of class DAO.
     */
    @Test
    public void testChiffAffClient() throws Exception {
        System.out.println("chiffAffClient");
        int client = 0;
        String dateDep = "";
        String dateFin = "";
        DAO instance = null;
        float expResult = 0.0F;
        float result = instance.chiffAffClient(client, dateDep, dateFin);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProduit method, of class DAO.
     */
    @Test
    public void testAddProduit() throws Exception {
        System.out.println("addProduit");
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
        
        List<Produit> lp = dao.getProduitFromCategorie(3);
        
        System.out.println();
        
    }

    /**
     * Test of deleteProd method, of class DAO.
     */
    @Test
    public void testDeleteProd() throws Exception {
        System.out.println("deleteProd");
        int reference = 0;
        DAO instance = null;
        instance.deleteProd(reference);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProd method, of class DAO.
     */
    @Test
    public void testUpdateProd() throws Exception {
        System.out.println("updateProd");
        int ref = 0;
        String nom = "";
        int fournisseur = 0;
        int categorie = 0;
        String quantite_par_unite = "";
        float prix_unitaire = 0.0F;
        int unites_en_stock = 0;
        int unites_commandees = 0;
        int niveau_de_reappro = 0;
        int indisponible = 0;
        DAO instance = null;
        instance.updateProd(ref, nom, fournisseur, categorie, quantite_par_unite, prix_unitaire, unites_en_stock, unites_commandees, niveau_de_reappro, indisponible);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
