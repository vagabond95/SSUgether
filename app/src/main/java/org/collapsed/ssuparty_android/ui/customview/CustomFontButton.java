package org.collapsed.ssuparty_android.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import org.collapsed.ssuparty_android.R;

public class CustomFontButton extends AppCompatButton {
    public CustomFontButton(Context context) {
        super(context);
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setCustomFont(context, attrs);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String customFont = a.getString(R.styleable.CustomTextView_customFont);
        this.setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        super.setTypeface( FontCache.getFont(ctx, asset) );
        return true;
    }

    public void setFont(Context ctx, String font) {
        super.setTypeface( FontCache.getFont(ctx, font) );
    }
}
