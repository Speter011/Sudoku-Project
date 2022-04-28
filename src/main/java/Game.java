import java.util.Scanner;

public class Game {


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


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copyMatrix[i][j] = matrix[i][j];
            }
        }
        GridGenerator.SolveSudoku(matrix, 0, 0);

        while(!GridGenerator.isGridFull(copyMatrix)){
            System.out.println("Make a move! (format : 1,1,1)");
            step = input.next();


            int valid = 0;
            /*
            int counter = 0;
            ch = step.toCharArray();
            for (char c : ch) {
                if (c == ',') {
                    counter++;
                    System.out.println("hell nah");
                }
            }
            if (counter == 2){
                System.out.println("stupid");
                valid++;
            }
            */

            stepParts = step.split(",");
            //ascii values 0 = 48, 9 = 57
            for (String stepPart : stepParts) {
                ascii = stepPart.charAt(0);
                if (asciiMin <= ascii && ascii <= asciiMax) {
                    valid = 1;
                    System.out.println("yes");
                }
                else {
                    System.out.println("Invalid input! \nYour number has to be from 0-9.");
                }
            }

            if(valid == 1){
                row = Integer.parseInt(stepParts[0]) - 1;
                col = Integer.parseInt(stepParts[1]) - 1;
                value = Integer.parseInt(stepParts[2]);

                if(row >= 0 && row < 10 && col >= 0 && col < 10 && value < 10){

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
                        else{
                            System.out.println("Wrong answer buckaroo!\n");
                            copyMatrix[row][col] = 0;
                        }
                    }
                }
            }
        }
        System.out.println("You win woo!");
        System.out.println();
    }
}