package com.taylor;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ActivityC extends AppCompatActivity implements View.OnClickListener, Observer<Status> {
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setVisibility(View.GONE);
        findViewById(R.id.iv_arrow).setOnClickListener(this);
        /**
         * add observer for LiveData
         */
        StatusLiveData.getInstance().observe(this, this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_arrow:
                changeArrowStatus(++level);
                putStatus(level);
                break;
        }
    }

    /**
     * put status data into LiveData
     */
    private void putStatus(int level) {
        Status status = new Status();
        status.setLevel(level);
        StatusLiveData.getInstance().setValue(status);
    }

    private void changeArrowStatus(int level) {
        ImageView ivArrow = findViewById(R.id.iv_arrow);
        LevelListDrawable levelListDrawable = ((LevelListDrawable) ivArrow.getDrawable());
        levelListDrawable.setLevel(level % 2);
    }

    @Override
    public void onChanged(@Nullable Status status) {
        /**
         * get status data when it is changed and update UI
         */
        int level = status.getLevel();
        changeArrowStatus(level);
    }
}
