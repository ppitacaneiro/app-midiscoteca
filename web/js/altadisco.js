var album = {};

function buscarInfoDisco() {
    
    let artista = $("#artista").val();
    let disco = $("#disco").val();

    let genresAlbum = [];
    let htmlGenres;
    let styleAlbums = [];
    let htmlStyles;
    let songs = [];
    let htmlSongs;

    $.ajax({
        url: 'BuscarInfoDisco',
        method: 'POST',
        data: {artista: artista, disco: disco},
        beforeSend: function () {
            $("#loading").show();
            $("#btn-buscar-info-album").prop("disabled", true);
        },
        success: function (resultJson) {
            console.log(resultJson);
            album = resultJson;
            if (jQuery.isEmptyObject(album)) {
                $("#infoAlbum").hide();
                $("#noInfoAlbum").show();
            } else {

                genresAlbum = album.genres;
                htmlGenres = getGenresOfAlbum(genresAlbum);

                styleAlbums = album.styles;
                htmlStyles = getStylesOfAlbum(styleAlbums);

                songs = album.songs;
                htmlSongs = getSongsOfAlbum(songs);

                $("#albumTitle").text(album.title);
                $("#albumArtist").text(album.artist);
                $("#imageAlbum").attr("src",album.imageCover);
                $("#year").text(album.year);
                $("#genres").html(htmlGenres);
                $("#styles").html(htmlStyles);
                $("#songs").html(htmlSongs);

                $("#infoAlbum").show();
                $("#noInfoAlbum").hide();
            }
        },
        complete: function () {
            $("#loading").hide();
            $("#btn-buscar-info-album").prop("disabled", false);
        },
        error: function (jqXHR, exception) {
            console.log('ERROR al recuperar datos de album!');
        }
    });
}

function guardarInfoAlbum() {
    
    let messageUser;
    
    $.ajax({
        url: 'GuardarInfoDisco',
        method: 'POST',
        data: {album:JSON.stringify(album)},
        beforeSend: function () {
            $("#loading-save").show();
        },
        success: function (resultJson) {
            messageUser = resultJson;
            
            $("#alert-guardar-info-album").show();
            if (messageUser.statusCode === 200)
            {
               $("#alert-guardar-info-album").addClass("alert-success");
            } else {
               $("#alert-guardar-info-album").addClass("alert-danger");
            }
            $("#alert-guardar-info-album").text(messageUser.message);
        },
        complete: function () {
            $("#loading-save").hide();
        },
        error: function (jqXHR, exception) {
            console.log('ERROR al guardar datos de album!');
        }
    });
}

