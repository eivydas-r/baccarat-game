import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaccaratGameLogic {
    // Evaluate two hands at the end of the game and return a string
    // depending on the winner: “Player”, “Banker”, “Draw”.
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){
        int bankerAmount = handTotal(hand1);
        int playerAmount = handTotal(hand2);

        if((playerAmount == 9 || playerAmount == 8) && (playerAmount != bankerAmount)){
            // player natural win
            return "Player";
        } else if((bankerAmount == 9 || bankerAmount == 8) && (playerAmount != bankerAmount)){
            // banker natural win
            return "Banker";
        } else if(playerAmount == bankerAmount){
            // draw
            return "Draw";
        } else if(bankerAmount > playerAmount) {
            // banker wins
            return "Banker";
        } else
            return "Player"; // player wins
    }

    // Take a hand and return how many points that hand is worth
    public int handTotal(ArrayList<Card> hand){
        int total = 0;

        for(Card c : hand){
            switch(c.value){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    total+=c.value;
                    break;
                default:
                    // else 10,11,12,13 are 0 pts.
            }
        }

        if(total > 9){
            total = total - 10;
        }

        return total;
    }

    // will return true if banker should be dealt a third card, otherwise return false
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){
        int totalCards = handTotal(hand);

        if(totalCards <= 2){ // draw another card
            return true;
        } else if((totalCards == 3) || (totalCards == 4) || (totalCards == 5) || (totalCards == 6)){ // depends if player drew 3rd card
            if(playerCard == null){
                if(totalCards <= 5)
                    return true;
                else return false;
            }

            switch(playerCard.value){
                case 0:
                case 1:
                    if(totalCards <= 3) return true;
                    else return false;
                case 2:
                case 3:
                    if(totalCards <= 4) return true;
                    else return false;
                case 4:
                case 5:
                    if(totalCards <= 5) return true;
                    else return false;
                case 6:
                case 7:
                    if(totalCards <= 6) return true;
                    else return false;
                case 8:
                case 9:
                    if(totalCards <= 3) return true;
                    else return false;
            }
        } else {
            return false;
        }
        return false;
    }

    // will return true if player should be dealt a third card, otherwise return false
    public boolean evaluatePlayerDraw(ArrayList<Card> hand){
        int totalCards = handTotal(hand);

        if(totalCards <= 5){ // draw another card
            return true;
        } else
            return false;
    }
}
