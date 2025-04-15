package search;

public class BinarySearchExample {

    public static void main(String[] args) {

        //Define a sorted array - must be sorted for the binary search to work
        int[] numbers = {5, 12, 17, 23, 38, 44, 77, 84, 90};

        int searchNumber = 12;

        int resultIndex = binarySearch(numbers, searchNumber);

        if(resultIndex != -1){
            System.out.println("Element found at index : " + resultIndex);
        } else {
            System.out.println("Element not found in the array.");
        }
    }

    public static int binarySearch(int[] arr, int key){

        int low = 0;
        int high = arr.length - 1;

        while (low <= high){
            int mid = (low + high)/2;

            if(arr[mid] == key){
                return mid;
            }
            if(arr[mid] < key){
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        //If we reach here, the element was not found in array
        return -1;
    }


}
