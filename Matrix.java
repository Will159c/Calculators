public class Matrix {
    double[][] A;

    public Matrix (double[][] A) {
        this.A = A;
    }

    public void display() {
        System.out.println();
        for(int i = 0; i < A.length; i++){
            System.out.println();
            for(int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + ", ");
            }
        }
        System.out.println();
    }

    /*
     * Row and Column Operations
    */

    public void swapRows(int row1, int row2) {
        //Make dummy row
        double[] dummy = new double[A[0].length];

        //Copy row2 to dummy
        for(int i = 0; i < A[0].length; i++) {
            dummy[i] = A[row2][i];
        }

        //Copy row1 to row2
        for(int i = 0; i < A[0].length; i++) {
            A[row2][i] = A[row1][i] ;
        }

        //Copy dummy to row1
        for(int i = 0; i < A[0].length; i++) {
            A[row1][i] = dummy[i];
        }

    }

    public void addRows(int row1, int row2, double n) {
        //Add R1 + nR2

        //Loop through each entry
        for(int i = 0; i < A[0].length; i++) {
            A[row1][i] += A[row2][i]*n;
        }
    }

    public void scaleRow(int row, double n) {
        //Loop through every entry in row row
        for(int i = 0; i < A[0].length; i++) {
            A[row][i] *= n;
        }
    }

    public boolean isZeroRow(int row) {
        boolean isZeroRow = true;
        for(int j = 0; j < A[0].length; j++) {
            if(A[row][j] != 0) isZeroRow = false;
        }
        return isZeroRow;
    }

    public boolean isZeroColumn(int column) {
        boolean isZeroColumn = true;
        for(int i = 0; i < A.length; i++) {
            if(A[i][column] != 0) isZeroColumn = false;
        }
        return isZeroColumn;
    }
 
    public int countZeroRows() {
        int count = 0;
        for(int i = 0; i < A.length; i++) {
            if(isZeroRow(i)) count++;
        }
        return count;
    }
    /*
     * Scalars
     */

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

    public void rref() {
        organize();
        int z = countZeroRows();

        //loop through every column
        for(int i = 0; i < A.length; i++) {
            //Check if the pivot is 0.
            if(A[i][i] == 0 && !isZeroColumn(i)) {
                //Find next non-zero pivot
                for(int j = i; j < A.length; j++) {
                    if(A[j][i] != 0) {
                        swapRows(i,j);
                        break;
                    }
                }
            }

            //Normalize pivot
            if(A[i][i] != 0) {
                double divisor = 1/A[i][i];
                scaleRow(i,divisor);
                //eliminate the rest of the rowss
                for(int j = 0; j < A.length - z; j++) {
                    if(j != i) addRows(j,i,-A[j][i]);
                }
            }
        }
        //move 0 rows to the bottom.
        organize();
    }

    public void rrefWithSteps() {
        organize();
        int z = countZeroRows();

        //loop through every column
        for(int i = 0; i < A.length; i++) {
            //Check if the pivot is 0.
            if(A[i][i] == 0 && !isZeroColumn(i)) {
                //Find next non-zero pivot
                for(int j = i; j < A.length; j++) {
                    if(A[j][i] != 0) {
                        swapRows(i,j);
                        display();
                        System.out.printf("Swap R%d and R%d",i,j);
                        break;
                    }
                }
            }

            //Normalize pivot
            if(A[i][i] != 0) {
                double divisor = 1/A[i][i];
                scaleRow(i,divisor);
                display();
                System.out.printf("Scale R%d by %f", i, divisor);
                //eliminate the rest of the rowss
                for(int j = 0; j < A.length - z; j++) {
                    if(j != i) addRows(j,i,-A[j][i]);
                    display();
                    System.out.printf("Add rows R%d and %fR%d",j,-A[j][i],i);
                }
            }
        }
        
        //move 0 rows to the bottom.
        organize();
    }

    /*
     * Misc.
     */

    public void organize() {
        //Change -0 to 0
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                //round
                A[i][j] = (int)(A[i][j]*10);
                A[i][j] /= 10;
                if(A[i][j] == -0) A[i][j] = 0;
            }
        }
        
        //Move all zero rows to the bottom
        //Loop thru each row except last.
        for(int i = 0; i < A.length - 1; i++) {
            if(isZeroRow(i)) {
                //Loop thru every other row starting from the bottom, and not including itself
                for(int j = 1; j <= A.length && j != i; j++) {
                    if(!isZeroRow(A.length - j)) {
                        swapRows(A.length - j, i);
                    }
                }
            }
        }
    }
}
