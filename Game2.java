/**
 * NAME: Doan Phuong Anh
 * Date: 070618
 * Assignment 1: Part 5, 6, 7, 8
 */

import java.util.*;
public class Game2 {
    public static void main(String[] args) {
        /Create a 10* 10 board
        String[][] board = new String[10][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "*";
            }
        }

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
        //third enemy
        int enRow3 = 0;
        int enCol3 = 0;
        /**
         * The three enemies have to be in different locations
         * from each other
         * and from the player
         */
        while ((enRow1 == enRow2 && enCol1 == enCol2) || (enRow1 == row && enCol1 == col) || (enRow2 == row && enCol2 == col) || 
            (enRow3 == row && enCol3 == col) || (enRow3 == enRow1 && enCol3 == enCol1) || (enRow3 == enRow2 && enCol3 == enCol2)) {
            //first enemy
            enRow1 = num.nextInt(9) + 1;
            enCol1 = num.nextInt(9) + 1;
            //second enemy
            enRow2 = num.nextInt(9) + 1;
            enCol2 = num.nextInt(9) + 1;
            //third enemy
            enRow3 = num.nextInt(9) + 1;
            enCol3 = num.nextInt(9) + 1;
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
            giftRow = num.nextInt(9) + 1;
            giftCol= num.nextInt(9) + 1;
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
            int preEnRow3 = enRow3;
            int preEnCol3 = enCol3;

            //Move the enemy
            if (move .equals("A") && col > 0) {
                col -= 1;
                enCol3 -= 2;

            } else if (move .equals("D") && col < 9) {
                col += 1;
                enCol3 += 2;

            } else if (move .equals("S") && row < 9) {
                row += 1;
                enRow3 += 2;

            } else if (move .equals("W") && row > 0){
                row -= 1;
                enRow3 -= 2;

            } else if (move .equals("D") && col == 9) {
                col = 0;
                enCol3 += 2;

            } else if (move .equals("A") && col == 0) {
                col = 3;
                enCol3 -= 2;

            } else if (move .equals("S") && row == 9) {
                row = 0;
                enRow3 += 2;

            } else if (move .equals("W") && row == 0){
                row = 3;
                enRow3 -= 2;

            } else {
                System.out.println("The move is not valid");
            }

            //When the enemies hit the wall, they will bounce back one step (two steps for the third)
            // first enemy moves form left to right
            enCol1 += 1;

            //second enemy moves downwards
            enRow2 += 1;

            //Update the postion of the enemies
            board[enRow1][preEnCol1] = "*";
            board[preEnRow2][enCol2] = "*";
            board[preEnRow3][preEnCol3] = "*";
            board[enRow1][enCol1] = "E";
            board[enRow2][enCol2] = "E";
            board[enRow3][enCol3] = "E";

            //Game ends when the player meets the enemy
            if ((enRow1 == row && enCol1 == col) || (enRow2 == row && enCol2 == col) || (enRow3 == row && enCol3 == col)) {
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
             * its position and gift's position are the same
             * When enemies meet gift, enemies will take the priority to present
             */
            if (row == giftRow && col == giftCol) {
                System.out.println("YOU WIN");
                return;
            }

            if ((enRow1 == giftRow && enCol1 == giftCol) || (enRow2 == giftRow && enCol2 == giftCol) || (enRow3 == giftRow && enCol3 == giftCol)) {
                board[giftRow][giftCol] = "E";
            } else {
                board[giftRow][giftCol] = "G";
            }

            //update the position of player
            //in case the enemy moves to the previous position of the player
            board[row][col] = "P";
            if ((preRow == enRow1 && preCol == enCol1) || (preRow == enRow2 && preCol == enCol2) || (preRow == enRow3 && preCol == enCol3)) { 
                board[preRow][preCol] = "E";
            } else {
                board[preRow][preCol] = "*";
            }


            or (int i = 0; i < board.length; i ++) {
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
