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

        MatrixMethods matrixMethods = new MatrixMethods();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        frame.setSize(600, 400);
        JTabbedPane NewMatrixTab = new JTabbedPane();

        JButton newMatrix = new JButton("Create Matrix");
        JButton multiply = new JButton("Multiply");

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

        OpperationsPane.add(MatrixOneB);
        OpperationsPane.add(MatrixTwoB);
        OpperationsPane.add(AdditionB);
        OpperationsPane.add(SubtractionB);
        OpperationsPane.add(RREFB);
        OpperationsPane.add(MultiplyB);

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
        final newMatrix[] one = { null };
        final newMatrix[] two = { null };
        newMatrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(rows2.getText()) > 0 && Integer.parseInt(columns2.getText()) > 0) {
                    k[0]++;
                    if (k[0] == 1) {
                        one[0] = new newMatrix(Integer.parseInt(rows2.getText()),
                                Integer.parseInt(columns2.getText()), 1);
                    } else if (k[0] == 2) {
                        two[0] = new newMatrix(Integer.parseInt(rows2.getText()),
                                Integer.parseInt(columns2.getText()), 2);
                    }
                }
            }
        });

        MultiplyB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (one[0] != null && two[0] != null) {
                    one[0].updateMatrix();
                    two[0].updateMatrix();

                    Point pointOne = one[0].getpPosition();
                    Point pointTwo = two[0].getpPosition();
                    if (MatrixOneB.isSelected() && MatrixTwoB.isSelected()) {

                        if (pointOne.x > pointTwo.x) {
                            double[][] temp = matrixMethods.multiplyMatrices(one[0].getMatrix(), two[0].getMatrix());
                            answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Multiplcation Answer");

                        }else if(pointOne.x < pointTwo.x){
                            double[][] temp = matrixMethods.multiplyMatrices(two[0].getMatrix(), one[0].getMatrix());
                            answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Multiplcation Answer");
                        }

                    } else {
                        System.out.println("Please select two matrices");
                    }
                }
            }
        });

        AdditionB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (one[0] != null && two[0] != null) {
                    one[0].updateMatrix();
                    two[0].updateMatrix();

                    if (MatrixOneB.isSelected() && MatrixTwoB.isSelected()) {
                        newMatrix tempMM;

                        Point pointOne = one[0].getpPosition();
                        Point pointTwo = two[0].getpPosition();
                        if (pointOne.x > pointTwo.x) {
                            double[][] temp = matrixMethods.addMatrices(one[0].getMatrix(), two[0].getMatrix());
                            answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Addition Answer");

                        }else if(pointOne.x < pointTwo.x){
                            double[][] temp = matrixMethods.addMatrices(two[0].getMatrix(), one[0].getMatrix());
                            answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Addition Answer");
                        }

                    } else {
                        System.out.println("Please select two matrices");
                    }
                }
            }
        });

        SubtractionB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                one[0].updateMatrix();
                two[0].updateMatrix();
                if (MatrixOneB.isSelected() && MatrixTwoB.isSelected()) {
                    newMatrix tempMM;

                    Point pointOne = one[0].getpPosition();
                    Point pointTwo = two[0].getpPosition();
                    if (pointOne.x < pointTwo.x) {
                        double[][] temp = matrixMethods.subtractMatrices(one[0].getMatrix(), two[0].getMatrix());
                        answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Subtraction Answer");
                    }else if(pointOne.x > pointTwo.x){
                        double[][] temp = matrixMethods.subtractMatrices(two[0].getMatrix(), one[0].getMatrix());
                        answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Subtraction Answer");
                    }

                } else {
                    System.out.println("Please select two matrices");
                }
            }
        });


        RREFB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                one[0].updateMatrix();
                two[0].updateMatrix();
                if (MatrixOneB.isSelected() && !MatrixTwoB.isSelected()) {
                    double[][] temp = matrixMethods.computeRREF(one[0].getMatrix());
                    answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Subtraction Answer");
                }else if(MatrixTwoB.isSelected() && !MatrixOneB.isSelected()){
                    double[][] temp = matrixMethods.computeRREF(two[0].getMatrix());
                    answerBox answer = new answerBox(temp.length, temp[0].length, temp, "Subtraction Answer");
                } else {
                    System.out.println("Please select one matrix");
                }
            }
        });
    }


}
