/** Deck.java
*   Author: Obiora
*   
*   Models a typical deck of playing cards
*   To be used with Card class
*
*  NAME: Obiora Okeke
* UNI: oio2105
*/


import java.util.*;
class Deck{

    private Card[] deck; // contains the cards to play with
    private int top; // controls the "top" of the deck to deal from
    //made arrays for the suits and ranks
    private static final char[] suits = {'s', 'h', 'c', 'd'};
    private static final int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
                                        11, 12, 13};
    // constructs a default Deck
    public Deck(){
        // your code here
        top = 0;
        deck = new Card[52];
        int place = 0;
        //creating each card for the deck with nested for loop
        for(char icon:suits){
            for(int num:ranks){
                deck[place] = new Card(icon,num);
                place ++;
            }
        }
        
    }

    // Deals the top card off the deck
    public Card deal(){
        // your code here
        //shows top card 
        Card currentCard = deck[top];
        //moves top variable to the next card
        top++;
        return(currentCard);
    }


    // returns true provided there is a card left in the deck to deal
    public boolean canDeal(){
        // your code here
        //if top is on the last card, it returns false
        boolean status = true;
        if(top > 51){
            status = false;
        }
        return status;
    }

    // Shuffles the deck
    public void shuffle(){
        // your code here
        //converting the deck to an arraylist
        ArrayList<Card> tempDeck = new ArrayList<Card>(Arrays.asList(deck));
        //Shuffles deck 5 times just from the sake of shuffling
        for(int i = 0; i < 5; i++){
            Collections.shuffle(tempDeck);
        }
        //converting the deck back to an array
        deck = tempDeck.toArray(new Card[0]);
        //resetting the top back to the beinging of the deck
        top = 0;
    }

    // Returns a string representation of the whole deck
    public String toString(){
       // your code here
       String deckString = "";
        for(Card car:deck){
            deckString = deckString + car.toString();
        }
        return(deckString);
    }

    // you may wish to have more helper methods to simplify
    // and shorten the methods above.


    
}