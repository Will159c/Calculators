import java.util.*;
public class MatrixMethods{
    int row;
    int column;
    int[][] theMatrix;

    public MatrixMethods(){

        Scanner input = new Scanner(System.in);
        //inputs
        System.out.println("How many rows:");
        this.row = input.nextInt();
        System.out.println("How many columns:");
        this.column = input.nextInt();

        this.theMatrix = new int[row][column];

    }

    public void WriteMatrixValues() {
        Scanner in = new Scanner(System.in);
        int dataEntered = 1;
        for (int i = 0; i <= row - 1; i++) {
            for (int j = 0; j <= column - 1; j++) {
                System.out.println("Enter value for row, column: " + (i+1) + ", " + (j+1));
                dataEntered = in.nextInt();
                theMatrix[i][j] = dataEntered;
            }
        }

    }

    public void CheckForZeros(){
        int[] tempRow = new int[row];
        for (int i = 0; i < theMatrix.length; i++) {
            for (int j = 0; j < theMatrix[0].length; j++) {
                if(theMatrix[i][j] == 0){
                    tempRow = theMatrix[i];
                    theMatrix[i] = theMatrix[Math.min(j+1, row-1)];
                    theMatrix[Math.min(j+1, row-1)] = tempRow;
                }
            }
        }
        //currently not working
        //make sure there are no zeros on the diagonal
        // for (int i = 0; i < row - 1; i++) {
        //     for (int j = 0; j < column - 1; j++) {
        //         if (i == j && theMatrix[i][j]==0) {
        //             for (int[] element : theMatrix) {
        //                 if(element[i] != 0){
        //                     tempRow = theMatrix[i];
        //                     theMatrix[i] = element;
        //                     element = tempRow;
        //                 }
        //             }
        //         }
        //     }
        // }

        }
    
    public void RREF(){
        //check for zeros

        //RREF
    }

    public void printMatrix(){
        System.out.println("Your Matrix: ");
        for (int i = 0; i < theMatrix.length; i++) {
            for (int j = 0; j < theMatrix[0].length; j++) {
                 System.out.print(theMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}