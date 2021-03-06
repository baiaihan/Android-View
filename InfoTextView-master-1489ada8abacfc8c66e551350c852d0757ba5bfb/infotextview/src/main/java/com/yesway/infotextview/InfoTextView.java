
package com.yesway.infotextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * InfoTextView展示信息
 */
public class InfoTextView extends FrameLayout {

    private TextView mTvKey;
    private TextView mTvValue;
    private String mKeyStr;
    private String mValue;
    /**
     * 右侧图
     */
    private Drawable mRightImg;
    /**
     * 左侧图
     */
    private Drawable mLeftImg;
    /**
     * value字体颜色
     */
    private int mValueColor;
    /**
     * key字体颜色
     */
    private int mKeyColor;
    /**
     * 右侧指示图标状态
     */
    private int rightImgVisibility;
    /**
     * 左侧指示图标状态
     */
    private int leftImgVisibility;
    /**
     * 分割线
     */
    private View bottomView;
    /**
     * 分割线颜色
     */
    private int mBottomColor;
    /**
     * 右侧指示图标
     */
    private ImageView mIvGo;

    /**
     * 左侧icon图标
     */
    private ImageView mIvIcon;

    public InfoTextView(Context context) {
        this(context, null);
    }

    public InfoTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InfoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.info_textview, this);

        mIvIcon = (ImageView) findViewById(R.id.iv_icon);
        mIvGo = (ImageView) findViewById(R.id.iv_go);
        mTvKey = (TextView) findViewById(R.id.tv_key);
        mTvValue = (TextView) findViewById(R.id.tv_value);
        bottomView = findViewById(R.id.bottom_view);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InfoTextView, defStyleAttr, 0);

        mKeyStr = a.getString(R.styleable.InfoTextView_infoKey);
        mKeyColor = a.getColor(R.styleable.InfoTextView_infoKeyColor,
                context.getResources().getColor(R.color.white));
        mValue = a.getString(R.styleable.InfoTextView_infoValue);
        mValueColor = a.getColor(R.styleable.InfoTextView_infoValueColor,
                context.getResources().getColor(R.color.white));
        mBottomColor = a.getColor(R.styleable.InfoTextView_infoBottomColor,
                context.getResources().getColor(R.color.title_bg));
        rightImgVisibility = a.getInt(R.styleable.InfoTextView_rightImgVisibility, 0);
        leftImgVisibility = a.getInt(R.styleable.InfoTextView_leftImgVisibility, 0);

        mRightImg = a.getDrawable(R.styleable.InfoTextView_rightImgSrc);
        mLeftImg =a.getDrawable(R.styleable.InfoTextView_leftImgSrc);

        mIvIcon.setImageDrawable(mLeftImg);
        mIvGo.setImageDrawable(mRightImg!=null?mRightImg:context.getResources().getDrawable(R.drawable.go_forward));

        mIvIcon.setVisibility(leftImgVisibility);
        mIvGo.setVisibility(rightImgVisibility);
        mTvValue.setVisibility(View.VISIBLE);

        a.recycle();
        init();
    }

    private void init() {
        mTvValue.setTextColor(mValueColor);
        mTvKey.setTextColor(mKeyColor);
        bottomView.setBackgroundColor(mBottomColor);
        mTvKey.setText(mKeyStr);
        if (!TextUtils.isEmpty(mValue)) {
            mTvValue.setText(mValue);
        }
    }

    public void setBottomViewVisible(int visible) {
        bottomView.setVisibility(visible);
    }

    /**
     * 设置值
     *
     * @param value
     */
    public void setValue(String value) {
        mTvValue.setText(value);
    }

    public String getValue() {
        return mTvValue.getText().toString().trim();
    }


}
