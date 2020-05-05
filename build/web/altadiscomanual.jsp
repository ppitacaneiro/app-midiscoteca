<%-- 
    Document   : altadiscomanual
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
            <h4 class="mt-2">Alta de Disco</h4>
            <form action="AltaDiscoManualProcesar" method="post">
                <div class="form-group">
                    <label for="artista">Artista</label>
                    <input type="text" class="form-control" id="artista" name="artista" placeholder="Nombre del artista">
                </div>
                <div class="form-group">
                    <label for="titulo">Titulo</label>
                    <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Titulo">
                </div>
                <div class="form-group">
                    <label for="yearAlbum">Año</label>
                    <select class="form-control" id="yearAlbum" name="yearAlbum">
                    </select>
                </div>
                <p class="mt-4">Géneros</p>
                <hr>
                <% for (Genre genre : genres) {%>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="check-generos" name="check-generos" value="<%= genre.getName()%>">
                    <label class="form-check-label" for="check-generos"><%= genre.getName()%></label>
                </div>
                <% } %>
                <p class="mt-4">Estilos</p>
                <hr>
                <% for (Style style : styles) {%>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="check-estilos" name="check-estilos" value="<%= style.getName()%>">
                    <label class="form-check-label" for="check-estilos"><%= style.getName()%></label>
                </div>
                <% }%>
                <p class="mt-4">Canciones</p>
                <hr>
                <div id="canciones-disco">
                    <div class="input-group" id="song-1">
                        <input type="text" class="form-control" placeholder="Título de la canción" aria-label="Título de la canción" aria-describedby="button-addon4" id="title-song-1" name="title-song-1">
                        <div class="input-group-append" id="button-addon4">
                            <button class="btn btn-outline-secondary" type="button" onclick="addSong();">+</button>
                            <button class="btn btn-outline-secondary" type="button" onclick="removeSong();">-</button>
                        </div>
                    </div>
                </div>
                <hr>
                <input type="hidden" name="num-songs" id="num-songs" value="">
                <input type="submit" class="btn btn-primary" value="Guardar">
                <button type="button" class="btn btn-secondary">Cancelar</button>
            </form>
        </div>
        <jsp:include page="includes/js_scripts.jsp"/>
        <script src="js/altadiscomanual.js"></script>
        <script>
            $(document).ready(function () {
                createComboYears();
            });
        </script>
    </body>
</html>
