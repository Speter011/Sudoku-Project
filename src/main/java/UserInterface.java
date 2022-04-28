import java.util.Scanner;

public class UserInterface {

    public int InterfaceCreation(int [][] matrix, Scanner input){

        String option = "";
        boolean valid = true;
        while(!option.equals("2")){

            System.out.println("[1] New Game \n[2] Exit");
            option = (input.next());

            if(option.equals("1")){
                System.out.println("Choose difficulty: \n[1] Easy \n[2] Medium \n[3] Hard");
                //more than 36 clues, 27-36 clues, 19-26 clues on a 9x9 grid
                do {
                    option = (input.next());
                    if(option.equals("1")) {
                        GridGenerator.removeNumbers(matrix, 81 - 79);//36 az eredeti
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
                    else {
                        System.out.println("Invalid input! \n(input should be 1, 2, or 3)");
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
