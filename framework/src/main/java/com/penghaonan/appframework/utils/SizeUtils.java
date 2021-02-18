package com.penghaonan.appframework.utils;

public class SizeUtils {
    public static int[] keepRatioFitIn(int wMax, int hMax, int wOrigin, int hOrigin) {
        float maxRatio = (float) wMax / hMax;
        float originRatio = (float) wOrigin / hOrigin;
        int[] res = new int[2];
        if (maxRatio > originRatio) {
            res[0] = (int) (originRatio * hMax);
            res[1] = hMax;
        } else {
            res[0] = wMax;
            res[1] = (int) (wMax / originRatio);
        }
        return res;
    }
}
