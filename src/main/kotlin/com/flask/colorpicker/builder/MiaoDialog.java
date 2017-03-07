package com.flask.colorpicker.builder;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.slider.AlphaSlider;
import com.flask.colorpicker.slider.LightnessSlider;
import com.rarnu.tophighlight.R;

/**
 * Created by zhaibingjie on 17/3/7.
 */

public class MiaoDialog extends Dialog implements View.OnClickListener {
    private LinearLayout pickerContainer;
    private ColorPickerView colorPickerView;
    private LightnessSlider lightnessSlider;
    private AlphaSlider alphaSlider;
    private EditText colorEdit;
    private LinearLayout colorPreview;
    private TextView sureText, cancelText, titleText;

    private boolean isLightnessSliderEnabled = true;
    private boolean isAlphaSliderEnabled = true;
    private boolean isColorEditEnabled = false;
    private boolean isPreviewEnabled = false;
    private int pickerCount = 1;
    private int defaultMargin = 0;
    private Integer[] initialColor = new Integer[]{null, null, null, null, null};

    private MiaoDialog(Context context) {
        this(context, R.style.miaoDialog);
    }

    private MiaoDialog(Context context, int themeResId) {
        this(context, themeResId, false);
    }

    public MiaoDialog(Context context, int themeResId, boolean isLongClick) {
        super(context, themeResId);

        setContentView(R.layout.picker_container);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);

        LinearLayout.LayoutParams layoutParamsForColorPickerView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParamsForColorPickerView.weight = 1;
        colorPickerView = new ColorPickerView(context);

        pickerContainer =  (LinearLayout) findViewById(R.id.picker_dialog);
        pickerContainer.addView(colorPickerView, 0, layoutParamsForColorPickerView);

        titleText = (TextView) findViewById(R.id.id_title);
        titleText.setText(isLongClick ? "点击时的背景色":"栏目背景色");
        sureText = (TextView) findViewById(R.id.id_sure);
        cancelText = (TextView) findViewById(R.id.id_cancel);

        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setCanceledOnTouchOutside(false);
    }

    public MiaoDialog setPositiveButton(final ColorPickerClickListener onClickListener) {
        //positiveButtonOnClick(dialog, onClickListener);
        sureText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveButtonOnClick(onClickListener);
                dismiss();
            }
        });
        return this;
    }

    @Override
    public void onClick(View v) {

    }

    private void positiveButtonOnClick(ColorPickerClickListener onClickListener) {
        int selectedColor = colorPickerView.getSelectedColor();
        Integer[] allColors = colorPickerView.getAllColors();
        onClickListener.onClick(selectedColor, allColors);
    }
}
