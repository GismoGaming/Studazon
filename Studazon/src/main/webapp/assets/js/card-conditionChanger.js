const cards = document.getElementsByClassName("listing-cond");
for(let index=0; index < cards.length; index++){
    switch (cards[index].innerHTML) {
        case "Fair":
            cards[index].style.backgroundColor = "#D7B358";
            break;
        case "Good":
            cards[index].style.backgroundColor = "#6FC2D9";
            break;
        case "New":
            cards[index].style.backgroundColor = "#7E6FD9";
            break;
        // Assume condition is poor
        case "Poor":
        default:
            cards[index].style.backgroundColor = "#D96F6F";
            break;
    }
 }