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
    public static void HUB(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        frame.setSize(600, 400);


        frame.add(panel);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////Everything I added or changed for actual visuals//////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JButton newMatrix = new JButton("New Matrix");
        JButton multiply = new JButton("Multiply");

        frame.add(multiply, BorderLayout.NORTH);
        frame.add(newMatrix, BorderLayout.SOUTH);


        JPanel panel3 = new JPanel();
        GridLayout gl = new GridLayout(4,2); // 4 rows, 2 columns
        panel.setLayout(gl);

        JLabel rows = new JLabel("Rows");
        JTextArea rows2 = new JTextArea();
        panel.add(rows);
        panel.add(rows2);
        JLabel columns = new JLabel("Columns");
        JTextArea columns2 = new JTextArea();
        panel.add(columns);
        panel.add(columns2);





//end
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



//Action Listeners

        final int[] k = {0};
        final MatrixMethods[] one = {null};
        final MatrixMethods[] two = {null};
        newMatrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                k[0]++;

                if(k[0] == 1)  {
                    one[0] = new MatrixMethods(Integer.parseInt(rows2.getText()),
                            Integer.parseInt(columns2.getText()));
                } else if(k[0] == 2) {
                    two[0] = new MatrixMethods(Integer.parseInt(rows2.getText()),
                            Integer.parseInt(columns2.getText()));
                } else {
                    return;
                }
            }
        });

        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(one[0] != null && two[0] != null) {
                    one[0].updateMatrix();
                    two[0].updateMatrix();



                    double[][] temp = multiplyMatrices(one[0].getMatrix(), two[0].getMatrix());
                    answerBox answer = new answerBox(temp.length, temp.length, temp);

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
