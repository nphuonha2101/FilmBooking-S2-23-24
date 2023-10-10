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
            System.arraycopy(cols, 0, result[i], 0, cols.length);
        }
        return result;
    }
private static void printArr(String[][] arr){
    for (String[] strings : arr) {
        System.out.println(Arrays.toString(strings));
        System.out.println();
    }

}
    public static void main(String[] args) {
        String data = "01010101 01010101 01010101";
        printArr(convertTo2DArr(data));
    }
}
