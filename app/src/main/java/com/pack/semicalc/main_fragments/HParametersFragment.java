package com.pack.semicalc.main_fragments;
import com.pack.semicalc.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pack.semicalc.Parametr_convertions.ParamConvertions;

import androidx.fragment.app.Fragment;

public class HParametersFragment extends Fragment {

    private EditText CB11,CB12,CB21,CB22;
    private EditText CE11,CE12,CE21,CE22;
    private EditText CC11,CC12,CC21,CC22;
    private Button CBbtn,CEbtn,CCbtn;

    private double[][] Y;
    private boolean[][] toFill;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root =inflater.inflate(R.layout.h_parameters_layout, container, false);
        CB11 = root.findViewById(R.id.CB11);
        CB12 = root.findViewById(R.id.CB12);
        CB21 = root.findViewById(R.id.CB21);
        CB22 = root.findViewById(R.id.CB22);

        CE11 = root.findViewById(R.id.CE11);
        CE12 = root.findViewById(R.id.CE12);
        CE21 = root.findViewById(R.id.CE21);
        CE22 = root.findViewById(R.id.CE22);

        CC11 = root.findViewById(R.id.CC11);
        CC12 = root.findViewById(R.id.CC12);
        CC21 = root.findViewById(R.id.CC21);
        CC22 = root.findViewById(R.id.CC22);

        CBbtn = root.findViewById(R.id.CBbtn);
        CEbtn = root.findViewById(R.id.CEbtn);
        CCbtn = root.findViewById(R.id.CCbtn);

        CBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CBbtnClick(v);
            }
        });

        CEbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CEbtnClick(v);
            }
        });

        CCbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CCbtnClick(v);
            }
        });

        return root;
    }

    private void FillY()
    {
        for(int t=0;t<2;t++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    tryFill(i,j);

                }
            }
        }

    }

    private void tryFill(int i,int j)
    {
        if (toFill[i][j])
        {
            int blank = 0;
            for (int k = 0; k < 3; k++)
            {
                if (toFill[i][k])
                    blank++;
            }

            if (blank == 1)
            {
                Y[i][j] = 0;
                for (int k = 0; k < 3; k++)
                {
                    if (!toFill[i][k])
                    {
                        Y[i][j] -= Y[i][k];
                    }
                }
                toFill[i][j] = false;
                return;
            }



            blank = 0;
            for (int k = 0; k < 3; k++)
            {
                if (toFill[k][j])
                    blank++;
            }

            if (blank == 1)
            {
                Y[i][j] = 0;
                for (int k = 0; k < 3; k++)
                {
                    if (!toFill[k][j])
                    {
                        Y[i][j] -= Y[k][j];
                    }
                }
                toFill[i][j] = false;
            }
        }
    }

    private void UpdateViews()
    {
        double[][] Yb,Ye,Yc;
        Yb = new double[2][2];
        Ye = new double[2][2];
        Yc = new double[2][2];


        //Common Base
        Yb[0][0] = Y[1][1];
        Yb[0][1] = Y[1][2];
        Yb[1][0] = Y[2][1];
        Yb[1][1] = Y[2][2];

        double[][] hb = ParamConvertions.YtoH(Yb);
        CB11.setText(String.format("%.2e",hb[0][0]));
        CB12.setText(String.format("%.2e",hb[0][1]));
        CB21.setText(String.format("%.2e",hb[1][0]));
        CB22.setText(String.format("%.2e",hb[1][1]));



        //Common Emitter
        Ye[0][0] = Y[0][0];
        Ye[0][1] = Y[0][2];
        Ye[1][0] = Y[2][0];
        Ye[1][1] = Y[2][2];

        double[][] he = ParamConvertions.YtoH(Ye);
        CE11.setText(String.format("%.2e",he[0][0]));
        CE12.setText(String.format("%.2e",he[0][1]));
        CE21.setText(String.format("%.2e",he[1][0]));
        CE22.setText(String.format("%.2e",he[1][1]));


        //Common Collector
        Yc[0][0] = Y[0][0];
        Yc[0][1] = Y[0][1];
        Yc[1][0] = Y[1][0];
        Yc[1][1] = Y[1][1];

        double[][] hc = ParamConvertions.YtoH(Yc);
        CC11.setText(String.format("%.2e",hc[0][0]));
        CC12.setText(String.format("%.2e",hc[0][1]));
        CC21.setText(String.format("%.2e",hc[1][0]));
        CC22.setText(String.format("%.2e",hc[1][1]));

    }


    public void CBbtnClick(View view)
    {
        double[][] hb = new double[2][2];
        hb[0][0] = Double.valueOf(CB11.getText().toString());
        hb[0][1] = Double.valueOf(CB12.getText().toString());
        hb[1][0] = Double.valueOf(CB21.getText().toString());
        hb[1][1] = Double.valueOf(CB22.getText().toString());

        double[][] yb = ParamConvertions.HtoY(hb);

        Y = new double[3][3];
        toFill = new boolean[3][3];

        Y[1][1] = yb[0][0];
        Y[1][2] = yb[0][1];
        Y[2][1] = yb[1][0];
        Y[2][2] = yb[1][1];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                toFill[i][j] = false;
            }
        }
        for(int i=0;i<3;i++)
        {
            toFill[0][i] = true;
            toFill[i][0] = true;
        }

        FillY();

        UpdateViews();
    }

    public void CEbtnClick(View view)
    {
        double[][] he = new double[2][2];
        he[0][0] = Double.valueOf(CE11.getText().toString());
        he[0][1] = Double.valueOf(CE12.getText().toString());
        he[1][0] = Double.valueOf(CE21.getText().toString());
        he[1][1] = Double.valueOf(CE22.getText().toString());

        double[][] ye = ParamConvertions.HtoY(he);

        Y = new double[3][3];
        toFill = new boolean[3][3];

        Y[0][0] = ye[0][0];
        Y[0][2] = ye[0][1];
        Y[2][0] = ye[1][0];
        Y[2][2] = ye[1][1];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                toFill[i][j] = false;
            }
        }
        for(int i=0;i<3;i++)
        {
            toFill[1][i] = true;
            toFill[i][1] = true;
        }

        FillY();

        UpdateViews();
    }


    public void CCbtnClick(View view)
    {
        double[][] hc = new double[2][2];
        hc[0][0] = Double.valueOf(CC11.getText().toString());
        hc[0][1] = Double.valueOf(CC12.getText().toString());
        hc[1][0] = Double.valueOf(CC21.getText().toString());
        hc[1][1] = Double.valueOf(CC22.getText().toString());

        double[][] yc = ParamConvertions.HtoY(hc);

        Y = new double[3][3];
        toFill = new boolean[3][3];

        Y[0][0] = yc[0][0];
        Y[0][1] = yc[0][1];
        Y[1][0] = yc[1][0];
        Y[1][1] = yc[1][1];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                toFill[i][j] = false;
            }
        }
        for(int i=0;i<3;i++)
        {
            toFill[2][i] = true;
            toFill[i][2] = true;
        }

        FillY();

        UpdateViews();
    }

}
