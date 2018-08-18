package com.example.kanika.digitocracy.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kanika.digitocracy.R;

public class FragmentEditprofile extends Fragment {

    ImageView img;
    EditText editprofile_name, editprofile_gender;
    Button notification,editprofile_chang_pw,editprofile_update;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragmenteditprofile,container,false);

        img=view.findViewById(R.id.profile_image);
        editprofile_name=view.findViewById(R.id.editprofile_name);
        editprofile_gender=view.findViewById(R.id.editprofile_gender);
        notification=view.findViewById(R.id.editprofile_notification);
        editprofile_chang_pw=view.findViewById(R.id.editprofile_change_pw);
        editprofile_update=view.findViewById(R.id.editprofile_upadte);


        return view;
    }
}