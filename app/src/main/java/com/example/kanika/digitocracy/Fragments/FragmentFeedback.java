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

<<<<<<< HEAD:app/src/main/java/com/example/kanika/digitocracy/Fragments/FragmentFeedback.java
import com.example.kanika.digitocracy.R;
=======
import retrofit2.http.HEAD;
>>>>>>> a009ddb44fa023ec20d5d1333f13d1427e403e34:app/src/main/java/com/example/kanika/digitocracy/FragmentFeedback.java

public class FragmentFeedback extends Fragment {


    EditText feedback_subject,feedback_phone,feedback_message;
    Button feedback_submit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feedback,container,false);


        feedback_subject=view.findViewById(R.id.feedback_subject);
        feedback_message=view.findViewById(R.id.feedback_meassage);
        feedback_phone=view.findViewById(R.id.feedback_phone);
        feedback_submit=view.findViewById(R.id.feedback_submit);


<<<<<<< HEAD:app/src/main/java/com/example/kanika/digitocracy/Fragments/FragmentFeedback.java

=======
>>>>>>> a009ddb44fa023ec20d5d1333f13d1427e403e34:app/src/main/java/com/example/kanika/digitocracy/FragmentFeedback.java
        return view;
    }
}
