package com.example.kanika.digitocracy.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kanika.digitocracy.R;

public class FragmentPrivacyPolice extends Fragment {

    TextView privacypolice_tv,privacypolice_description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fregment_privacypolice,container,false);

        privacypolice_tv=view.findViewById(R.id.privacypolice_tv);
        privacypolice_description=view.findViewById(R.id.privacypolice_description);


        return view;
    }
}
