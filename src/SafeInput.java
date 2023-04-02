import java.util.Scanner;

public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" + prompt + ": "); // show prompt add space"
            retString = pipe.nextLine();
        } while(retString.length() == 0);
        //Return input
        return retString;

    }

    public static int getInt(Scanner pipe, String prompt) {
        //Init variables
        int returnInt = 0;
        String trash = "";
        boolean validInput = false;
        //While valid input is false, run the loop
        while (!validInput) {
            System.out.print("\n" + prompt + ": ");
            //Check if the input is a valid integer, if so end the loop and set the return integer.
            if(pipe.hasNextInt()) {
                //Valid input, return input
                validInput = true;
                returnInt = pipe.nextInt();
            } else {
                trash = pipe.next();
                System.out.println("Invalid entry: " + trash + ". Please inter a valid whole integer.");
            }
            //Clear pipe
            pipe.nextLine();
        }
        return returnInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        //Init variables
        double returnDouble = 0;
        boolean validInput = false;
        String trash = "";

        //While loop until true
        while (!validInput) {
            //Set up prompt
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                //Valid input, return input
                validInput = true;
                returnDouble = pipe.nextDouble();
            } else {
                //Invalid entry, let them know and loop
                trash = pipe.next();
                System.out.println("Invalid entry: " + trash + ". Please enter a valid number.");
            }
            pipe.nextLine();
        }
        return returnDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int input = 0;
        String trash = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                input = pipe.nextInt();
                if (input >= low && input <= high) {
                    //Valid input, return input
                    validInput = true;
                } else {
                    //Invalid input (out of range), let them know and loop
                    System.out.println("Error: Input has to be between " + low + " and " + high);
                }
            } else {
                //Invalid input, non integer
                trash = pipe.next();
                System.out.println("Error: non-integer input. You entered" + trash);
            }
            //Clear pipe
            pipe.nextLine();
        }
        //Return input
        return input;
    }


    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double input = 0;
        String trash = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.println("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                input = pipe.nextDouble();
                if (input >= low && input <= high) {
                    validInput = true;
                } else {
                    System.out.println("Error: Input has to be between " + low + " and " + high);
                }
            } else {
                trash = pipe.next();
                System.out.println("Error: non-integer input. You entered" + trash);
            }
            pipe.nextLine();
        }
        return input;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input = "";
        String trash = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.println("\n" + prompt + ": ");
            input = pipe.next();
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) {
                validInput = true;
            } else {
                trash = input;
                System.out.println("Error, enter Y or N. You entered " + trash);
            }
            pipe.nextLine(); // Clear the pipe
        }
        if (input.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }


    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        //Variables
        String input = "";
        boolean validInput = false;
        String trash = "";

        //While false, loop
        while (!validInput) {
            System.out.print(prompt + ": ");
            if (pipe.hasNext(regEx)) {
                input = pipe.next(regEx);
                validInput = true;
            } else {
                trash = pipe.next();
                System.out.println("Error: Input must match the pattern " + regEx + ".");
            }
            pipe.nextLine(); // Clear the pipe
        }
        //Return input
        return input;
    }
    public static void prettyHeader(String msg) {
        //Init variables
        int totalLength = 60;
        int msgLength = msg.length();
        //This calculates spaces by getting the message length and subtracting it from the total, dividing by 2 because 2 sides.
        int spaceLength = (totalLength - msgLength - 6) / 2;
        String middle = "";
        String starsTop = "";
        String spacesMid = "";
        String starsMid = "***";
        //starsTop prints 60 stars. Use it for bottom too.
        for (int i = 0; i < totalLength; i++) {
            starsTop += "*";
        }
        //Use the spaceLength from above to print spaces for middle
        for (int i = 0; i < spaceLength; i++) {
            spacesMid += " ";
        }
        //Set up middle line
        middle = starsMid + spacesMid + msg + spacesMid + starsMid;

        //Print lines
        System.out.println(starsTop);
        System.out.println(middle);
        System.out.println(starsTop);
    }
}
