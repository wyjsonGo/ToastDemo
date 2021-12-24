package com.wyjson.toastdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
            ToastW.makeText(this, "文本内容", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.tv_toast_success) {
            ToastW.makeSuccess(this, "Say hello success").show();
        } else if (v.getId() == R.id.tv_toast_error) {
            ToastW.makeError(this, "Failed to say hello").show();
        } else if (v.getId() == R.id.tv_toast_warning) {
            ToastW.makeWarning(this, "Please enter an account number").show();
        } else if (v.getId() == R.id.tv_toast_info) {
            ToastW.makeInfo(this, "Already used,come again tomorrow").show();
        }
    }
}