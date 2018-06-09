/**
 * NAME: Doan Phuong Anh
 * Date: 070618
 * Assignment 1: Part 1, 2, 3, 4
 */

import java.util.*;
public class Game {
    public static void main(String[] args) {
        //Create the board
        String[][] board = {
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
        };

        //Locate player
        int row = 0;
        int col = 0;
        board[row][col] = "P";

        //Locate enemy
        Random num = new Random();
        //first enemy
        int enRow1 = 0;
        int enCol1 = 0;
        //second enemy
        int enRow2 = 0;
        int enCol2 = 0;
        /**
         * The two enemies have to be in different locations
         * from each other
         * and from the player
         */
        while ((enRow1 == enRow2 && enCol1 == enCol2) || (enRow1 == row && enCol1 == col) || (enRow2 == row && enCol2 == col)) {
            //first enemy
            enRow1 = num.nextInt(3) + 1;
            enCol1 = num.nextInt(3) + 1;
            //second enemy
            enRow2 = num.nextInt(3) + 1;
            enCol2 = num.nextInt(3) + 1;
        }
        board[enRow1][enCol1] = "E";
        board[enRow2][enCol2] = "E";

        /**
         * Locate gift
         * The initial position of the gift should be random,
         * different from locations of player and enemies
         */
        int giftRow = 0;
        int giftCol = 0;
        while ((giftRow == enRow2 && giftCol== enCol2) || (giftRow == row && giftCol == col) || (giftRow == enRow1 && giftCol== enCol1)) {
            giftRow = num.nextInt(3) + 1;
            giftCol= num.nextInt(3) + 1;
           }
           board[giftRow][giftCol] = "G";


        //Print out the board
        for (int i = 0; i < board.length; i ++) {
            for (int j  = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(" ");
        }

        //Print out instruction
        System.out.println("\tInstruction: ");
        System.out.println("Left: push A; \nRight: push D; \nDown: push S; \nUp: push W; \nExit Game: ESC");
        Scanner console = new Scanner(System.in);
        System.out.println(" ");
        System.out.print("\tYour Move: ");
        String move = console.next();

        //prompt for next move
        while (!move.equals("ESC")) {
            //Store the previous location of the player and the enemy
            int preRow = row;
            int preCol = col;
            int preEnCol1 = enCol1;
            int preEnRow2 = enRow2;

            //Move the enemy
            // first enemy  moves form left to right
            if (enCol1 == 3) {
                enCol1 = 0;
            } else {
                enCol1 += 1;
            }

            //second enemy moves downwards
            if (enRow2 == 3) {
                enRow2 = 0;
            } else {
                enRow2 += 1;
            }
            //update the position of the enemy every time player moves
            board[enRow1][preEnCol1] = "*";
            board[preEnRow2][enCol2] = "*";
            board[enRow1][enCol1] = "E";
            board[enRow2][enCol2] = "E";


            if (move .equals("A") && col > 0) {
                col -= 1;

            } else if (move .equals("D") && col < 3) {
                col += 1;

            } else if (move .equals("S") && row < 3) {
                row += 1;

            } else if (move .equals("W") && row > 0){
                row -= 1;

            } else if (move .equals("D") && col == 3) {
                col = 0;

            } else if (move .equals("A") && col == 0) {
                col = 3;

            } else if (move .equals("S") && row == 3) {
                row = 0;

            } else if (move .equals("W") && row == 0){
                row = 3;

            } else {
                System.out.println("The move is not valid");
            }

            //Game ends when the player meets the enemy
            if (enRow1 == row && enCol1 == col) {
                board[row][col] = "E";
                for (int i = 0; i < board.length; i ++) {
                    for (int j  = 0; j < board[0].length; j++) {
                        System.out.print(board[i][j] + " ");
                    }
                    System.out.println(" ");
                }
                System.out.println("GAME OVER.");
                return;
            }

            /**
             * Game ends and the player wins when
             *its position and gift's position are the same
             * When enemies meet gift, enemies will take the priority to present
             */
            if (row == giftRow && col == giftCol) {
                System.out.println("YOU WIN");
                return;
            }

            if ((enRow1 == giftRow && enCol1 == giftCol) || (enRow2 == giftRow && enCol2 == giftCol)) {
                board[giftRow][giftCol] = "E";
            } else {
                board[giftRow][giftCol] = "G";
            }

            //update the position of player
            board[row][col] = "P";
            if (preRow == enRow1 && preCol == enCol1) { //in case the enemy moves to the previous position of the player
                board[enRow1][enCol1] = "E";
            } else if (preRow == enRow2 && preCol == enCol2) { //in case the enemy moves to the previous position of the player
                board[enRow2][enCol2] = "E";
            }
            else {
                board[preRow][preCol] = "*";
            }



            for (int i = 0; i < board.length; i ++) {
                for (int j  = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println(" ");
            }
            System.out.print("Your Move: ");
            move = console.next();
        }

        //Game ends when ECS is pushed
        System.out.println("GAME OVER.");


    }
}
