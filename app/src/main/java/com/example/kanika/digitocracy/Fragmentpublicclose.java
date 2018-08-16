package com.example.kanika.digitocracy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragmentpublicclose extends Fragment {

        TextView tv1,b1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragmentpublicclose,container,false);

        tv1=(TextView)view.findViewById(R.id.tv1);
        b1=(TextView) view.findViewById(R.id.b1);

        return view;

    }
}
