import java.util.Scanner;
import java.io.IOException;


public class PatsGame {

	//validate moves -- might be working
	private Boolean validWord(String startingWord, String newWord){
		String seed = startingWord;
		String tree = newWord;
		Boolean output = true;
		if (seed.equals(tree)) return false;
		else if ((tree.length() < seed.length()-1) || (tree.length() > seed.length() + 1)) return false;
		else if (tree.length() == seed.length()-1){
			int treeScore = tree.length();
			String tempSeed = seed;
			for (int i=0; i < tree.length(); i++){
				int charToRemove = tempSeed.indexOf(tree.charAt(i));
				if (charToRemove != -1) {
					StringBuilder sb = new StringBuilder(tempSeed);
					sb.deleteCharAt(charToRemove);
					tempSeed = sb.toString();
					treeScore--;				
				}
			}
			if (treeScore != 0){
				output = false;
			}
		}
		else if (tree.length() == seed.length() + 1){
			int seedScore = seed.length();
			String tempTree = tree;
			for (int i=0; i < seed.length(); i++){
				int charToRemove = tempTree.indexOf(seed.charAt(i));
				if (charToRemove != -1) {
					StringBuilder sb = new StringBuilder(tempTree);
					sb.deleteCharAt(charToRemove);
					tempTree = sb.toString();
					seedScore--;				
				}
			}
			if (seedScore != 0){
				output = false;
			}
		}
		else if (tree.length() == seed.length()){
			int seedScore = seed.length();
			String tempTree = tree;
			for (int i=0; i < seed.length(); i++){
				int charToRemove = tempTree.indexOf(seed.charAt(i));
				if (charToRemove != -1) {
					StringBuilder sb = new StringBuilder(tempTree);
					sb.deleteCharAt(charToRemove);
					tempTree = sb.toString();
					seedScore--;				
				}
			}
			if (seedScore > 1){
				output = false;
			}
		}

		return output;
	}

	private Boolean yesNoCheck (Scanner scanner) {
		System.out.println("Please enter 'y' for yes or 'n' for no");
		String check = scanner.next();
		if (check.equals("y")) {
			return true;
		}
		else if (check.equals("n")) {
			return false;
		}
		else {
			return(yesNoCheck(scanner));
		}
	}

	public static void main (String [] args) throws IOException {
		Boolean running = true;
		PatsGame game = new PatsGame();
		Dictionary dict = new Dictionary();
		System.out.println("Please enter your starting word");
		Scanner scanner = new Scanner (System.in);
		String temp = scanner.next();
		String word = temp;
		while (running){
			System.out.println("Add, take away or change a letter or rearrange the letters to create a new valid word");
			System.out.println("The current word is: '" + word + "' please enter your new word");
			temp = scanner.next();
			if (dict.contains(temp)) {System.out.println(temp + " is in the dictionary!");}
			else {
				System.out.println(temp + " is not in the dictionary!");
			}
			if (game.validWord(word, temp)){
				System.out.println("Is: '" + temp + "' accepted by the group?");
				if (game.yesNoCheck(scanner)){
					word = temp;
					System.out.println("The word '" + temp + "' is valid and therefore is the new word");
				}
				else {
					word = word;
					System.out.println("The word '" + temp + "' is invalid and therefore you are eliminated");
				}
			}
			else {
				System.out.println("The word '" + temp + "' is invalid and therefore you are eliminated (you used invalid letters)");

			}

		}
	}
}