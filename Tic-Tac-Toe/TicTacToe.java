import java.util.Scanner;
public class TicTacToe {
    static char[][] board = new char[3][3];    // 3x3 game board
    static char currentPlayer = 'X';    // Current player symbol
    
    // Player names
    static String player1;
    static String player2;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("      WELCOME TO TIC TAC TOE      ");
        System.out.println("=================================");
        
        // Take player names
        System.out.print("Enter name of Player 1 (X): ");
        player1 = sc.nextLine();
        System.out.print("Enter name of Player 2 (O): ");
        player2 = sc.nextLine();

        System.out.println("\nInstructions:");
        System.out.println("1. The game is played on a 3x3 grid.");
        System.out.println("2. " + player1 + " = X, " + player2 + " = O");
        System.out.println("3. Enter your move as: row column");
        System.out.println("4. Row and Column values must be between 0 and 2.");
        System.out.println("   Example: 1 2 means row 1, column 2");
        System.out.println("=================================\n");
        boolean playAgain;

        do {
            initializeBoard();
            playGame();

            System.out.print("Do you want to play again? (yes/no): ");
            String choice = sc.next();
            playAgain = choice.equalsIgnoreCase("yes");
            sc.nextLine(); 
        } while (playAgain);

        System.out.println("Thanks for playing Tic Tac Toe!");
    }
    /**
     * Initialize the board with empty spaces
     */
    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    /**
     * Main game loop
     */
    static void playGame() {
        boolean gameEnded = false;
        int moves = 0;
        while (!gameEnded) {
            printBoard();
            String currentPlayerName = (currentPlayer == 'X') ? player1 : player2;
            System.out.println(currentPlayerName + "'s turn (" + currentPlayer + ")");
            int row, col;
            // Take valid input
            while (true) {
                System.out.print("Enter row and column (0-2): ");
                row = sc.nextInt();
                col = sc.nextInt();

                if (isValidMove(row, col)) {
                    break;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }
            // Place the move
            board[row][col] = currentPlayer;
            moves++;

            // Check for win
            if (checkWinner()) {
                printBoard();
                System.out.println("🎉 " + currentPlayerName + " wins!");
                gameEnded = true;
            }
            // Check for draw
            else if (moves == 9) {
                printBoard();
                System.out.println("😐 It's a draw!");
                gameEnded = true;
            }
            // Switch player
            else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }
    /**
     * Print the game board
     */
    static void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
    /**
     * Check if the move is valid
     */
    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (board[row][col] != ' ') {
            return false;
        }
        return true;
    }
    /**
     * Check if current player has won
     */
    static boolean checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer &&
                board[1][j] == currentPlayer &&
                board[2][j] == currentPlayer) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }
}
