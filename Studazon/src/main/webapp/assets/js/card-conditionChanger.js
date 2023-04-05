var cards = document.getElementsByClassName("listing-cond");
for(var index=0;index < cards.length;index++){
    switch (cards[index].innerHTML) {
        case "Bad":
            cards[index].style.backgroundColor = "#D96F6F"
            break;
        case "Used":
            cards[index].style.backgroundColor = "#D9B56F"
            break;
        case "New":
        default:
            cards[index].style.backgroundColor = "#6FC2D9"
            break;
    }
 }