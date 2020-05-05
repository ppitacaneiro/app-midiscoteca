<%-- 
    Document   : editardisco
    Created on : 15-abr-2020, 10:50:03
    Author     : Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Genre"%>
<%@page import="models.Style"%>
<%
    ArrayList<Genre> genres = null;
    ArrayList<Style> styles = null;
    genres = (ArrayList<Genre>) request.getAttribute("genres");
    styles = (ArrayList<Style>) request.getAttribute("styles");
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
            <h4 class="mt-2">Editar Disco</h4>
            <form action="BuscarEditarDiscos" method="GET"> 
                <div class="form-group">
                    <label for="artista">Artista</label>
                    <input type="text" class="form-control" id="artista" name="artista" placeholder="artista..." onfocus="autompletarNombreArtista('artista');">
                </div>
                <div class="form-group">
                    <label for="album">Album</label>
                    <input type="text" class="form-control" id="album" name="album" placeholder="album..." onfocus="autompletarNombreAlbum('album');">
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="estilo">Estilo</label>
                            <select class="form-control" id="estilo" name="estilo">
                                <option value="">Seleccionar estilo</option>
                                <% for (Style style : styles) {%>
                                <option value="<%= style.getId()%>"><%= style.getName()%></option>
                                <% }%>
                            </select>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="genero">Género</label>
                            <select class="form-control" id="genero" name="genero">
                                <option value="">Seleccionar Género</option>
                                <% for (Genre genre : genres) {%>
                                <option value="<%= genre.getId()%>"><%= genre.getName()%></option>
                                <% }%>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="Buscar">
                    <button type="button" class="btn btn-secondary">Cancelar</button>
                </div>
            </form>
        </div>
        <jsp:include page="includes/js_scripts.jsp"/>
    </body>
</html>
