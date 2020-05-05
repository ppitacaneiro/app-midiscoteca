function getInfoAlbum(idAlbum) {

    let album = {};
    let albumSongs = [];
    let albumStyles = [];
    let albumGenres = [];
    let songs;
    let styles;
    let genres;

    $.ajax({
        url: 'AlbumInfo',
        method: 'POST',
        data: {idAlbum: idAlbum},
        success: function (resultJson) {
            console.log(resultJson);
            if (resultJson !== null) {
                album = resultJson;

                albumSongs = album.songs;
                songs = getSongsOfAlbum(albumSongs);

                albumGenres = album.genres;
                genres = getGenresOfAlbum(albumGenres);

                albumStyles = album.styles;
                styles = getStylesOfAlbum(albumStyles);

                $("#albumTitle").text(album.title);
                $("#albumArtist").text(album.artist);
                $("#year").text(album.year);
                document.getElementById('imageAlbum').src = PATH_IMAGES + album.imageCover + '.jpg';
                $("#genres").html(genres);
                $("#styles").html(styles);
                $("#songs").html(songs);
            }
        },
        error: function (jqXHR, exception) {
            console.log('ERROR al recuperar datos de album!');
        }
    });
}

function getStylesOfAlbum(stylesAlbum) {
    let htmlStyles = "";

    for (let i = 0; i < stylesAlbum.length; i++) {
        htmlStyles += "<div class=\"p-3 mb-2 bg-primary text-white\">";
        htmlStyles += stylesAlbum[i].name;
        htmlStyles += "</div>";
    }

    return htmlStyles;
}

function getGenresOfAlbum(genresAlbum) {
    let htmlGenres = "";

    for (let i = 0; i < genresAlbum.length; i++) {
        htmlGenres += "<div class=\"p-3 mb-2 bg-secondary text-white\">";
        htmlGenres += genresAlbum[i].name;
        htmlGenres += "</div>";
    }

    return htmlGenres;
}

function getSongsOfAlbum(songsAlbum) {

    let htmlSongs = "";

    for (let i = 0; i < songsAlbum.length; i++) {
        htmlSongs += "<li class=\"list-group-item\">";
        htmlSongs += songsAlbum[i].title;
        htmlSongs += "</li>";
    }

    return htmlSongs;
}


