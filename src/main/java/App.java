import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        GridGenerator GridInstance = new GridGenerator();
        Scanner input = new Scanner(System.in);

        int [][] grid = new int[9][9];

        UserInterface InterfaceInstance = new UserInterface();

        GridInstance.fillGrid(grid);
        InterfaceInstance.InterfaceCreation(grid, input);
        GridInstance.gridToString(grid);
        input.close();
    }
}