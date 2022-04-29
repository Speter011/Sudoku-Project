import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Author: Peter Stefan
 * class for creating and solving the grid
 */
public class GridGenerator {

    private int[][] matrixFirst;
    private final static int Size = 9;
    private static int min = 1;
    private static int max = 9;
    private static int counter = 0;

    public GridGenerator() {
        this.matrixFirst = new int[Size][Size];
    }

    /**
     *  Displays the current grid/game state
      * @param matrix the grid we want to display
     */
    public static void gridToString(int[][] matrix) {
        //go through rows and columns
        for (int i = 0; i < Size; i++) {
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

    /**
     * Checks if the current grid is full or not
     * @param matrix our grid
     * @return true if it is full, false if it's not
     */
    public static boolean isGridFull(int[][] matrix) {
        //go through rows and columns
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a value is already in use in a row or not
     * @param matrix our grid
     * @param value value in cell
     * @param row row we want to check
     * @return true if it is used, false if not
     */
    public static boolean inRow(int [][] matrix, int value, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            if (matrix[row][i] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a value is already in use in  a column or not
     * @param matrix our grid
     * @param value value in the cell
     * @param col column we check
     * @return true if it is used, false if not
     */
    public static boolean inCol(int[][] matrix, int value, int col) {
        for (int i = 0; i < matrix[col].length; i++) {
            if (matrix[i][col] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a value is already in use in a sqrt(size) square
     * @param matrix grid we check
     * @param value value in the cell
     * @param row current row
     * @param col current col
     * @return true if it is used, false if it's not
     */
    public static boolean inSquare(int[][] matrix, int value, int row, int col) {
        //identify which of the 9 cells(sqrt(size) if different size) we are using
        int sqrt = (int) Math.sqrt(Size);
        //"coordinates" for top left corner of our square
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

    /**
     * Solves a sudoku table
     * @param matrix the table we want solved
     * @param row starting row
     * @param col starting column
     * @return true if it's solvable false if not
     */
    public static boolean SolveSudoku(int[][] matrix, int row, int col) {

        if (row == Size - 1 && col == Size)
            return true;
        if (col == Size) {
            row++;
            col = 0;
        }
        //only start on a cell where the value is not 0
        if (matrix[row][col] != 0){
            return SolveSudoku(matrix, row, col + 1);
        }
        //check every possible value
        for (int value = 1; value < 10; value++) {
            if (!inRow(matrix, value, row)) {
                //check if value hasn't been used in column yet
                if (!inCol(matrix, value, col)) {
                    //check if value has been used in the square yet
                    if (!inSquare(matrix, value, row, col)) {
                        matrix[row][col] = value;

                        //recursive call of the method
                        if (SolveSudoku(matrix, row, col))
                            return true;
                    }
                    matrix[row][col] = 0;
                }
            }
        }
        return false;
    }

    /**
     * Fills a grid with random numbers creating a unique filled in random sudoku table each time
     * @param matrix the grid
     * @return true if grid is filled, false if not
     */
    public static boolean fillGrid(int[][] matrix) {
        //arrayList for numbers we want to use
        ArrayList<Integer> numberList = new ArrayList<>();
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
                //shuffle the list so that the results become random
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
                                //recursive call for the method
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

    /**
     * Remove required amount of numbers from a filled in grid
     * @param matrix grid we use
     * @param difficulty determines the number of cells we want to use
     *                   for example difficulty = 1 removes one cell leaving 80 clues in a 9x9 sudoku table
     */
    public static void removeNumbers(int[][] matrix, int difficulty){
        int rangeMin = min - 1;
        int rangeMax = max - 1;
        int row = 0;
        int col = 0;

        for(int i = 0; i < difficulty; i++){
            //get a random cell and remove it's contents
            while (matrix[row][col] == 0){
                Random rn = new Random();
                row = rn.nextInt((rangeMax - rangeMin + 1) + rangeMin);
                col = rn.nextInt((rangeMax - rangeMin + 1) + rangeMin);
            }
            matrix[row][col] = 0;
        }
    }
}