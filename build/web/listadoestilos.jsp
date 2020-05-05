<%-- 
    Document   : listadoestilos
    Created on : 21-abr-2020, 18:13:46
    Author     : Pablo
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Style"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Style> styles = null;
    styles = (ArrayList<Style>) request.getAttribute("Styles");
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="js/paths.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="includes/navbar.jsp"/>
            <h1>Estilos</h1>
            <div class="row">
                <% for (Style style : styles) {%>
                <div class="col-sm-3 mt-10">
                    <div class="card">
                        <img src="images/albums/<%= style.getImage()%>.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><%= style.getName() %></h5>
                            <a href="ListaAlbumsEstilo?id_style=<%= style.getId() %>" class="btn btn-primary">Ver Discos</a>
                        </div>
                    </div>
                </div>
                <% }%>
            </div>
        </div>
    </body>
    <jsp:include page="includes/js_scripts.jsp"/>
</html>
