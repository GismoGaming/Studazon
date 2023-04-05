// Get the modal
var modal = document.getElementById("view-modalPopup");

// Get the button that opens the modal
var btn = document.getElementById("modal_open");

// Get the element that closes the modal
var closeButton = document.getElementById("modal_close");
function openModal() {
    modal = document.getElementById("view-modalPopup");
    modal.style.display = "block";
}

function openViewModal() {
    modal = document.getElementById("view-modalPopup");
    modal.style.display = "block";
}

function closeModal() {
    modal = document.getElementById("view-modalPopup");
    modal.style.display = "none";
}

function closeEditModal() {
    modal = document.getElementById("view-modalPopup");
    modal.style.display = "none";
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

function changeModal() {
    openViewModal();
}

// Get all listingCard elements
const listingCards = document.querySelectorAll('.listingCard');

// Attach a click event listener to each listingCard
listingCards.forEach(listingCard => {
    listingCard.addEventListener('click', () => {
        // Get the values from the data attributes
        const picture =  listingCard.dataset.image;
        const title = listingCard.dataset.title;
        const author = listingCard.dataset.author;
        const isbn = listingCard.dataset.isbn;
        const condition = listingCard.dataset.condition;
        const price = listingCard.dataset.price;
        const comments = listingCard.dataset.comments;

        console.log(picture)

        // Display the values in the modal
        document.getElementById('view-title').textContent = title;
        document.getElementById('view-author').textContent = author;
        document.getElementById('view-isbn').textContent = isbn;
        document.getElementById('view-condition').textContent = condition;
        document.getElementById('view-price').textContent = "$" + price;
        document.getElementById('view-comments').textContent = comments;
        document.getElementById('view-img').src = "data:image/jpg;base64,"+picture;
        document.getElementById('view-modalPopup').style.display = 'block';
    });
});