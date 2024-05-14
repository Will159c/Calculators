
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class MatrixMethods {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MULTIPLICATION

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("Matrix multiplication is not possible");
        }

        double[][] result = new double[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MULTIPLICATION

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Add

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double[][] addMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            // Matrices must have the same dimensions for addition
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }

        int rows = matrix1.length;
        int columns = matrix1[0].length;

        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Add

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // subtract

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double[][] subtractMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            // Matrices must have the same dimensions for subtraction
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }

        int rows = matrix1.length;
        int columns = matrix1[0].length;

        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // subtract

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RREF

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double[][] computeRREF(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        double[][] rrefMatrix = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            rrefMatrix[i] = Arrays.copyOf(matrix[i], numCols);
        }

        int lead = 0;
        for (int row = 0; row < numRows; row++) {
            if (lead >= numCols)
                break;
            int i = row;
            while (rrefMatrix[i][lead] == 0) {
                i++;
                if (i == numRows) {
                    i = row;
                    lead++;
                    if (numCols == lead)
                        return rrefMatrix;
                }
            }
            double[] temp = rrefMatrix[row];
            rrefMatrix[row] = rrefMatrix[i];
            rrefMatrix[i] = temp;

            double lv = rrefMatrix[row][lead];
            for (int j = 0; j < numCols; j++) {
                rrefMatrix[row][j] /= lv;
            }
            System.out.println("Step " + (row + 1) + ": Divide row " + (row + 1) + " by " + lv);

            for (int j = 0; j < numRows; j++) {
                if (j != row) {
                    double lv2 = rrefMatrix[j][lead];
                    for (int k = 0; k < numCols; k++) {
                        rrefMatrix[j][k] -= lv2 * rrefMatrix[row][k];
                    }
                    System.out.println("Step " + (row + 1) + ": Subtract " + lv2 + " times row " + (row + 1) + " from row " + (j + 1));
                }
            }
            lead++;
        }
        return rrefMatrix;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RREF

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}