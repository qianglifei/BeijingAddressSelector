package com.bksx.mobile.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment myDialogFragment = new MyDialogFragment(true,1);
                //使用dialogView弹出对话窗来选择地址
                myDialogFragment.show(getSupportFragmentManager(), "");

                myDialogFragment.setOnPernamentAddress(new MyDialogFragment.OnPermanentAddress() {
                    @Override
                    public void setPermanentAddress(String[] address, String codeId) {
                        if (address[2].equals("请选择")) {
                            //textView_jydwszd.setText(address[0] + address[1]);
                        } else {
                            //textView_jydwszd.setText(address[0] + address[1] + address[2]);
                        }
                    }
                });
            }
        });
    }
}
