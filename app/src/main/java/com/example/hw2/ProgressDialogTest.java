package com.example.hw2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressDialogTest extends AppCompatActivity {
    private int[] data = new int[100];
    int hasData = 0;
    final int PROGRESS_DIALOG = 0x112;
    int progressStatus = 0;
    Dialog dialog;
    ProgressBar progressBar;
    TextView progressText;
    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pd);
        Button back2 = findViewById(R.id.back2);
        back2.setOnClickListener(view -> {
            Intent intent = new Intent(ProgressDialogTest.this, MainActivity.class);
            startActivity(intent);
        });

        Button execBn = findViewById(R.id.button9);
        execBn.setOnClickListener(view -> showDialog(PROGRESS_DIALOG));
    }

    @Override
    public Dialog onCreateDialog(int id, Bundle status) {
        System.out.println("------create------");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_progress_dialog, null);
        builder.setView(dialogView);
        builder.setCancelable(false);

        progressBar = dialogView.findViewById(R.id.progressBar);
        progressText = dialogView.findViewById(R.id.progressText);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        progressText.setText("任务完成百分比: 0%");

        dialog = builder.create();
        return dialog;
    }

    @Override
    public void onPrepareDialog(int id, Dialog dialog) {
        System.out.println("------prepare------");
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case PROGRESS_DIALOG:
                progressStatus = 0;
                new Thread(() -> {
                    while (progressStatus < 100) {
                        progressStatus = doWork();
                        final int currentProgress = progressStatus;
                        runOnUiThread(() -> {
                            progressBar.setProgress(currentProgress);
                            progressText.setText("任务完成百分比: " + currentProgress + "%");
                        });
                    }
                    runOnUiThread(() -> dialog.dismiss());
                }).start();
                break;
        }
    }

    public int doWork() {
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}
