import java.util.*;
//add push 
//add blackjack vs 21 
//add bet (rounds)
//add graphical user interface

public class BlackJackv2 {
	public static Vector userHand = new Vector(); 
	public static Vector compHand = new Vector(); 
	public static Vector userValue = new Vector(); 
	public static Vector compValue = new Vector(); 
	
	
	public static void getCard(Vector hand) { //inputs the vector and adds the card to the vector
		hand.add(deck());
	}
	
	public static String deck() { //puts the random number in the array of card choices
		String card = RANKS[Dealer()];
		
		return card;
	}
	
	public static final String[] RANKS = { //the card choices 
			
		       "Ace", "2", "3", "4", "5", "6", "7",
		       "8", "9", "10", "Jack", "Queen", "King"
		       
		       };
	
	
	
	public static int Dealer() {  //Random number generator
		Random rand = new Random();
		int card = rand.nextInt(13);
		return card;
	}
	
	
	
	
	public static void convert(Vector Hand, Vector Value) { // the first vector is your hand and the second is your value
		Value.clear();
		for(int i = 0; i < Hand.size();i++) {
			Value.add(Hand.get(i));
			if (Value.get(i) == "Jack" || Value.get(i) == "Queen" || Value.get(i) == "King") {
				Value.set(i, "10");
			}
			if (Value.get(i) == "Ace") {
				Value.set(i, "11");
			}
		}
		aceChecker(Hand,Value);
	}
	
	public static int total(Vector Value) {
		int sum = 0;
		for(int i = 0; i < Value.size(); i++) {
			
			int change = Integer.valueOf((String) Value.get(i));
			
			sum = sum + change;
		}
		return sum;
	}
	
	public static void startGame() {
		getCard(userHand);
		getCard(userHand);
		convert(userHand, userValue);
		
		getCard(compHand);
		getCard(compHand);
		convert(compHand, compValue);
		
		System.out.println("Your hand is: " + userHand + " and your total is " + total(userValue));
		System.out.println("Computers showing card is " + compHand.get(1));
		userInput();
		
	}
	public static void userInput() {
		System.out.println("Type 1 to get a new card");
		System.out.println("Type 2 to hold");
		Scanner input = new Scanner(System.in);
		int answer = input.nextInt();
		
		if(answer == 1) {
		getCard(userHand);
		convert(userHand, userValue);
		System.out.println("Your hand is: " + userHand + " and your total is " + total(userValue));
		
		if(total(userValue) > 21) {
			
			System.out.println("You broke 21 and lost");
			System.out.println("comp hand is: " + compHand);
		} else {
			
		userInput();
		}
		
		} else if (answer == 2) {
			
			System.out.println("I see you want to hold");
			compPlay();
			
		}
	}
	public static void aceChecker(Vector Hand, Vector Value) {
		if(total(Value) > 21 ) {
			for (int i= 0; i < Value.size(); i++) {
				if(Hand.get(i) == "Ace") {
					Value.set(i, "1");
				}
			}
		}
	}
	
	public static void compPlay() {
		while(total(compValue) < 15) {
			getCard(compHand);
			convert(compHand,compValue);
		}
		if (total(compValue) > 21) {
			System.out.println("computer broke 21");
			System.out.println("Comp is: " + compHand + " and the total was " + total(compValue));
		} else {
			ranking();
		}
	}
	
	public static void ranking() {
		int userTotal = total(userValue);
		int compTotal = total(compValue);
		System.out.println("user total:" + userTotal );
		System.out.println("Computer total:" + compTotal);
		
		if(userTotal > compTotal) {
			System.out.println("You Won!");
		} else {
			System.out.println("You Lost");
		}
		
	}
	

	
	
	
	
	public static void main(String[] args) {
		
		startGame();
	

	}

}
 