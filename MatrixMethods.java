public class MatrixMethods{


    public static void CheckForZeros(int Matrix[][]){
        for(int [] row: Matrix){
            
            for(int cell: row){
                if(cell == 3){
                    //write code to swap the zeros in the rows to fit with RRE
                    System.out.println(cell);
                }
                
            }
        }

        for (int i = 0; i < Matrix.length; i++) {
            System.out.println(Matrix[(i+3)%3][i]);
        }
    }
}