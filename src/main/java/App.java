import java.util.Scanner;

/**
 * Author: Peter Stefan
 * Main class, runs the application
 */
public class App {
    public static void main(String[] args) {

        //get input
        Scanner input = new Scanner(System.in);

        //create a fresh grid to store the current game state
        int [][] grid = new int[9][9];

        //instances of classes
        UserInterface InterfaceInstance = new UserInterface();
        Game GameInstance = new Game();

        // initialise game
        GridGenerator.fillGrid(grid);
        //flag for exiting the game
        int flag = InterfaceInstance.InterfaceCreation(grid, input);;
        while (flag != 0) {
            GridGenerator.gridToString(grid);
            System.out.println();
            // start game
            GameInstance.MakeAMove(grid, input);
            // initialise game
            //get flag value to see if we should exit or not
            flag = InterfaceInstance.InterfaceCreation(grid, input);
        }
        input.close();
    }
}