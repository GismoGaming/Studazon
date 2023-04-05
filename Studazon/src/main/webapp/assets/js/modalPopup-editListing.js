// Get the modal
var modal = document.getElementById("modalPopup");

// Get the button that opens the modal
var btn = document.getElementById("modal_open");

// Get the element that closes the modal
var closeButton = document.getElementById("modal_close");
var edit_closeButton = document.getElementById("edit-modal_close");

var modifyButton = document.getElementById("modifyListingBtn");
var deleteButton = document.getElementById("deleteListingBtn");
function openModal() {
    modal = document.getElementById("modalPopup");
    modal.style.display = "block";
}

function openEditModal() {
    modal = document.getElementById("edit-modalPopup");
    modal.style.display = "block";
}

function closeModal() {
    modal = document.getElementById("modalPopup");
    modal.style.display = "none";
}

function closeEditModal() {
    modal = document.getElementById("edit-modalPopup");
    modal.style.display = "none";
}

// When the user clicks on <span> (x), close the modal
closeButton.onclick = function () {
    closeModal();
}

edit_closeButton.onclick = function () {
    closeEditModal();
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        closeModal();
    }
}

function changeModal() {
    openEditModal();
}

// Get all listingCard elements
const listingCards = document.querySelectorAll('.listingCard');

// Attach a click event listener to each listingCard
listingCards.forEach(listingCard => {
    listingCard.addEventListener('click', () => {
        // Get the values from the data attributes
        const title = listingCard.dataset.title;
        const author = listingCard.dataset.author;
        const isbn = listingCard.dataset.isbn;
        const condition = listingCard.dataset.condition;
        const price = listingCard.dataset.price;
        const comments = listingCard.dataset.comments;
        const id = listingCard.dataset.id;

        // Display the values in the modal
        document.getElementById('edit-title').value = title;
        document.getElementById('edit-author').value = author;
        document.getElementById('edit-isbn').value = parseInt(isbn);
        document.getElementById('edit-condition').value = condition;
        document.getElementById('edit-price').value = price;
        document.getElementById('edit-comments').value = comments;
        document.getElementById('edit-id').value = id;
        document.getElementById('edit-modalPopup').style.display = 'block';
    });
});

 modifyButton.onclick = function() {
     document.getElementById('action').value = "update";
     alert(document.getElementById('action').value)
     closeEditModal();
 }

 deleteButton.onclick = function() {
     document.getElementById('action').value = "delete";
     //console.log(document.getElementById('action').value)
     closeEditModal();
 }