<%-- 
    Document   : login
    Created on : 4 déc. 2019, 14:21:29
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to your account</title>
        <link rel="stylesheet" type="text/css" href ="css/login.css"/>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
    </head>
    <body>
         <div class="wrapper">
            <form class="form-signin" method="POST">       
              <h2 class="form-signin-heading">Login</h2>
              <input type="text" class="form-control" name="username" placeholder="Username" required="" autofocus="" />
              <input type="password" class="form-control" name="password" placeholder="Password" required=""/>      
              <button class="btn btn-lg btn-primary btn-block" type="submit" name="action" value="connect">Login</button>   
            </form>
         </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
    </body>
</html>
