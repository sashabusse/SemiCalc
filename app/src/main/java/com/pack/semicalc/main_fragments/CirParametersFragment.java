package com.pack.semicalc.main_fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pack.semicalc.R;

public class CirParametersFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d("tag","cir_parameters_fragment");
        return inflater.inflate(R.layout.cir_parameters_layout, container, false);
    }
}
