package com.pack.semicalc.Parametr_convertions;




public class ParamConvertions {
    public static double[][] HtoY(double[][] h)
    {
        double[][] y = new double[2][2];
        double detH = h[0][0]*h[1][1]-h[0][1]*h[1][0];
        y[0][0] = 1/h[0][0];
        y[0][1] = -h[0][1]/h[0][0];
        y[1][0] = h[1][0]/h[0][0];
        y[1][1] = detH/h[0][0];
        return y;
    }

    public static double[][] YtoH(double[][] y)
    {
        double[][] h = new double[2][2];
        double detY = y[0][0] * y[1][1] - y[0][1] * y[1][0];
        h[0][0] = 1 / y[0][0];
        h[0][1] = -y[0][1] / y[0][0];
        h[1][0] = y[1][0] / y[0][0];
        h[1][1] = detY / y[0][0];
        return h;
    }

}
