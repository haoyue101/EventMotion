package com.example.app.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * <p>created by wyh in 2021/12/10</p>
 */
public abstract class BaseConstraintLayout extends ConstraintLayout {

    protected Context mContext;
    protected LayoutInflater mLayoutInflater;

    public BaseConstraintLayout(@NonNull Context context) {
        this(context, null);
    }


    public BaseConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayoutInflater(context);
    }

    private void initLayoutInflater(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
}
