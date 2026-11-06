import java.util.Arrays;
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = {5,5,7,3,2,8,2,4,2, 0};
		System.out.println(Arrays.toString(arr));
			
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));
	
		}
  
	static void selectionSort(int[] arr){
		int n = arr.length;
		for(int i = 0 ; i < n ; i++){
			int small, small_index;
			small = arr[i];
			small_index = i;
			
			//Compare the value with other remaining element to determine which one is smaller
			for(int j = i +1 ; j < n ; j++){
				//will swap value if smaller value is found
				if(small > arr[j]){
					small = arr[j];
					small_index = j;
				}
					
			}
			//no change if the selected value is smallest
			if(small_index != i){
				int temp = arr[i];
				arr[i] = arr[small_index];
				arr[small_index] = temp;
			}
		}
			

		}
		
	}
		




