<%-- 
    Document   : discosporestilo
    Created on : 23-abr-2020, 17:12:52
    Author     : Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Album"%>
<%
    ArrayList<Album> albums = null;
    albums = (ArrayList<Album>) request.getAttribute("Albums");
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
            <h1>Listado Albums</h1>
            <div class="row">
                <% for (Album album : albums) {%>
                <div class="col-sm-3 mt-10">
                    <div class="card">
                        <img src="images/albums/<%= album.getImageCover()%>.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><%= album.getTitle()%></h5>
                            <p class="card-text"><%= album.getArtist()%></p>
                            <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#albumInfo" onclick="getInfoAlbum('<%= album.getId()%>');">+ Info</a>
                        </div>
                    </div>
                </div>
                <% }%>                
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="albumInfo" tabindex="-1" role="dialog" aria-labelledby="albumInfo" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalScrollableTitle">Album Info</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm">
                                    <h2 id="albumTitle" class="text-capitalize font-weight-bold"></h2>
                                    <h5 id="albumArtist" class="text-uppercase"></h5>
                                    <p class="text-primary" id="year"></p>   
                                    <div class="row" id="genres"></div>
                                    <div class="row" id="styles"></div>
                                </div>
                                <div class="col-sm">
                                    <img src="" id="imageAlbum">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm">
                                    <ul class="list-group list-group-flush" id="songs">
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="includes/js_scripts.jsp"/>
    <script src="js/album.js"></script>
</html>
