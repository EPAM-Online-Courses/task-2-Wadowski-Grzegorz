package efs.task.syntax;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    int M = -1;
    int guess_number = -1;
    int L = -1;
    int tries = 1;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try{
            M = Integer.parseInt(argument);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(UsefulConstants.NOT_A_NUMBER);
        }
        if( M < 1  || M > UsefulConstants.MAX_UPPER_BOUND ){
            throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
        }
    }

    public void play() {
        Random rand = new Random();
        guess_number = rand.nextInt(M);
        guess_number++;
        //System.out.println("guess_number = " + guess_number);

        L = (int) (Math.log((double) M) / Math.log(2)) + 1;
        //System.out.println("L = " + L);

        // Zakres odgadywanej liczby
        System.out.println("<1," + M + ">");

        Scanner scan_in = new Scanner(System.in);
        String user_input;
        int user_number = -1;

        while( L > 0 ){
            System.out.print("[");
            for(int i = 0; i < tries; i++){
                System.out.print("*");
            }
            for(int i = 0; i < L - 1; i++){
                System.out.print(".");
            }


            System.out.println("]");

            System.out.println(UsefulConstants.GIVE_ME);
            user_input = scan_in.nextLine();
            try{
                user_number = Integer.parseInt(user_input);
            }catch( NumberFormatException e ){
                throw new IllegalArgumentException(UsefulConstants.NOT_A_NUMBER);
            }

            if( user_number > guess_number ) {
                System.out.println(UsefulConstants.TO_MUCH);
            }else if( user_number < guess_number ){
                System.out.println(UsefulConstants.TO_LESS);
            }else if( user_number == guess_number ){
                System.out.println(UsefulConstants.YES);
                break;
            }

            L--;
            tries++;
        }

        if( L > 0 ){
            System.out.println(UsefulConstants.CONGRATULATIONS);
        } else{
            System.out.println(UsefulConstants.UNFORTUNATELY);
        }
    }
}