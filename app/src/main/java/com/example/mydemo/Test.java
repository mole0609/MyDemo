package com.example.mydemo;

public class Test {
    public static void main(String[] args) {
        int arr[] = {7, 2, 3, 4, 5, 1,6};
        maopaosort(arr);
    }

    private static void maopaosort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int num : arr){
            System.out.println(num);
        }
    }
}
