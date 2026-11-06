
import java.util.Arrays;

public class ExchangeSort {
    public static void main(String[] args) {
        int[] arr = {5,7,3,2,2,8,2,4,2};
        System.out.println(Arrays.toString(arr));
        
        exchangeSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    static void exchangeSort(int[] arr){
        int n = arr.length;

        for(int i = 0; i< n ; i++){
            for(int j = i+1; j < n; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;

                }
            }
        }

    }
}
