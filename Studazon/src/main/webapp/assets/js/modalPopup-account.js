// Get the modal
let modal = document.getElementById("account-modalPopup");

// Get the button that opens the modal
let btn = document.getElementById("modal_open");

// Get the element that closes the modal
let closeButton = document.getElementById("modal_close");

function openModal() {
    modal = document.getElementById("account-modalPopup");
    modal.style.display = "block";
}

function openViewModal() {
    modal = document.getElementById("account-modalPopup");
    modal.style.display = "block";
}

function closeModal() {
    modal = document.getElementById("account-modalPopup");
    modal.style.display = "none";
}

// When the user clicks on <span> (x), close the modal
closeButton.onclick = function () {
    closeModal();
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target === modal) {
        closeModal();
    }
}

function changeModal() {
    openViewModal();
}
window.addEventListener("keydown", function(event) {
    if (event.key === "Escape")
    {
        closeModal();
    }
})