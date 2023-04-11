// Get the modal
let modal = document.getElementById("modalPopup");

// Get the button that opens the modal
let btn = document.getElementById("modal_open");

// Get the element that closes the modals
let closeButton = document.getElementById("modal_close");
let edit_closeButton = document.getElementById("edit-modal_close");
let modifyButton = document.getElementById("modifyListingBtn");
let deleteButton = document.getElementById("deleteListingBtn");

// Open the 'Create Listing' Modal
function openModal() {
    modal = document.getElementById("modalPopup");
    modal.style.display = "block";
}

// Open the 'Modify Listing' Modal
function openEditModal() {
    modal = document.getElementById("edit-modalPopup");
    modal.style.display = "block";
}

// Close the 'Create Listing' Modal
function closeModal() {
    modal = document.getElementById("modalPopup");
    modal.style.display = "none";
}

// Close the 'Modify Listing' Modal
function closeEditModal() {
    modal = document.getElementById("edit-modalPopup");
    modal.style.display = "none";
}

// When the user clicks on <span> (x) in 'Create Listing' modal, close the modal
closeButton.onclick = function () {
    closeModal();
}

// When the user clicks on <span> (x) in 'Modify Listing' modal, close the modal
edit_closeButton.onclick = function () {
    closeEditModal();
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target === modal) {
        closeModal();
        closeEditModal();
    }
}

// When the user presses Esc, the modal will close
window.addEventListener("keydown", function(event) {
    if (event.key === "Escape")
    {
        closeModal();
        closeEditModal();
    }
});

function changeModal() {
    openEditModal();
}

const formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
});

function GetCost(text)
{
    let cost = formatter.format(parseFloat(text));

    if (cost === "$0.00")
        return "Free";
    return cost;
}

// Get all listingCard elements
const listingCards = document.querySelectorAll('.listingCard');

// Attach a click event listener to each listingCard
listingCards.forEach(listingCard => {
    let priceText = listingCard.querySelector('.listing-price') ;
    priceText.textContent = GetCost(priceText.textContent);
    listingCard.addEventListener('click', () => {

        // Get the values from the data attributes
        const title = listingCard.dataset.title;
        const author = listingCard.dataset.author;
        const isbn = listingCard.dataset.isbn;
        const condition = listingCard.dataset.condition;
        const price = listingCard.dataset.price;
        const comments = listingCard.dataset.comments;
        const imageUrl = listingCard.querySelector('.listing-picture').src;
        const id = listingCard.dataset.id;

        // Display the values in the modal
        document.getElementById('edit-title').value = title;
        document.getElementById('edit-author').value = author;
        document.getElementById('edit-isbn').value = parseInt(isbn);
        document.getElementById('edit-condition').value = condition;
        document.getElementById('edit-price').value = price;
        document.getElementById('edit-comments').value = comments;
        document.getElementById('edit-image').src = imageUrl;
        console.log(imageUrl);
        document.getElementById('edit-image').alt = title;
        document.getElementById('edit-id').value = id;
        document.getElementById('edit-modalPopup').style.display = 'block';
    });
});

// If the user clicks on the "Modify" button, update the listing
modifyButton.onclick = function() {
 document.getElementById('action').value = "update";
 closeEditModal();
}

// If the user clicks on the "Delete" button, get confirmation and then delete listing
deleteButton.onclick = function() {
 if(confirm('Are you sure you want to delete this listing?')){
     document.getElementById('action').value = "delete";
 }
 closeEditModal();
}

function hover(element){
    element.setAttribute('src', './assets/Interactivity/Create%20Listing%20Hover.png')
}
function unhover(element){
    element.setAttribute('src', './assets/Interactivity/Create%20Listing.png')
}