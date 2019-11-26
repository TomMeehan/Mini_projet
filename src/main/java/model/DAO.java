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
import java.util.ArrayList;
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
                       while (rs.next()) {
                            me = new Client(Code,rs.getString("societe"),rs.getString("contact"),rs.getString("fonction"),rs.getString("adresse"),rs.getString("ville"),rs.getString("region"),rs.getString("code_postale"),rs.getString("pays"),rs.getString("telephone"),rs.getString("fax"));
                       }
               }
        }
        return me;
    }
    public void updateClientInfos(String societe,String contact,String fonction,String adresse,String ville,String region,String code_postal,String pays,String telephone,String fax){
        
    }
}
