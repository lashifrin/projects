package com.projects.connectfour;

import java.util.Scanner;

class ConnectFourBoard{
    int[][] board = new int[6][7];

    public ConnectFourBoard(){
        board = new int[][]{
                {0,0,0,0,0,0,0,},
                {0,0,0,0,0,0,0,},
                {0,0,0,0,0,0,0,},
                {0,0,0,0,0,0,0,},
                {0,0,0,0,0,0,0,},
                {0,0,0,0,0,0,0,},
        };
    }

    public boolean isCellAvailable(int column){
        return board[0][column]==0;
    }

    public boolean placeMove(int column, int player){
        if(!isCellAvailable(column)) {
            System.out.println("Wrong column!");
            return false;
        }
        for(int i=5;i>=0;--i){
            if(board[i][column] == 0) {
                board[i][column] = player;
                return true;
            }
        }
        return false;
    }

    public void printTheBoard(){
        System.out.println();
        for(int i=0;i<=5;++i){
            for(int j=0;j<=6;++j){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

public class ConnectFour
{

    public static boolean isPlayerOne = true;

    public static void main(String [] args)
    {
        ConnectFourBoard board = new ConnectFourBoard();
        board.printTheBoard();
        ConnectFour game = new ConnectFour();
        game.startTheGame(board);
    }

    public void startTheGame(ConnectFourBoard board)
    {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a number between 1 - 7 player " + (isPlayerOne == true ? "1" : "2"));
            String input = sc.next();
            int intInputValue = 0;
            try {
                intInputValue = Integer.parseInt(input) - 1;
                if(intInputValue > 6 || intInputValue < 0)
                    continue;

                if(!board.placeMove(intInputValue, isPlayerOne == true ? 1 : 2))
                {
                    System.out.println("Wrong move!");
                    continue;
                }
                board.printTheBoard();
                int result = checkBoardResult(board);
                if(result == 1)
                {
                    System.out.println("Winner is 1");
                    break;
                }
                else if(result == 2)
                {
                    System.out.println("Winner is 2");
                    break;
                }
                else if(result == 0)
                {
                    System.out.println("It is a draw!");
                    break;
                }

                isPlayerOne = isPlayerOne == true ? false : true;
                continue;
            } catch (NumberFormatException ne) {
                System.out.println("Input is not a number, continue.");
            }
        }

    }

    public int checkBoardResult(ConnectFourBoard b)
    {
        int playerOne = 0, playerTwo = 0;
        for (int i = 5; i >= 0; --i)
        {
            for (int j = 0; j <= 6; ++j)
            {
                if (b.board[i][j] == 0)
                    continue;
                /*
                check to the right of the board
                 */
                if (j <= 3)
                {
                    for (int k = 0; k < 4; ++k)
                    {
                        if (b.board[i][j + k] == 1)
                            playerOne++;
                        else if (b.board[i][j + k] == 2)
                            playerTwo++;
                        else
                            break;
                    }
                    if (playerOne == 4)
                        return 1;
                    else if (playerTwo == 4)
                        return 2;

                    playerOne = 0;
                    playerTwo = 0;
                }

                /*
                Check higher cells
                 */
                if (i >= 3)
                {
                    for (int k = 0; k < 4; ++k)
                    {
                        if (b.board[i - k][j] == 1)
                            playerOne++;
                        else if (b.board[i - k][j] == 2)
                            playerTwo++;
                        else
                            break;
                    }
                    if (playerOne == 4)
                        return 1;
                    else if (playerTwo == 4)
                        return 2;
                    playerOne = 0;
                    playerTwo = 0;
                }
                /*
                Will check diagonals
                going [SE]-[NW] \\\
                 */
                //Checking diagonal up-left
                if (j >= 3 && i >= 3)
                {
                    for (int k = 0; k < 4; ++k)
                    {
                        if (b.board[i - k][j - k] == 1)
                            playerOne++;
                        else if (b.board[i - k][j - k] == 2)
                            playerTwo++;
                        else
                            break;
                    }
                    if (playerOne == 4)
                        return 1;
                    else if (playerTwo == 4)
                        return 2;
                    playerOne = 0;
                    playerTwo = 0;
                }
                /*
                Will check diagonals
                going [SW]-[NE] ///
                */
                if (j <= 3 && i >= 3)
                {
                    for (int k = 0; k < 4; ++k)
                    {
                        if (b.board[i - k][j + k] == 1)
                            playerOne++;
                        else if (b.board[i - k][j + k] == 2)
                            playerTwo++;
                        else
                            break;
                    }
                    if (playerOne == 4)
                        return 1;
                    else if (playerTwo == 4)
                        return 2;
                    playerOne = 0;
                    playerTwo = 0;
                }

            }
        }

        for(int col=0;col<7;++col){
            if(b.board[0][col]==0)
                return -1;
        }

        return 0;
    }

}
