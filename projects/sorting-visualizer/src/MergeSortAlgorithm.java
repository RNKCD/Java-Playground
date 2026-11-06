import java.util.Arrays;
public class MergeSortAlgorithm {
    public static void main(String[] args) {
        int[] arr = {1,4,3,6,7,8,5,2,4,7,};
        System.out.println(Arrays.toString(arr));
        
        
        mergesort(arr);
        System.out.println(Arrays.toString(arr));
        
                
    }
        
    private static void mergesort( int[] arr) {
        int n = arr.length;
        //return if there is 1 element
        if(arr.length <= 1){
            return;
        }

        int mid = n/2;

        //creating a new left and right array that will re-loop
        int[] left_arr = Arrays.copyOfRange(arr, 0, mid);
        int[] right_arr = Arrays.copyOfRange(arr, mid, n);


        //splitting the array to even smaller pieces
        mergesort(left_arr);
        mergesort(right_arr);
        merge(arr, left_arr, right_arr);
        
    }

    private static void merge(int[] arr, int[] left_arr, int[] right_arr){
        int i=0, j=0, k =0;
        //will compare the values of left and right array till one array is empty
        //then the remaining number in array is already sorted 
        //we can just add it onto the big array
        while(i < left_arr.length && j < right_arr.length){
            if(left_arr[i] < right_arr[j]){
                arr[k] = left_arr[i];
                i++;
                k++;
            }
            else{
                arr[k] = right_arr[j];
                j++;
                k++;
            }
        }

        //will fill the remaining spot of arr if sorted left has more element to be set
        while(i < left_arr.length){
            arr[k] = left_arr[i];
                i++;
                k++;
        }

        //will fill the remaining spot of arr if sorted right has more element to be set
        while(j < right_arr.length){
            arr[k] = right_arr[j];
                j++;
                k++;
        }
        
    }
}