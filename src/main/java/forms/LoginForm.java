/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import beans.User;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import model.Client;
import model.DAO;
import model.DataSourceFactory;

/**
 *
 * @author Tom
 */
public final class LoginForm {
    
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_PASSWORD = "password";
    
    private DAO dao = new DAO(DataSourceFactory.getDataSource());
    
    private String result;
    private Map<String, String> errors = new HashMap<String,String>();
    
    public User connectUser ( HttpServletRequest request ) {
        
        String username = getFieldValue(request, FIELD_USERNAME);
        String password = getFieldValue(request, FIELD_PASSWORD);
        
        User user = new User();
        
        try {
            validateCredentials(username,password);
        } catch ( Exception e ) {
            setErreur ( FIELD_USERNAME + FIELD_PASSWORD , e.getMessage() );
        }
        
        if(errors.isEmpty())
            result = "Successs";
        else
            result = "Failure";
        
        user.setUsername(username);
        user.setPassword(password);
        
        return user;
    }

    public String getResult() {
        return result;
    }
    
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
    
    private void validateCredentials(String username, String password) throws Exception {
        
        Client client = dao.getClientInfos(password);
        if (client == null){
            throw new Exception("Ce mot de passe ne correspond à aucun utilisateur");
        }else if (!client.getContact().equals(username)){
            throw new Exception("Utilisateur introuvable");
        }
        
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    private void setErreur (String champ, String message) {
        errors.put(champ,message);
    }
    
}
