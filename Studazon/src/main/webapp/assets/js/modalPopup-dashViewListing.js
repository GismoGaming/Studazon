// Get the modal
let modal = document.getElementById("view-modalPopup");

// Get the element that closes the modal
let closeButton = document.getElementById("modal_close");
let viewListingUp = false;
function openViewModal() {
    modal = document.getElementById("view-modalPopup");
    modal.style.display = "block";
    viewListingUp = true;
}

function closeViewModal() {
    modal = document.getElementById("view-modalPopup");
    modal.style.display = "none";
    viewListingUp = false;
}

// When the user clicks on <span> (x), close the modal
closeButton.onclick = function () {
    closeViewModal();
}

// When the user clicks anywhere outside of the modal, close it
window.addEventListener("click",  function (event) {
    if (event.target === modal) {
        closeViewModal();
    }
})

function changeModal() {
    openViewModal();
}

// Get all listingCard elements
const listingCards = document.querySelectorAll('.listingCard');
window.addEventListener("keydown", function(event) {
    if (viewListingUp && event.key === "Escape")
    {
        closeModal();
    }
});

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

// Attach a click event listener to each listingCard
listingCards.forEach(listingCard => {
    let priceText = listingCard.querySelector('.listing-price') ;
    priceText.textContent = GetCost(priceText.textContent);
    listingCard.addEventListener('click', () => {
        // Get the values from the data attributes
        const picture =  listingCard.dataset.image;
        const title = listingCard.dataset.title;
        const author = listingCard.dataset.author;
        const isbn = listingCard.dataset.isbn;
        const condition = listingCard.dataset.condition;
        const price = listingCard.dataset.price;
        const comments = listingCard.dataset.comments;
        const bookID = listingCard.dataset.id;

        // Display the values in the modal
        document.getElementById('view-title').textContent = title;
        document.getElementById('view-author').textContent = author;
        document.getElementById('view-isbn').textContent = isbn;
        document.getElementById('view-condition').textContent = condition;
        document.getElementById('view-price').textContent = GetCost(price);
        document.getElementById('view-comments').textContent = comments;
        document.getElementById('view-bookID').value = bookID;
        document.getElementById('view-img').src = "data:image/jpg;base64,"+picture;
        document.getElementById('view-modalPopup').style.display = 'block';
    });
});