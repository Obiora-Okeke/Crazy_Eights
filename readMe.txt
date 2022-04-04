NAME: Obiora Okeke
UNI: oio2105

--Card.java--
getSuit() method:
    - returns the card's suit

getRank() method:
    - returns the card's rank

toString() method:
    - adujsts the special ranks(1,11,12,13) to their proper names(Ace, Jack, Queen, King)
    - adjusts the suits to their whole names (Spades, Hearts, Clubs, Diamonds)
    - Then returns readable string

--Deck.java--
Deck() method:
    - created empty array with 52 spaces
    - then created each card for the deck with nested for loops

deal() method:
    - shows top card 
    - moves top variable to the next card

canDeal() method:
    - returns true provided there is a card left in the deck to deal
    - if top is on the last card, it returns false

shuffle() method:
    - Converts the deck to an arraylist
    - Shuffles the deck 5 times 
    - converts the deck back to an array
    - sets top variable to the top of the deck

toString() method:
    - prints out each card in the deck in a user friendly way



--Player.java--
Player() method:
    - Initializes new instance of Player class

addCard(Card c) Method:
    - adds the card that was passed as a parameter to the player's hand.

playsTurn() Method:
    - Runs until the player chooses a card (I check to see if the card is valid in the Game.java file)
    - prints out players deck
    - tells player to choose a card or type 100 to draw from deck
    - if the player draws a card, it checks if the deck needs to be shuffled first and shuffles if necessary
    - returns chosen card

getHand() Method:
    - returns player's hand that's an arraylist

handToString() Method:
    - Returns a printable string representing the player's hand

--Game.java--
***Helper methods are listed last***
Game() Method:
    - Shuffles card
    - Deals the first card

play() Method:
    - prints a welcome message and the rules using the welcome() helper method
    - give 7 cards to both the player and the computer
    - shows the the current card and the currentSuit
    - Starst a while that ends when the player or computer wins
    - player1's turn and checks if the card they played is allowed
    - Checks if player1 won and if so, ends the game
    - Checks if a wild card was played by player and has the player choose a suit
    - Computer's turn and checks if the card they played is allowed
    - Checks if computer won and if so, ends the game
    - Someone eventually wins
    - asks player if they want to play again and return true or false

computerTurn() Method:
    - runs until the computer can place down a card
    - checks for the first available card to play
    - draws card if no card is playable
    - plays acceptable card
    - Checks if a wildcard was played and has the computer choose the suit of their next card

*helper methods*
fillHand(ArrayList handCards) Method:
    - Uses for loops to add 7 cards to the computer's hand

fillPlayerHand(ArrayList handCards) Method:
    - Uses for loops to add 7 cards to the player's hand

anotherGame() Method:
    - Asks the player if they want to play another game and returns another game

winner() Method:
    - checks if the player has 0 cards and prints a message if the player won
    - checks if the computer won if the player didn't win already and prints message if the computer won
    - continues the game if no one won

comDraw() Method:
    - checks if the deck can deal cards
    - if the deck can deal then the computer can draw cards
    - if not then the deck is shuffled then the computer can draw a card


suitFormatter() Method:
    - Formats the current suit to be presented to player

wildCardCom() Method:
    - checks if an 8 was played by the computer
    - sets the currentSuit to the suit of the next card in the com's hand
    - if a wildcard was not played then the game continues as normal

wildCardPlayer() Method:
    - checks if an 8 was played 
    - sets the suit to the suit of theplayer's choice
    - if a wildcard was not played then the game continues as normal

isItPlayable()Method:
    - Makes player chooses a card that is allowed to be played
    - checks if card chosen matches the faceup's suit or rank if not, the card is added back into the deck and the player has to choose a different card

welcome() Method:
    - Prints out a welcome message with the rules of the game.

I would have liked to be able to remove the cards that are in the player's and computer's hand from the deck after its been shuffled so that a card doesn't
show up thats already in someone's hand but it would require being able to identify whenever the deck was shuffled and removing from an array. This would 
require a lot of comparisons and technically the game would still work without doing this. I would have to have done it but I couldn't get it to work but 
here's my idea:
- shuffle the deck
- use a nested for loop that goes through each card in the hand on the outside and the inside for loop would go through the deck
- if there is a match, then that card should be moved to the front and the top tracker should be moved forward
- This should be done for both player's hands