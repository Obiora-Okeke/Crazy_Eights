/** Card.java
*   Author: Obiora
*   
*   
*   Models a typical playing card
*
*  NAME: Obiora Okeke
* UNI: oio2105
*/

class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){
        // your code here
        this.suit = suit;
        this.rank = rank;
    }

    // Accessor for suit
    public char getSuit(){
        // your code here;
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        // your code here;
        return rank;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
        // your code here
        String name, icon = "";
        //adujst the special ranks that have a name
        if (rank == 1){name = "Ace";}
        else if(rank == 11){name = "Jack";}
        else if(rank == 12){name = "Queen";}
        else if(rank == 13){name = "King";}
        else{name = Integer.toString(rank);}
        //adjusts the suits to their whole names
        switch (suit){
            case 's': icon = "Spades";
                break;
            case 'h': icon = "Hearts";
                break;
            case 'c': icon = "Clubs";
                break;
            case 'd': icon = "Diamonds";
                break;
        }
        //returns readable string
        return (name + " of " + icon + "\n");
    }
}