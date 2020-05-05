function autompletarNombreArtista(idCampo) {
    
    var arrayArtistsName = [];
    
    $.ajax({
        url: 'BuscarListadoArtistas',
        method: 'POST',
        data: {},
        success: function (resultJson) {
            arrayArtistsName = resultJson;
        },
        complete: function () {
            // console.log('complete...');
            $("#" + idCampo + "").autocomplete({
                source: arrayArtistsName
            });
        },
        error: function (jqXHR, exception) {
            console.log('ERROR al recuperar datos de artistas!');
        }
    });
}

function autompletarNombreAlbum(idCampo) {
    
    var arrayAlbumsName = [];
    
    $.ajax({
        url: 'BuscarListadoAlbums',
        method: 'POST',
        data: {},
        success: function (resultJson) {
            arrayAlbumsName = resultJson;
        },
        complete: function () {
            // console.log('complete...');
            $("#" + idCampo + "").autocomplete({
                source: arrayAlbumsName
            });
        },
        error: function (jqXHR, exception) {
            console.log('ERROR al recuperar datos de albums!');
        }
    });
}





