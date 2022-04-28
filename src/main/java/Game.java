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
        boolean flag = false;
        String step;
        String option = "";
        String[] stepParts;


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copyMatrix[i][j] = matrix[i][j];
            }
        }

        while(!GridGenerator.isGridFull(copyMatrix)){
            System.out.println("Make a move! (format : 1,1,1)");
            step = input.next();

            //ascii values 0 = 48, 9 = 57
            stepParts = step.split(",");
            for(int i = 0; i < stepParts.length; i++){
                ascii = stepParts[i].charAt(0);
                if(asciiMin < ascii  && ascii < asciiMax){
                    flag = true;
                }
                else{
                    System.out.println("Invalid input! \nYour number has to be from 0-9.");
                    flag = false;
                    step = input.next();
                }
            }

            if(flag){
                row = Integer.parseInt(stepParts[0]) - 1;
                col = Integer.parseInt(stepParts[1]) - 1;
                value = Integer.parseInt(stepParts[2]);

                //check if move is valid
                if(GridGenerator.SolveSudoku(copyMatrix, 0, 0)){
                    GridGenerator.gridToString(copyMatrix);
                    System.out.println("\n");
                }
                else{
                    System.out.println("Wrong answer buckaroo!\n");
                    copyMatrix[row][col] = matrix[row][col];
                }
                copyMatrix[row][col] = value;
            }
        }
        System.out.println("You win woo!");
        System.out.println();
    }
}