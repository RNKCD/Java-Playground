public class StrassenMultiplication{
    private int count;

    //Performs Strassen Multiplication on 2 matrices
    public int[][] strassenmultiply(int[][]A, int[][]B){
        int len = A.length;

        //base case for recursion
        if( len ==1){
            int[][] c = new int[1][1];
            c[0][0] = A[0][0] * B[0][0];
            count++;
            return c;

        }

        //splitting the matrrix
        int mid = len /2;
        
        //creating sub matrices of A and B
        int[][] A11 = new int[mid][mid];
        int[][] A12 = new int[mid][mid];
        int[][] A21 = new int[mid][mid];
        int[][] A22 = new int[mid][mid];

        int[][] B11 = new int[mid][mid];
        int[][] B12 = new int[mid][mid];
        int[][] B21 = new int[mid][mid];
        int[][] B22 = new int[mid][mid];

        //Fillling the sub matrices:
        for(int i = 0; i < mid; i++){
            for(int j =0 ; j < mid ; j++){
                A11[i][j] = A[i][j];  //left top
                A12[i][j] = A[i][j + mid];   //right top
                A21[i][j] = A[i+mid] [j];   //left bottom
                A22[i][j] = A[i+mid] [j +mid]; //right bottom\

                B11[i][j] = B[i][j];  //left top
                B12[i][j] = B[i][j + mid];   //right top
                B21[i][j] = B[i+mid] [j];   //left bottom
                B22[i][j] = B[i+mid] [j +mid]; //right bottom

            }
        }

        //Calculating the 7 product
        int[][] M1 = strassenmultiply(add(A11, A22), add(B11, B22));
        int[][] M2 = strassenmultiply(add(A21, A22), B11);
        int[][] M3 = strassenmultiply(A11, subtract(B12, B22));
        int[][] M4 = strassenmultiply(A22, subtract(B21, B11));
        int[][] M5 = strassenmultiply(add(A11, A12),B22);
        int[][] M6 = strassenmultiply(subtract(A21, A11), add(B11, B12));
        int[][] M7 = strassenmultiply(subtract(A12, A22), add(B21, B22));

        //Combining the result
        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);
        
        //Combining Small Matrix C to make big C matrix
        int[][]C = new int[len][len];    
        for(int i = 0; i < mid; i++){
            for(int j =0 ; j < mid ; j++){
                C[i][j] = C11[i][j]; 
                C[i][j + mid] = C12[i][j]; 
                C[i+mid] [j] = C21[i][j];  
                C[i+mid] [j +mid] = C22[i][j];
            }
        }



        return C; 
    }

    //Helper Method
    //Adds 2 matrices
    private int[][] add(int[][] A, int[][] B){
        int len = A.length;
        int[][] C = new int[len][len];
        for(int i = 0; i< len ;i++){
            for(int j = 0; j < len ; j++){
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    //Helper Method
    //Subtracts 2 matrices
    private int[][] subtract(int[][] A, int[][] B){
        int len = A.length;
        int[][] C = new int[len][len];
        for(int i = 0; i< len ;i++){
            for(int j = 0; j < len ; j++){
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
    
    public int multiplication_count(){
        return count;
    }
    
}