import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class MatrixMain {

    public static void main(String[] args) {
        Matrix matrix = new Matrix("./x.txt");
        while(matrix.hasChanged()) {
            matrix.nextGeneration();
        }
        System.out.println(matrix.getSumOfX(0));
        System.out.println(matrix.getSumOfX(990));
        System.out.println(matrix.getSumOfX(991));
        System.out.println(matrix.getSumOfX(992));
        System.out.println(matrix.getSumOfX(1000));
    }
}

class Matrix {
    char[][] matrix = new char[1001][1000];
    char[][] tempMatrix = null;
    int changeCount = 0;
    Matrix(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            int row = 0;
            int length = 0;
            for(String line : lines) {
                length = line.length();
                for (int column = 0; column < 1000; column++) {
                    if (column < length) matrix[row][column] = line.charAt(column);
                    else matrix[row][column] = ' ';
                }
                row++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean nextGeneration() {
        tempMatrix = matrix.clone();
        for(int row = 0; row < 1001; row++) {
            for (int column = 0; column < 1000; column++) {
                tempMatrix[row][column] = applyRule(matrix[row][column], getTop(row,column), getBottom(row,column));
            }
        }
        matrix = tempMatrix.clone();
        return hasChanged();
    }

    public int getSumOfX(int row) {
        int result = 0;
        for (char c : matrix[row]) {
            if (c == 'X') result++;
        }
        return result;
    }

    public boolean hasChanged() {
        return changeCount > 0;
    }

    private char getTop(int row, int column) {
        if (row == 0) return ' ';
        return matrix[row-1][column];
    }

    private char getBottom(int row, int column) {
        if (row == 1000) return ' ';
        return matrix[row+1][column];
    }

    private char applyRule(char current, char top, char bottom) {
        changeCount++;
        if (current == ' ' && top== ' ' && bottom == ' ') return ' ';
        else if (current == ' ' && top == ' ' && bottom == 'X') return ' ';
        else if (current == ' ' && top == 'X' && bottom == ' ') return 'X';
        else if (current == ' ' && top == 'X' && bottom == 'X') return 'X';
        else if (current == 'X' && top == ' ' && bottom == ' ') return ' ';
        else if (current == 'X' && top == ' ' && bottom == 'X') return 'X';
        else if (current == 'X' && top == 'X' && bottom == ' ') return ' ';
        else if (current == 'X' && top == 'X' && bottom == 'X') return 'X';
        else {
            changeCount--;
            return current;
        }
    }

}