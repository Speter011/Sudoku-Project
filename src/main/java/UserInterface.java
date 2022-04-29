import java.util.Scanner;

/**
 * Author: Stefan Peter
 * Class for setting up the interface and using inputs in menus
 */
public class UserInterface {
    /**
     * Creates a user interface where we can choose different game options, and exit the program
     * @param matrix current grid
     * @param input user input variable
     * @return returns 1 if we go start a game
     *         returns 0 if we don't
     *         also has an option to exit the game
     */
    public int InterfaceCreation(int [][] matrix, Scanner input){

        String option = "";
        boolean valid = true;
        while(!option.equals("2")){

            System.out.println("[1] New Game \n[2] Exit");
            option = (input.next());

            if(option.equals("1")){
                System.out.println("Choose difficulty: \n[1] Easy \n[2] Medium \n[3] Hard \n[4] Test");
                //more than 36 clues, 27-36 clues, 19-26 clues on a 9x9 grid
                do {
                    option = (input.next());
                    if(option.equals("1")) {
                        GridGenerator.removeNumbers(matrix, 81 - 36); //36 az eredeti
                        return 1;
                    }
                    if(option.equals("2")) {
                        GridGenerator.removeNumbers(matrix, 81 - 27);
                        return 1;
                    }
                    if(option.equals("3")) {
                        GridGenerator.removeNumbers(matrix, 81 - 19);
                        return 1;
                    }
                    if(option.equals("4")){
                        GridGenerator.removeNumbers(matrix, 81 - 78); //testing the code with low number of holes
                        return 1;
                    }
                    else {
                        System.out.println("Invalid input! \n(input should be 1, 2, 3, or 4)");
                        valid = false;
                    }

                } while(!valid);

            }
            else if(option.equals("2")) {
                System.exit(0);
            }
            else {
                System.out.println("Invalid input! \n(Input should be 1, or 2)");
            }
        }
        return 0;
    }
}
