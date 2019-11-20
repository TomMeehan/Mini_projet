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
public class DAO {
    
      	protected final DataSource myDataSource;
        
	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
    
    public void updateClientInfos(String societe,String contact,String fonction,String adresse,String ville,String region,String code_postal,String pays,String telephone,String fax){
        
    }
}
