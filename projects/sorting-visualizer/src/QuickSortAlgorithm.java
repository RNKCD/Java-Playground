import java.util.Arrays;
public class QuickSortAlgorithm {
    public static void main(String[] args) {
        int[] arr = {1,4,3,6,7,8,5,2,4,7,};
        System.out.println(Arrays.toString(arr));
        
        
        quicksort(arr, 0 , arr.length -1);
        System.out.println(Arrays.toString(arr));
        
                
    }
        
    private static void quicksort(int[] arr,int low, int high) {
        //stops recursion if only one element to sort
        if(low >= high){
            return;
        }

        //find the index of the sorted pivotpoint(first value)
        int pivotpoint = partition(arr, low, high);
        
        //creating array of value small and big than pivotvalue
        quicksort(arr, low, pivotpoint-1);
        quicksort(arr, pivotpoint + 1, high);
                
    }

    private static int partition(int[] arr, int low, int high){
        
        //pivotpoint is first index
        int pivotpoint = low;
    
        int j = low;

        for( int i = low +1 ; i<= high; i++){
            //loop runs till i find value less than pivotpoint and j takes a step
            if(arr[i] < arr[pivotpoint]){
                j++;
                
                //Swapping the element of i and j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        //after the loop; the value of j should be the place of pivotpoint
        //swapping pivotpoint and j
        int temp = arr[j];
        arr[j] = arr[pivotpoint];
        arr[pivotpoint] = temp;

        //j currently is the index of swapped  pivotvalue
        return j;
    }
}
