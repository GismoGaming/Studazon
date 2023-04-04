// Get the modal
var modal = document.getElementById("modalPopup");

// Get the button that opens the modal
var btn = document.getElementById("modal_open");

// Get the element that closes the modal
var closeButton = document.getElementById("modal_close");
function openModal() {
    modal.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
closeButton.onclick = function () {
    closeModal();
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        closeModal();
    }
}

function closeModal() {
    modal.style.display = "none";
}
