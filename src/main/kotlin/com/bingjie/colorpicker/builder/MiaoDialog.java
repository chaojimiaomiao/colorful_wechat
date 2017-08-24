package com.bingjie.colorpicker.builder;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bingjie.colorpicker.ColorCircle;
import com.bingjie.colorpicker.ColorPickerView;
import com.bingjie.colorpicker.Utils;
import com.bingjie.colorpicker.renderer.ColorWheelRenderer;
import com.bingjie.colorpicker.slider.AlphaSlider;
import com.bingjie.colorpicker.slider.LightnessSlider;
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
    private float lightness = 1;
    private float alpha = 1;

    private boolean isLightnessSliderEnabled = true;
    private boolean isAlphaSliderEnabled = true;
    private boolean isColorEditEnabled = false;
    private boolean isPreviewEnabled = false;
    private int pickerCount = 1;
    private int defaultMargin = 0;
    private Integer[] initialColors = new Integer[]{Color.WHITE, null, null, null, null};
    private Integer initialColor;
    private int colorSelection = 0;
    private ColorCircle currentColorCircle;
    private ColorWheelRenderer renderer;

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

        lightnessSlider = new LightnessSlider(context);
        LinearLayout.LayoutParams layoutParamsForLightnessBar = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDimensionAsPx(context, R.dimen.default_slider_height));
        lightnessSlider.setLayoutParams(layoutParamsForLightnessBar);

        alphaSlider = new AlphaSlider(context);
        alphaSlider.setLayoutParams(layoutParamsForLightnessBar);

        pickerContainer =  (LinearLayout) findViewById(R.id.picker_dialog);
        pickerContainer.addView(colorPickerView, 0, layoutParamsForColorPickerView);
        pickerContainer.addView(lightnessSlider, 1);
        pickerContainer.addView(alphaSlider, 2);

        colorPickerView.setInitialColors(initialColors, getStartOffset(initialColors));

        colorPickerView.setLightnessSlider(lightnessSlider);
        lightnessSlider.setColor(getStartColor(initialColors));
        colorPickerView.setAlphaSlider(alphaSlider);
        alphaSlider.setColor(getStartColor(initialColors));

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

    private Integer getStartOffset(Integer[] colors) {
        Integer start = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == null) {
                return start;
            }
            start = (i + 1) / 2;
        }
        return start;
    }

    private static int getDimensionAsPx(Context context, int rid) {
        return (int) (context.getResources().getDimension(rid) + .5f);
    }

    private int getStartColor(Integer[] colors) {
        Integer startColor = getStartOffset(colors);
        return startColor == null ? Color.WHITE : colors[startColor];
    }

    public void setInitialColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        this.alpha = Utils.getAlphaPercent(color);
        this.lightness = hsv[2];
        this.initialColors[this.colorSelection] = color;
        this.initialColor = color;
        //setColorPreviewColor(color);
        setColorToSliders(color);
        currentColorCircle = findNearestByColor(color);
    }

    private void setColorToSliders(int selectedColor) {
        if (lightnessSlider != null)
            lightnessSlider.setColor(selectedColor);
        if (alphaSlider != null)
            alphaSlider.setColor(selectedColor);
    }

    private ColorCircle findNearestByColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        ColorCircle near = null;
        double minDiff = Double.MAX_VALUE;
        double x = hsv[1] * Math.cos(hsv[0] * Math.PI / 180);
        double y = hsv[1] * Math.sin(hsv[0] * Math.PI / 180);

        for (ColorCircle colorCircle : renderer.getColorCircleList()) {
            float[] hsv1 = colorCircle.getHsv();
            double x1 = hsv1[1] * Math.cos(hsv1[0] * Math.PI / 180);
            double y1 = hsv1[1] * Math.sin(hsv1[0] * Math.PI / 180);
            double dx = x - x1;
            double dy = y - y1;
            double dist = dx * dx + dy * dy;
            if (dist < minDiff) {
                minDiff = dist;
                near = colorCircle;
            }
        }
        return near;
    }
}
