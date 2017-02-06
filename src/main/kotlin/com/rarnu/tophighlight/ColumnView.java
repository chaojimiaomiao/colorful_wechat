package com.rarnu.tophighlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by zhaibingjie on 17/1/30.
 */

public class ColumnView extends RelativeLayout {

    private int mResourceId;
    private String mTextString;

    public ColumnView(Context context, int resourceId, String textString) {
        super(context);

        this.mResourceId = resourceId;
        this.mTextString = textString;

        initView();
    }

    private void initView() {

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.column_item, null);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, UIUtils.INSTANCE.dip2px(50)));
        addView(linearLayout);
    }
}
