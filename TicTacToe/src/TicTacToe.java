/**
 * @author William Mueller
 * DE CSII
 * TicTacToeHashCode
 * 3/16/2020
 */

import java.util.Arrays;

public class TicTacToe {
   public final static int ROWS = 3;
   public final static int COLS = 3;
   public final static int POSSIBILITIES = (int) Math.pow(3,9);
   public final static int CHAR_POSSIBILITIES = 3; // x, o or space
  
   /**
    * finds the number of characters in an array of characters
    * @param b the array of characters
    * @param ch a given character
    * @return the number of ch found in b
    */
   private static int numChars(char[][] b, char ch) {
      int total = 0;
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++)
            if (ch == b[r][c]) 
               total++;
      return total;
   }
  
   /**
    * finds whether a certain board is valid
    * @param board the board to be tested
    * @return true if the board is valid, false otherwise
    */
   public static boolean valid(char[][] board) {
   
   // Ensure there are at least 3 xs and 2 os, or 3 os and 2 xs
   // Make sure there are at least one more x or one more o
      int numX = numChars(board, 'x');
      int numO = numChars(board, 'o');
      if (! (numX > 2 || numO > 2)) 
         return false;
      if ((numX == numO + 1) || (numO == numX + 1)) 
         return true;
      return false;
   }
  
   /**
    * converts a given board into a string
    * @param b the given board
    * @return the string version of that board
    */
   public static String boardToString(char[][] b) {
      String result = "";
      for (int r = 0; r < ROWS; r++) {
         for (int c = 0; c < COLS; c++) 
            result += b[r][c];
      //     result += "\n";
      }
      return result;
   }
   
   /**
    * converts a given string into a board
    * @param board the given string
    * @return the board version of that string
    */
   public static char[][] stringToBoard(String board) {
      char[][] b = new char[ROWS][COLS];
      int index = 0;
      for (int r = 0; r < ROWS; r++) {
         for (int c = 0; c < COLS; c++) 
            b[r][c] = whichLetter(board.charAt(index++));
      }
      return b;
   }

   /**
    * converts a character into a numeric value
    * @param ch the character to be converted
    * @return the assigned value for that character
    */
   public static char whichLetter(char ch) {
      switch (ch) {
         case '1' : 
            return 'x';
         case '2' : 
            return 'o';
         case '0'  : 
            return ' ';
         default: 
            return ch;
      }
   }
     
   /**
    * creates a board from a string
    * @param s the given string
    * @return a board from the given string
    */
   public static char[][] makeBoard(String s) {
      char[][] b = new char[ROWS][COLS];
      int ch = 0;
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++){         
            b[r][c] = whichLetter(s.charAt(ch));
            ch++;
         }
      return b;
   }
   
   /**
    * adds one to the current string
    * @param s the inputted string
    * @return a string with one added to the inputted string
    */
   private static String addOne(String s) {
   // s is a 9 character string, composed of 0s, 1s, and 2s.  Add 1 to the last char, adjusting
   // all the rest of the characters as necessary.
      boolean carry = false;
      char ch[] = s.toCharArray();
      ch[ch.length-1] =  (char)((int)(ch[ch.length-1])+1);
      for (int n = ch.length-1; n >=0; n--) {
         if (carry) ch[n] = (char)((int)(ch[n])+1);
         if (ch[n] == '3') {
            carry = true;
            ch[n] = '0';
         }
         else
            carry = false;      
      }
      return new String(ch);
   }
   
   /**
    * fills a board with values
    * @return a string array of values
    */
   public static String[] fillValues() {
      String strBoard = "000000000";
      String[] values = new String[POSSIBILITIES];
      int index = 0;
      values[index++] = strBoard;
      while (!strBoard.equals("222222222") ) {
         strBoard = addOne(strBoard);
         values[index++] = strBoard;
      }
      return values;
   }
   
   /**
    * checks if a board is a diagonal winner
    * @param board to be checked
    * @return true if it is a diagonal winner, false otherwise
    */
   private static boolean diagonalWin(char[][] board) {
      int wins = 0;
      if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x') || 
         (board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) 
         wins++;
      

         if ((board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x') ||
           (board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')) 
            wins++;
         
      return wins == 1;
   }
   
   /**
    * checks if the board is a row winner
    * @param board to be checked
    * @return true if it is a row winner, false otherwise
    */
   private static boolean rowWin(char[][] board) {
      char ch;
      int wins = 0;
      boolean win = true;
      for (int r = 0; r < ROWS; r++) {
         win = true;
         ch = board[r][0];
         for (int c = 0; c < COLS; c++) 
            if (ch != board[r][c]) {
               win = false;
               break;
            }
         if (win && ch != ' ')
            wins++;
      } 
      return wins == 1;
   } 
   
   /**
    * checks if the board is a column winner
    * @param board to be checked
    * @return true if it is a column winner, false otherwise
    */
   private static boolean colWin(char[][] board) {
      char ch;
      int wins = 0;
      boolean win = true;
      for (int c = 0; c < COLS; c++) {
         win = true;
         ch = board[0][c];
         for (int r = 0; r < ROWS; r++) 
            if (ch != board[r][c]) {
               win = false;
               break;
            }
         if (win && ch != ' ') 
            wins++;
      } 
        return wins == 1;
   } 
   
   /**
    * returns whether a board is a winner
    * @param b the board to be checked
    * @return true if it is a winner, false otherwise
    */
   public static boolean isWin(char[][]b) {
     return valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b));
     }


   /**
    * returns whether a given board is a winner
    * @param b the board to be checked
    * @return a string that says if the board is a winner or a loser
    */
   public static String isWinString(char[][]b) {
     boolean v = valid(b);
     boolean r = rowWin(b);
     boolean c = colWin(b);
     boolean d = diagonalWin(b);
     if (valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b))) {
       if (r) return "Row";
       if (c) return "Col";
       if (d) return "Dia";
       return "winner";
     }
     else
       return "loser";
   }
     
   /**
    * returns whether a string is a winner
    * @param s the string to be checked
    * @return true if it is a winner, false otherwise
    */
   public static boolean isWin(String s) {
      char[][] b = stringToBoard(s);
      return isWin(b);
   }

   /**
    * returns a string of whether a given string is a winner
    * @param s the string to be checked
    * @return a string that says if the inputted string is a winner or a loser
    */
   public static String isWinString(String s) {
      char[][] b = stringToBoard(s);
      return isWinString(b);
   }
     
   public static void main(String[] args) {
      String s = "ooooxxxox";
      char[][] b = stringToBoard(s);
      System.out.println("Valid:   " + valid(b));
      System.out.println("Row Win: " + rowWin(b));
      System.out.println("Col Win: " + colWin(b));
      System.out.println("Dia Win: " + diagonalWin(b));
      System.out.println(isWin(b));
       
   }    
}
