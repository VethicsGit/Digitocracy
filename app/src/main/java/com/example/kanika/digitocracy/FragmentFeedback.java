package com.example.kanika.digitocracy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentFeedback extends Fragment {


    EditText feedback_subject,feedback_phone,feedback_message;
    Button feedback_submit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feedback,container,false);
<<<<<<< HEAD

        feedback_subject=view.findViewById(R.id.feedback_subject);
        feedback_message=view.findViewById(R.id.feedback_meassage);
        feedback_phone=view.findViewById(R.id.feedback_phone);
        feedback_submit=view.findViewById(R.id.feedback_submit);

=======
>>>>>>> 9f6869e3e176717d6f334de9382f8199ceb8189b
        return view;
    }
}
