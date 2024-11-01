package org.ironbound.ironbound_core.util;

public class Base2Log {

    /**
     * {https://www.geeksforgeeks.org/how-to-calculate-log-base-2-of-an-integer-in-java/}
     * Function to calculate the
     * log base 2 of an integer
     */
    public static int log2(int N) {


        int result = (int) (Math.log(N) / Math.log(2));

        return result;
    }
}
