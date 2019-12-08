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
import org.junit.Ignore;
/**
 *
 * @author pedago
 */
public class DAOTest {
    
    private static DataSource myDataSource;
    private static Connection myConnection ;
    private DAO dao;
  
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
    public void getClientTest() throws Exception {
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
    
    @Test
    public void addCommandeTestError() throws SQLException {
        
        //TODO
        
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
}
