
function openPreview(src) {
    const previewOverlay = document.getElementById("previewOverlay");
    const previewImage = document.getElementById("previewImage");
    previewImage.src = src;
    previewOverlay.style.display = "flex";
}

function closePreview() {
    document.getElementById("previewOverlay").style.display = "none";
}

