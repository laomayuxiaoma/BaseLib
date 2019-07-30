package com.mhd.baseui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;


/**
 * @author zhangming
 * @Date 2019/07/29
 * @Description: 自定义TextView可设置部分字体大小，颜色，下划线，删除线,下标等
 */
public class MTextView extends android.support.v7.widget.AppCompatTextView {

    private SpannableString strSpan;

    public MTextView(Context context) {
        super(context);
        init(context);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //去掉周围的边距
        //        this.setIncludeFontPadding(false);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        strSpan = new SpannableString(text);
    }

    //设置前景色，对文字设置颜色
    public MTextView setForegroundColor(int start, int end, int color) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        setSubSpan(foregroundColorSpan, start, end);
        return this;
    }

    //设置背景色，对文字背景设置颜色
    public MTextView setBackgroundColor(int start, int end, int color) {
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(color);
        setSubSpan(backgroundColorSpan, start, end);
        return this;
    }

    //设置字体的相对大小
    public MTextView setRelativeSize(int start, int end, float proportion) {
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(proportion);
        setSubSpan(relativeSizeSpan, start, end);
        return this;
    }

    //设置字体的相对大小(size单位为dp)
    public MTextView setAbsoluteSize(int start, int end, int size) {
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(size, true);
        setSubSpan(absoluteSizeSpan, start, end);
        return this;
    }

    //设置字体样式如斜体，粗体(Typeface.BOLD)
    public MTextView setStyle(int start, int end, int style) {
        StyleSpan styleSpan = new StyleSpan(style);
        setSubSpan(styleSpan, start, end);
        return this;
    }

    //设置字体类型（如"monospace","serif","sans-serif"）
    public MTextView setTypeface(int start, int end, String family) {
        TypefaceSpan typefaceSpan = new TypefaceSpan(family);
        setSubSpan(typefaceSpan, start, end);
        return this;
    }

    //设置字体类型（如"monospace","serif","sans-serif"）
    @RequiresApi(api = Build.VERSION_CODES.P)
    public MTextView setTypeface(int start, int end, Typeface typeface) {
        TypefaceSpan typefaceSpan = new TypefaceSpan(typeface);
        setSubSpan(typefaceSpan, start, end);
        return this;
    }

    //设置字体下划线
    public MTextView setUnderline(int start, int end) {
        UnderlineSpan underlineSpan = new UnderlineSpan();
        setSubSpan(underlineSpan, start, end);
        return this;
    }

    //设置字体删除线
    public MTextView setStrikethrough(int start, int end) {
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        setSubSpan(strikethroughSpan, start, end);
        return this;
    }

    //设置文字为上标(proportion:字体相对大小)
    public MTextView setSuperscript(int start, int end, float proportion) {
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        setRelativeSize(start, end, proportion);
        setSubSpan(superscriptSpan, start, end);
        return this;
    }

    //设置文字为上标(size:字体绝对大小单位dp)
    public MTextView setSuperscript(int start, int end, int size) {
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        setAbsoluteSize(start, end, size);
        setSubSpan(superscriptSpan, start, end);
        return this;
    }

    //设置文字为下标(proportion:字体相对大小)
    public MTextView setSubscript(int start, int end, float proportion) {
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        setRelativeSize(start, end, proportion);
        setSubSpan(subscriptSpan, start, end);
        return this;
    }

    //设置文字为上标(size:字体绝对大小单位dp)
    public MTextView setSubscript(int start, int end, int size) {
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        setAbsoluteSize(start, end, size);
        setSubSpan(subscriptSpan, start, end);
        return this;
    }

    //设置图片
    public MTextView setReplaceImage(int start, int end, int resourceId) {
        ImageSpan imageSpan = new ImageSpan(getContext(), resourceId);
        setSubSpan(imageSpan, start, end);
        return this;
    }

    //设置图片
    public MTextView setReplaceImage(int start, int end, Bitmap bitmap) {
        ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
        setSubSpan(imageSpan, start, end);
        return this;
    }

    private void setSubSpan(CharacterStyle subSpan, int start, int end) {
        strSpan.setSpan(subSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        setText(strSpan);
    }
}
