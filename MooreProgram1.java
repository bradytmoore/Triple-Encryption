/*
 * Brady Moore
 * 
 * CS354
 * 
 * 10/6/2022
 * 
 * Program 1
 * Pro. Walter
 * 
 */

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.lang.*;


class Columnar{
    List<Character> characterlist;
}

class MooreProgram1{
    String text;
    String keyword;

    



char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k','l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', }; 

char [] capAbc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
char [] sym = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '!', ',', '.', ' ', '?', '-', '<', '>', '/', '%', '^', '#', '(', ')', '&', '@'};

// char[] kwabc = new char[abc.length];

    //creating keyword and text variables for the MooreProgram1 Object
    public MooreProgram1(String keyword, String text){
        this.keyword = keyword.toLowerCase();
        this.text = text.toLowerCase();
    }
    
    //removes duplicates from keyword
    public String remDup(){
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        String noDup = "";


        for(int i = 0; i < keyword.length(); i++)
            set.add(keyword.charAt(i));
            for(Character character : set) noDup += character;
            keyword = noDup;


            return keyword;
        
    }

    //concatenates for keyword sub cipher
    public String conCat(String keyword) {

        //creating an array for the chars in the keyword string
        char[] keychar = keyword.toCharArray();
        StringBuilder preCip = new StringBuilder();

        //appending both the keyword chars and the alphabet into the same string
        preCip.append(keychar);
        preCip.append(abc);

        String cipher = preCip.toString();


        return cipher;
    }

    public String keywordCipher(String keyword, String text) {
        String cipher = "";
        text = text.toLowerCase();

        //Putting keyword and text into 2d array
        char[] keyArr = keyword.toCharArray();
        char[] textArr = text.toCharArray();

        //Going through both the abc and text arrays and noticing similarities
        for (int i = 0; i < textArr.length; i++) {
            for (int j = 0; j < abc.length; j++) {
                if (textArr[i] == abc[j]) {
                    
                    int temp = new String(keyArr).indexOf(keyArr[j]);
                    cipher += keyArr[temp];
                } 
                else if (textArr[i] == sym[j]) {
                    cipher += textArr[i];
                }
            }
        }
        return cipher;
    }


    
    
    //encrypts the text with columnar cipher
    public String colCip(String keyword, String text){

        // keyword.toLowerCase();
        // text.toLowerCase();


        Columnar[] columnars = new Columnar[keyword.length()];
        StringBuilder cipherText = new StringBuilder("");
        int columnIndex = 0;

        //initializing columnars
        for(int i = 0; i < keyword.length(); i++){
            columnars[i] = new Columnar();
            columnars[i].characterlist = new ArrayList<Character>();

        }

        //goes through text and puts the chars in columnar sequence
        for(int i = 0; i < text.length(); i++){

            //If columnIndex value reaches the length of the keyword its value is set to index 0
            if(columnIndex == keyword.length()){
                columnIndex = 0;
            }

            //adds text to columnars
            columnars[columnIndex].characterlist.add(text.charAt(i));

            columnIndex++;
        }

        //sorts the chars in the keyword into the sortedKey by ascending order
        char[] keyArray = keyword.toCharArray();
        Arrays.sort(keyArray);
        String sortedKey = new String(keyArray);

        //iterates through columnars
        for(int i = 0; i < keyword.length(); i++){
            char currentChar = sortedKey.charAt(i);
            int indexOfCurrentCharInKey = keyword.indexOf(currentChar);
            Columnar columnar = columnars[indexOfCurrentCharInKey];
            List<Character> characterlist = columnar.characterlist;
            ListIterator<Character> characterListIterator = characterlist.listIterator();

            while(characterListIterator.hasNext()){
                cipherText.append(characterListIterator.next());
            }
        }

        return cipherText.toString();
        
    }

    public String changeCaps(String cipher, String text) {
        String output = "";

        //finding the ascii value of the cipher and seeing if its between 65 and 90 or A-Z and if it is
        for (int i = 0; i < cipher.length(); i++) {
            if (text.codePointAt(i) <= 90 && text.codePointAt(i) >= 65) {
                
                String temp = String.valueOf(cipher.charAt(i));

                output += temp.toUpperCase();
            } 
            else if (text.codePointAt(i) <= 122 && text.codePointAt(i) >= 97) {
                String temp = String.valueOf(cipher.charAt(i));
                output += temp.toLowerCase();
            } else {
                output += String.valueOf(cipher.charAt(i));
            }
        }
        return output;
    }


    
    



    public static void main(String[] args)throws ArrayIndexOutOfBoundsException{

        

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please provide a keyword" + "\n");
        String keyword = keyboard.nextLine();
        System.out.println(keyword);

        System.out.println("Please provide text to cipher" + "\n");
        String text = keyboard.nextLine();
        System.out.println(text);
           

        System.out.println("Please select a cipher to apply:");
        System.out.println("\n 1. Keyword Cipher \n 2. Columnar Cipher \n 3. Both");

        int choice = keyboard.nextInt();


        if(choice == 1){
            MooreProgram1 kwc = new MooreProgram1(keyword, text);
            String keyW = kwc.conCat(keyword);

            MooreProgram1 kwc1 = new MooreProgram1((keyW), text);
            System.out.println(text);
            
            String keyW1 = kwc1.remDup();
            String cipher = kwc1.keywordCipher(keyW1, text);
            System.out.println(kwc1.changeCaps(cipher, text));
            
            
            
        
            
        }
        else if(choice == 2){
            MooreProgram1 clc = new MooreProgram1(keyword, text);
            String keyW = clc.remDup();

            MooreProgram1 clc1 = new MooreProgram1(keyW, text);
            String encryption = clc1.colCip(keyW, text);

            System.out.println(text);
            System.out.println(encryption);

            

            
        }
        else{
            
            MooreProgram1 both = new MooreProgram1(keyword, text);

            System.out.println("Starting Keyword Cipher");

            
            MooreProgram1 kwc = new MooreProgram1(keyword, text);
            String keyW = kwc.conCat(keyword);

            MooreProgram1 kwc1 = new MooreProgram1((keyW), text);
            System.out.println(text);
            
            String keyW1 = kwc1.remDup();
            String cipher = kwc1.keywordCipher(keyW1, text);
            System.out.println(kwc1.changeCaps(cipher, text));


            System.out.println("Starting Columnar Cipher");

            MooreProgram1 clc = new MooreProgram1(keyword, cipher);
            String keyW2= clc.remDup();

            MooreProgram1 clc1 = new MooreProgram1(keyW2, text);
            String encryption = clc1.colCip(keyW2, text);

            System.out.println(text);
            System.out.println(encryption);
            

            
            // String cipherText = colTran.colCip(keyword, text);

            // System.out.println(cipherText);




        }
        keyboard.close();
    }

}

