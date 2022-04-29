import java.util.Scanner;

/**
 * Author: Peter Stefan
 * Class to get inputs while the game is running, and input values in the cells, check if win condition is reached
 */
public class Game {

    /**
     * A method to receive and use user input in the game
     * @param matrix current grid
     * @param input input from user
     */

    public void MakeAMove(int [][] matrix, Scanner input){

        int row;
        int col;
        int value;
        int ascii;
        int asciiMin = 48;
        int asciiMax = 57;
        int size = matrix.length;
        int[][] copyMatrix = new int[size][size];
        char [] ch;
        String step;
        String[] stepParts;

        //copy matrix, solve the original and use it to determine validity of inputs
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copyMatrix[i][j] = matrix[i][j];
            }
        }
        GridGenerator.SolveSudoku(matrix, 0, 0);

        //loop until the grid is not full, when it is the game is won
        while(!GridGenerator.isGridFull(copyMatrix)){
            System.out.println("Make a move! (format : 1,1,1)");
            step = input.next();

            //check if the input is right or not
            boolean valid = false;

            //separate input string into parts to check the input against ascii codes
            // to see if it is between 0-9, and it is a number
            stepParts = step.split(",");
            //ascii values 0 = 48, 9 = 57
            for (String stepPart : stepParts) {
                ascii = stepPart.charAt(0);
                if (asciiMin <= ascii && ascii <= asciiMax) {
                    valid = true;
                }
                else {
                    System.out.println("Invalid input! \nYour number has to be from 0-9.");
                }
            }

            //if the input is valid check the cell value against our filled out grid
            if(valid){
                row = Integer.parseInt(stepParts[0]) - 1;
                col = Integer.parseInt(stepParts[1]) - 1;
                value = Integer.parseInt(stepParts[2]);

                if(row >= 0 && row < 10 && col >= 0 && col < 10 && value < 10){

                    //check for already filled in cell
                    if(copyMatrix[row][col] != 0){
                        System.out.println("That cell is already taken :( Find your own!");
                    }

                    else{
                        copyMatrix[row][col] = value;
                        //check if move is valid
                        if(value == matrix[row][col]){
                            GridGenerator.gridToString(copyMatrix);
                            System.out.println("\n");
                        }
                        //check for wrong answers
                        else{
                            System.out.println("Wrong answer buckaroo!\n");
                            copyMatrix[row][col] = 0;
                        }
                    }
                }
            }
        }
        //tell the player they won yay!
        System.out.println("You win woo!");
        System.out.println();
    }
}