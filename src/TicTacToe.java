import java.util.Scanner;
public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];
    private static Scanner input = new Scanner(System.in);
    private static boolean endGame;
    private static boolean playAgain;
    public static void main(String[] args) {
        //X always starts the game
        String player = "X";
        //Game over is false, allow the game to continue
        endGame = false;
        //Clear the array as this will always be a fresh game
        clearBoard();
        while (!endGame) {
            //Show the board/array
            display();
            //Initialize variables for move coordinates
            int[] move = getPlayerMove(player);
            int row = move[0];
            int col = move[1];
            //Call the validMove method to check if the space is empty or within bounds
            if (isValidMove(row, col)) {
                //If the move is valid, put the "player" AKA their symbol in the array locaiton
                board[row][col] = player;

                if (isWin(player) || isTie()) {
                    display();
                    //If there is a win or tie announce it and prompt to play again
                    System.out.println(isWin(player) ? player + " wins!" : "It's a tie!");
                    //Use safe input to prompt play again question
                    playAgain = SafeInput.getYNConfirm(input, "Play again? (Y/N)");
                    //If player said no to another game, end game/program
                    if (!playAgain) {
                        endGame = true;
                    } else {
                        //Player said yes to another game, clear board and start again.
                        clearBoard();
                    }
                } else {
                    //If there are more moves to be made, switch player
                    player = player.equals("X") ? "O" : "X";
                }
            } else {
                //Invalid move, let the player know and continue
                System.out.println("Invalid move, please try again.");
            }
        }
    }

    //Set all board elements to a space
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    // Show the board to the player
    private static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + (j < COL - 1 ? " | " : ""));
            }
            System.out.println(i < ROW - 1 ? "\n---------" : "");
        }
    }
    //get coordinates from the move which should be 1-3 for each row and col. Set player name using the switch from above
    private static int[] getPlayerMove(String player) {
        //Use SafeInput to make sure input is within bounds, in this case 1-3.
        int rowInput = SafeInput.getRangedInt(input, "Player " + player + ", enter the row number (1-3): ", 1, 3);
        int colInput = SafeInput.getRangedInt(input, "Player " + player + ", enter the column number (1-3): ", 1, 3);
        //Subtract 1 from player input since arrays start at 0.
        int row = rowInput - 1;
        int col = colInput - 1;
        //Return coordinates
        return new int[]{row, col};
    }
    //Return true if there is an open space at the attempted move position
    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" "));
    }
    //Check for a win by using all the different win methods
    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }
    //Check for win in column by checking if all strings in a column match the player
    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (player.equals(board[0][i]) && player.equals(board[1][i]) && player.equals(board[2][i])) {
                return true;
            }
        }
        return false;
    }
    //Check for win in row (all strings in a number/row match)
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (player.equals(board[i][0]) && player.equals(board[i][1]) && player.equals(board[i][2])) {
                return true;
            }
        }
        return false;
    }
    //Diagonal win, using specific places in the array instead of whole rows or columns
    private static boolean isDiagonalWin(String player) {
        return (player.equals(board[0][0]) && player.equals(board[1][1]) && player.equals(board[2][2])) ||
                (player.equals(board[0][2]) && player.equals(board[1][1]) && player.equals(board[2][0]));
    }

    //If there are no more spaces in the board and another win condition hasn't been met, return tie.
    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}