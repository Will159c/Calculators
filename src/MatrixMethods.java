
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MatrixMethods {
    int row;
    int column;
    double[][] A;
    Scanner input = new Scanner(System.in);
    JTextArea[][] matrixTextArea;


    public MatrixMethods() {

        // inputs
        System.out.println("How many rows:");
        this.row = input.nextInt();
        System.out.println("How many columns:");
        this.column = input.nextInt();

        this.A = new double[row][column];
        this.matrixTextArea = new JTextArea[row][column];
        gridLayout();

    }



    public void display() {
        System.out.println("Your Matrix");
        for (int i = 0; i < A.length; i++) {
            System.out.println();
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + ", ");
            }
        }
        System.out.println();
    }

//     /*
//      * Row Operations
//      */

    public void swapRows(int row1, int row2) {
        // Make dummy row
        double[] dummy = new double[A[0].length];

        // Copy row2 to dummy
        for (int i = 0; i < A[0].length; i++) {
            dummy[i] = A[row2][i];
        }

        // Copy row1 to row2
        for (int i = 0; i < A[0].length; i++) {
            A[row2][i] = A[row1][i];
        }

        // Copy dummy to row1
        for (int i = 0; i < A[0].length; i++) {
            A[row1][i] = dummy[i];
        }

    }

    public void addRows(int row1, int row2, double n) {
        // Add R1 + nR2

        // Loop through each entry
        for (int i = 0; i < A[0].length; i++) {
            A[row1][i] += A[row2][i] * n;
        }
    }

    public void scaleRow(int row, double n) {
        // Loop through every entry in row row
        for (int i = 0; i < A[0].length; i++) {
            A[row][i] *= n;
        }
    }

    public boolean isZeroRow(int row) {
        boolean isZeroRow = true;
        for (int j = 0; j < A[0].length; j++) {
            if (A[row][j] != 0)
                isZeroRow = false;
        }
        return isZeroRow;
    }

    // Not Working
    public void organize() {
        //Change -0 to 0
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                if(A[i][j] == 0){ 
                    A[i][j] = 0;
                }
            }
        }

    // Loop thru each row except last row
    for(int i = 0;i<A.length-1;i++){

        // If it is a zero-row switch with bottom
        if (isZeroRow(i)) {
            for (int j = 0; j < A.length; j++) {
                if (!isZeroRow(A.length - 1 - j)) {
                    swapRows(i, A.length - j - 1);
                    break;
                }
            }
        }
    }
}

//     /*
//      * Scalars
//      */

    public double det() {
        //Check if it is square
        if(A.length == A[0].length) {
            double[][] B = A;
            double det = 0;

            //Trivial 1x1
            if(A.length == 1) {
                det = A[0][0];
            }

            //Trivial 2x2
            if(A.length == 2) {
                det = (A[0][0]*A[1][1]) - (A[0][1]*A[1][0]);
            }
            
            //NxN 
            if(A.length > 2) {
                for(int i = 0; i < A.length; i++) {
                    submatrix(0,i);
                    det = det + Math.pow(-1, i) * (B[0][i]) * det();
                    //Reset A:
                    A = B;
                }
            }
            return det;
        }
        else {
            System.out.println("Undefined");
            return 0;
        } 
    }

    public double trace() {
        //Check if A is square
        if(A.length == A[0].length) {
            double ans = 0;
            for(int i = 0; i < A.length; i++) {
                ans = ans + A[i][i];
            }
            return ans;
        }
        else {
            System.out.println("Undefined");
            return 0;
        }
    }

    /*
     * Matrix Operations
     */

    public void multiplyBy(double[][] B) {
        //Check if A's height == B's Width
        if(A[0].length == B.length) {
            double[][] ans = new double[A.length][B[0].length];
            for(int i = 0; i < A.length; i++) {
                for(int j = 0; j < B[0].length; j++) {
                    ans[i][j] = 0;
                    for(int k = 0; k < B.length; k++) {
                        ans[i][j] += A[i][k]*B[k][j];
                    }
                }
            }
            A = ans;
        }
        else {
            //Error Message
            System.out.println("Undefined");
        }
    }

    public void multiplyBy(double k) {
        double[][] ans = new double[A.length][A[0].length];
        
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                ans[i][j] = A[i][j] * k;
            }
        }

        A = ans;
    }

    public void add(double[][] B) {
        //Check if sizes match
        if(A.length == B.length && A[0].length == B[0].length) {
            //Define Answer Matrix
            double[][] ans = new double[A.length][A[0].length];

            //Loop through each entry
            for(int i = 0; i < A.length; i++) {
                for(int j = 0; j < A[0].length; j++) {
                    ans[i][j] = A[i][j] + B[i][j];
                }
            }

            A = ans;
        } 
        else{
            //Error Message
            System.out.println("Undefined");
        }
    }

    public void transpose() {
        double[][] ans = new double[A[0].length][A.length];

        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                ans[j][i] = A[i][j];
            }
        }

        A = ans;
    }

    public void submatrix(int row, int column) {
        //Check if it is square
        if(A.length == A[0].length) {

            //Check if size is 2x2 or more
            if(A.length > 1) {
                double[][] ans = new double[A.length-1][A[0].length - 1];

                //Assign values
                for(int i = 0, k = 0; i < A.length && k < ans.length; i++) {
                    if(i != row) {
                        for(int j = 0, l = 0; j < A.length && l < ans.length; j++) {
                            if(j != column) {
                                ans[k][l] = A[i][j];
                                l++;
                            }
                        }
                        k++;
                    } 
                }
                A = ans;
            }
        }
    }

    public void cofactor() {
        //Check if square
        if (A.length == A[0].length) {

            double[][] ans = new double[A.length][A[0].length];
    
            for(int i = 0; i < A.length; i++) {
                for(int j = 0 ; j < A.length; j++) {
                    submatrix(i, j);
                    ans[i][j] = Math.pow(-1 , i) * Math.pow(-1, j) * det();
                }
            }
                A = ans;
            }   
    }

    public void adjoint() {
        cofactor();
        transpose();
    }

    public void invert() {
        if(det() != 0) {
            adjoint();
            multiplyBy(1/(det()));
        }
        else {
            System.out.println("Undefined");
        }
    }

    public void updateMatrix(){
        for (int i = 0; i < row; i++) {
            
        for (int j = 0; j < column; j++) {
            A[i][j] = Double.parseDouble(matrixTextArea[i][j].getText());
        }
    }
        System.out.println("Updated");
    }

    public void printMatrix(){
        System.out.println("Your Matrix: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(A[i][j] + "\t");
            }
            System.out.println();
        }

    }




    public void gridLayout() {
        JFrame frame = new JFrame();
        // JTextArea textArea = null;
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


        // double[][] matrix = new double[row][column];
        // JTextArea[][] matrixTextArea = new JTextArea[row][column];


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrixTextArea[i][j] = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(matrixTextArea[i][j]);
                panel.add(scrollPane);

                matrixTextArea[i][j].setText("0.0");


                double value = Double.parseDouble(matrixTextArea[i][j].getText());
                A[i][j] = value;

              }
            }

                updateMatrix.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                            updateMatrix();

                    }
                });

                display.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        printMatrix();
                    }
                });

                RREF.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        matrixTextArea[0][0].setText("1000");
                        System.out.println("RREF YAY!");

                        for (int i = 0; i < row-1; i++) {
                            for (int j = 0; j < column-1; j++) {
                                A[i][j] = Double.parseDouble(matrixTextArea[i][j].getText());
                            }
                        }
                        swapRows(1,2);
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