<%-- 
    Document   : altadisco
    Created on : 11-abr-2020, 14:49:22
    Author     : Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controllers.AltaDiscoManual"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="includes/navbar.jsp"/>
            <h4 class="mt-2">Alta de Disco</h4>
            <form>
                <div class="form-group">
                    <label for="artista">Artista</label>
                    <input type="text" class="form-control" id="artista" placeholder="Nombre del artista">
                </div>
                <div class="form-group">
                    <label for="disco">Disco</label>
                    <input type="text" class="form-control" id="disco" placeholder="Titulo del disco">
                </div>
                <button class="btn btn-primary" type="button" onclick="buscarInfoDisco();" id="btn-buscar-info-album">
                    <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true" id="loading" style="display:none;"></span>
                    Buscar Información
                </button>
                <hr>
                <div class="container mt-2" id="noInfoAlbum" style="display:none;">
                    <div class="alert alert-warning" role="alert"> 
                        No se han registros para los criterios de búsqueda!

                    </div>
                    <a type="button" class="btn btn-primary" href="AltaDiscoManual">Alta Manual</a>
                </div>
                <div class="container mt-2" id="infoAlbum" style="display:none;">
                    <div class="alert alert-primary" role="alert">
                        Hemos encontrado los siguientes datos para los criterios de búsqueda!
                    </div>
                    <div class="row">
                        <div class="col-sm">
                            <h2 id="albumTitle" class="text-capitalize font-weight-bold"></h2>
                            <h5 id="albumArtist" class="text-uppercase"></h5>
                            <p class="text-secondary" id="year"></p>   
                            <div class="row" id="genres"></div>
                            <div class="row" id="styles"></div>
                        </div>
                        <div class="col-sm">
                            <img src="" id="imageAlbum">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm">
                            <ul class="list-group list-group-flush" id="songs"></ul>
                        </div>
                    </div>
                    <div class="alert" role="alert" id="alert-guardar-info-album" style="display: none"></div>
                    <button class="btn btn-primary" type="button" id="btn-guardar-info-album" onclick="guardarInfoAlbum();">
                        <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true" id="loading-save" style="display:none;"></span>
                        Guardar
                    </button>
                </div>
            </form>
        </div>
    </body>
    <jsp:include page="includes/js_scripts.jsp"/>
    <script src="js/album.js"></script>
    <script src="js/altadisco.js"></script>
</html>
