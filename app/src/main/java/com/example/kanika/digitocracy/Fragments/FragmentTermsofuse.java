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

public class FragmentTermsofuse extends Fragment {

    TextView terms_tv,terms_description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_termsofuse,container,false);


        terms_tv=view.findViewById(R.id.terms_tv);
        terms_description=view.findViewById(R.id.terns_dsecription);
        return view;
    }
}
