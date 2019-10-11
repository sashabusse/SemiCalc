package com.pack.semicalc.main_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.pack.semicalc.R;


public class CirParametersFragment extends Fragment {

    private EditText[][] H;
    private EditText RGen,RLoad;
    private TextView KI,KU,KP,Rin,Rout;
    private Button RecalcBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.cir_parameters_layout, container, false);

        H = new EditText[2][2];

        H[0][0] = root.findViewById(R.id.H_CIR11);
        H[0][1] = root.findViewById(R.id.H_CIR12);
        H[1][0] = root.findViewById(R.id.H_CIR21);
        H[1][1] = root.findViewById(R.id.H_CIR22);

        RGen = root.findViewById(R.id.RGEN);
        RLoad = root.findViewById(R.id.RLOAD);

        KI = root.findViewById(R.id.KI_TV);
        KU = root.findViewById(R.id.KU_TV);
        KP = root.findViewById(R.id.KP_TV);
        Rin = root.findViewById(R.id.RIN_TV);
        Rout = root.findViewById(R.id.ROUT_TV);

        RecalcBtn = root.findViewById(R.id.Cir_RecalcBtn);

        RecalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecalcBtnOnClick(v);
            }
        });

        return root;
    }

    private void RecalcBtnOnClick(View v)
    {
        double[][] h = new double[2][2];

        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                h[i][j] = Double.valueOf(H[i][j].getText().toString());
            }
        }

        double r_gen = Double.valueOf(RGen.getText().toString());
        double r_load = Double.valueOf(RLoad.getText().toString());

        double detH = h[0][0]*h[1][1]-h[0][1]*h[1][0];

        double ki = h[1][0]/(1+h[1][1]*r_load);
        double ku = -h[1][0]*r_load/(h[0][0]+r_load*detH);
        double kp = ki*ku;

        double r_in = (h[0][0]+r_load*detH)/(1+h[1][1]*r_load);
        double r_out = (h[0][0]+r_gen)/(detH+h[1][1]*r_gen);

        KI.setText("KI = " + String.format("%.2e",ki));
        KU.setText("KU = " + String.format("%.2e",ku));
        KP.setText("KP = " + String.format("%.2e",kp));
        Rin.setText("Rin = " + String.format("%.2e",r_in));
        Rout.setText("Rout = " + String.format("%.2e",r_out));
    }

}
