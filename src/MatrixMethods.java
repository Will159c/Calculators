

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

    public void gridLayout() {
        JFrame frame = new JFrame();
        JTextArea textArea = null;
        JPanel panel = new JPanel(new GridLayout(row, column));
        frame.setSize(600, 400);
        frame.add(panel);

        JButton exit = new JButton("Exit");
        JButton RREF = new JButton("RREF");
        JButton display = new JButton("display");
        JButton updateMatrix = new JButton("Update");

        frame.add(updateMatrix, BorderLayout.WEST);
        frame.add(exit, BorderLayout.NORTH);
        frame.add(RREF, BorderLayout.SOUTH);
        frame.add(display, BorderLayout.EAST);


        double[][] matrix = new double[row][column];
        JTextArea[][] matrixTextArea = new JTextArea[row][column];


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrixTextArea[i][j] = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(matrixTextArea[i][j]);
                panel.add(scrollPane);

                matrixTextArea[i][j].setText("0.0");


                double value = Double.parseDouble(matrixTextArea[i][j].getText());
                matrix[i][j] = value;

              }
            }

                updateMatrix.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                matrix[i][j] = Double.parseDouble(matrixTextArea[i][j].getText());
                            }
                        }
                        System.out.println("Updated");
                    }
                });

                display.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                System.out.println("Index " + i + " " + j + " == " + matrix[i][j]);
                            }
                        }
                    }
                });

                RREF.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        matrixTextArea[0][0].setText("1000");
                        System.out.println("RREF YAY!");

                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                matrix[i][j] = Double.parseDouble(matrixTextArea[i][j].getText());
                            }
                        }
                    }
                });

                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Action to perform when the button is clicked
                        System.out.println("Button clicked! Closing the JFrame...");
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                });

                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            }
}