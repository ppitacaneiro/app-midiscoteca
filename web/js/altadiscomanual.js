/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var contadorLineas = 1;

function addSong() {
    
    contadorLineas++;
    let htmlSong = "<div class=\"input-group\" id=\"song-" + contadorLineas + "\">";
    htmlSong += "<input type=\"text\" id=\"title-song-" + contadorLineas + "\" name=\"title-song-" + contadorLineas + "\" class=\"form-control\" placeholder=\"Título de la canción\" aria-label=\"Título de la canción\" aria-describedby=\"button-addon4\">\n"
    htmlSong += "</div>";
    
    $("#canciones-disco").append(htmlSong);
    $("#num-songs").val(contadorLineas);
    
    alert(contadorLineas);
}

function removeSong() {
    if (contadorLineas > 1) {
        $("#song-" + contadorLineas).remove();
        contadorLineas--;
        $("#num-songs").val(contadorLineas);
        
        alert(contadorLineas);
    }
}

function createComboYears() {
    const minYear = 1920;
    let thisYear = new Date().getFullYear();
    let htmlYears;
    
    for (let year = minYear; year <= thisYear; year++) {
        htmlYears += "<option value=\"" + year + "\">" + year + "</option>";
    }
    
    $("#yearAlbum").html(htmlYears);
}



