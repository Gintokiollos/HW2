package com.example.hw2;

import android.view.View;

public class ExternalListener implements View.OnClickListener {
    private MainActivity activity;
    public ExternalListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        activity.updateListenersRecord("外部類");
    }
}
