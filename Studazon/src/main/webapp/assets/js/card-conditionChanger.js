var listingCards = document.getElementsByClassName("listing-cond");
for(var index=0;index < listingCards.length;index++){
    switch (listingCards[index].innerHTML) {
        case "Bad":
            listingCards[index].style.backgroundColor = "#D96F6F"
            break;
        case "Used":
            listingCards[index].style.backgroundColor = "#D9B56F"
            break;
        case "New":
        default:
            listingCards[index].style.backgroundColor = "#6FC2D9"
            break;
    }
 }