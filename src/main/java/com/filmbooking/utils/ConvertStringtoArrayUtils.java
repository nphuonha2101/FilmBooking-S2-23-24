package com.filmbooking.utils;

import java.util.Arrays;

public class ConvertStringtoArrayUtils {
    private static String[][] convertTo2DArr(String data){
        String[][] result = null;
        String[] rows = data.split(" ");
        result = new String[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split("");
            result[i] = new String[cols.length];
            for (int j = 0; j < cols.length; j++) {
                result[i][j]= cols[j];
            }
        }
        return result;
    }
private static void printArr(String[][] arr){
    for (int i = 0; i < arr.length; i++) {
        System.out.println(Arrays.toString(arr[i]));
        System.out.println();
    }

}
    public static void main(String[] args) {
        String data = "01010101 01010101 01010101";
        printArr(convertTo2DArr(data));
    }
}
