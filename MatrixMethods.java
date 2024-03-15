import java.util.*;

public class MatrixMethods {
    int row;
    int column;
    double[][] theMatrix;
    Scanner input = new Scanner(System.in);

    public MatrixMethods() {

        
        // inputs
        System.out.println("How many rows:");
        this.row = input.nextInt();
        System.out.println("How many columns:");
        this.column = input.nextInt();

        this.theMatrix = new double[row][column];

    }

    public void WriteMatrixValues() {

        int dataEntered = 1;
        for (int i = 0; i <= row - 1; i++) {
            for (int j = 0; j <= column - 1; j++) {
                System.out.println("Enter value for row, column: " + (i + 1) + ", " + (j + 1));
                dataEntered = input.nextInt();
                theMatrix[i][j] = dataEntered;
            }
        }

    }

    public void CheckForZeros() {
        double[] tempRow = new double[row];
        for (int i = 0; i < theMatrix.length; i++) {
            for (int j = 0; j < theMatrix[0].length; j++) {
                if (theMatrix[i][j] == 0) {
                    tempRow = theMatrix[i];
                    theMatrix[i] = theMatrix[Math.min(j + 1, row - 1)];
                    theMatrix[Math.min(j + 1, row - 1)] = tempRow;
                }
            }
        }

        // for (int r = 0, c = 0; r < row - 1 && c < column - 1; r++, c++) {

        //     if (theMatrix[r][c] == 0) {

        //         for (int r1 = 0; r1 < theMatrix.length - 1; r1++) {

        //             if (theMatrix[r1][c] != 0) {

        //                 tempRow = theMatrix[r];
        //                 theMatrix[r][c] = theMatrix[r1][c];
        //                 theMatrix[r1] = tempRow;
        //                 break;
        //             }
        //         }
        //     }
        // }

    }

    public void RREF() {
        // check for zeros
       CheckForZeros();

       double divisor = 1;
       double cell;
       for (int currentRow = 0; currentRow < theMatrix.length-1; currentRow++) {
            divisor = theMatrix[currentRow][currentRow];
            for(int currentColumn = 0; currentColumn < theMatrix[0].length; currentColumn++){
                cell = theMatrix[currentRow][currentColumn] / divisor;
                theMatrix[currentRow][currentColumn] = Math.ceil(cell * 100)/100;
            }
            divisor = theMatrix[currentRow+1][currentRow+1];
            for (int currentColumn1 = 0; currentColumn1 < theMatrix.length; currentColumn1++) {
                cell = theMatrix[currentRow][currentColumn1] / divisor;
                theMatrix[currentRow + 1][currentColumn1] = Math.ceil(cell * 100)/100;
            }
            for (int currentColumn = 0; currentColumn < theMatrix.length; currentColumn++) {
                cell = theMatrix[currentRow][currentColumn] - theMatrix[currentRow + 1][currentColumn];
                theMatrix[currentRow + 1][currentColumn] = Math.ceil(cell * 100)/100;
            }

       }
        // RREF
    }

    public void printMatrix() {
        System.out.println("Your Matrix: ");
        for (int i = 0; i < theMatrix.length; i++) {
            for (int j = 0; j < theMatrix[0].length; j++) {
                System.out.print(theMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}