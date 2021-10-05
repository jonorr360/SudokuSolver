

public class Main {

    public static final int ROW_COL = 9; // 9X9 rows and columns to represent sudoku plot
    public static final int[][] board = { { 0, 7, 6, 9, 0, 0, 8, 5, 0 },
                                          { 0, 1, 9, 3, 8, 6, 2, 0, 0 },
                                          { 8, 4, 0, 0, 1, 0, 0, 3, 9 },
                                          { 7, 0, 4, 1, 6, 9, 0, 0, 0 },
                                          { 0, 0, 8, 0, 7, 0, 9, 6, 2 },
                                          { 0, 9, 3, 0, 0, 5, 0, 1, 7 },
                                          { 4, 3, 0, 2, 0, 0, 1, 0, 6 },
                                          { 2, 0, 0, 6, 9, 1, 0, 0, 0 },
                                          { 9, 0, 0, 0, 0, 4, 5, 2, 8 }, };// 2-d Array for the sudoku game


    public static void main(String[] args) {

        if (solveBoard(board)) {
            System.out.println("Solved successfully!");
        }
        else {
            System.out.println("Unsolvable board :(");
        }
        printBoard();
    }

    // Print Method
    public static void printBoard() {
        for (int i = 0; i < ROW_COL; i++) {
            for (int j = 0; j < ROW_COL; j++) {
                System.out.print(" | " + board[i][j]);
            }
            System.out.println(" | ");
        }
    }
    // Check for Rows
    // checks the entire row to see if any of the numbers are the same
    public static boolean checkRow(int rowIndex, int colIndex, int numAdded){
        for(int i = 0; i < ROW_COL; i++){
            if(board[rowIndex][i] == numAdded){
                return true;
            }
        }
        return false;
    }
    // Check for Columns
    // checks the entire columns to see if any of the numbers are the same
    public static boolean checkCol(int rowIndex, int colIndex, int numAdded){
        for(int i = 0; i < ROW_COL; i++){
            if(board[i][colIndex] == numAdded){
                    return true;
            }
        }
        return false;
    }
    // Find box
    // finds the parameters of the current box to check with checkBox, return the boolean answer
    public static boolean checkBox(int rowIndex, int colIndex, int numAdded) {
        int boxRow = rowIndex - (rowIndex % 3);
        int boxCol = colIndex - (colIndex % 3);
        for(int i = boxRow; i < boxRow + 3; i++){
            for(int j = boxCol; j < boxCol + 3; j++){
                if(board[i][j] == numAdded){
                        return true;
                }
            }
        }
        return false;
    }
    //Ties methods checkRow, checkCol, checkBox all together for one boolean answer
    public static boolean isNumThere(int rowIndex, int colIndex, int numAdded){
        boolean ans1 = checkRow(rowIndex, colIndex, numAdded);
        boolean ans2 = checkCol(rowIndex, colIndex, numAdded);
        boolean ans3 = checkBox(rowIndex, colIndex, numAdded);
        if((ans1 == true)||(ans2 == true)||(ans3 == true))
            return true;

        return false;
    }

    public static boolean solveBoard(int[][] board){
        for(int i = 0; i < ROW_COL; i++){
            for(int j = 0; j < ROW_COL; j++){
                if(board[i][j] == 0){
                    for(int posNum = 1; posNum < 10; posNum++){
                        if(!isNumThere(i, j, posNum)){
                            board[i][j] = posNum;

                            if(solveBoard(board)){
                                return true;
                            }
                            else
                                board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

}
