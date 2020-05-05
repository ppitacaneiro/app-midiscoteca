<%-- 
    Document   : altadiscomanual
    Created on : 15-abr-2020, 10:50:03
    Author     : Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = (String) request.getAttribute("message");
    String id = (String) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="js/paths.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="includes/navbar.jsp"/>
            <h4 class="mt-2">Alta de Disco - Adjuntar Portada</h4>
            <div class="alert alert-primary" role="alert">
                <%= message %>
            </div>
            <form action="AltaDiscoManualProcesarFichero" method="post" enctype="multipart/form-data">
                <div class="input-group">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="file" aria-describedby="file">
                        <label class="custom-file-label" for="file">Seleccionar imagen de portada</label>
                    </div>
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="file">Guardar</button>
                    </div>
                </div>
                <hr>
                <input type="hidden" name="idAlbum" id="idAlbum" value="<%= id %>">
                <input type="submit" class="btn btn-primary" value="Guardar">
                <button type="button" class="btn btn-secondary">Cancelar</button>
            </form>
        </div>
        <jsp:include page="includes/js_scripts.jsp"/>
        <script src="js/altadiscomanual.js"></script>
    </body>
</html>
