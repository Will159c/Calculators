//puclic class that houses the main opperations

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HubFrame {
    public static void HUB() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        frame.setSize(600, 400);
        JTabbedPane NewMatrixTab = new JTabbedPane();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////// Everything I added or changed for actual
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// visuals//////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JButton newMatrix = new JButton("Create Matrix");
        JButton multiply = new JButton("Multiply");

        // panel.add(multiply, BorderLayout.NORTH);
        panel.add(newMatrix);

        GridLayout gl = new GridLayout(5, 1); // 4 rows, 2 columns
        panel.setLayout(gl);

        JLabel rows = new JLabel("Enter Rows: ");
        rows.setHorizontalTextPosition(JLabel.CENTER);
        rows.setVerticalTextPosition(JLabel.CENTER);
        JTextArea rows2 = new JTextArea();
        panel.add(rows);
        panel.add(rows2);
        JLabel columns = new JLabel("Enter Columns: ");
        JTextArea columns2 = new JTextArea();
        panel.add(columns);
        panel.add(columns2);

        /*
         * Oppertion panel
         */
        JPanel OpperationsPane = new JPanel(new GridLayout(4, 2));

        JCheckBox MatrixOneB = new JCheckBox("Matrix One");
        JCheckBox MatrixTwoB = new JCheckBox("Matrix Two");
        JButton AdditionB = new JButton("Add");
        JButton MultiplyB = new JButton("Multiply");
        JButton SubtractionB = new JButton("Subtract");
        JButton RREFB = new JButton("RREF");
        JButton DeterminantB = new JButton("Det()");
        JButton InverseB = new JButton("Inverse Of");

        OpperationsPane.add(MatrixOneB);
        OpperationsPane.add(MatrixTwoB);
        OpperationsPane.add(AdditionB);
        OpperationsPane.add(SubtractionB);
        OpperationsPane.add(RREFB);
        OpperationsPane.add(DeterminantB);
        OpperationsPane.add(MultiplyB);
        OpperationsPane.add(InverseB);

        // adding tabs to the hub
        NewMatrixTab.add(panel, "New Matrix");
        NewMatrixTab.add(OpperationsPane, "Opperations");
        frame.getContentPane().add(NewMatrixTab);

        // frame.add(panel);

        // end
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Action Listeners

        final int[] k = { 0 };
        final MatrixMethods[] one = { null };
        final MatrixMethods[] two = { null };
        newMatrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                k[0]++;

                if (k[0] == 1) {
                    one[0] = new MatrixMethods(Integer.parseInt(rows2.getText()),
                            Integer.parseInt(columns2.getText()));
                } else if (k[0] == 2) {
                    two[0] = new MatrixMethods(Integer.parseInt(rows2.getText()),
                            Integer.parseInt(columns2.getText()));
                } else {
                    return;
                }
            }
        });

        MultiplyB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (one[0] != null && two[0] != null) {
                    one[0].updateMatrix();
                    two[0].updateMatrix();

                    double[][] temp = multiplyMatrices(one[0].getMatrix(), two[0].getMatrix());
                    answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Multiplcation Answer");

                }
            }
        });
        RREFB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                one[0].updateMatrix();
                one[0].printMatrix();
                double[][] temp;
                MatrixMethods tempMM = new MatrixMethods(one[0].row, one[0].column);
                System.out.println("listener");

                if (MatrixOneB.isSelected() && !MatrixTwoB.isSelected()) {

                    tempMM.rref();
                    temp = tempMM.A;
                    printMatrix(one[0].A);
                    answerBox answer = new answerBox(temp.length, temp[0].length, temp, "RREF Answer");

                }
            }
        });
        SubtractionB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                one[0].updateMatrix();
                two[0].updateMatrix();
                if (MatrixOneB.isSelected() && MatrixTwoB.isSelected()) {
                    MatrixMethods tempMM;

                    Point pointOne = one[0].getpPosition();
                    Point pointTwo = two[0].getpPosition();
                    if (pointOne.x > pointTwo.x) {
                        tempMM = new MatrixMethods(one[0].row, one[0].column);
                        tempMM.subtract(two[0].A);
                        printMatrix(tempMM.A);
                        answerBox answer = new answerBox(tempMM.A.length, tempMM.A[0].length, tempMM.A, "RREF Answer");
                    }else if(pointOne.x < pointTwo.x){
                        tempMM = new MatrixMethods(two[0].row, two[0].column);
                        tempMM.subtract(one[0].A);
                        printMatrix(tempMM.A);
                        answerBox answer = new answerBox(tempMM.A.length, tempMM.A[0].length, tempMM.A, "RREF Answer");
                    }

                } else {
                    System.out.println("Please select two matrices");
                }
            }
        });

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 5/2/2024 MULTIPLICATION

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
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

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 5/2/2024 MULTIPLICATION

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
