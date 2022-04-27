import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        GridGenerator GridInstance = new GridGenerator();
        Scanner input = new Scanner(System.in);

        int [][] grid = new int[9][9];

        UserInterface InterfaceInstance = new UserInterface();
        Game GameInstance = new Game();

        GridInstance.fillGrid(grid);
        InterfaceInstance.InterfaceCreation(grid, input);
        GridInstance.gridToString(grid);
        GameInstance.MakeAMove(grid, input);
        input.close();
    }
}