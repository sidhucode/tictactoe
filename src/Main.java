import java.util.*;
public class Main {
    private static int[][] board;
    private static int playerToken;
    private static String player1 = "X";
    private static String player0 = "O";
    private static boolean toggle;
    private static final int keyValue = 10; //Used to give  base for the board (Must be >= 4)

    public static void main(String[] args) {

        board = new int[3][3];
        setBoard();
        clear();
        printBoard();
        playerToken = 0;
        int game = -1;

        while (game == -1) {
            delay(1);
            clear();
            action();
            game = score();
            toggleDelay(1);
            if (playerToken == 0)
                playerToken += 1;
            else
                playerToken -= 1;
        }


    }
    public static void action(){
        boolean pass = false;
        while (!pass) {
            System.out.println("(Player " + playerToken + ") Choose an action:");
            System.out.println("1: View Board");
            System.out.println("2: Play/Make Move");
            System.out.println("3: Help/Hints");
            System.out.println("4: Settings\n");
            System.out.print("Enter here: ");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            clear();
            if (num == 1) {
                printBoard();
                toggleDelay(1);
                clear();
            }
            else if (num == 2) {
                play();
                pass = true;
            }
            else if (num == 3){
                help();
            }
            else {
                settings();
            }
        }
    }
    public static void setBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = keyValue;
            }
        }
    }
    public static void clear(){
        for (int i = 0; i<50; i++){
            System.out.println();
        }
    }
    public static void delay(long secondsToSleep){
        try {
            Thread.sleep(secondsToSleep * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    public static void settings (){
        boolean pass = false;
        while (!pass) {
            System.out.println("Settings: ");
            System.out.println("1: Icons ");
            System.out.println("2: Toggle ");
            System.out.println("3: Exit\n");
            System.out.print("Enter here: ");
            Scanner sc = new Scanner(System.in);
            Scanner st = new Scanner(System.in);
            int num = sc.nextInt();
            if (num == 1) {
                clear();
                System.out.println("(Player " + playerToken + ") Choose Your Icon");
                System.out.println("0: Default");
                System.out.println("Or Character of Choice:\n");
                System.out.print("Enter here: ");
                String str = st.nextLine();

                if (str.equals("0")) {
                    clear();
                }
                else {
                    if (playerToken == 0) {
                        player0 = str;
                    } else {
                        player1 = str;
                    }
                }

                delay(1);
                clear();
            } else if (num == 2) {
                clear();
                toggle = !toggle;
                if (toggle) {
                    System.out.println("Toggle [ENABLED]");
                    toggleDelay(1);
                    System.out.println("Interactions may now require you to press ENTER to proceed");
                }
                else {
                    System.out.println("Toggle [DISABLED]");
                    toggleDelay(1);
                    System.out.println("Interactions may proceed after a delay");
                }
                toggleDelay(1);
                clear();
            } else {
                clear();
                pass = true;
            }
        }
    }
    public static void printBoard(){
        for (int[] x : board) {
            for (int y : x) {
                if (y == keyValue)
                    System.out.print("░ ");
                else if (y == 1)
                    System.out.print(player1+" ");
                else
                    System.out.print(player0+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void play(){
        Scanner play = new Scanner(System.in);
        int row, column;
        boolean pass = false;
        while (!pass) {
            clear();
            System.out.print("(Player " + playerToken + ") " + "Enter a row number: ");
            row = play.nextInt();
            System.out.print("(Player " + playerToken + ") " + "Enter a column number: ");
            column = play.nextInt();
            if (column > 2 || row > 2) {
                System.out.print("\nThat spot is invalid! ");
                help();
            } else if (board[row][column] == keyValue) {
                board[row][column] = playerToken;
                pass = true;
            } else {
                System.out.println("This spot is already taken!");
                delay(1);
            }
        }
        clear();
        printBoard();
    }
    public static void help(){
        System.out.println("Hint: Use indexes 0-2 from left to right and top to bottom");
        System.out.println("Here's a Guide:");
        System.out.println("(0,0)(0,1)(0,2)");
        System.out.println("(1,0)(1,1)(1,2)");
        System.out.println("(2,0)(2,1)(2,2)");
        toggleDelay(2);
        clear();
    }
    public static int score() {
        int sum1 = 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                sum1 += board[i][j];
            }
            if (sum1 == 0) {
                System.out.println("Player 0 Wins!");
                return 0;
            }
            else if (sum1 == 3) {
                System.out.println("Player 1 Wins!");
                return 1;
            }
            else {
                sum1 = 0;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum1 += board[i][j];
            }
            if (sum1 == 0) {
                System.out.println("Player 0 Wins!");
                return 0;
            }
            else if (sum1 == 3) {
                System.out.println("Player 1 Wins!");
                return 1;
            }
            else {
                sum1 = 0;
            }
        }
        if (board[0][0] + board[1][1] + board[2][2] == 0) {
            System.out.println("Player 0 Wins!");
            return 0;
        }
        else if (board[0][0] + board[1][1] + board[2][2] == 3) {
            System.out.println("Player 1 Wins!");
            return 1;
        }
        else if (board[2][0] + board[1][1] + board[0][2] == 0) {
            System.out.println("Player 0 Wins!");
            return 0;
        }
        else if (board[2][0] + board[1][1] + board[0][2] == 3) {
            System.out.println("Player 1 Wins!");
            return 1;
        }
        else {
            return -1;
        }
    }
    public static void toggleDelay(long secondsToSleep){
        Scanner delay = new Scanner(System.in);
        if (toggle) {
            System.out.print("\nPress Enter to Continue: ");
            delay.nextLine();
            clear();
        }
        else {
            delay(secondsToSleep);
        }
    }
}


/*
CODE ARCHIVE:

    public static void oldPrintBoard() {
        for (int[] x : board) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

*/