import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class GridGenerator {


    private int[][] matrix;
    private final int size = 9;
    public GridGenerator(){
        this.matrix = new int[size][size];
    }


    private int min = 1;
    private int max = 9;

    public void gridToString() {

        for (int i = 0; i < this.matrix.length; i++) {
            // length returns number of rows
            //System.out.print("|" + i + "| ");
            for (int j = 0; j < this.matrix[i].length; j++) {
                Random rn = new Random();
                int nextNum = rn.nextInt(this.max - this.min +1) + this.min;
                // here length returns # of columns corresponding to current row

                System.out.print("|" + nextNum +"| ");
            }
            System.out.println();
        }
    }

    public boolean isGridFull() {
        for (int i = 0; i < this.matrix.length; i++) {
            // length returns number of rows
            for (int j = 0; j < this.matrix[i].length; j++) {
                // here length returns # of columns corresponding to current row
                if (this.matrix[i][j] == 0){
                    return false;
                }
            }
        }
        System.out.println("We have a full grid");
        return true;
    }

    private boolean inRow(int value, int row) {
        for (int i = 0; i < this.matrix[row].length; i++) {
            // here length returns # of columns corresponding to current row
            if (this.matrix[row][i] == value){
                return true;
            }
        }
        return false;
    }

    private boolean inCol(int value, int col) {
        for (int i = 0; i < this.matrix[col].length; i++) {
            // here length returns # of columns corresponding to current row
            if (this.matrix[i][col] == value){
                return true;
            }
        }
        return false;
    }

    private boolean inSquare(int value, int row, int col){
        //identify which of the 9 squares we are using
        int[][] square;
        int sqrt = (int)Math.sqrt(size);
        int cornerRow = row - row % sqrt;
        int cornerColumn = col - col % sqrt;

        for(int j = cornerRow; j < cornerRow + sqrt; j++){
            for(int k = cornerColumn; k < cornerColumn + sqrt; k++){
                if (this.matrix[j][k] == value){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean solveGrid() {
        int counter = 0;
        int row = 0;
        int col = 0;
        for(int i = 0; i < size*size; i++){
            row = i/size;
            col = i%size;
            if (this.matrix[row][col] == 0){
                //check if value hasn't been used yet in row
                for(int value = 1; value <= size; value++){
                    if(inRow(value, row) == false){
                        //check if value hasn't been used in column yet
                        if(inCol(value, col) == false){
                            //check if value has been used in the square yet
                            if(inSquare(value, row, col) == false){
                                this.matrix[row][col] = value;
                                if(isGridFull() == true){
                                    counter++;
                                    break;
                                }
                                else if(solveGrid() == true){
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        this.matrix[row][col] = 0;
        return false;
    }


    public boolean fillGrid() {
        ArrayList<Integer> numberList = new ArrayList<Integer>();
        for(int i = 1; i < size + 1; i++){
            numberList.add(i);
        }
        int counter = 0;
        //find next empty cell
        for(int i = 0; i < size*size; i++){
            int row = i/size;
            int col = i%size;
            if (this.matrix[row][col] == 0){
                Collections.shuffle(numberList);
                //check if value hasn't been used yet in row
                for(int value = 1; value <= numberList.size(); value++){
                    if(inRow(value, row) == false){
                        //check if value hasn't been used in column yet
                        if(inCol(value, col) == false){
                            //check if value has been used in the square yet
                            if(inSquare(value, row, col) == false){
                                this.matrix[row][col] = value;
                                if(isGridFull() == true){
                                    counter++;
                                    break;
                                }
                                else if(solveGrid() == true){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }



    public int[][] getMatrix() {
        return this.matrix;
    }
}
