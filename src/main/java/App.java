import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int [][] grid = new int[9][9];

        UserInterface InterfaceInstance = new UserInterface();
        Game GameInstance = new Game();

        GridGenerator.fillGrid(grid);
        InterfaceInstance.InterfaceCreation(grid, input);
        GridGenerator.gridToString(grid);
        System.out.println();
        //GridGenerator.SolveSudoku(grid, 0, 0);
        //System.out.println("\n");
        //GridGenerator.gridToString(grid);
        GameInstance.MakeAMove(grid, input);
        input.close();
    }
}