package com.wyjson.toastdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wyjson.toastdemo.widget.toast.ToastW;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_toast_text) {
            ToastW.makeText("文本内容");
        } else if (v.getId() == R.id.tv_toast_success) {
            ToastW.makeSuccess("Say hello success");
        } else if (v.getId() == R.id.tv_toast_error) {
            ToastW.makeError("Failed to say hello");
        } else if (v.getId() == R.id.tv_toast_warning) {
            ToastW.makeWarning("Please enter an account number");
        } else if (v.getId() == R.id.tv_toast_info) {
            ToastW.makeInfo("Already used,come again tomorrow");
        }
    }
}