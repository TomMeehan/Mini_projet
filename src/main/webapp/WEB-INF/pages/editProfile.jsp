<%-- 
    Document   : editProfil
    Created on : 10 déc. 2019, 16:18:01
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <jsp:include page="/WEB-INF/includes/head.jsp"/>
        <title>Edit profile</title>
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp"/>  
        <form>
            <div class="form-group">
              <label for="Nom"></label>
              <input type="text" class="form-control" id="formGroupExampleInput" placeholder="Example input">
            </div>
            <div class="form-group">
              <label for="formGroupExampleInput2">Another label</label>
              <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Another input">
            </div>
         </form>
    </body>
</html>
