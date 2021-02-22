package bullscows;

import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int bulls = 0;
        int cows = 0;
        int guesses = 1;
        int len = 0;
        int possible_symbols = 0;
        boolean notFound = true;
        boolean firstInput = true;


        System.out.println("Input the length of the secret code:");
        if (firstInput) {
            String len1 = sc.nextLine();
            try {
                len = Integer.parseInt(len1);
            } catch (NumberFormatException e) {
                System.out.println("Error.Invalid user input: " + len1);
                firstInput = false;
            }
        }

        System.out.println("Input the number of possible symbols in the code:");
        if (firstInput) {
            String possible_symbols1 = sc.nextLine();
            try {
                possible_symbols = Integer.parseInt(possible_symbols1);
            } catch (NumberFormatException e) {
                System.out.println("Error.Invalid user input: " + possible_symbols1);
                firstInput = false;
            }
        }

        if (len > 36 || len == 0) {
            System.out.println("Error: can't generate a secret number with a length of " + len + " because " +
                    "there aren't enough unique digits");
        } else if (possible_symbols > 36 || possible_symbols == 0) {
            System.out.println("Error of possible symbols len, max length is no more than 36");
        } else if (possible_symbols < len) {
            System.out.println("Error: it's not possible to generate a code with a length of " + len +"" +
                    "with " + possible_symbols +"unique symbols");
        } else {
            System.out.println("Okay, let's start a game!");
            chosenNum = rand(len, possible_symbols);
            String str = replaceWithStars(chosenNum);

            System.out.print("The secret is prepared:" +  str + " ");
            num(len,possible_symbols);
        }


        //String guessedNumber = sc.nextLine();



        if ((len <= 36 && len > 0) && (possible_symbols <= 36 && possible_symbols > 0) && (possible_symbols >= len)) {
            do {
                System.out.println("Turn " + guesses + ": ");

                String guessedNumber = sc.nextLine();
                //System.out.println(guessedNumber.length() + " " + len);

                if (guessedNumber.length() == len) {
                    bulls = computeBulls(guessedNumber, chosenNum);
                    cows = computeCows(guessedNumber, chosenNum);
                }

                if (guessedNumber.length() != len) {
                    System.out.println("Error. Your guess should contain " + len + " symbols (Digits)");
                }  else if (bulls == len) {
                    System.out.println("Grade: " + bulls + " bulls");
                    System.out.println("Congratulations! You guessed the secret code.");
                    notFound = false;
                } else {
                    System.out.println("Grade: " + bulls + " bulls and " + cows + " Cows");
                    guesses++;
                }
            } while (notFound);
        }

        //sc.close();
    }

    private static String chosenNum = "";

    private static String rand(int len, int possible_symbols) {
        Random random = new Random();
        int randomDigit = 0;
        //char symbol = Character.forDigit(randomDigit, symbolsRange);
        //String text = String.valueOf(pseudoRandomNumber);

        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder();

        while (len != sb.length()) {
            int last_index_symbol = symbols.indexOf(symbols.charAt(possible_symbols-1));
            randomDigit = random.nextInt(last_index_symbol - 0) + 0;
            char b = symbols.charAt(randomDigit);
            if (!sb.toString().contains(String.valueOf(b))) {
                sb.append(b);
            }
        }

        return sb.toString();
    }



    private static String replaceWithStars(String chosenNum) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chosenNum.length(); i++){
            sb.append('*');
        }

        return sb.toString();

    }

    private static void num (int len, int possible_symbols){

        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        char b = symbols.charAt(possible_symbols - 1);
        //System.out.println(b);

        if (possible_symbols < 11) {
            System.out.print(" (0 - " + "9" + ")\n");
        } else if (possible_symbols == 11) {
            System.out.print(" (0 - 9) " + "(a)" + "\n");
        } else {
            System.out.print(" (0 - 9) " + "(a - " + b + ")\n");
        }
    }


    private static int computeBulls(String num1, String num2) {
        int bullCounter = 0;

        for (int i = 0; i < num2.length(); i++) {
            if (num2.charAt(i) == num1.charAt(i)) {
                    bullCounter++;
            }

        }
        return bullCounter;
    }

    private static int computeCows(String num1, String num2) {
        int cowsCounter = 0;
        for (int i = 0; i < num2.length(); i++) {
            for (int j = 0; j < num1.length(); j++) {
                if (num2.charAt(i) == num1.charAt(j) && i != j) {
                    cowsCounter++;
                }
            }
        }
            return cowsCounter;
    }

}