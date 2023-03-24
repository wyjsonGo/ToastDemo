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
        makeText(MyApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
    }

    public static void makeText(@StringRes int resId) {
        makeText(MyApplication.getInstance(), resId, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ShowToast")
    private static Toast makeText(Context context, CharSequence text, int duration) {
        if (mToast != null)
            mToast.cancel();
        try {
            mToast = Toast.makeText(context, null, duration);
            mToast.setText(text);
        } catch (NullPointerException e) {
            e.printStackTrace();
            mToast = Toast.makeText(context, text, duration);
        }
        return mToast;
    }

    @SuppressLint("ShowToast")
    private static Toast makeText(Context context, @StringRes int resId, int duration) {
        if (mToast != null)
            mToast.cancel();
        try {
            mToast = Toast.makeText(context, null, duration);
            mToast.setText(context.getResources().getText(resId));
        } catch (NullPointerException e) {
            e.printStackTrace();
            mToast = Toast.makeText(context, context.getResources().getText(resId), duration);
        }
        return mToast;
    }

    public static void makeSuccess(@StringRes int resId) {
        makeSuccess(MyApplication.getInstance().getString(resId));
    }

    public static void makeWarning(@StringRes int resId) {
        makeWarning(MyApplication.getInstance().getString(resId));
    }

    public static void makeError(@StringRes int resId) {
        makeError(MyApplication.getInstance().getString(resId));
    }

    public static void makeInfo(@StringRes int resId) {
        makeInfo(MyApplication.getInstance().getString(resId));
    }

    public static void makeSuccess(CharSequence text) {
        ToastW.makeCustomSmall(
                MyApplication.getInstance(),
                text,
                Background.SUCCESS,
                Icon.SUCCESS,
                text.toString().trim().length() <= 14 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG
        ).show();
    }

    public static void makeWarning(CharSequence text) {
        ToastW.makeCustomSmall(
                MyApplication.getInstance(),
                text,
                Background.WARNING,
                Icon.WARNING,
                text.toString().trim().length() <= 14 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG
        ).show();
    }

    public static void makeError(CharSequence text) {
        ToastW.makeCustomSmall(
                MyApplication.getInstance(),
                text,
                Background.ERROR,
                Icon.ERROR,
                text.toString().trim().length() <= 14 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG
        ).show();
    }

    public static void makeInfo(CharSequence text) {
        ToastW.makeCustomSmall(
                MyApplication.getInstance(),
                text,
                Background.INFO,
                Icon.INFO,
                text.toString().trim().length() <= 14 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG
        ).show();
    }

    private static Toast makeCustomSmall(Context context, CharSequence text, int background, int iconResource, int duration) {
        int xOffset = context.getResources().getDimensionPixelSize(R.dimen.toast_x_offset);
        int yOffset = context.getResources().getDimensionPixelSize(R.dimen.toast_y_offset);
        return ToastW.makeCustom(context, text, background, IconPosition.START, iconResource, Gravity.TOP | Gravity.START | Gravity.END, xOffset, yOffset, duration);
    }

    private static Toast makeCustom(Context context, CharSequence text, int background, IconPosition iconPosition, int iconResource, int gravity, int xOffset, int yOffset, int duration) {
        if (mToast != null)
            mToast.cancel();

        View toastView = LayoutInflater.from(context).inflate(R.layout.toast, null);
        LinearLayout llRootLayout = toastView.findViewById(R.id.toast_root_layout);
        TextView tvMsg = toastView.findViewById(R.id.toast_tv_message);

        tvMsg.setText(text);
        llRootLayout.setBackgroundResource(background);

        mToast = new Toast(MyApplication.getInstance());
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
        static final int NONE = 0;
        static final int SUCCESS = R.mipmap.toast_mark_success;
        static final int ERROR = R.mipmap.toast_mark_no;
        static final int WARNING = R.mipmap.toast_mark_warning;
        static final int INFO = R.mipmap.toast_mark_info;
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
