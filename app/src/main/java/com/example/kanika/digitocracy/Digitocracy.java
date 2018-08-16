package com.example.kanika.digitocracy;

import android.app.Application;

import com.example.kanika.digitocracy.extra.TypefaceUtil;

public class Digitocracy extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/brandonreg.otf");

    }
}
