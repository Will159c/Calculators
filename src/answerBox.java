import javax.swing.*;
import java.awt.*;

public class answerBox {
    JTextArea[][] answerArea;
    int row;
    int column;
    double[][] matrix;

    public answerBox(int row, int column, double[][] matrix) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;

        this.answerArea = new JTextArea[row][column];
        gridLayout();
    }

    public void gridLayout() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(row, column));
        frame.setSize(700, 500);

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // Calculate the position to place the JFrame in the bottom right corner
        int x = screenWidth - frame.getWidth();
        int y = screenHeight - frame.getHeight();

        JLabel answer = new JLabel("Answer");
        frame.add(answer, BorderLayout.NORTH);

        frame.setLocation(x, y);
        frame.add(panel);


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                answerArea[i][j] = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(answerArea[i][j]);
                panel.add(scrollPane);

                answerArea[i][j].setText(String.valueOf(matrix[i][j]));

            }
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}


