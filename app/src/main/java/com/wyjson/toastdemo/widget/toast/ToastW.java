package com.wyjson.toastdemo.widget.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

import com.wyjson.toastdemo.MyApplication;
import com.wyjson.toastdemo.R;


public class ToastW {

    private static Toast mToast;

    public static void makeText(CharSequence text) {
        makeText(MyApplication.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void makeText(@StringRes int resId) {
        makeText(MyApplication.getInstance().getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ShowToast")
    public static Toast makeText(Context context, CharSequence text, int duration) {
        if (mToast != null)
            mToast.cancel();
        try {
            mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), null, duration);
            mToast.setText(text);
        } catch (NullPointerException e) {
            e.printStackTrace();
            mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), text, duration);
        }
        return mToast;
    }

    @SuppressLint("ShowToast")
    public static Toast makeText(Context context, @StringRes int resId, int duration) {
        if (mToast != null)
            mToast.cancel();
        try {
            mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), null, duration);
            mToast.setText(MyApplication.getInstance().getApplicationContext().getResources().getText(resId));
        } catch (NullPointerException e) {
            e.printStackTrace();
            mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getText(resId), duration);
        }
        return mToast;
    }

    public static void makeSuccess(@StringRes int resId) {
        makeSuccess(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getText(resId)).show();
    }

    public static void makeWarning(@StringRes int resId) {
        makeWarning(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getText(resId)).show();
    }

    public static void makeError(@StringRes int resId) {
        makeError(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getText(resId)).show();
    }

    public static void makeInfo(@StringRes int resId) {
        makeInfo(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getText(resId)).show();
    }

    public static void makeSuccess(CharSequence textCharSequence) {
        makeSuccess(MyApplication.getInstance().getApplicationContext(), textCharSequence).show();
    }

    public static void makeWarning(CharSequence textCharSequence) {
        makeWarning(MyApplication.getInstance().getApplicationContext(), textCharSequence).show();
    }

    public static void makeError(CharSequence textCharSequence) {
        makeError(MyApplication.getInstance().getApplicationContext(), textCharSequence).show();
    }

    public static void makeInfo(CharSequence textCharSequence) {
        makeInfo(MyApplication.getInstance().getApplicationContext(), textCharSequence).show();
    }

    public static Toast makeSuccess(Context context, CharSequence textCharSequence) {
        return ToastW.makeCustomSmall(context, textCharSequence, Background.SUCCESS, Icon.SUCCESS, textCharSequence.toString().trim().length() <= 14 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }

    public static Toast makeWarning(Context context, CharSequence textCharSequence) {
        return ToastW.makeCustomSmall(context, textCharSequence, Background.WARNING, Icon.WARNING, textCharSequence.toString().trim().length() <= 14 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }

    public static Toast makeError(Context context, CharSequence textCharSequence) {
        return ToastW.makeCustomSmall(context, textCharSequence, Background.ERROR, Icon.ERROR, textCharSequence.toString().trim().length() <= 14 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }

    public static Toast makeInfo(Context context, CharSequence textCharSequence) {
        return ToastW.makeCustomSmall(context, textCharSequence, Background.INFO, Icon.INFO, textCharSequence.toString().trim().length() <= 14 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }

    private static Toast makeCustomSmall(Context context, CharSequence text, int background, int iconResource, int duration) {
        int xOffset = context.getResources().getDimensionPixelSize(R.dimen.toast_x_offset);
        int yOffset = context.getResources().getDimensionPixelSize(R.dimen.toast_y_offset);
        return ToastW.makeCustom(context, text, background, IconPosition.START, iconResource, Gravity.TOP | Gravity.LEFT | Gravity.RIGHT, xOffset, yOffset, duration);
    }

    public static Toast makeCustom(Context context, CharSequence text, int background, IconPosition iconPosition, int iconResource, int gravity, int xOffset, int yOffset, int duration) {
        if (mToast != null)
            mToast.cancel();

        View toastView = LayoutInflater.from(context).inflate(R.layout.toast, null);
        LinearLayout llRootLayout = toastView.findViewById(R.id.toast_root_layout);
        TextView tvMsg = toastView.findViewById(R.id.toast_tv_message);

        tvMsg.setText(text);
        llRootLayout.setBackgroundResource(background);

        mToast = new Toast(MyApplication.getInstance().getApplicationContext());
        mToast.setDuration(duration);
        mToast.setGravity(gravity, xOffset, yOffset);
        setIcon(context, tvMsg, iconResource, iconPosition);
        mToast.setView(toastView);
        return mToast;
    }

    private static class Background {
        static final int INFO = R.drawable.toast_bg_info;
        static final int SUCCESS = R.drawable.toast_bg_success;
        static final int ERROR = R.drawable.toast_bg_error;
        static final int WARNING = R.drawable.toast_bg_warning;
    }

    public enum IconPosition {
        START,
        END,
        TOP,
        BOTTOM
    }

    private static class Icon {
        static final int SUCCESS = (R.mipmap.toast_mark_success);
        static final int ERROR = (R.mipmap.toast_mark_no);
        static final int WARNING = (R.mipmap.toast_mark_warning);
        static final int INFO = (R.mipmap.toast_mark_info);
    }

    private static void setIcon(Context context, TextView tvMsg, int iconResource, IconPosition iconPosition) {
        if (iconResource == 0)
            return;
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), iconResource, null);
        if (iconPosition == IconPosition.BOTTOM) {
            tvMsg.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, drawable);
        } else if (iconPosition == IconPosition.START) {
            tvMsg.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
        } else if (iconPosition == IconPosition.END) {
            tvMsg.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null);
        } else if (iconPosition == IconPosition.TOP) {
            tvMsg.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
        }
    }


}
