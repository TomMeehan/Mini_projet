/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.sql.DataSource;

/**
 *
 * @author pedago
 */
public class DataSourceFactory {
    
    public static DataSource getDataSource () {
        
        org.apache.derby.jdbc.ClientDataSource ds = new org.apache.derby.jdbc.ClientDataSource();
        ds.setDatabaseName("comptoirs");
        ds.setUser("god");
        ds.setPassword("god");
        ds.setServerName("localhost");
        ds.setPortNumber(1527);
        
        return ds;
    }


}
