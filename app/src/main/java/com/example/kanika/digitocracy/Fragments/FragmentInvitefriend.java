package com.example.kanika.digitocracy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class FragmentInvitefriend extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i=new Intent(android.content.Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
        i.putExtra(android.content.Intent.EXTRA_TEXT, "extra text that you want to put");
        startActivity(Intent.createChooser(i,"Share via"));
    }
}
