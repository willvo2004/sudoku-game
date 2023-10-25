public class Sudoku {

    /**
     * Size of the grid.
     */
    private final int size;
    /**
     * The sudoku grid.
     */
    private final int[][] grid;

    /**
     * Constructor creates a sudoku grid by creating a shallow copy of numbers and
     * having a size that is equal to the length of numbers.
     * @param numbers square-Sudoku grid.
     */
    public Sudoku (int[][] numbers) {
        this.size = numbers.length;
        this.grid = numbers;
    }


    /**
     * Getter method to access the size of the sudoku grid.
     * @return size of sudoku grid.
     */
    public int getSize () {
        return this.size;
    }

    /**
     * Getter method to access the sudoku grid.
     * @return sudoku grid.
     */
    public int[][] getGrid () {
        return this.grid;
    }

    /**
     * Getter method to access the value at a specific row and column.
     * @param row row index of grid.
     * @param col column index of grid.
     * @return value at valid row and column, or -1 if indices are out of range.
     */
    public int getDigitAt (int row, int col) {
        try {
            return this.grid[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /**
     * Checks if a given row index abides by the rules of Sudoku. A valid row has no duplicate digits and
     * only digits between 1 and size n.
     * @param row row index of the grid.
     * @return True if the row has no duplicate digits and is only between 1 and size n. False if otherwise.
     */
    public boolean isValidRow (int row) {
        try {
            int j;
            int[] checker = new int[this.size];

            for (int i = 0; i < this.size; i++) {
                checker[i] = this.grid[row][i];
                if (i < this.size - 1) {
                    j = i + 1;
                } else {
                    return true;
                }
                if (checker[i] < 1 || checker[i] > this.size) {
                    return false;
                }

                while (this.grid[row][j] != checker[i] && j != this.size - 1) {
                    j ++;
                }
                if (this.grid[row][j] == checker[i]) {
                    return false;
                }
            }
            return true;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Checks if a given column index abides by the rules of Sudoku. A valid column has no duplicates and
     * only digits between 1 and size n.
     * @param col Column index of the grid
     * @return True if the column has no duplicates and is only between 1 and size n. False if otherwise.
     */
    public boolean isValidCol (int col) {
        try {
            int j;
            int[] checker = new int[this.size]; // okay if every grid is a square
            for (int i = 0; i < this.size; i ++) {
                checker[i] = this.grid[i][col];
                if (i < this.size - 1) {
                    j = i + 1;
                } else {
                    return true;
                }
                if (checker[i] < 1 || checker[i] > this.size) {
                    return false;
                }

                while (checker[i] != this.grid[j][col] && j != this.size - 1) {
                    j ++;
                }
                if (this.grid[j][col] == checker[i]) {
                    return false;
                }
            }
            return true;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Checks if 3x3 box in a 9x9 grid abides by the rules of Sudoku. A valid 3x3 box has no duplicates and
     * only digits between 1 and size n.
     * @param row Given row index of the grid. Index points to the top left corner of the 3x3 box.
     * @param col Given column index of the grid. Index points to the top left corner of the 3x3 box.
     * @return True if the 3x3 box has no duplicates and is only between 1 and size n. False if otherwise.
     */
    public boolean isValidBox (int row, int col) {
        try {
            if (row % 3 == 0 && col % 3 == 0) {
                int[] boxRow = new int[9]; // assuming grid is always 3x3
                int index = 0;

                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        boxRow[index++] = this.grid[row + i][col + j]; // 3x3 grid is valid nvm this is better
                    }
                }
                int j = 0;
                int[] checker = new int[boxRow.length];

                for (int i = 0; i < boxRow.length; i++) {
                    checker[i] = boxRow[i];
                    if (i < boxRow.length - 1) {
                        j = i + 1;
                    } else {
                        return true;
                    }
                    if (checker[i] < 1 || checker[i] > this.size) {
                        return false;
                    }

                    while (j != boxRow.length - 1 && checker[i] != boxRow[j]) {
                        j ++;
                    }
                    if (checker[i] == boxRow[j]) {
                        return false;
                    }
                }
            } else {
                return false;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Checks if the entire grid abides by the rules of Sudoku. Each valid row and column has no duplicates and
     * is only between 1 and size n. If the grid is 9x9, then each valid 3x3 box also has no duplicates and
     * is only between 1 and size.
     * @return True if each row, column, and potentially 3x3 box has no duplicates and is only between 1 and size n.
     * False if otherwise.
     */
    public boolean isValidSolution () {
        int rowChecker = 0;
        int colChecker = 0;
        int boxChecker = 0;

        for (int i = 0; i < this.size + 1; i++) {
            if (isValidRow(i)) {
                rowChecker ++;
            }
            if (isValidCol(i)) {
                colChecker ++;
            }
        }
        if (this.size == 9) {
            for (int i = 0; i < 10; i+= 3) {
                for (int j = 0; j < 10; j += 3) {
                    if (isValidBox(i,j)) {
                        boxChecker ++;
                    }
                }
            }
            if (boxChecker != 9 || colChecker != 9 || rowChecker != 9) {
                return false;
            }
        }
        return rowChecker == this.size && colChecker == this.size;
    }

    /**
     * Compares two Sudoku objects and checks if they are the same grid. That is, they have the same contents.
     * @param other The Sudoku object to be compared.
     * @return True if the two Sudoku objects have the same contents. False if otherwise.
     */
    public boolean equals (Sudoku other) {
        int[][] otherGrid = other.getGrid();

        if (this.size != otherGrid.length) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (this.grid[i].length != otherGrid[i].length) {
                return false;
            }
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] != otherGrid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * toString method to print out meaningful information about the Sudoku grid.
     * @return Each element spaced out and organized into a nxn grid.
     * Leading and trailing white spaces are removed.
     */
    public String toString () {
        String digits = "";
        for (int i = 0; i < this.size; i ++) {
            digits = digits + "\n";
            for (int j = 0; j < this.grid[i].length; j ++) {
                digits += this.grid[i][j] + " ";
            }
        }
        return digits.strip();
    }

}