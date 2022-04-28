import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GridGenerator {

    private int[][] matrixFirst;
    private final static int Size = 9;
    private static int min = 1;
    private static int max = 9;
    private static int counter = 0;

    public GridGenerator() {
        this.matrixFirst = new int[Size][Size];
    }

    public static void gridToString(int[][] matrix) {

        for (int i = 0; i < Size; i++) {
            // length returns number of rows
            //System.out.print("|" + i + "| ");

            for (int j = 0; j < Size; j++) {
                if(matrix[i][j] == 0){
                    System.out.print("| | ");
                }
                else {
                    System.out.print("|" + matrix[i][j] + "| ");
                }
            }
            System.out.println();
        }
    }


    public static boolean isGridFull(int[][] matrix) {
        for (int i = 0; i < Size; i++) {
            // length returns number of rows
            for (int j = 0; j < Size; j++) {
                // here length returns # of columns corresponding to current row
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean inRow(int [][] matrix, int value, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            // here length returns # of columns corresponding to current row
            if (matrix[row][i] == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean inCol(int[][] matrix, int value, int col) {
        for (int i = 0; i < matrix[col].length; i++) {
            // here length returns # of columns corresponding to current row
            if (matrix[i][col] == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean inSquare(int[][] matrix, int value, int row, int col) {
        //identify which of the 9 squares we are using
        int sqrt = (int) Math.sqrt(Size);
        int cornerRow = row - row % sqrt;
        int cornerColumn = col - col % sqrt;

        for (int j = cornerRow; j < (cornerRow + sqrt); j++) {
            for (int k = cornerColumn; k < (cornerColumn + sqrt); k++) {
                if (matrix[j][k] == value) {
                    return true;
                }
            }
        }
        return false;
    }


    //public int[][] getMatrix() {return this.matrix;}


    public static boolean SolveSudoku(int[][] matrix, int row, int col) {


        if (row == Size - 1 && col == Size)
            return true;
        if (col == Size) {
            row++;
            col = 0;
        }
        if (matrix[row][col] != 0){
            return SolveSudoku(matrix, row, col + 1);
        }
        for (int value = 1; value < 10; value++) {
            if (!inRow(matrix, value, row)) {
                //check if value hasn't been used in column yet
                if (!inCol(matrix, value, col)) {
                    //check if value has been used in the square yet
                    if (!inSquare(matrix, value, row, col)) {
                        matrix[row][col] = value;

                        if (SolveSudoku(matrix, row, col))
                            return true;
                    }
                    matrix[row][col] = 0;
                }
            }
        }
        return false;
    }

    public static boolean fillGrid(int[][] matrix) {
        ArrayList<Integer> numberList = new ArrayList<Integer>();
        for (int i = 1; i < Size + 1; i++) {
            numberList.add(i);
        }

        int row = 0;
        int col = 0;
        //find next empty cell
        for (int i = 0; i < Size * Size; i++) {
            row = i / Size;
            col = i % Size;
            if (matrix[row][col] == 0) {
                Collections.shuffle(numberList);
                //check if value hasn't been used yet in row
                for (int j = 0; j < numberList.size(); j++) {
                    int value = numberList.get(j);
                    if (!inRow(matrix, value, row)) {
                        //check if value hasn't been used in column yet
                        if (!inCol(matrix, value, col)) {
                            //check if value has been used in the square yet
                            if (!inSquare(matrix, value, row, col)) {
                                matrix[row][col] = value;
                                if (isGridFull(matrix)) {
                                    counter++;
                                    return true;
                                }
                                else if (fillGrid(matrix)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        matrix[row][col] = 0;
        return false;
    }

    public static void removeNumbers(int[][] matrix, int difficulty){
        int rangeMin = min - 1;
        int rangeMax = max - 1;
        int row = 0;
        int col = 0;

        for(int i = 0; i < difficulty; i++){

            while (matrix[row][col] == 0){
                Random rn = new Random();
                row = rn.nextInt((rangeMax - rangeMin + 1) + rangeMin);
                col = rn.nextInt((rangeMax - rangeMin + 1) + rangeMin);
            }
            matrix[row][col] = 0;
        }
    }
}