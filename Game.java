/** Game.java
*   Author: Obiora
*   
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*  NAME: Obiora Okeke
* UNI: oio2105
*/


import java.util.Scanner;
import java.util.ArrayList;

class Game{

    private char currentSuit; // need in case an 8 is played
    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    
    
    // sets up the Game object for play
    public Game(){
        // your code here
        cards = new Deck();
        p1 = new Player();
        compHand = new ArrayList<Card>();
        input = new Scanner(System.in);
        cards.shuffle();
        faceup = cards.deal();
        currentSuit = faceup.getSuit();
    }

   

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){
        // your code here
        //prints welcome message with rules
        welcome();
        //Gives both players 7 cards
        fillHand(compHand);
        fillPlayerHand(p1);

        //opens the game up with the top card
        System.out.println("The faceup card is: " + faceup.toString());
        currentSuit = faceup.getSuit();
        System.out.println("The suit is: " + suitFormatter() + "\n\n");

        //The while loop will run until someone wins
        boolean status = true;
        while (status == true){
            //player1's turn and checks if the card they played is allowed
            faceup = isItPlayable();
            System.out.println("***You played the: "+faceup.toString());
            //Checks if player1 won and if so, ends the game
            status = winner();
            if(status == false){break;}
            //Checks if a wild card was played by player
            wildCardPlayer();
            //computer's turn and checks if the card they played is allowed
            faceup = computerTurn();
            //checks if computer won and if so, ends the game
            status = winner();
            if(status == false){break;}
        }
        //asks player if they want to play again
        boolean another = anotherGame();
        return another;
    }

    /* Naive computer player AI that does one of two actions:
        1) Plays the first card in their hand that is a valid play
        2) If no valid cards, draws until they can play

        You may choose to use a different approach if you wish but
        this one is fine and will earn maximum marks
     */
     private Card computerTurn(){
        // your code here
        //runs until the computer can place down a card
        int choiceNumber = 100;
        while (choiceNumber == 100){
            //checks for the first available card to play
            int indexCom = 0;
            for(Card comC:compHand){
                if (comC.getRank() == faceup.getRank()){
                    choiceNumber = indexCom;
                }
                else if (comC.getSuit() == currentSuit){
                    choiceNumber = indexCom;
                }
                indexCom++;
            }
            //draws card if no card is playable
            if (choiceNumber == 100){
                comDraw();
            }
        }
        //plays acceptable card
        Card choiceCard = compHand.get(choiceNumber);
        compHand.remove(choiceNumber);
        faceup = choiceCard;
        System.out.println("***The computer played the: "+faceup.toString());
        //Checks if a wildcard was played
        wildCardCom();
        return(choiceCard);
    }
    
// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.
    /*
        Fills computer deck with seven cards
    */
    private void fillHand(ArrayList handCards){
        for(int i = 0; i < 7; i++){
           handCards.add(cards.deal()); 
        }
    }

    /*
        Fills player deck with seven cards
    */
    private void fillPlayerHand(Player player1){
        for(int i = 0; i < 7; i++){
           player1.addCard(cards.deal()); 
        }
    }

    /*
        Checks if player wants to play again
    */
    private boolean anotherGame(){
        boolean again;
        System.out.println("Do you want to play again? (y/n)");
        char playAgain = input.next().charAt(0);
        if (playAgain == 'y' || playAgain == 'Y'){
            again = true;
        }
        else{
            again = false;
        }
        return again;
    }

    /*
        Checks to see if someone won
    */
    private boolean winner(){
        boolean whoWon;
        //checks to see if player has 0 cards
        if (p1.getHand().size() < 1){
            whoWon = false;
            System.out.println("WOW YOU'RE A WINNER!!!");
        }
        //checks to see if computer has 0 cards
        else if(compHand.size() < 1){
            whoWon = false;
            System.out.println("DANG, THE COMPUTER WON THIS TIME :(");
        }
        //continues the game if no one won
        else{
            whoWon = true;
        }
        return whoWon;
    }

    /*
        Draws cards for the computer
    */
    private void comDraw(){
        //checks if the deck can deal cards
        if(cards.canDeal() == true){
            System.out.println("The Computer drew a card");
            compHand.add(cards.deal());
        }
        //if the deck can't deal then it gets shuffled
        else{
            System.out.println("The Deck needed to be shuffled");
            cards.shuffle();
            System.out.println("The Computer drew a card");
            compHand.add(cards.deal());
        }
    }

    /*
        Formats the current suit to be presented to player
    */
    private String suitFormatter(){
        String icon = "";
        switch (currentSuit){
            case 's': icon = "Spades";
                break;
            case 'h': icon = "Hearts";
                break;
            case 'c': icon = "Clubs";
                break;
            case 'd': icon = "Diamonds";
                break;
        }
        return icon;
    }

    /*
        Makes com choose a suit if it plays a wildcard
    */
    private void wildCardCom(){
        //checks if an 8 was played by the com
        if(faceup.getRank() == 8){
            System.out.println("The computer is changing the suit (c,h,d,s)");
            //sets the currentSuit to the suit of the next card in the com's hand
            currentSuit = compHand.get(0).getSuit();
            System.out.print("The computer changed the suit to: "
             + suitFormatter() + "\n");
            System.out.print("The faceup card is: ");
            System.out.println(faceup.toString());
            System.out.print("The suit is: " + suitFormatter() + "\n\n");
        }
        //if a wildcard was not played then the game continues as normal
        else{
            System.out.print("The faceup card is: ");
            System.out.println(faceup.toString());
            currentSuit = faceup.getSuit();
            System.out.print("The suit is: " + suitFormatter() + "\n\n");
        }
    }

    /*
        Makes player choose a suit if it plays a wildcard
    */
    private void wildCardPlayer(){
        //checks if an 8 was played 
        if(faceup.getRank() == 8){
            System.out.println("What do you want the suit to be? (c,h,d,s)");
            //sets the suit to the suit of theplayer's choice
            currentSuit = input.next().charAt(0);
            System.out.print("The faceup card is: ");
            System.out.println(faceup.toString());
            System.out.print("The suit is: " + suitFormatter() + "\n\n");
        }
        //if a wildcard was not played then the game continues as normal
        else{
            System.out.print("The faceup card is: ");
            System.out.println(faceup.toString());
            currentSuit = faceup.getSuit();
            System.out.print("The suit is: " + suitFormatter() + "\n\n");
        }
    }

    /*
        Makes player chooses a card that is allowed to be played
    */
    private Card isItPlayable(){
        boolean playable = false;
        Card playableCard = faceup;
        while (playable == false){
            playableCard = p1.playsTurn(cards);
            //checks if card chosen matches the faceup's suit or rank
            //if not, the card is added back into the deck and the 
            //player has to choose a different card
            if(playableCard.getRank() != faceup.getRank() &&
            playableCard.getSuit() != faceup.getSuit()){
                System.out.println("You can't play that card you cheater!!!");
                p1.getHand().add(playableCard);
                playable = false;
            }
            else{
                playable = true;
            }
        }
        return playableCard;
    }

    /*
        A welcome message and the rules for the game
    */
    private void welcome(){
        System.out.println("WELCOME TO CRAZY EIGHTS!!! \n HAPPY TO SEE YOU");
        System.out.println("*Seven cards are dealt to each player\n*The "+
        "remaining cards are placed in a pile face down. This pile is "+
        "called the “stock pile”\n*The top card on the stock pile is removed"+
        " and turned face up. It is now the first card in a new pile "+
        "called the \"discard pile\". (Notice the stock pile cards are "+
        "face down and the discard pile is face up.)\n*Each turn a player "+
        "discards a card from their hand into the discard pile by matching "+
        "either the suit or the rank of the top card of the discard pile. "+
        "If the player has no cards in their hand that match the suit or "+
        "the rank, they must draw from the stock pile until they get a "+
        "playable card or they may play an eight. The player’s discarded "+
        "card is now the top card of the discard pile.\n*Eights are wild. "+
        "That means an eight may be played at any time and the player "+
        "discarding the eight then chooses the suit they wish the next "+
        "player to have to match.\n*If a player discards the final card in "+
        "their hand, they win.\n*If the stock pile is exhausted, the game "+
        "ends and the player with the fewest cards left in their hand is "+
        "declared the winner.\n\n");
    }
}