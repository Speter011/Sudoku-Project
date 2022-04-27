import java.util.Scanner;

public class UserInterface {

    public void InterfaceCreation(int [][] matrix, Scanner input){

        String option = "";
        while(!option.equals("2")){

            System.out.println("1. New Game \n2. Exit");
            option = (input.next());

            if(option.equals("1")){
                System.out.println("Choose difficulty: \n1. Easy \n2. Medium \n3. Hard");
                //more than 36 clues, 27-36 clues, 19-26 clues on a 9x9 grid

                option = (input.next());
                if(option.equals("1")) {
                    GridGenerator.removeNumbers(matrix, 81 - 36);
                    return;
                }
                if(option.equals("2")) {
                    GridGenerator.removeNumbers(matrix, 81 - 27);
                    return;
                }
                if(option.equals("3")) {
                    GridGenerator.removeNumbers(matrix, 81 - 19);
                    return;
                }
                else {
                    System.out.println("Invalid input! \n(input should be 1, 2, or 3)");
                }
            }
            else if(option.equals("3")) {
                System.exit(0);
            }
            else {
                System.out.println("Invalid input! \n(input should be 1, or 2)");
            }
        }
    }
}
