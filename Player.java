/** Player.java
*   Author: Obiora
*   
*   Player class as part of Crazy Eights
*   To be used with Game, Card, Deck classes
*
*  NAME: Obiora Okeke
* UNI: oio2105
*/

import java.util.ArrayList;
import java.util.Scanner;

class Player{
    
    private ArrayList<Card> hand; // the player's hand
    private Scanner input;

    public Player(){
        // your code here
        hand = new ArrayList<Card>();
        input = new Scanner(System.in);
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        // your code here
        hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Deck deck){
        // your code here
        int choiceNumber = 100;
        //Runs until player chooses a card
        while (choiceNumber == 100){
            //prints out players deck
            System.out.println(handToString());
            //tells player to choose a card or type 100 to draw from deck
            System.out.println("Please select which card you would "+
            "like to place down or type 100 to draw a new card!\n");
            //lets player choose a card
            choiceNumber = input.nextInt();
            System.out.println();
            //draws card if player typed 100
            if (choiceNumber == 100){
                //check if deck needs to be shuffled
                if(deck.canDeal() == true){
                    //draws if deck doesn't need to be shufled
                    System.out.println("You drew a card");
                    addCard(deck.deal());
                }
                else{
                    //shuffles deck then draws
                    System.out.println("The Deck needed to be shuffled");
                    deck.shuffle();
                    System.out.println("You drew a card");
                    addCard(deck.deal());
                }
            }
        }
        //plays card
        Card choiceCard = hand.get(choiceNumber);
        hand.remove(choiceNumber);
        return(choiceCard);
    }

    
    // Accessor for the players hand
    public ArrayList<Card> getHand(){
        // your code here
        return hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        // your code here
        int placement = 0;
        String handP = "";
        for(Card car:hand){
            handP += placement + ".  " + car.toString();
            placement++;
        }
        return (handP);
    }

// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.

} // end
