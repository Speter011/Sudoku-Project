import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int [][] grid = new int[9][9];

        UserInterface InterfaceInstance = new UserInterface();
        Game GameInstance = new Game();



        //GridGenerator.SolveSudoku(grid, 0, 0);
        //System.out.println("\n");
        //GridGenerator.gridToString(grid);
        // initialise game
        GridGenerator.fillGrid(grid);
        int flag = InterfaceInstance.InterfaceCreation(grid, input);;
        while (flag != 0) {
            GridGenerator.gridToString(grid);
            System.out.println();
            // start game
            GameInstance.MakeAMove(grid, input);
            // initialise game
            //GridGenerator.fillGrid(grid);
            flag = InterfaceInstance.InterfaceCreation(grid, input);
        }
        input.close();
    }
}