import java.util.Random;

public class GridGenerator {


    private int[][] matrix;
    public GridGenerator(){
        this.matrix = new int[9][9];
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
    public int[][] getMatrix() {
        return this.matrix;
    }
}
