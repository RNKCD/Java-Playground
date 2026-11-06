import java.util.Arrays;
public class RecursiveBinarySearch {
    static int count= 0;
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.println(Arrays.toString(arr));
        
        int low = 0;
        int high = arr.length - 1;
        System.out.println(binarySearch(low, high, 8, arr));
        System.out.println("Interation: " + count);
        
    
    }
  
    static int binarySearch(int low, int high, int target, int[] arr) {
        if (low > high) {
            return -1;
        }
        count++;
        int mid = (low + high) / 2;

        if (arr[mid] == target) {  
            return mid;  
        }

        if (target < arr[mid]) {
            return binarySearch(low, mid - 1, target, arr);  
        } else {
            return binarySearch(mid + 1, high, target, arr); 
        }
    }
}