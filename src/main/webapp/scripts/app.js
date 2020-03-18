async function removeArtist(id) {
    try {
        let response = await fetch('/artists?id=' + id, {method: 'DELETE'});
        let result = await response.json();
        let rowId = 'artist-' + result["id"];
        let row = document.getElementById(rowId);
        row.remove();
    } catch (e) {
        console.error(e);
        alert('An error occurred. Please check the consoles of the browser and the backend.');
    }
}

async function removeAlbum(id) {
    try {
        let response = await fetch('/albums?id=' + id, {method: 'DELETE'});
        let result = await response.json();
        let rowId = 'album-' + result["id"];
        let row = document.getElementById(rowId);
        row.remove();
    } catch (e) {
        console.error(e);
        alert('An error occurred. Please check the consoles of the browser and the backend.');
    }
}