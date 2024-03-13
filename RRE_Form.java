import java.util.*;

public class RRE_Form{
    public static void main(String[] args) {
        System.out.println("Enter your 3x3 matrix, row by row");
        Scanner input = new Scanner(System.in);
        
        int[][] Matrix = {
            {input.nextInt(),input.nextInt(),input.nextInt()},
            {input.nextInt(),input.nextInt(),input.nextInt()},
            {input.nextInt(),input.nextInt(),input.nextInt()}
        };

        MatrixMethods.CheckForZeros(Matrix);

        input.close();

    }

}

