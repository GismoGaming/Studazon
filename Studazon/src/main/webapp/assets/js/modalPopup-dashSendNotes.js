// Get the modal
let modalSendNote = document.getElementById("sendNotes-modalPopup");

// Get the element that closes the modal
let closeSendButton = document.getElementById("sendNotes_modal_close");
function openSendModal() {
    modalSendNote = document.getElementById("sendNotes-modalPopup");
    modalSendNote.style.display = "block";
    sendNotesUp = true;
}

let sendNotesUp = false;

function closeSendModal() {
    modalSendNote = document.getElementById("sendNotes-modalPopup");
    modalSendNote.style.display = "none";
    sendNotesUp = false;

    document.getElementById('send-bookID').value = document.getElementById('view-bookID').value;

    console.log(document.getElementById('send-bookID').value)
}
// When the user clicks on <span> (x), close the modal
closeSendButton.onclick = function () {
    closeSendModal();
}

// When the user clicks anywhere outside of the modal, close it
window.addEventListener("click",  function (event) {
    if (event.target === modalSendNote) {
        closeSendModal();
    }
})
function changeSendModal() {
    openSendModal();
}

window.addEventListener("keydown", function(event) {
    if (sendNotesUp && event.key === "Escape")
    {
        closeSendModal();
    }
});