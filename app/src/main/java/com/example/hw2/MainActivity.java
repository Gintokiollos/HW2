package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    private TextView textView2;
    private String listenersRecord = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        textView2 = findViewById(R.id.textView2);
        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        button7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,ConfigurationTest.class);
                startActivity(intent);
            }
        });
        button8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent(MainActivity.this,ProgressDialogTest.class);
                startActivity(intent2);
            }
        });
        // 将活动自身作为监听器
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListenersRecord("Activity");
            }
        });

        // 使用内部类作为监听器
        button2.setOnClickListener(new InternalListener());

        // 使用匿名类作为监听器
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListenersRecord("匿名类");
            }
        });

        // 使用Lambda表达式作为监听器
        button4.setOnClickListener(v -> updateListenersRecord("Lambda表達式"));

        // 使用外部类作为监听器
        button5.setOnClickListener(new ExternalListener(this));

        // 按钮6：显示记录
        button6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(listenersRecord);
            }
        });
    }

    // 更新监听器记录的方法
    void updateListenersRecord(String className) {
        listenersRecord = className;
    }

    // 内部类作为监听器
    class InternalListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            updateListenersRecord("內部類");
        }


    }

}

