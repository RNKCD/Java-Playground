import java.util.Arrays;
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5,5,7,3,2,8,2,4,2};
        System.out.println(Arrays.toString(arr));
            
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    
    }
  
    static void bubbleSort(int[] arr){
        int n = arr.length;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                    if((j!= n-1) && (arr[j] >arr[j+1])){
                        int temp = arr[j+1];
                        arr[j+1] = arr[j];
                        arr[j] = temp;
                    
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        
    }
        
}



