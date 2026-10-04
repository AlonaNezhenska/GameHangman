import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordList {

    public static final String WORDS_PATH = "words.txt";

    public static void main(String[] args) throws IOException {
        File dictionary = new File(WORDS_PATH);
        Scanner textScanner = new Scanner(dictionary);
        ArrayList<String> words = new ArrayList<>();

        while (textScanner.hasNext()) {
            words.add(textScanner.nextLine());
        }

        textScanner.close();

        String randomWord = WordList.getRandomWord(words);
        System.out.println("Random Word: " + randomWord);
    }

    public static String getRandomWord(List<String> words) {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }
}




//        boolean finished = false;
//        int lives = 7;
//        
//        while(finished==false) {
//      
//        	
//        	String letter = input.next();
//        	//check for a valid input
//        	while(letter.length() != 1 || Character.isDigit(letter.charAt(0)) ) {
//        		System.out.println("Error inmput - Try Again");
//        		letter = input.next();	
//        		
//        	}
//        }
//        	//check if the letter in the word
//       boolean found = false;
//       for(int i=0;i<textArray.length;i++) {
//    	   if(letter.charAt(0)== textArray[i]) {
//    		   myAnswers[i]=textArray[i];
//    		   
//    		   found=true;
//    	   }
//    	   
//       }
//       
//       if(!found) {
//    	   lives--;
//    	   
//    	   System.out.println("Wrong Letter");
    	   
    	   
     //  }
      
        
    