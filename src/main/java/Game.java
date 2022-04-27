import java.util.Scanner;

public class Game {

    public void MakeAMove(int [][] matrix, Scanner input){

        GridGenerator GridInstance = new GridGenerator();
        int row = 0;
        int col = 0;
        int value = 0;
        String step = "";
        String[] stepParts;

        while(!GridInstance.isGridFull(matrix)){
            

        }
        System.out.println("Make a move!");
        step = input.next();

        stepParts = step.split(",");
        row = Integer.parseInt(stepParts[0]);
        col = Integer.parseInt(stepParts[0]);
        value = Integer.parseInt(stepParts[0]);

        matrix[row][col] = value;
        GridInstance.gridToString(matrix);
    }
}